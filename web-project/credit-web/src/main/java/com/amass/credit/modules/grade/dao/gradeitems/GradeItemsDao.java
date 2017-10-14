/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.dao.gradeitems;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.grade.entity.gradeitems.GradeItems;

/**
 * 评分选项管理DAO接口
 * @author yangqy
 * @version 2017-07-04
 */
@MyBatisDao
public interface GradeItemsDao extends CrudDao<GradeItems> {
	
}