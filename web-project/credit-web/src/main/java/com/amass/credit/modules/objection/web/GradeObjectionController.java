/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.objection.web;

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
import com.amass.credit.modules.objection.entity.GradeObjection;
import com.amass.credit.modules.objection.service.GradeObjectionService;

/**
 * 异议申请Controller
 * @author yangyq
 * @version 2017-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/objection/gradeObjection")
public class GradeObjectionController extends BaseController {

	@Autowired
	private GradeObjectionService gradeObjectionService;
	
	@ModelAttribute
	public GradeObjection get(@RequestParam(required=false) String id) {
		GradeObjection entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gradeObjectionService.get(id);
		}
		if (entity == null){
			entity = new GradeObjection();
		}
		return entity;
	}
	
	@RequiresPermissions("objection:gradeObjection:view")
	@RequestMapping(value = {"list", ""})
	public String list(GradeObjection gradeObjection, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GradeObjection> page = gradeObjectionService.findPage(new Page<GradeObjection>(request, response), gradeObjection); 
		model.addAttribute("page", page);
		return "modules/objection/gradeObjectionList";
	}

	@RequiresPermissions("objection:gradeObjection:view")
	@RequestMapping(value = "form")
	public String form(GradeObjection gradeObjection, Model model) {
		model.addAttribute("gradeObjection", gradeObjection);
		return "modules/objection/gradeObjectionForm";
	}

	@RequiresPermissions("objection:gradeObjection:edit")
	@RequestMapping(value = "save")
	public String save(GradeObjection gradeObjection, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gradeObjection)){
			return form(gradeObjection, model);
		}
		gradeObjectionService.save(gradeObjection);
		addMessage(redirectAttributes, "保存异议申请成功");
		return "redirect:"+Global.getAdminPath()+"/objection/gradeObjection/?repage";
	}
	
	@RequiresPermissions("objection:gradeObjection:edit")
	@RequestMapping(value = "delete")
	public String delete(GradeObjection gradeObjection, RedirectAttributes redirectAttributes) {
		gradeObjectionService.delete(gradeObjection);
		addMessage(redirectAttributes, "删除异议申请成功");
		return "redirect:"+Global.getAdminPath()+"/objection/gradeObjection/?repage";
	}

}