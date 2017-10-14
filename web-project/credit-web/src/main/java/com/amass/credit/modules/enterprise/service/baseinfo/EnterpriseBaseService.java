/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.service.baseinfo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.enterprise.entity.baseinfo.EnterpriseBase;
import com.amass.credit.modules.enterprise.dao.baseinfo.EnterpriseBaseDao;

/**
 * 模具主体信息Service
 * @author yangyq
 * @version 2017-06-20
 */
@Service
@Transactional(readOnly = true)
public class EnterpriseBaseService extends CrudService<EnterpriseBaseDao, EnterpriseBase> {

	public EnterpriseBase get(String id) {
		return super.get(id);
	}
	
	public List<EnterpriseBase> findList(EnterpriseBase enterpriseBase) {
		return super.findList(enterpriseBase);
	}
	
	public Page<EnterpriseBase> findPage(Page<EnterpriseBase> page, EnterpriseBase enterpriseBase) {
		return super.findPage(page, enterpriseBase);
	}
	
	@Transactional(readOnly = false)
	public void save(EnterpriseBase enterpriseBase) {
		super.save(enterpriseBase);
	}
	
	@Transactional(readOnly = false)
	public void delete(EnterpriseBase enterpriseBase) {
		super.delete(enterpriseBase);
	}
	
}