/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.license.dao;

import java.util.List;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.license.entity.SubjectLicense;

/**
 * 许可证信息DAO接口
 * @author yangyq
 * @version 2017-06-23
 */
@MyBatisDao
public interface SubjectLicenseDao extends CrudDao<SubjectLicense> {
	public List<SubjectLicense> getSubjectLicense(String subId);
	
}