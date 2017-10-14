/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.graderecord.dao;

import java.util.List;

import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.graderecord.entity.GradeRecord;
import com.amass.credit.modules.graderecord.entity.GradeRecordDetailVo;

/**
 * 评分记录信息DAO接口
 * @author yangyq
 * @version 2017-06-19
 */
@MyBatisDao
public interface GradeRecordDao extends CrudDao<GradeRecord> {
	
	public List<GradeRecordDetailVo>  selectGradeRdDetail(GradeRecord record);
	
	public List<GradeRecord> findHouseGradeRecordList(GradeRecord entity);
	
}