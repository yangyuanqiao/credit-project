/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.creditact.web.event;
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
import com.amass.credit.modules.creditact.entity.event.RecordsEvent;
import com.amass.credit.modules.creditact.service.event.RecordsEventService;
import com.google.common.collect.Lists;

/**
 * 事件行为信息Controller
 * @author yangyq
 * @version 2017-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/creditact/event/recordsEvent")
public class RecordsEventController extends BaseController {

	@Autowired
	private RecordsEventService recordsEventService;
	
	@ModelAttribute
	public RecordsEvent get(@RequestParam(required=false) String id) {
		RecordsEvent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = recordsEventService.get(id);
		}
		if (entity == null){
			entity = new RecordsEvent();
		}
		return entity;
	}
	
	@RequiresPermissions("creditact:event:recordsEvent:view")
	@RequestMapping(value = {"list", ""})
	public String list(RecordsEvent recordsEvent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RecordsEvent> page = recordsEventService.findPage(new Page<RecordsEvent>(request, response), recordsEvent); 
		model.addAttribute("page", page);
		return "modules/creditact/event/recordsEventList";
	}

	@RequiresPermissions("creditact:event:recordsEvent:view")
	@RequestMapping(value = "subeventlist")
	public String getEventListBySubId(RecordsEvent recordsEvent, HttpServletRequest request, HttpServletResponse response, Model model) {
		String subId= request.getParameter("subId");
		String subType= request.getParameter("subType");
		model.addAttribute("subType", subType);
		recordsEvent.setSubId(subId);
		Page<RecordsEvent> page = recordsEventService.findPage(new Page<RecordsEvent>(request, response), recordsEvent); 
		model.addAttribute("page", page);
		model.addAttribute("subId", subId);
		return "modules/creditact/event/subjectEventList";
	}

	
	@RequiresPermissions("creditact:event:recordsEvent:view")
	@RequestMapping(value = "form")
	public String form(RecordsEvent recordsEvent, Model model) {
		model.addAttribute("recordsEvent", recordsEvent);
		return "modules/creditact/event/recordsEventForm";
	}

	@RequiresPermissions("creditact:event:recordsEvent:edit")
	@RequestMapping(value = "save")
	public String save(RecordsEvent recordsEvent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, recordsEvent)){
			return form(recordsEvent, model);
		}
		recordsEventService.save(recordsEvent);
		addMessage(redirectAttributes, "保存事件行为信息成功");
		return "redirect:"+Global.getAdminPath()+"/creditact/event/recordsEvent/?repage";
	}
	
	@RequiresPermissions("creditact:event:recordsEvent:edit")
	@RequestMapping(value = "delete")
	public String delete(RecordsEvent recordsEvent, RedirectAttributes redirectAttributes) {
		recordsEventService.delete(recordsEvent);
		addMessage(redirectAttributes, "删除事件行为信息成功");
		return "redirect:"+Global.getAdminPath()+"/creditact/event/recordsEvent/?repage";
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
            String fileName = "事件行为模板.xlsx";
    		List<RecordsEvent> list = Lists.newArrayList();
    		list.add(new RecordsEvent());
    		new ExportExcel("事件行为模板", RecordsEvent.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
	} catch (Exception e) {
			logger.debug(e.getMessage());
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/creditact/event/recordsEvent/?repage";
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
			List<RecordsEvent> fileData = ei.getDataList(RecordsEvent.class);
			//字典列表
			//List<Dict> subjectTypeList = DictUtils.getDictList("SUBJECT_TYPE2");
			
			//项目列表
			//Map<String,String> subjectMap = new HashMap<String,String>();
			//List<RecordsEvent> recordEventList = recordsEventService.findList(new RecordsEvent());
			
			
			for (RecordsEvent target : fileData)
			{
				try
				{
					 if(StringUtils.isBlank(target.getEventName()))
					{
						failureMsg.append("<br/>事件标题不能为空");
						failureNum++;
					}
					else
					{
						target.setDeparrment("");
						recordsEventService.save(target);
						successNum++;
					}
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
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息，审核后生效。"+failureMsg);
		} 
		catch (Exception e) 
		{
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/creditact/event/recordsEvent/?repage";
    }

}