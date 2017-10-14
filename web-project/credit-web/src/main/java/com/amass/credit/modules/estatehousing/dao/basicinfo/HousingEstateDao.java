/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.estatehousing.dao.basicinfo;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.estatehousing.entity.basicinfo.HousingEstate;

/**
 * 小区楼盘DAO接口
 * @author yangyq
 * @version 2017-08-15
 */
@MyBatisDao
public interface HousingEstateDao extends CrudDao<HousingEstate> {
	
}