/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.hrorganization.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.service.TreeService;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.modules.hrorganization.entity.HrOrganization;
import com.amass.credit.modules.hrorganization.dao.HrOrganizationDao;

/**
 * 社区组织Service
 * @author yangyq
 * @version 2017-04-25
 */
@Service
@Transactional(readOnly = true)
public class HrOrganizationService extends TreeService<HrOrganizationDao, HrOrganization> {

	public HrOrganization get(String id) {
		return super.get(id);
	}
	
	public List<HrOrganization> findList(HrOrganization hrOrganization) {
		if (StringUtils.isNotBlank(hrOrganization.getParentIds())){
			hrOrganization.setParentIds(","+hrOrganization.getParentIds()+",");
		}
		return super.findList(hrOrganization);
	}
	
	@Transactional(readOnly = false)
	public void save(HrOrganization hrOrganization) {
		super.save(hrOrganization);
	}
	
	@Transactional(readOnly = false)
	public void delete(HrOrganization hrOrganization) {
		super.delete(hrOrganization);
	}
	
}