/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.grade.entity.GradeLevel;
import com.amass.credit.modules.grade.dao.GradeLevelDao;

/**
 * 信用等级信息管理Service
 * @author yangqy
 * @version 2017-06-20
 */
@Service
@Transactional(readOnly = true)
public class GradeLevelService extends CrudService<GradeLevelDao, GradeLevel> {

	public GradeLevel get(String id) {
		return super.get(id);
	}
	
	public List<GradeLevel> findList(GradeLevel gradeLevel) {
		return super.findList(gradeLevel);
	}
	
	public Page<GradeLevel> findPage(Page<GradeLevel> page, GradeLevel gradeLevel) {
		return super.findPage(page, gradeLevel);
	}
	
	@Transactional(readOnly = false)
	public void save(GradeLevel gradeLevel) {
		super.save(gradeLevel);
	}
	
	@Transactional(readOnly = false)
	public void delete(GradeLevel gradeLevel) {
		super.delete(gradeLevel);
	}
	
}