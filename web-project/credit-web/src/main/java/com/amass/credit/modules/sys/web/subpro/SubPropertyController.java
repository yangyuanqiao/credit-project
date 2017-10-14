/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.sys.web.subpro;

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
import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.common.web.BaseController;
import com.amass.credit.modules.sys.entity.subpro.SubProperty;
import com.amass.credit.modules.sys.entity.subpro.SubPropertyVo;
import com.amass.credit.modules.sys.service.subpro.SubPropertyService;

/**
 * 主体属性信息Controller
 * @author yangyq
 * @version 2017-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/subpro/subProperty")
public class SubPropertyController extends BaseController {

	@Autowired
	private SubPropertyService subPropertyService;
	
	@ModelAttribute
	public SubProperty get(@RequestParam(required=false) String id) {
		SubProperty entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = subPropertyService.get(id);
		}
		if (entity == null){
			entity = new SubProperty();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:subpro:subProperty:view")
	@RequestMapping(value = {"list", ""})
	public String list(SubProperty subProperty, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SubProperty> page = subPropertyService.findPage(new Page<SubProperty>(request, response), subProperty); 
		model.addAttribute("page", page);
		return "modules/sys/subpro/subPropertyList";
	}
	
	@RequiresPermissions("sys:subpro:subProperty:view")
	@RequestMapping(value = "detail")
	public String detail(SubPropertyVo subPropertyVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		subPropertyVo = new SubPropertyVo();
		
		String subType=request.getParameter("subType");
		String subId = request.getParameter("subId");
		if(StringUtils.isNotBlank(subType) && StringUtils.isNotBlank(subId)){
			subPropertyVo.setSubType(Long.parseLong(subType));
			subPropertyVo.setSubId(subId);
			List<SubPropertyVo> subPropertyVoList = subPropertyService.findListBySubId(subPropertyVo); 
			model.addAttribute("page", subPropertyVoList);
		}
		model.addAttribute("subType", subType);
		model.addAttribute("subId", subId);
		return "modules/sys/subpro/subPropertyDetail";
	}

	@RequiresPermissions("sys:subpro:subProperty:view")
	@RequestMapping(value = "form")
	public String form(SubProperty subProperty, Model model) {
		model.addAttribute("subProperty", subProperty);
		return "modules/sys/subpro/subPropertyForm";
	}

	@RequiresPermissions("sys:subpro:subProperty:edit")
	@RequestMapping(value = "save")
	public String save(SubProperty subProperty, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, subProperty)){
			return form(subProperty, model);
		}
		subPropertyService.save(subProperty);
		addMessage(redirectAttributes, "保存主体属性信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/subpro/subProperty/?repage";
	}
	
	@RequiresPermissions("sys:subpro:subProperty:edit")
	@RequestMapping(value = "delete")
	public String delete(SubProperty subProperty, RedirectAttributes redirectAttributes) {
		subPropertyService.delete(subProperty);
		addMessage(redirectAttributes, "删除主体属性信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/subpro/subProperty/?repage";
	}

}