/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.creditact.web.punishment;

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
import com.amass.credit.modules.creditact.entity.event.RecordsEvent;
import com.amass.credit.modules.creditact.entity.punishment.RecordsPunishment;
import com.amass.credit.modules.creditact.service.punishment.RecordsPunishmentService;
import com.amass.credit.modules.enterprise.entity.manage.EnterpriseManage;
import com.google.common.collect.Lists;

/**
 * 处罚行为信息Controller
 * @author yangyq
 * @version 2017-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/creditact/punishment/recordsPunishment")
public class RecordsPunishmentController extends BaseController {

	@Autowired
	private RecordsPunishmentService recordsPunishmentService;
	
	@ModelAttribute
	public RecordsPunishment get(@RequestParam(required=false) String id) {
		RecordsPunishment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = recordsPunishmentService.get(id);
		}
		if (entity == null){
			entity = new RecordsPunishment();
		}
		return entity;
	}
	
	@RequiresPermissions("creditact:punishment:recordsPunishment:view")
	@RequestMapping(value = {"list", ""})
	public String list(RecordsPunishment recordsPunishment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RecordsPunishment> page = recordsPunishmentService.findPage(new Page<RecordsPunishment>(request, response), recordsPunishment); 
		model.addAttribute("page", page);
		return "modules/creditact/punishment/recordsPunishmentList";
	}
	
	@RequiresPermissions("creditact:punishment:recordsPunishment:view")
	@RequestMapping(value = "subjectPunishList")
	public String getPunishmentListBySubId(RecordsPunishment recordsPunishment, HttpServletRequest request, HttpServletResponse response, Model model) {
		String subId= request.getParameter("subId");
		recordsPunishment.setSubId(Long.parseLong(subId));
		String subType= request.getParameter("subType");
		model.addAttribute("subType", subType);
		Page<RecordsPunishment> page = recordsPunishmentService.findPage(new Page<RecordsPunishment>(request, response), recordsPunishment); 
		model.addAttribute("page", page);
		model.addAttribute("subId", subId);
		return "modules/creditact/punishment/subjectPunishList";
	}
	@RequiresPermissions("creditact:punishment:recordsPunishment:view")
	@RequestMapping(value = "form")
	public String form(RecordsPunishment recordsPunishment, Model model) {
		model.addAttribute("recordsPunishment", recordsPunishment);
		return "modules/creditact/punishment/recordsPunishmentForm";
	}

	@RequiresPermissions("creditact:punishment:recordsPunishment:edit")
	@RequestMapping(value = "save")
	public String save(RecordsPunishment recordsPunishment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, recordsPunishment)){
			return form(recordsPunishment, model);
		}
		recordsPunishmentService.save(recordsPunishment);
		addMessage(redirectAttributes, "保存处罚行为信息成功");
		return "redirect:"+Global.getAdminPath()+"/creditact/punishment/recordsPunishment/?repage";
	}
	
	@RequiresPermissions("creditact:punishment:recordsPunishment:edit")
	@RequestMapping(value = "delete")
	public String delete(RecordsPunishment recordsPunishment, RedirectAttributes redirectAttributes) {
		recordsPunishmentService.delete(recordsPunishment);
		addMessage(redirectAttributes, "删除处罚行为信息成功");
		return "redirect:"+Global.getAdminPath()+"/creditact/punishment/recordsPunishment/?repage";
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
            String fileName = "行政处罚信息.xlsx";
    		List<RecordsPunishment> list = Lists.newArrayList();
    		new ExportExcel("行政处罚信息", RecordsPunishment.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
	} catch (Exception e) {
			logger.debug(e.getMessage());
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/creditact/punishment/recordsPunishment/?repage";
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
			List<RecordsPunishment> fileData = ei.getDataList(RecordsPunishment.class);
			//字典列表
			//List<Dict> subjectTypeList = DictUtils.getDictList("SUBJECT_TYPE2");
			
			//项目列表
			//Map<String,String> subjectMap = new HashMap<String,String>();
			//List<RecordsEvent> recordEventList = recordsEventService.findList(new RecordsEvent());
			
			for (RecordsPunishment target : fileData)
			{
				try
				{
					 if(StringUtils.isBlank(target.getSubName()))
					{
						failureMsg.append("<br/>处罚机构名称不能为空");
						failureNum++;
						continue;
						
					}
					if(StringUtils.isBlank(target.getPunishType()))
					{
							failureMsg.append("<br/>事件类别不能为空");
							failureNum++;
							continue;
					}
					
					recordsPunishmentService.save(target);
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
		return "redirect:"+Global.getAdminPath()+"/creditact/punishment/recordsPunishment/?repage";
    }
}