/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.cow.web;

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
import com.amass.credit.modules.cow.entity.CrdCowOperation;
import com.amass.credit.modules.cow.service.CrdCowOperationService;

/**
 * 牛行经营场所Controller
 * @author yangyq
 * @version 2017-08-18
 */
@Controller
@RequestMapping(value = "${adminPath}/cow/crdCowOperation")
public class CrdCowOperationController extends BaseController {

	@Autowired
	private CrdCowOperationService crdCowOperationService;
	
	@ModelAttribute
	public CrdCowOperation get(@RequestParam(required=false) String id) {
		CrdCowOperation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = crdCowOperationService.get(id);
		}
		if (entity == null){
			entity = new CrdCowOperation();
		}
		return entity;
	}
	
	@RequiresPermissions("cow:crdCowOperation:view")
	@RequestMapping(value = {"list", ""})
	public String list(CrdCowOperation crdCowOperation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CrdCowOperation> page = crdCowOperationService.findPage(new Page<CrdCowOperation>(request, response), crdCowOperation); 
		model.addAttribute("page", page);
		return "modules/cow/crdCowOperationList";
	}

	@RequiresPermissions("cow:crdCowOperation:view")
	@RequestMapping(value = "form")
	public String form(CrdCowOperation crdCowOperation, Model model) {
		model.addAttribute("crdCowOperation", crdCowOperation);
		return "modules/cow/crdCowOperationForm";
	}

	@RequiresPermissions("cow:crdCowOperation:edit")
	@RequestMapping(value = "save")
	public String save(CrdCowOperation crdCowOperation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, crdCowOperation)){
			return form(crdCowOperation, model);
		}
		crdCowOperationService.save(crdCowOperation);
		addMessage(redirectAttributes, "保存牛行经营场所成功");
		return "redirect:"+Global.getAdminPath()+"/cow/crdCowOperation/?repage";
	}
	
	@RequiresPermissions("cow:crdCowOperation:edit")
	@RequestMapping(value = "delete")
	public String delete(CrdCowOperation crdCowOperation, RedirectAttributes redirectAttributes) {
		crdCowOperationService.delete(crdCowOperation);
		addMessage(redirectAttributes, "删除牛行经营场所成功");
		return "redirect:"+Global.getAdminPath()+"/cow/crdCowOperation/?repage";
	}

}