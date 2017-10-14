/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.web.gradeitems;

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
import com.amass.credit.modules.grade.entity.gradeitems.GradeItems;
import com.amass.credit.modules.grade.entity.gradeoptions.GradeOption;
import com.amass.credit.modules.grade.service.gradeitems.GradeItemsService;
import com.amass.credit.modules.grade.service.gradeoptions.GradeOptionService;

/**
 * 评分选项管理Controller
 * @author yangqy
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/grade/gradeitems/gradeItems")
public class GradeItemsController extends BaseController {

	@Autowired
	private GradeItemsService gradeItemsService;
	@Autowired
	private GradeOptionService optService;
	
	@ModelAttribute
	public GradeItems get(@RequestParam(required=false) String id) {
		GradeItems entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gradeItemsService.get(id);
		}
		if (entity == null){
			entity = new GradeItems();
		}
		return entity;
	}
	
	@RequiresPermissions("grade:gradeitems:gradeItems:view")
	@RequestMapping(value = {"list", ""})
	public String list(GradeItems gradeItems, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GradeItems> page = gradeItemsService.findPage(new Page<GradeItems>(request, response), gradeItems); 
		model.addAttribute("page", page);
		return "modules/grade/gradeitems/gradeItemsList";
	}

	@RequiresPermissions("grade:gradeitems:gradeItems:view")
	@RequestMapping(value = "form")
	public String form(GradeItems gradeItems, Model model) {
		
		
		model.addAttribute("gradeItems", gradeItems);
		//获取所有的选项值
		if(gradeItems != null && StringUtils.isNotBlank(gradeItems.getId())){
				GradeOption opt = new GradeOption();
				opt.setItemsId(Long.parseLong(gradeItems.getId()));
				model.addAttribute("optList", optService.findList(opt));
					
		}
		return "modules/grade/gradeitems/gradeItemsForm";
	}

	@RequiresPermissions("grade:gradeitems:gradeItems:edit")
	@RequestMapping(value = "save")
	public String save(GradeItems gradeItems, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gradeItems)){
			return form(gradeItems, model);
		}
		gradeItemsService.save(gradeItems);
		addMessage(redirectAttributes, "保存评分选项管理成功");
		return "redirect:"+Global.getAdminPath()+"/grade/gradeitems/gradeItems/?repage";
	}
	
	@RequiresPermissions("grade:gradeitems:gradeItems:edit")
	@RequestMapping(value = "delete")
	public String delete(GradeItems gradeItems, RedirectAttributes redirectAttributes) {
		gradeItemsService.delete(gradeItems);
		addMessage(redirectAttributes, "删除评分选项管理成功");
		return "redirect:"+Global.getAdminPath()+"/grade/gradeitems/gradeItems/?repage";
	}

}