/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.djorg.web;

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
import com.amass.credit.common.web.BaseController;
import com.amass.credit.common.utils.Str;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.modules.djorg.entity.DjOrganization;
import com.amass.credit.modules.djorg.service.DjOrganizationService;
import com.amass.credit.modules.sys.entity.User;
import com.amass.credit.modules.sys.utils.UserUtils;
import com.amass.credit.common.config.Global;

/**
 * 党组织Controller
 * @author lzw
 * @version 2015-07-28
 */
@Controller
@RequestMapping(value = "${adminPath}/djorg/djOrganization")
public class DjOrganizationController extends BaseController {

	@Autowired
	private DjOrganizationService djOrganizationService;
	
	@ModelAttribute
	public DjOrganization get(@RequestParam(required=false) String id) {
		DjOrganization entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = djOrganizationService.get(id);
		}
		if (entity == null){
			entity = new DjOrganization();
		}
		return entity;
	}
	
	@RequiresPermissions("djorg:djOrganization:view")
	@RequestMapping(value = {"list", ""})
	public String list(DjOrganization djOrganization, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<DjOrganization> list = djOrganizationService.findList(djOrganization); 
		model.addAttribute("list", list);
		return "modules/djorg/djOrganizationList";
	}

	@RequiresPermissions("djorg:djOrganization:view")
	@RequestMapping(value = "form")
	public String form(DjOrganization djOrganization, Model model) {
		if (djOrganization.getParent()!=null && StringUtils.isNotBlank(djOrganization.getParent().getId())){
			User user = UserUtils.getUser();
			djOrganization.setParent(djOrganizationService.get(djOrganization.getParent().getId()));
			if(djOrganization.getArea()==null){
				djOrganization.setArea(user.getDjorg().getArea());
			}
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(djOrganization.getId())){
				DjOrganization djOrganizationChild = new DjOrganization();
				djOrganizationChild.setParent(new DjOrganization(djOrganization.getParent().getId()));
				List<DjOrganization> list = djOrganizationService.findList(djOrganization); 
				if (list.size() > 0){
					djOrganization.setSort(list.get(list.size()-1).getSort());
					if (djOrganization.getSort() != null){
						djOrganization.setSort(djOrganization.getSort() + 30);
					}
				}
			}
		}
		if (djOrganization.getSort() == null){
			djOrganization.setSort(30);
		}
		model.addAttribute("djOrganization", djOrganization);
		return "modules/djorg/djOrganizationForm";
	}

	@RequiresPermissions("djorg:djOrganization:edit")
	@RequestMapping(value = "save")
	public String save(DjOrganization djOrganization, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, djOrganization)){
			return form(djOrganization, model);
		}			
		if(Str.IsEmpty(djOrganization.getId())){
			//设为新记录
			djOrganization.setIsNewRecord(true);
		}
		djOrganizationService.save(djOrganization);
		addMessage(redirectAttributes, "保存党组织成功");
		return "redirect:"+Global.getAdminPath()+"/djorg/djOrganization/?repage";
	}
	
	@RequiresPermissions("djorg:djOrganization:edit")
	@RequestMapping(value = "delete")
	public String delete(DjOrganization djOrganization, RedirectAttributes redirectAttributes) {
		djOrganizationService.delete(djOrganization);
		addMessage(redirectAttributes, "删除党组织成功");
		return "redirect:"+Global.getAdminPath()+"/djorg/djOrganization/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<DjOrganization> list = djOrganizationService.findList(isAll); 
		for (int i=0; i<list.size(); i++){
			DjOrganization e = list.get(i);
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