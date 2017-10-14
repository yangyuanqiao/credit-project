/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.dao.category;

import java.util.List;

import com.amass.credit.common.persistence.TreeDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.grade.entity.category.GradeCategory;

/**
 * 评分选项类别DAO接口
 * @author yangqy
 * @version 2017-06-21
 */
@MyBatisDao
public interface GradeCategoryDao extends TreeDao<GradeCategory> {
	public List<GradeCategory> findGrade(GradeCategory gradeCategory);
}