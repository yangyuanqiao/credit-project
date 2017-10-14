/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.sys.dao.subpro;

import java.util.List;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.sys.entity.subpro.SubProperty;
import com.amass.credit.modules.sys.entity.subpro.SubPropertyVo;

/**
 * 主体属性信息DAO接口
 * @author yangyq
 * @version 2017-08-16
 */
@MyBatisDao
public interface SubPropertyDao extends CrudDao<SubProperty> {
	public List <SubPropertyVo >findListBySubId(SubPropertyVo vo);
}