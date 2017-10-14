/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.service.operator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.enterprise.entity.operator.EnterpriseOperator;
import com.amass.credit.modules.enterprise.dao.operator.EnterpriseOperatorDao;

/**
 * 企业经营者信息Service
 * @author yangyq
 * @version 2017-06-20
 */
@Service
@Transactional(readOnly = true)
public class EnterpriseOperatorService extends CrudService<EnterpriseOperatorDao, EnterpriseOperator> {

	@Autowired
	EnterpriseOperatorDao opertatorDao;
	
	public EnterpriseOperator get(String id) {
		return super.get(id);
	}
	
	public List<EnterpriseOperator> findList(EnterpriseOperator enterpriseOperator) {
		return super.findList(enterpriseOperator);
	}
	
	public EnterpriseOperator getOneBySubId(String subId){
		return opertatorDao.getOneBySubId(subId);
	}
	
	public Page<EnterpriseOperator> findPage(Page<EnterpriseOperator> page, EnterpriseOperator enterpriseOperator) {
		return super.findPage(page, enterpriseOperator);
	}
	
	@Transactional(readOnly = false)
	public void save(EnterpriseOperator enterpriseOperator) {
		super.save(enterpriseOperator);
	}
	
	@Transactional(readOnly = false)
	public void delete(EnterpriseOperator enterpriseOperator) {
		super.delete(enterpriseOperator);
	}
	
}