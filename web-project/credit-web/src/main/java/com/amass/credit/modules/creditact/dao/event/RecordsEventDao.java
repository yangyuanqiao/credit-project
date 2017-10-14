/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.creditact.dao.event;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.creditact.entity.event.RecordsEvent;

/**
 * 事件行为信息DAO接口
 * @author yangyq
 * @version 2017-06-21
 */
@MyBatisDao
public interface RecordsEventDao extends CrudDao<RecordsEvent> {
	
}