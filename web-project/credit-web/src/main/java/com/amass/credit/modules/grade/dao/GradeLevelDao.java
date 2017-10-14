/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.dao;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.grade.entity.GradeLevel;

/**
 * 信用等级信息管理DAO接口
 * @author yangqy
 * @version 2017-06-20
 */
@MyBatisDao
public interface GradeLevelDao extends CrudDao<GradeLevel> {
	
}