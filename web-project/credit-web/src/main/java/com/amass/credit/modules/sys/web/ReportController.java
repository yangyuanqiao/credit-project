package com.amass.credit.modules.sys.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amass.credit.common.config.Global;
import com.amass.credit.common.web.BaseController;
import com.amass.credit.modules.sys.entity.User;
import com.amass.credit.modules.sys.utils.UserUtils;


/**
 * Report Controller
 * @author yangyq
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/report")
public class ReportController extends BaseController {
	
	private static final String IP_PORT = Global.getConfig("statistical.server");
//	private static final String IP_PORT = "http://183.238.134.214:19999/";
//	private static final String IP_PORT = "http://hjzongwang.com:9999/";
	//http://192.168.1.246:8088/WebReport/ReportServer?reportlet=ccms_report%2Fother%2Fhrms_mould_house.cpt&op=view
	//http/192.168.1.246:8088/WebReport/ReportServer?reportlet=ccms_report/other/hrms_mould_house.cpt&op=view&username=%E7%B3%BB%E7%BB%9F%E7%AE%A1%E7%90%86%E5%91%98
	private static final String SG_REPORT_PREFIX_URL = 
			IP_PORT+"/WebReport/ReportServer?reportlet=ccms_report%2Fother%2";
   private static final String SG_REPORT_SUFFIX_URL = ".cpt&op=view&username=";
   
	
	public String getName() {
		return UserUtils.getUser().getName();
	}

/**
    * 
    * @param model
    * @return
    */

	@RequestMapping(value="/jumpage")
	public String jumpPage(Model model) {
		return "modules/sys/datacountFrameset";
	}
	
	/**
	 * 统计提醒
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "datacount")
	public String datacount(Model model) {
		User user = UserUtils.getUser();
		model.addAttribute("user", user);
		//model.addAttribute("statistUrl", SG_REPORT_PREFIX_URL+"Fhrms_mould_house"+SG_REPORT_SUFFIX_URL+getName());
		return "modules/sys/datacount";
	}


	
	
}