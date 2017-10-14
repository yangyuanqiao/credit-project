/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.objection.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.objection.entity.GradeObjection;
import com.amass.credit.modules.objection.dao.GradeObjectionDao;

/**
 * 异议申请Service
 * @author yangyq
 * @version 2017-07-07
 */
@Service
@Transactional(readOnly = true)
public class GradeObjectionService extends CrudService<GradeObjectionDao, GradeObjection> {

	public GradeObjection get(String id) {
		return super.get(id);
	}
	
	public List<GradeObjection> findList(GradeObjection gradeObjection) {
		return super.findList(gradeObjection);
	}
	
	public Page<GradeObjection> findPage(Page<GradeObjection> page, GradeObjection gradeObjection) {
		return super.findPage(page, gradeObjection);
	}
	
	@Transactional(readOnly = false)
	public void save(GradeObjection gradeObjection) {
		super.save(gradeObjection);
	}
	
	@Transactional(readOnly = false)
	public void delete(GradeObjection gradeObjection) {
		super.delete(gradeObjection);
	}
	
}