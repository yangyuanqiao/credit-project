/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.graderecord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.graderecord.entity.GradeRecord;
import com.amass.credit.modules.graderecord.entity.GradeRecordDetailVo;
import com.amass.credit.modules.graderecord.dao.GradeRecordDao;

/**
 * 评分记录信息Service
 * @author yangyq
 * @version 2017-06-19
 */
@Service
@Transactional(readOnly = true)
public class GradeRecordService extends CrudService<GradeRecordDao, GradeRecord> {

	@Autowired
	private GradeRecordDao gradeRecordDao;
	
	
	public GradeRecord get(String id) {
		return super.get(id);
	}
	
	public List<GradeRecord> findList(GradeRecord gradeRecord) {
		return super.findList(gradeRecord);
	}
	
	public Page<GradeRecord> findPage(Page<GradeRecord> page, GradeRecord gradeRecord) {
		return super.findPage(page, gradeRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(GradeRecord gradeRecord) {
		super.save(gradeRecord);
	}
	@Transactional(readOnly = false)
	public void update(GradeRecord gradeRecord){
		gradeRecordDao.update(gradeRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(GradeRecord gradeRecord) {
		super.delete(gradeRecord);
	}
	@Transactional(readOnly = false)
	public List<GradeRecordDetailVo> getDetailList(GradeRecord gradeRecord){
		return gradeRecordDao.selectGradeRdDetail(gradeRecord);
	}
	
	
	public Page<GradeRecord> findHouseGradeRecordPage(Page<GradeRecord> page, GradeRecord gradeRecord) {
		gradeRecord.setPage(page);
		page.setList(dao.findHouseGradeRecordList(gradeRecord));
		return page;
	}
	
	
}