/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.renthouse.dao.leasecred;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.renthouse.entity.leasecred.RenthouseLeasecred;

/**
 * 出租屋租赁信息DAO接口
 * @author yangyq
 * @version 2017-06-23
 */
@MyBatisDao
public interface RenthouseLeasecredDao extends CrudDao<RenthouseLeasecred> {
	public RenthouseLeasecred getRenthouseLeasecred(String subId);
}