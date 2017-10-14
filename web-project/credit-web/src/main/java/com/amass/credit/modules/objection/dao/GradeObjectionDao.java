/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.objection.dao;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.objection.entity.GradeObjection;

/**
 * 异议申请DAO接口
 * @author yangyq
 * @version 2017-07-07
 */
@MyBatisDao
public interface GradeObjectionDao extends CrudDao<GradeObjection> {
	
}