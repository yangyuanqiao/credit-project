/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.service.manage;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.enterprise.entity.manage.EnterpriseManage;
import com.amass.credit.modules.enterprise.dao.manage.EnterpriseManageDao;

/**
 * 企业经营管理信息Service
 * @author yangyq
 * @version 2017-06-20
 */
@Service
@Transactional(readOnly = true)
public class EnterpriseManageService extends CrudService<EnterpriseManageDao, EnterpriseManage> {

	public EnterpriseManage get(String id) {
		return super.get(id);
	}
	
	public List<EnterpriseManage> findList(EnterpriseManage enterpriseManage) {
		return super.findList(enterpriseManage);
	}
	
	public Page<EnterpriseManage> findPage(Page<EnterpriseManage> page, EnterpriseManage enterpriseManage) {
		return super.findPage(page, enterpriseManage);
	}
	
	@Transactional(readOnly = false)
	public void save(EnterpriseManage enterpriseManage) {
		super.save(enterpriseManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(EnterpriseManage enterpriseManage) {
		super.delete(enterpriseManage);
	}
	
}