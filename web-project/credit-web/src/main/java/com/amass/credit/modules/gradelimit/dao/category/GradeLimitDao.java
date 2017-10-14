/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.gradelimit.dao.category;

import java.util.HashMap;
import java.util.List;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.gradelimit.entity.category.GradeLimit;
import com.amass.credit.modules.gradelimit.service.category.GradeLimitService;
import com.amass.credit.modules.graderecord.entity.GradeLimitDetailVo;
import com.amass.credit.modules.graderecord.entity.GradeRecordDetailVo;

/**
 * 条件限制DAO接口
 * @author yangyq
 * @version 2017-06-27
 */
@MyBatisDao
public interface GradeLimitDao extends CrudDao<GradeLimit> {
	
	public List<GradeLimit> gradeLimitList(String subType);
	
	public List<GradeLimitDetailVo> findLimitDetailList(HashMap<String,String> gradeLimit);
}