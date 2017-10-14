/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.dao.extendinfo;

import java.util.List;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.enterprise.entity.extendinfo.EnterpriseExtend;

/**
 * 主体扩展信息DAO接口
 * @author yangyq
 * @version 2017-06-21
 */
@MyBatisDao
public interface EnterpriseExtendDao extends CrudDao<EnterpriseExtend> {
	public List<EnterpriseExtend>  getALLBySubId(String subId);
}