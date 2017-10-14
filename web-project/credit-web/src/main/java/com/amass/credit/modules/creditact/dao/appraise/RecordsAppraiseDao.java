/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.creditact.dao.appraise;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.creditact.entity.appraise.RecordsAppraise;

/**
 * 评价行为信息DAO接口
 * @author yangyq
 * @version 2017-06-21
 */
@MyBatisDao
public interface RecordsAppraiseDao extends CrudDao<RecordsAppraise> {
	
}