/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.web.category;

import java.util.HashMap;
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
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.common.web.BaseController;
import com.amass.credit.modules.grade.entity.category.GradeCategory;
import com.amass.credit.modules.grade.service.category.GradeCategoryService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 评分选项类别Controller
 * @author yangqy
 * @version 2017-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/grade/category/gradeCategory")
public class GradeCategoryController extends BaseController {

	@Autowired
	private GradeCategoryService gradeCategoryService;
	
	@ModelAttribute
	public GradeCategory get(@RequestParam(required=false) String id) {
		GradeCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gradeCategoryService.get(id);
		}
		if (entity == null){
			entity = new GradeCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("grade:category:gradeCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(GradeCategory gradeCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<GradeCategory> list = gradeCategoryService.findList(gradeCategory); 
//		System.out.println(gradeCategory.getParent().getId()+"*****"+gradeCategory.getParent().getName());
		model.addAttribute("list", list);
		return "modules/grade/category/gradeCategoryList";
	}

	@RequiresPermissions("grade:category:gradeCategory:view")
	@RequestMapping(value = "form")
	public String form(GradeCategory gradeCategory, Model model) {
		if (gradeCategory.getParent()!=null && StringUtils.isNotBlank(gradeCategory.getParent().getId())){
			gradeCategory.setParent(gradeCategoryService.get(gradeCategory.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(gradeCategory.getId())){
				GradeCategory gradeCategoryChild = new GradeCategory();
				gradeCategoryChild.setParent(new GradeCategory(gradeCategory.getParent().getId()));
				List<GradeCategory> list = gradeCategoryService.findList(gradeCategory); 
				if (list.size() > 0){
					gradeCategory.setSort(list.get(list.size()-1).getSort());
					if (gradeCategory.getSort() != null){
						gradeCategory.setSort(gradeCategory.getSort() + 30);
					}
				}
			}
		}
		if (gradeCategory.getSort() == null){
			gradeCategory.setSort(30);
		}
		model.addAttribute("gradeCategory", gradeCategory);
		return "modules/grade/category/gradeCategoryForm";
	}

	@RequiresPermissions("grade:category:gradeCategory:edit")
	@RequestMapping(value = "save")
	public String save(GradeCategory gradeCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gradeCategory)){
			return form(gradeCategory, model);
		}
		gradeCategoryService.save(gradeCategory);
		addMessage(redirectAttributes, "保存评分选项类别成功");
		return "redirect:"+Global.getAdminPath()+"/grade/category/gradeCategory/?repage";
	}
	
	@RequiresPermissions("grade:category:gradeCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(GradeCategory gradeCategory, RedirectAttributes redirectAttributes) {
		gradeCategoryService.delete(gradeCategory);
		addMessage(redirectAttributes, "删除评分选项类别成功");
		return "redirect:"+Global.getAdminPath()+"/grade/category/gradeCategory/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<GradeCategory> list = gradeCategoryService.findList(new GradeCategory());
		for (int i=0; i<list.size(); i++){
			GradeCategory e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getCateName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "getCategoryBySub")
	public HashMap getCategoryBySub(@RequestParam(required=true) String subType,@RequestParam(required=false) String fatherId){
		GradeCategory cate= new GradeCategory();
		cate.setSubType(subType);
		if(StringUtils.isNoneBlank(fatherId)){
			cate.setParentIds(fatherId);
		}else{
			cate.setFatherFlag("0");
		}
		List<GradeCategory> catList = gradeCategoryService.findList(cate);
		Map<String, Object> returnMap = Maps.newHashMap();  
        returnMap.put("levelList", catList);  
        return (HashMap) returnMap;  
	}
	
}