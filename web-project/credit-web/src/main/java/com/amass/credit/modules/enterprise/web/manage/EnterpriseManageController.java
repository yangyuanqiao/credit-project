/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.web.manage;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amass.credit.common.config.Global;
import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.common.utils.excel.ExportExcel;
import com.amass.credit.common.utils.excel.ImportExcel;
import com.amass.credit.common.web.BaseController;
import com.amass.credit.modules.enterprise.entity.baseinfo.EnterpriseBase;
import com.amass.credit.modules.enterprise.entity.manage.EnterpriseManage;
import com.amass.credit.modules.enterprise.service.baseinfo.EnterpriseBaseService;
import com.amass.credit.modules.enterprise.service.manage.EnterpriseManageService;
import com.google.common.collect.Lists;

/**
 * 企业经营管理信息Controller
 * @author yangyq
 * @version 2017-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/enterprise/manage/enterpriseManage")
public class EnterpriseManageController extends BaseController {

	@Autowired
	private EnterpriseManageService enterpriseManageService;
	
	@Autowired
	private EnterpriseBaseService enterpriseBaseService;
	
	@ModelAttribute
	public EnterpriseManage get(@RequestParam(required=false) String id) {
		EnterpriseManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = enterpriseManageService.get(id);
		}
		if (entity == null){
			entity = new EnterpriseManage();
		}
		return entity;
	}
	
	@RequiresPermissions("enterprise:manage:enterpriseManage:view")
	@RequestMapping(value = {"list", ""})
	public String list(EnterpriseManage enterpriseManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EnterpriseManage> page = enterpriseManageService.findPage(new Page<EnterpriseManage>(request, response), enterpriseManage); 
		model.addAttribute("page", page);
		return "modules/enterprise/manage/enterpriseManageList";
	}

	@RequiresPermissions("enterprise:manage:enterpriseManage:view")
	@RequestMapping(value = "form")
	public String form(EnterpriseManage enterpriseManage, Model model) {
		model.addAttribute("enterpriseManage", enterpriseManage);
		return "modules/enterprise/manage/enterpriseManageForm";
	}

	@RequiresPermissions("enterprise:manage:enterpriseManage:edit")
	@RequestMapping(value = "save")
	public String save(EnterpriseManage enterpriseManage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, enterpriseManage)){
			return form(enterpriseManage, model);
		}
		enterpriseManageService.save(enterpriseManage);
		addMessage(redirectAttributes, "保存企业经营管理信息成功");
		return "redirect:"+Global.getAdminPath()+"/enterprise/manage/enterpriseManage/?repage";
	}
	
	@RequiresPermissions("enterprise:manage:enterpriseManage:edit")
	@RequestMapping(value = "delete")
	public String delete(EnterpriseManage enterpriseManage, RedirectAttributes redirectAttributes) {
		enterpriseManageService.delete(enterpriseManage);
		addMessage(redirectAttributes, "删除企业经营管理信息成功");
		return "redirect:"+Global.getAdminPath()+"/enterprise/manage/enterpriseManage/?repage";
	}
	
	/**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "经营状况信息.xlsx";
    		List<EnterpriseManage> list = Lists.newArrayList();
    		new ExportExcel("经营状况信息", EnterpriseManage.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
	} catch (Exception e) {
			logger.debug(e.getMessage());
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/enterprise/manage/enterpriseManage/?repage";
    }
    
    /**
	 * 导入数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try 
		{
			//统计成功与失败条数变量
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			//获取文件数据
			List<EnterpriseManage> fileData = ei.getDataList(EnterpriseManage.class);
			
			//判断文件内容数据是否符合规则
			for (EnterpriseManage target : fileData)
			{
				try
				{
					 String enterpriseId ="";
					 if(StringUtils.isBlank(target.getSubName()))
					{
							failureMsg.append("<br/>企业名称不能为空");
							failureNum++;
							continue;
					}
					 if(StringUtils.isBlank(target.getCreditNo()) && StringUtils.isBlank(target.getRegistryNo()))
					{
						failureMsg.append(target.getSubName()+"<br/>企业注册号和企业统一信用代码不能同时为空");
						failureNum++;
						continue;
					}else{
						EnterpriseBase enterpriseBase = new EnterpriseBase();
						if(!StringUtils.isBlank(target.getCreditNo()))
							enterpriseBase.setCreditNo(target.getCreditNo());
						else if(!StringUtils.isBlank(target.getRegistryNo()))
							enterpriseBase.setOrgRegistry(target.getRegistryNo());
						List <EnterpriseBase> enterpriseBaseList = enterpriseBaseService.findList(enterpriseBase);
						if(null !=enterpriseBaseList && !enterpriseBaseList.isEmpty()){
							enterpriseId = enterpriseBaseList.get(0).getId();
						}
					}
					
						//插入内容
						target.setSubId(Long.parseLong(enterpriseId));
						enterpriseManageService.save(target);
						successNum++;
					
				}
				catch (Exception ex) 
				{
					ex.printStackTrace();
					failureMsg.append(target.getSubName()+"<br/>导入失败：");
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息。"+failureMsg);
		} 
		catch (Exception e) 
		{
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/enterprise/manage/enterpriseManage/?repage";
    }

}