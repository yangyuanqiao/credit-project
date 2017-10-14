/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.announce.dao.content;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.announce.entity.content.AnnounceContent;

/**
 * 公示内容DAO接口
 * @author yangyq
 * @version 2017-06-23
 */
@MyBatisDao
public interface AnnounceContentDao extends CrudDao<AnnounceContent> {
	
}