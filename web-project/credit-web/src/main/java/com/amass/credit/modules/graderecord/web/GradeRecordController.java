/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.graderecord.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amass.credit.common.config.Global;
import com.amass.credit.common.constant.SystemConstants;
import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.common.web.BaseController;
import com.amass.credit.modules.gradelimit.service.category.GradeLimitService;
import com.amass.credit.modules.graderecord.entity.EstablishGradeVo;
import com.amass.credit.modules.graderecord.entity.GradeLimitDetailVo;
import com.amass.credit.modules.graderecord.entity.GradeRecord;
import com.amass.credit.modules.graderecord.entity.GradeRecordDetailVo;
import com.amass.credit.modules.graderecord.service.GradeRecordService;

/**
 * 评分记录信息Controller
 * @author yangyq
 * @version 2017-06-19
 */
@Controller
@RequestMapping(value = "${adminPath}/graderecord/gradeRecord")
public class GradeRecordController extends BaseController {

	@Autowired
	private GradeRecordService gradeRecordService;
	@Autowired
	private GradeLimitService gradeLimitService;
	
	@ModelAttribute
	public GradeRecord get(@RequestParam(required=false) String id,@RequestParam(required=false) String subType) {
		GradeRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gradeRecordService.get(id);
		}
		if (StringUtils.isBlank(subType)){
			subType = SystemConstants.SUB_TYPE_FOR_ENTERPRISE;
		}
		if (entity == null){
			entity = new GradeRecord();
			entity.setSubType(subType);
		}
		return entity;
	}
	
	@RequiresPermissions("graderecord:gradeRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(GradeRecord gradeRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GradeRecord> page = gradeRecordService.findPage(new Page<GradeRecord>(request, response), gradeRecord); 
		model.addAttribute("page", page);
		return "modules/graderecord/gradeRecordList";
	}

	@RequiresPermissions("graderecord:gradeRecord:view")
	@RequestMapping(value = "form")
	public String form(GradeRecord gradeRecord, Model model) {
		model.addAttribute("gradeRecord", gradeRecord);
		return "modules/graderecord/gradeRecordForm";
	}

	@RequiresPermissions("graderecord:gradeRecord:edit")
	@RequestMapping(value = "save")
	public String save(GradeRecord gradeRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gradeRecord)){
			return form(gradeRecord, model);
		}
		gradeRecordService.save(gradeRecord);
		addMessage(redirectAttributes, "保存评分记录成功");
		return "redirect:"+Global.getAdminPath()+"/graderecord/gradeRecord/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(GradeRecord gradeRecord, RedirectAttributes redirectAttributes) {
		//gradeRecordService.delete(gradeRecord);
		gradeRecord.setStatus(SystemConstants.PF_STATUS_UNAVIABLE);
		gradeRecordService.update(gradeRecord);
		addMessage(redirectAttributes, "评分记录设置失效成功，系统将重新进行评分");
		if(SystemConstants.SUB_TYPE_FOR_ENTERPRISE.equals(gradeRecord.getSubType())){
			return "redirect:"+Global.getAdminPath()+"/graderecord/gradeRecord/?repage";
		}else{
			return "redirect:"+Global.getAdminPath()+"/graderecord/gradeRecord/renthouselist?repage";
		}
		
	}
	
	//明细页
	@RequiresPermissions("graderecord:gradeRecord:view")
	@RequestMapping(value = "gradeRecordDetail")
	public String gradeRecordDetial(GradeRecord gradeRecord, Model model){
		List<GradeRecordDetailVo> gradeDetailVo = gradeRecordService.getDetailList(gradeRecord);
		logger.debug(gradeDetailVo.toString());
		model.addAttribute("gradeDetailVoLsit", gradeDetailVo);
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("subId", String.valueOf(gradeRecord.getSubId()));
		map.put("batch", gradeRecord.getBatch());
		List<GradeLimitDetailVo> levelLimitVo = gradeLimitService.findLimitDetailList(map);
		model.addAttribute("levelLimitVoList", levelLimitVo);
		return "modules/graderecord/gradeRecordDetail";
		
	}
	
	@RequiresPermissions("graderecord:gradeRecord:view")
	@RequestMapping(value = "renthouselist")
	public String renthouselist(GradeRecord gradeRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		gradeRecord.setSubType("2");
		Page<GradeRecord> page = gradeRecordService.findHouseGradeRecordPage(new Page<GradeRecord>(request, response), gradeRecord); 
		model.addAttribute("page", page);
		return "modules/graderecord/renthouseGradeList";
	}
	
	
	@RequiresPermissions("graderecord:gradeRecord:view")
	@RequestMapping(value = "establishList")
	public String establishList(GradeRecord gradeRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		gradeRecord.setSubType("3");
		//Page<EstablishGradeVo> page = gradeRecordService.findEstablishList(new Page<EstablishGradeVo>(request, response), gradeRecord); 
		model.addAttribute("page", null);
		return "modules/graderecord/establishList";
	}
	
	@RequiresPermissions("graderecord:gradeRecord:view")
	@RequestMapping(value = "cowsList")
	public String cowsList(GradeRecord gradeRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		gradeRecord.setSubType("4");
		//Page<GradeRecord> page = gradeRecordService.findHouseGradeRecordPage(new Page<GradeRecord>(request, response), gradeRecord); 
		model.addAttribute("page", null);
		return "modules/graderecord/cowsList";
	}
	
}