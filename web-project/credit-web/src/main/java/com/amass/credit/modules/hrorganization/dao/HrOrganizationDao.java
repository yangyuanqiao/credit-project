/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.hrorganization.dao;

import com.amass.credit.common.persistence.TreeDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.hrorganization.entity.HrOrganization;

/**
 * 社区组织DAO接口
 * @author yangyq
 * @version 2017-04-25
 */
@MyBatisDao
public interface HrOrganizationDao extends TreeDao<HrOrganization> {
	
}