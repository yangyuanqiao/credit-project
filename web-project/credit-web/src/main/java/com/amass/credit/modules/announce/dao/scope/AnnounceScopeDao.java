/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.announce.dao.scope;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.announce.entity.scope.AnnounceScope;

/**
 * 公示范围DAO接口
 * @author yangyq
 * @version 2017-06-23
 */
@MyBatisDao
public interface AnnounceScopeDao extends CrudDao<AnnounceScope> {
	
}