/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.estatehousing.web.basicinfo;

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
import com.amass.credit.modules.estatehousing.entity.basicinfo.HousingEstate;
import com.amass.credit.modules.estatehousing.service.basicinfo.HousingEstateService;

/**
 * 小区楼盘Controller
 * @author yangyq
 * @version 2017-08-15
 */
@Controller
@RequestMapping(value = "${adminPath}/estatehousing/basicinfo/housingEstate")
public class HousingEstateController extends BaseController {

	@Autowired
	private HousingEstateService housingEstateService;
	
	@ModelAttribute
	public HousingEstate get(@RequestParam(required=false) String id) {
		HousingEstate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = housingEstateService.get(id);
		}
		if (entity == null){
			entity = new HousingEstate();
		}
		return entity;
	}
	
	@RequiresPermissions("estatehousing:basicinfo:housingEstate:view")
	@RequestMapping(value = {"list", ""})
	public String list(HousingEstate housingEstate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HousingEstate> page = housingEstateService.findPage(new Page<HousingEstate>(request, response), housingEstate); 
		model.addAttribute("page", page);
		return "modules/estatehousing/basicinfo/housingEstateList";
	}

	@RequiresPermissions("estatehousing:basicinfo:housingEstate:view")
	@RequestMapping(value = "form")
	public String form(HousingEstate housingEstate, Model model) {
		model.addAttribute("housingEstate", housingEstate);
		return "modules/estatehousing/basicinfo/housingEstateForm";
	}

	@RequiresPermissions("estatehousing:basicinfo:housingEstate:edit")
	@RequestMapping(value = "save")
	public String save(HousingEstate housingEstate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, housingEstate)){
			return form(housingEstate, model);
		}
		housingEstateService.save(housingEstate);
		addMessage(redirectAttributes, "保存小区楼盘成功");
		return "redirect:"+Global.getAdminPath()+"/estatehousing/basicinfo/housingEstate/?repage";
	}
	
	@RequiresPermissions("estatehousing:basicinfo:housingEstate:edit")
	@RequestMapping(value = "delete")
	public String delete(HousingEstate housingEstate, RedirectAttributes redirectAttributes) {
		housingEstateService.delete(housingEstate);
		addMessage(redirectAttributes, "删除小区楼盘成功");
		return "redirect:"+Global.getAdminPath()+"/estatehousing/basicinfo/housingEstate/?repage";
	}

}