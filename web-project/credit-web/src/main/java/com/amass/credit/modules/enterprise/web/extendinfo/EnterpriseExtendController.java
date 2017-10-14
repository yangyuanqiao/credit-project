/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.web.extendinfo;

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
import com.amass.credit.modules.enterprise.entity.extendinfo.EnterpriseExtend;
import com.amass.credit.modules.enterprise.service.extendinfo.EnterpriseExtendService;

/**
 * 主体扩展信息Controller
 * @author yangyq
 * @version 2017-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/enterprise/extendinfo/enterpriseExtend")
public class EnterpriseExtendController extends BaseController {

	@Autowired
	private EnterpriseExtendService enterpriseExtendService;
	
	@ModelAttribute
	public EnterpriseExtend get(@RequestParam(required=false) String id) {
		EnterpriseExtend entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = enterpriseExtendService.get(id);
		}
		if (entity == null){
			entity = new EnterpriseExtend();
		}
		return entity;
	}
	
	@RequiresPermissions("enterprise:extendinfo:enterpriseExtend:view")
	@RequestMapping(value = {"list", ""})
	public String list(EnterpriseExtend enterpriseExtend, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EnterpriseExtend> page = enterpriseExtendService.findPage(new Page<EnterpriseExtend>(request, response), enterpriseExtend); 
		model.addAttribute("page", page);
		return "modules/enterprise/extendinfo/enterpriseExtendList";
	}

	@RequiresPermissions("enterprise:extendinfo:enterpriseExtend:view")
	@RequestMapping(value = "form")
	public String form(EnterpriseExtend enterpriseExtend, Model model) {
		model.addAttribute("enterpriseExtend", enterpriseExtend);
		return "modules/enterprise/extendinfo/enterpriseExtendForm";
	}

	@RequiresPermissions("enterprise:extendinfo:enterpriseExtend:edit")
	@RequestMapping(value = "save")
	public String save(EnterpriseExtend enterpriseExtend, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, enterpriseExtend)){
			return form(enterpriseExtend, model);
		}
		enterpriseExtendService.save(enterpriseExtend);
		addMessage(redirectAttributes, "保存主体扩展信息成功");
		return "redirect:"+Global.getAdminPath()+"/enterprise/extendinfo/enterpriseExtend/?repage";
	}
	
	@RequiresPermissions("enterprise:extendinfo:enterpriseExtend:edit")
	@RequestMapping(value = "delete")
	public String delete(EnterpriseExtend enterpriseExtend, RedirectAttributes redirectAttributes) {
		enterpriseExtendService.delete(enterpriseExtend);
		addMessage(redirectAttributes, "删除主体扩展信息成功");
		return "redirect:"+Global.getAdminPath()+"/enterprise/extendinfo/enterpriseExtend/?repage";
	}

}