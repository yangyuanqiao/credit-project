/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.dao.gradeoptions;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.grade.entity.gradeoptions.GradeOption;

/**
 * 评分选项值DAO接口
 * @author yangqy
 * @version 2017-07-04
 */
@MyBatisDao
public interface GradeOptionDao extends CrudDao<GradeOption> {
	
}