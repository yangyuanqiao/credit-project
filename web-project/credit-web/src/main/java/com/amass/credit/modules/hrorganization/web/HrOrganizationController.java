/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.hrorganization.web;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.amass.credit.common.config.Global;
import com.amass.credit.common.web.BaseController;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.modules.hrorganization.entity.HrOrganization;
import com.amass.credit.modules.hrorganization.service.HrOrganizationService;

/**
 * 社区组织Controller
 * @author yangyq
 * @version 2017-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/hrorganization/hrOrganization")
public class HrOrganizationController extends BaseController {

	@Autowired
	private HrOrganizationService hrOrganizationService;
	
	@ModelAttribute
	public HrOrganization get(@RequestParam(required=false) String id) {
		HrOrganization entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hrOrganizationService.get(id);
		}
		if (entity == null){
			entity = new HrOrganization();
		}
		return entity;
	}
	
	@RequiresPermissions("hrorganization:hrOrganization:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrOrganization hrOrganization, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<HrOrganization> list = hrOrganizationService.findList(hrOrganization); 
		model.addAttribute("list", list);
		return "modules/hrorganization/hrOrganizationList";
	}

	@RequiresPermissions("hrorganization:hrOrganization:view")
	@RequestMapping(value = "form")
	public String form(HrOrganization hrOrganization, Model model) {
		if (hrOrganization.getParent()!=null && StringUtils.isNotBlank(hrOrganization.getParent().getId())){
			hrOrganization.setParent(hrOrganizationService.get(hrOrganization.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(hrOrganization.getId())){
				HrOrganization hrOrganizationChild = new HrOrganization();
				hrOrganizationChild.setParent(new HrOrganization(hrOrganization.getParent().getId()));
				List<HrOrganization> list = hrOrganizationService.findList(hrOrganization); 
				if (list.size() > 0){
					hrOrganization.setSort(list.get(list.size()-1).getSort());
					if (hrOrganization.getSort() != null){
						hrOrganization.setSort(hrOrganization.getSort() + 30);
					}
				}
			}
		}
		if (hrOrganization.getSort() == null){
			hrOrganization.setSort(30);
		}
		model.addAttribute("hrOrganization", hrOrganization);
		return "modules/hrorganization/hrOrganizationForm";
	}

	@RequiresPermissions("hrorganization:hrOrganization:edit")
	@RequestMapping(value = "save")
	public String save(HrOrganization hrOrganization, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hrOrganization)){
			return form(hrOrganization, model);
		}
		hrOrganizationService.save(hrOrganization);
		addMessage(redirectAttributes, "保存社区组织管理成功");
		return "redirect:"+Global.getAdminPath()+"/hrorganization/hrOrganization/?repage";
	}
	
	@RequiresPermissions("hrorganization:hrOrganization:edit")
	@RequestMapping(value = "delete")
	public String delete(HrOrganization hrOrganization, RedirectAttributes redirectAttributes) {
		hrOrganizationService.delete(hrOrganization);
		addMessage(redirectAttributes, "删除社区组织管理成功");
		return "redirect:"+Global.getAdminPath()+"/hrorganization/hrOrganization/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<HrOrganization> list = hrOrganizationService.findList(new HrOrganization());
		for (int i=0; i<list.size(); i++){
			HrOrganization e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}