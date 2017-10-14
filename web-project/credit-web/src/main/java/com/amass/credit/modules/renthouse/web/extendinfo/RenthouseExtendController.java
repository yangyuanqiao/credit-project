/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.renthouse.web.extendinfo;

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
import com.amass.credit.modules.renthouse.entity.extendinfo.RenthouseExtend;
import com.amass.credit.modules.renthouse.service.extendinfo.RenthouseExtendService;

/**
 * 出租屋扩展信息Controller
 * @author yangyq
 * @version 2017-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/renthouse/extendinfo/renthouseExtend")
public class RenthouseExtendController extends BaseController {

	@Autowired
	private RenthouseExtendService renthouseExtendService;
	
	@ModelAttribute
	public RenthouseExtend get(@RequestParam(required=false) String id) {
		RenthouseExtend entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = renthouseExtendService.get(id);
		}
		if (entity == null){
			entity = new RenthouseExtend();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(RenthouseExtend renthouseExtend, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RenthouseExtend> page = renthouseExtendService.findPage(new Page<RenthouseExtend>(request, response), renthouseExtend); 
		model.addAttribute("page", page);
		model.addAttribute("renthouseBase", renthouseExtend);
		return "modules/renthouse/extendinfo/renthouseExtendList";
	}

	@RequiresPermissions("renthouse:extendinfo:renthouseExtend:view")
	@RequestMapping(value = "form")
	public String form(RenthouseExtend renthouseExtend, Model model) {
		model.addAttribute("renthouseExtend", renthouseExtend);
		return "modules/renthouse/extendinfo/renthouseExtendForm";
	}

	@RequiresPermissions("renthouse:extendinfo:renthouseExtend:edit")
	@RequestMapping(value = "save")
	public String save(RenthouseExtend renthouseExtend, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, renthouseExtend)){
			return form(renthouseExtend, model);
		}
		renthouseExtendService.save(renthouseExtend);
		addMessage(redirectAttributes, "保存出租屋扩展信息成功");
		return "redirect:"+Global.getAdminPath()+"/renthouse/extendinfo/renthouseExtend/?repage";
	}
	
	@RequiresPermissions("renthouse:extendinfo:renthouseExtend:edit")
	@RequestMapping(value = "delete")
	public String delete(RenthouseExtend renthouseExtend, RedirectAttributes redirectAttributes) {
		renthouseExtendService.delete(renthouseExtend);
		addMessage(redirectAttributes, "删除出租屋扩展信息成功");
		return "redirect:"+Global.getAdminPath()+"/renthouse/extendinfo/renthouseExtend/?repage";
	}

}