/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.web;

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
import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.web.BaseController;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.modules.grade.entity.GradeLevel;
import com.amass.credit.modules.grade.service.GradeLevelService;

/**
 * 信用等级信息管理Controller
 * @author yangqy
 * @version 2017-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/grade/gradeLevel")
public class GradeLevelController extends BaseController {

	@Autowired
	private GradeLevelService gradeLevelService;
	
	@ModelAttribute
	public GradeLevel get(@RequestParam(required=false) String id) {
		GradeLevel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gradeLevelService.get(id);
		}
		if (entity == null){
			entity = new GradeLevel();
		}
		return entity;
	}
	
	@RequiresPermissions("grade:gradeLevel:view")
	@RequestMapping(value = {"list", ""})
	public String list(GradeLevel gradeLevel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GradeLevel> page = gradeLevelService.findPage(new Page<GradeLevel>(request, response), gradeLevel); 
		model.addAttribute("page", page);
		return "modules/grade/gradeLevelList";
	}

	@RequiresPermissions("grade:gradeLevel:view")
	@RequestMapping(value = "form")
	public String form(GradeLevel gradeLevel, Model model) {
		model.addAttribute("gradeLevel", gradeLevel);
		return "modules/grade/gradeLevelForm";
	}

	@RequiresPermissions("grade:gradeLevel:edit")
	@RequestMapping(value = "save")
	public String save(GradeLevel gradeLevel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gradeLevel)){
			return form(gradeLevel, model);
		}
		gradeLevelService.save(gradeLevel);
		addMessage(redirectAttributes, "保存信用等级信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/grade/gradeLevel/?repage";
	}
	
	@RequiresPermissions("grade:gradeLevel:edit")
	@RequestMapping(value = "delete")
	public String delete(GradeLevel gradeLevel, RedirectAttributes redirectAttributes) {
		gradeLevelService.delete(gradeLevel);
		addMessage(redirectAttributes, "删除信用等级信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/grade/gradeLevel/?repage";
	}

}