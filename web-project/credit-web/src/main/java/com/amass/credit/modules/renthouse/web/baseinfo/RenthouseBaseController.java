/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.renthouse.web.baseinfo;

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
import com.amass.credit.common.web.BaseController;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.modules.enterprise.entity.baseinfo.EnterpriseBase;
import com.amass.credit.modules.enterprise.entity.extendinfo.EnterpriseExtend;
import com.amass.credit.modules.enterprise.entity.operator.EnterpriseOperator;
import com.amass.credit.modules.license.entity.SubjectLicense;
import com.amass.credit.modules.renthouse.entity.baseinfo.RenthouseBase;
import com.amass.credit.modules.renthouse.entity.leasecred.RenthouseLeasecred;
import com.amass.credit.modules.renthouse.service.baseinfo.RenthouseBaseService;

/**
 * 出租屋基础信息Controller
 * @author yangyq
 * @version 2017-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/renthouse/baseinfo/renthouseBase")
public class RenthouseBaseController extends BaseController {

	@Autowired
	private RenthouseBaseService renthouseBaseService;
	
	@ModelAttribute
	public RenthouseBase get(@RequestParam(required=false) String id) {
		RenthouseBase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = renthouseBaseService.get(id);
		}
		if (entity == null){
			entity = new RenthouseBase();
		}
		return entity;
	}
	
	@RequiresPermissions("renthouse:baseinfo:renthouseBase:view")
	@RequestMapping(value = {"list", ""})
	public String list(RenthouseBase renthouseBase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RenthouseBase> page = renthouseBaseService.findPage(new Page<RenthouseBase>(request, response), renthouseBase); 
		model.addAttribute("page", page);
		return "modules/renthouse/baseinfo/renthouseBaseList";
	}

	@RequiresPermissions("renthouse:baseinfo:renthouseBase:view")
	@RequestMapping(value = "form")
	public String form(RenthouseBase renthouseBase, Model model) {
		model.addAttribute("renthouseBase", renthouseBase);
		return "modules/renthouse/baseinfo/renthouseBaseForm";
	}

	@RequiresPermissions("renthouse:baseinfo:renthouseBase:edit")
	@RequestMapping(value = "save")
	public String save(RenthouseBase renthouseBase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, renthouseBase)){
			return form(renthouseBase, model);
		}
		renthouseBaseService.save(renthouseBase);
		addMessage(redirectAttributes, "保存出租屋基础信息成功");
		return "redirect:"+Global.getAdminPath()+"/renthouse/baseinfo/renthouseBase/?repage";
	}
	
	@RequiresPermissions("renthouse:baseinfo:renthouseBase:edit")
	@RequestMapping(value = "delete")
	public String delete(RenthouseBase renthouseBase, RedirectAttributes redirectAttributes) {
		renthouseBaseService.delete(renthouseBase);
		addMessage(redirectAttributes, "删除出租屋基础信息成功");
		return "redirect:"+Global.getAdminPath()+"/renthouse/baseinfo/renthouseBase/?repage";
	}
	
	
	@RequiresPermissions("renthouse:baseinfo:renthouseBase:view")
	@RequestMapping(value = "detailInfo")
	public String detailInfo(RenthouseBase renthouseBase, Model model,HttpServletRequest request) {
		String type= request.getParameter("type");
		model.addAttribute("renthouseBase", renthouseBase);
		if("leasecred".equals(type)){
			RenthouseLeasecred leasecred = renthouseBaseService.getRenthouseLeasecred(renthouseBase.getId());//租赁信息
			model.addAttribute("leasecred",leasecred);
			return "modules/renthouse/baseinfo/leasecredDetail";
		}else if ("extend".equals(type)){
			return "modules/renthouse/baseinfo/renthouseDetail";
		}else if("license".equals(type)){
			List<SubjectLicense> subjectLicense = renthouseBaseService.getSubjectLicense(renthouseBase.getId());//许可证信息
			model.addAttribute("subjectLicense",subjectLicense);
			return "modules/renthouse/baseinfo/licenseDetail";
		}else {
			return "modules/renthouse/baseinfo/renthouseDetail";
		}
		
	}

}