/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.web.baseinfo;

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
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.common.web.BaseController;
import com.amass.credit.modules.enterprise.entity.baseinfo.EnterpriseBase;
import com.amass.credit.modules.enterprise.entity.extendinfo.EnterpriseExtend;
import com.amass.credit.modules.enterprise.entity.manage.EnterpriseManage;
import com.amass.credit.modules.enterprise.entity.operator.EnterpriseOperator;
import com.amass.credit.modules.enterprise.service.baseinfo.EnterpriseBaseService;
import com.amass.credit.modules.enterprise.service.extendinfo.EnterpriseExtendService;
import com.amass.credit.modules.enterprise.service.manage.EnterpriseManageService;
import com.amass.credit.modules.enterprise.service.operator.EnterpriseOperatorService;

/**
 * 模具主体信息Controller
 * @author yangyq
 * @version 2017-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/enterprise/baseinfo/enterpriseBase")
public class EnterpriseBaseController extends BaseController {

	@Autowired
	private EnterpriseBaseService enterpriseBaseService;
	
	@Autowired
	private EnterpriseOperatorService enterpriseOptServece;
	
	@Autowired
	private EnterpriseManageService enterpriseManageServece;
	
	@Autowired
	private EnterpriseExtendService enterpriseExtendServece;
	
	@ModelAttribute
	public EnterpriseBase get(@RequestParam(required=false) String id) {
		EnterpriseBase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = enterpriseBaseService.get(id);
		}
		if (entity == null){
			entity = new EnterpriseBase();
		}
		return entity;
	}
	
	@RequiresPermissions("enterprise:baseinfo:enterpriseBase:view")
	@RequestMapping(value = {"list", ""})
	public String list(EnterpriseBase enterpriseBase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EnterpriseBase> page = enterpriseBaseService.findPage(new Page<EnterpriseBase>(request, response), enterpriseBase); 
		model.addAttribute("page", page);
		return "modules/enterprise/baseinfo/enterpriseBaseList";
	}

	@RequiresPermissions("enterprise:baseinfo:enterpriseBase:view")
	@RequestMapping(value = "form")
	public String form(EnterpriseBase enterpriseBase, Model model) {
		model.addAttribute("enterpriseBase", enterpriseBase);
		return "modules/enterprise/baseinfo/enterpriseBaseForm";
	}

	@RequiresPermissions("enterprise:baseinfo:enterpriseBase:edit")
	@RequestMapping(value = "save")
	public String save(EnterpriseBase enterpriseBase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, enterpriseBase)){
			return form(enterpriseBase, model);
		}
		enterpriseBaseService.save(enterpriseBase);
		addMessage(redirectAttributes, "保存模具主体信息成功");
		return "redirect:"+Global.getAdminPath()+"/enterprise/baseinfo/enterpriseBase/?repage";
	}
	
	@RequiresPermissions("enterprise:baseinfo:enterpriseBase:edit")
	@RequestMapping(value = "delete")
	public String delete(EnterpriseBase enterpriseBase, RedirectAttributes redirectAttributes) {
		enterpriseBaseService.delete(enterpriseBase);
		addMessage(redirectAttributes, "删除模具主体信息成功");
		return "redirect:"+Global.getAdminPath()+"/enterprise/baseinfo/enterpriseBase/?repage";
	}

	
	@RequiresPermissions("enterprise:baseinfo:enterpriseBase:view")
	@RequestMapping(value = "detailInfo")
	public String detailInfo(EnterpriseBase enterpriseBase, Model model,HttpServletRequest request) {
		String type= request.getParameter("type");
		model.addAttribute("enterpriseBase", enterpriseBase);
		if("opt".equals(type)){
			EnterpriseOperator operator = enterpriseOptServece.getOneBySubId(enterpriseBase.getId());
			model.addAttribute("operator",operator);
			return "modules/enterprise/baseinfo/operatorDetail";
		}else if ("man".equals(type)){
			EnterpriseManage manage = new EnterpriseManage();
			manage.setSubId(Long.parseLong(enterpriseBase.getId()));
			List<EnterpriseManage> manageList = enterpriseManageServece.findList(manage);
			if(null != manageList && !manageList.isEmpty()){
				model.addAttribute("manage",manageList.get(0));
			}
			return "modules/enterprise/baseinfo/managerDetail";
		}else{
			List<EnterpriseExtend> enterpriseExtendList = enterpriseExtendServece.getALLBySubId(enterpriseBase.getId());
			model.addAttribute("enterpriseExtendList",enterpriseExtendList);
			return "modules/enterprise/baseinfo/extendInfoDetail";
		}
		
	}
}