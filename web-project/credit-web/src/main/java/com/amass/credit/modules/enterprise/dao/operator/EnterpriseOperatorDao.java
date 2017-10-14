/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.dao.operator;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.enterprise.entity.operator.EnterpriseOperator;

/**
 * 企业经营者信息DAO接口
 * @author yangyq
 * @version 2017-06-20
 */
@MyBatisDao
public interface EnterpriseOperatorDao extends CrudDao<EnterpriseOperator> {
	
	public EnterpriseOperator getOneBySubId(String subId);
	
}