/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.gradelimit.dao.limitrule;

import java.util.ArrayList;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.gradelimit.entity.limitrule.GradeRule;

/**
 * 规则DAO接口
 * @author yangyq
 * @version 2017-06-27
 */
@MyBatisDao
public interface GradeRuleDao extends CrudDao<GradeRule> {
	public ArrayList findTableColumnMap(String tableName);
}