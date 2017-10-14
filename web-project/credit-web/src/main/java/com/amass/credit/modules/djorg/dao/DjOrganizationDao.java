/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.djorg.dao;

import com.amass.credit.common.persistence.TreeDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.djorg.entity.DjOrganization;

/**
 * 组织DAO接口
 * @author lzw
 * @version 2015-07-28
 */
@MyBatisDao
public interface DjOrganizationDao extends TreeDao<DjOrganization> {
	
}