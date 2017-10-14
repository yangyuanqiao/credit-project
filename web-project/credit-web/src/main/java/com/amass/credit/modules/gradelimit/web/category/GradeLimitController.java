/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.gradelimit.web.category;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amass.credit.common.config.Global;
import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.common.web.BaseController;
import com.amass.credit.modules.grade.entity.GradeLevel;
import com.amass.credit.modules.grade.service.GradeLevelService;
import com.amass.credit.modules.gradelimit.entity.category.GradeLimit;
import com.amass.credit.modules.gradelimit.service.category.GradeLimitService;
import com.google.common.collect.Maps;

/**
 * 条件限制Controller
 * @author yangyq
 * @version 2017-06-27
 */
@Controller
@RequestMapping(value = "${adminPath}/gradelimit/category/gradeLimit")
public class GradeLimitController extends BaseController {

	@Autowired
	private GradeLimitService gradeLimitService;
	@Autowired
	private GradeLevelService gradeLevelService;
	
	@ModelAttribute
	public GradeLimit get(@RequestParam(required=false) String id) {
		GradeLimit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gradeLimitService.get(id);
		}
		if (entity == null){
			entity = new GradeLimit();
		}
		return entity;
	}
	
	@RequiresPermissions("gradelimit:category:gradeLimit:view")
	@RequestMapping(value = {"list", ""})
	public String list(GradeLimit gradeLimit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GradeLimit> page = gradeLimitService.findPage(new Page<GradeLimit>(request, response), gradeLimit); 
		model.addAttribute("page", page);
		return "modules/gradelimit/category/gradeLimitList";
	}

	@RequiresPermissions("gradelimit:category:gradeLimit:view")
	@RequestMapping(value = "form")
	public String form(GradeLimit gradeLimit, Model model) {
		model.addAttribute("gradeLimit", gradeLimit);
		return "modules/gradelimit/category/gradeLimitForm";
	}

	@RequiresPermissions("gradelimit:category:gradeLimit:edit")
	@RequestMapping(value = "save")
	public String save(GradeLimit gradeLimit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gradeLimit)){
			return form(gradeLimit, model);
		}
		gradeLimitService.save(gradeLimit);
		addMessage(redirectAttributes, "保存条件限制成功");
		return "redirect:"+Global.getAdminPath()+"/gradelimit/category/gradeLimit/?repage";
	}
	
	@RequiresPermissions("gradelimit:category:gradeLimit:edit")
	@RequestMapping(value = "delete")
	public String delete(GradeLimit gradeLimit, RedirectAttributes redirectAttributes) {
		gradeLimitService.delete(gradeLimit);
		addMessage(redirectAttributes, "删除条件限制成功");
		return "redirect:"+Global.getAdminPath()+"/gradelimit/category/gradeLimit/?repage";
	}
	
	@RequestMapping(value="getLevelList")  
    @ResponseBody 
    public Map getModelList(HttpServletRequest request) {  
        List<GradeLevel> levelList = null;  
        try{  
        	GradeLevel entity = new GradeLevel();
        	entity.setSubjectType(request.getParameter("subType"));
        	levelList = gradeLevelService.findList(entity);  
        }catch(Exception e){  
            logger.error("获取信用级别异常:{}", e.getMessage());  
        }  
        Map<String, Object> returnMap = Maps.newHashMap();  
        returnMap.put("levelList", levelList);  
        return returnMap;  
    }  

}