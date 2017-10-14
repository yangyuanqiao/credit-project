/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.gradelimit.web.limitrule;

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
import com.amass.credit.modules.gen.entity.GenTable;
import com.amass.credit.modules.gen.service.GenTableService;
import com.amass.credit.modules.grade.entity.gradeitems.GradeItems;
import com.amass.credit.modules.grade.service.gradeitems.GradeItemsService;
import com.amass.credit.modules.gradelimit.entity.category.GradeLimit;
import com.amass.credit.modules.gradelimit.entity.limitrule.GradeRule;
import com.amass.credit.modules.gradelimit.service.category.GradeLimitService;
import com.amass.credit.modules.gradelimit.service.limitrule.GradeRuleService;
import com.google.common.collect.Maps;

/**
 * 规则Controller
 * @author yangyq
 * @version 2017-06-27
 */
@Controller
@RequestMapping(value = "${adminPath}/gradelimit/limitrule/gradeRule")
public class GradeRuleController extends BaseController {

	@Autowired
	private GradeRuleService gradeRuleService;
	@Autowired
	private GenTableService genTableService;
	
	@Autowired
	private GradeLimitService gradeLimitService;
	
	@Autowired
	private GradeItemsService gradeItemsService;
	
	
	@ModelAttribute
	public GradeRule get(@RequestParam(required=false) String id) {
		GradeRule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gradeRuleService.get(id);
		}
		if (entity == null){
			entity = new GradeRule();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(GradeRule gradeRule, HttpServletRequest request, HttpServletResponse response, Model model) {
		String itemid = request.getParameter("itemId");
		if(!StringUtils.isBlank(itemid)){
			gradeRule = new GradeRule();
			gradeRule.setItemId(itemid);
		}
		
		Page<GradeRule> page = gradeRuleService.findPage(new Page<GradeRule>(request, response), gradeRule); 
		model.addAttribute("page", page);
		String from = request.getParameter("from");
		if("items".equals(from)){
			GradeItems items = gradeItemsService.get(itemid);
			model.addAttribute("gradeItem", items);
		}else{
			GradeLimit limitCat = gradeLimitService.get(itemid);
			model.addAttribute("limitCat", limitCat);
		}
		
		return "modules/gradelimit/limitrule/gradeRuleList";
	}

	
	@RequestMapping(value = "form")
	public String form(GradeRule gradeRule, Model model) {
		// 获取物理表列表
		        GenTable table = new GenTable();
		        table.setComments("www");
				List<GenTable> tableList = genTableService.findTableListFormDb(table);
				model.addAttribute("tableList", tableList);
				model.addAttribute("gradeRule", gradeRule);
		return "modules/gradelimit/limitrule/gradeRuleForm";
	}

	
	@RequestMapping(value = "save")
	public String save(GradeRule gradeRule, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gradeRule)){
			return form(gradeRule, model);
		}
		gradeRuleService.save(gradeRule);
		addMessage(redirectAttributes, "保存规则成功");
		return "redirect:"+Global.getAdminPath()+"/gradelimit/limitrule/gradeRule/?repage&itemId="+String.valueOf(gradeRule.getItemId());
	}
	
	@RequiresPermissions("gradelimit:limitrule:gradeRule:edit")
	@RequestMapping(value = "delete")
	public String delete(GradeRule gradeRule, RedirectAttributes redirectAttributes) {
		gradeRuleService.delete(gradeRule);
		addMessage(redirectAttributes, "删除规则成功");
		return "redirect:"+Global.getAdminPath()+"/gradelimit/limitrule/gradeRule/?itemId="+String.valueOf(gradeRule.getItemId());
	}
	
	
	@RequestMapping(value="getTableColumn")  
    @ResponseBody
    public Map getTableColumn(HttpServletRequest request,Model model) {  
        Map<String, Object> returnMap = Maps.newHashMap();  
       
        	String  tableName=request.getParameter("tableName");
        	returnMap.put("columnList",gradeRuleService.findTableColumnMap(tableName)); 
        
        return returnMap;  
    }  

}