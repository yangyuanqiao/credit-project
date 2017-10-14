/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.gradelimit.dao.detail;

import java.util.List;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.gradelimit.entity.detail.GradeLimitDetail;

/**
 * 条件明细DAO接口
 * @author yangyq
 * @version 2017-06-29
 */
@MyBatisDao
public interface GradeLimitDetailDao extends CrudDao<GradeLimitDetail> {
	
	public int insertBatch(List<GradeLimitDetail> GradeLimitDetailList);
	
}