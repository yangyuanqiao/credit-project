/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.announce.web.scope;

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
import com.amass.credit.modules.announce.entity.content.AnnounceContent;
import com.amass.credit.modules.announce.entity.scope.AnnounceScope;
import com.amass.credit.modules.announce.service.content.AnnounceContentService;
import com.amass.credit.modules.announce.service.scope.AnnounceScopeService;

/**
 * 公示范围Controller
 * @author yangyq
 * @version 2017-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/announce/scope/announceScope")
public class AnnounceScopeController extends BaseController {

	@Autowired
	private AnnounceScopeService announceScopeService;
	
	@Autowired
	private AnnounceContentService announceContentService;
	
	@ModelAttribute
	public AnnounceScope get(@RequestParam(required=false) String id) {
		AnnounceScope entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = announceScopeService.get(id);
		}
		if (entity == null){
			entity = new AnnounceScope();
		}
		return entity;
	}
	
	@RequiresPermissions("announce:scope:announceScope:view")
	@RequestMapping(value = {"list", ""})
	public String list(AnnounceScope announceScope, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AnnounceScope> page = announceScopeService.findPage(new Page<AnnounceScope>(request, response), announceScope); 
		model.addAttribute("page", page);
		return "modules/announce/scope/announceScopeList";
	}

	@RequiresPermissions("announce:scope:announceScope:view")
	@RequestMapping(value = "form")
	public String form(AnnounceScope announceScope, Model model) {
		model.addAttribute("announceScope", announceScope);
		
		model.addAttribute("contentList", announceContentService.findList(new AnnounceContent()));
		return "modules/announce/scope/announceScopeForm";
	}

	@RequiresPermissions("announce:scope:announceScope:edit")
	@RequestMapping(value = "save")
	public String save(AnnounceScope announceScope, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, announceScope)){
			return form(announceScope, model);
		}
		announceScopeService.save(announceScope);
		addMessage(redirectAttributes, "保存公示范围成功");
		return "redirect:"+Global.getAdminPath()+"/announce/scope/announceScope/?repage";
	}
	
	@RequiresPermissions("announce:scope:announceScope:edit")
	@RequestMapping(value = "delete")
	public String delete(AnnounceScope announceScope, RedirectAttributes redirectAttributes) {
		announceScopeService.delete(announceScope);
		addMessage(redirectAttributes, "删除公示范围成功");
		return "redirect:"+Global.getAdminPath()+"/announce/scope/announceScope/?repage";
	}

}