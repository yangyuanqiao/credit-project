/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.service.extendinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.enterprise.entity.extendinfo.EnterpriseExtend;
import com.amass.credit.modules.enterprise.dao.extendinfo.EnterpriseExtendDao;

/**
 * 主体扩展信息Service
 * @author yangyq
 * @version 2017-06-21
 */
@Service
@Transactional(readOnly = true)
public class EnterpriseExtendService extends CrudService<EnterpriseExtendDao, EnterpriseExtend> {
	
	@Autowired
	EnterpriseExtendDao extendDao;

	public EnterpriseExtend get(String id) {
		return super.get(id);
	}
	
	public List<EnterpriseExtend> findList(EnterpriseExtend enterpriseExtend) {
		return super.findList(enterpriseExtend);
	}
	
	public Page<EnterpriseExtend> findPage(Page<EnterpriseExtend> page, EnterpriseExtend enterpriseExtend) {
		return super.findPage(page, enterpriseExtend);
	}
	
	@Transactional(readOnly = false)
	public void save(EnterpriseExtend enterpriseExtend) {
		super.save(enterpriseExtend);
	}
	
	@Transactional(readOnly = false)
	public void delete(EnterpriseExtend enterpriseExtend) {
		super.delete(enterpriseExtend);
	}
	
	public List<EnterpriseExtend> getALLBySubId(String subId){
		return extendDao.getALLBySubId(subId);
	}
	
}