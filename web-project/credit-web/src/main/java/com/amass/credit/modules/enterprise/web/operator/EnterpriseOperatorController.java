/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.web.operator;

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
import com.amass.credit.modules.enterprise.entity.operator.EnterpriseOperator;
import com.amass.credit.modules.enterprise.service.operator.EnterpriseOperatorService;

/**
 * 企业经营者信息Controller
 * @author yangyq
 * @version 2017-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/enterprise/operator/enterpriseOperator")
public class EnterpriseOperatorController extends BaseController {

	@Autowired
	private EnterpriseOperatorService enterpriseOperatorService;
	
	@ModelAttribute
	public EnterpriseOperator get(@RequestParam(required=false) String id) {
		EnterpriseOperator entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = enterpriseOperatorService.get(id);
		}
		if (entity == null){
			entity = new EnterpriseOperator();
		}
		return entity;
	}
	
	@RequiresPermissions("enterprise:operator:enterpriseOperator:view")
	@RequestMapping(value = {"list", ""})
	public String list(EnterpriseOperator enterpriseOperator, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EnterpriseOperator> page = enterpriseOperatorService.findPage(new Page<EnterpriseOperator>(request, response), enterpriseOperator); 
		model.addAttribute("page", page);
		return "modules/enterprise/operator/enterpriseOperatorList";
	}

	@RequestMapping(value = "form")
	public String form(EnterpriseOperator enterpriseOperator, Model model) {
		model.addAttribute("enterpriseOperator", enterpriseOperator);
		return "modules/enterprise/operator/enterpriseOperatorForm";
	}

	@RequiresPermissions("enterprise:operator:enterpriseOperator:edit")
	@RequestMapping(value = "save")
	public String save(EnterpriseOperator enterpriseOperator, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, enterpriseOperator)){
			return form(enterpriseOperator, model);
		}
		enterpriseOperatorService.save(enterpriseOperator);
		addMessage(redirectAttributes, "保存企业经营者信息成功");
		return "redirect:"+Global.getAdminPath()+"/enterprise/operator/enterpriseOperator/?repage";
	}
	
	@RequiresPermissions("enterprise:operator:enterpriseOperator:edit")
	@RequestMapping(value = "delete")
	public String delete(EnterpriseOperator enterpriseOperator, RedirectAttributes redirectAttributes) {
		enterpriseOperatorService.delete(enterpriseOperator);
		addMessage(redirectAttributes, "删除企业经营者信息成功");
		return "redirect:"+Global.getAdminPath()+"/enterprise/operator/enterpriseOperator/?repage";
	}
	
	@RequestMapping(value = "read")
	public String read(EnterpriseOperator enterpriseOperator, Model model) {
		model.addAttribute("enterpriseOperator", enterpriseOperator);
		return "modules/enterprise/operator/enterpriseOperatorRead";
	}

}