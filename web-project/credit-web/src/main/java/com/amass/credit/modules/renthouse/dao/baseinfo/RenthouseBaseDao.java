/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.renthouse.dao.baseinfo;

import java.util.List;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.license.entity.SubjectLicense;
import com.amass.credit.modules.renthouse.entity.baseinfo.RenthouseBase;
import com.amass.credit.modules.renthouse.entity.leasecred.RenthouseLeasecred;

/**
 * 出租屋基础信息DAO接口
 * @author yangyq
 * @version 2017-06-22
 */
@MyBatisDao
public interface RenthouseBaseDao extends CrudDao<RenthouseBase> {
	
	public List<RenthouseBase> findAllRentHouseList(RenthouseBase baseInfo) ;
}