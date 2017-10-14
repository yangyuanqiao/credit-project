/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.creditact.web.appraise;

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
import com.amass.credit.common.web.BaseController;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.common.utils.excel.ExportExcel;
import com.amass.credit.common.utils.excel.ImportExcel;
import com.amass.credit.modules.creditact.entity.appraise.RecordsAppraise;
import com.amass.credit.modules.creditact.entity.punishment.RecordsPunishment;
import com.amass.credit.modules.creditact.service.appraise.RecordsAppraiseService;
import com.amass.credit.modules.enterprise.entity.manage.EnterpriseManage;
import com.google.common.collect.Lists;

/**
 * 评价行为信息Controller
 * @author yangyq
 * @version 2017-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/creditact/appraise/recordsAppraise")
public class RecordsAppraiseController extends BaseController {

	@Autowired
	private RecordsAppraiseService recordsAppraiseService;
	
	@ModelAttribute
	public RecordsAppraise get(@RequestParam(required=false) String id) {
		RecordsAppraise entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = recordsAppraiseService.get(id);
		}
		if (entity == null){
			entity = new RecordsAppraise();
		}
		return entity;
	}
	
	@RequiresPermissions("creditact:appraise:recordsAppraise:view")
	@RequestMapping(value = {"list", ""})
	public String list(RecordsAppraise recordsAppraise, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RecordsAppraise> page = recordsAppraiseService.findPage(new Page<RecordsAppraise>(request, response), recordsAppraise); 
		model.addAttribute("page", page);
		return "modules/creditact/appraise/recordsAppraiseList";
	}

	@RequiresPermissions("creditact:appraise:recordsAppraise:view")
	@RequestMapping(value = "subjectAppraiseList")
	public String getAppraiseBySubId(RecordsAppraise recordsAppraise, HttpServletRequest request, HttpServletResponse response, Model model) {
		String subId= request.getParameter("subId");
		recordsAppraise.setSubId(Long.parseLong(subId));
		String subType= request.getParameter("subType");
		model.addAttribute("subType", subType);
		Page<RecordsAppraise> page = recordsAppraiseService.findPage(new Page<RecordsAppraise>(request, response), recordsAppraise); 
		model.addAttribute("page", page);
		model.addAttribute("subId", subId);
		return "modules/creditact/appraise/subjectAppraiseList";
	}

	
	@RequiresPermissions("creditact:appraise:recordsAppraise:view")
	@RequestMapping(value = "form")
	public String form(RecordsAppraise recordsAppraise, Model model) {
		model.addAttribute("recordsAppraise", recordsAppraise);
		return "modules/creditact/appraise/recordsAppraiseForm";
	}

	@RequiresPermissions("creditact:appraise:recordsAppraise:edit")
	@RequestMapping(value = "save")
	public String save(RecordsAppraise recordsAppraise, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, recordsAppraise)){
			return form(recordsAppraise, model);
		}
		recordsAppraiseService.save(recordsAppraise);
		addMessage(redirectAttributes, "保存评价行为信息成功");
		return "redirect:"+Global.getAdminPath()+"/creditact/appraise/recordsAppraise/?repage";
	}
	
	@RequiresPermissions("creditact:appraise:recordsAppraise:edit")
	@RequestMapping(value = "delete")
	public String delete(RecordsAppraise recordsAppraise, RedirectAttributes redirectAttributes) {
		recordsAppraiseService.delete(recordsAppraise);
		addMessage(redirectAttributes, "删除评价行为信息成功");
		return "redirect:"+Global.getAdminPath()+"/creditact/appraise/recordsAppraise/?repage";
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
            String fileName = "评价行为信息.xlsx";
    		List<RecordsAppraise> list = Lists.newArrayList();
    		new ExportExcel("评价行为信息", RecordsAppraise.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
	} catch (Exception e) {
			logger.debug(e.getMessage());
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/creditact/appraise/recordsAppraise/?repage";
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
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			//获取文件数据
			List<RecordsAppraise> fileData = ei.getDataList(RecordsAppraise.class);
			//字典列表
			//List<Dict> subjectTypeList = DictUtils.getDictList("SUBJECT_TYPE2");
			
			//项目列表
			//Map<String,String> subjectMap = new HashMap<String,String>();
			//List<RecordsEvent> recordEventList = recordsEventService.findList(new RecordsEvent());
			
			for (RecordsAppraise target : fileData)
			{
				try
				{
					 if(StringUtils.isBlank(target.getSubName()))
					{
						failureMsg.append("<br/>主体名称不能为空");
						failureNum++;
						continue;
						
					}
					if(StringUtils.isBlank(target.getActType()))
					{
							failureMsg.append("<br/>评价类别不能为空");
							failureNum++;
							continue;
					}
					
					recordsAppraiseService.save(target);
					successNum++;
						
				}
				catch (Exception ex) 
				{
					ex.printStackTrace();
					failureMsg.append("<br/>导入失败：");
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息，"+failureMsg);
		} 
		catch (Exception e) 
		{
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/creditact/appraise/recordsAppraise/?repage";
    }

}