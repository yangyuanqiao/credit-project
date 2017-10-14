/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.announce.web.content;

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
import com.amass.credit.modules.announce.service.content.AnnounceContentService;

/**
 * 公示内容Controller
 * @author yangyq
 * @version 2017-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/announce/content/announceContent")
public class AnnounceContentController extends BaseController {

	@Autowired
	private AnnounceContentService announceContentService;
	
	@ModelAttribute
	public AnnounceContent get(@RequestParam(required=false) String id) {
		AnnounceContent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = announceContentService.get(id);
		}
		if (entity == null){
			entity = new AnnounceContent();
		}
		return entity;
	}
	
	@RequiresPermissions("announce:content:announceContent:view")
	@RequestMapping(value = {"list", ""})
	public String list(AnnounceContent announceContent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AnnounceContent> page = announceContentService.findPage(new Page<AnnounceContent>(request, response), announceContent); 
		model.addAttribute("page", page);
		return "modules/announce/content/announceContentList";
	}

	@RequiresPermissions("announce:content:announceContent:view")
	@RequestMapping(value = "form")
	public String form(AnnounceContent announceContent, Model model) {
		model.addAttribute("announceContent", announceContent);
		return "modules/announce/content/announceContentForm";
	}

	@RequiresPermissions("announce:content:announceContent:edit")
	@RequestMapping(value = "save")
	public String save(AnnounceContent announceContent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, announceContent)){
			return form(announceContent, model);
		}
		announceContentService.save(announceContent);
		addMessage(redirectAttributes, "保存公示内容成功");
		return "redirect:"+Global.getAdminPath()+"/announce/content/announceContent/?repage";
	}
	
	@RequiresPermissions("announce:content:announceContent:edit")
	@RequestMapping(value = "delete")
	public String delete(AnnounceContent announceContent, RedirectAttributes redirectAttributes) {
		announceContentService.delete(announceContent);
		addMessage(redirectAttributes, "删除公示内容成功");
		return "redirect:"+Global.getAdminPath()+"/announce/content/announceContent/?repage";
	}

}