/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.cow.dao;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.cow.entity.CrdCowOperation;

/**
 * 牛行经营场所DAO接口
 * @author yangyq
 * @version 2017-08-18
 */
@MyBatisDao
public interface CrdCowOperationDao extends CrudDao<CrdCowOperation> {
	
}