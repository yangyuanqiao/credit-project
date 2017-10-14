/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.web.gradeoptions;

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
import com.amass.credit.modules.grade.entity.gradeoptions.GradeOption;
import com.amass.credit.modules.grade.service.gradeoptions.GradeOptionService;

/**
 * 评分选项值Controller
 * @author yangqy
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/grade/gradeoptions/gradeOption")
public class GradeOptionController extends BaseController {

	@Autowired
	private GradeOptionService gradeOptionService;
	
	@ModelAttribute
	public GradeOption get(@RequestParam(required=false) String id) {
		GradeOption entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gradeOptionService.get(id);
		}
		if (entity == null){
			entity = new GradeOption();
		}
		return entity;
	}
	
	@RequiresPermissions("grade:gradeoptions:gradeOption:view")
	@RequestMapping(value = {"list", ""})
	public String list(GradeOption gradeOption, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GradeOption> page = gradeOptionService.findPage(new Page<GradeOption>(request, response), gradeOption); 
		model.addAttribute("page", page);
		return "modules/grade/gradeoptions/gradeOptionList";
	}

	@RequiresPermissions("grade:gradeoptions:gradeOption:view")
	@RequestMapping(value = "form")
	public String form(GradeOption gradeOption, Model model) {
		model.addAttribute("gradeOption", gradeOption);
		return "modules/grade/gradeoptions/gradeOptionForm";
	}

	@RequiresPermissions("grade:gradeoptions:gradeOption:edit")
	@RequestMapping(value = "save")
	public String save(GradeOption gradeOption, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gradeOption)){
			return form(gradeOption, model);
		}
		gradeOptionService.save(gradeOption);
		addMessage(redirectAttributes, "保存评分选项值成功");
		return "redirect:"+Global.getAdminPath()+"/grade/gradeoptions/gradeOption/?repage";
	}
	
	@RequiresPermissions("grade:gradeoptions:gradeOption:edit")
	@RequestMapping(value = "delete")
	public String delete(GradeOption gradeOption, RedirectAttributes redirectAttributes) {
		gradeOptionService.delete(gradeOption);
		addMessage(redirectAttributes, "删除评分选项值成功");
		return "redirect:"+Global.getAdminPath()+"/grade/gradeoptions/gradeOption/?repage";
	}

}