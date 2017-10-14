/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.gradelimit.service.category;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.gradelimit.dao.category.GradeLimitDao;
import com.amass.credit.modules.gradelimit.entity.category.GradeLimit;
import com.amass.credit.modules.graderecord.entity.GradeLimitDetailVo;

/**
 * 条件限制Service
 * @author yangyq
 * @version 2017-06-27
 */
@Service
@Transactional(readOnly = true)
public class GradeLimitService extends CrudService<GradeLimitDao, GradeLimit> {

	@Autowired
	public GradeLimitDao limitDao;
	
	public GradeLimit get(String id) {
		GradeLimit gradeLimit = super.get(id);
		return gradeLimit;
	}
	
	public List<GradeLimit> findList(GradeLimit gradeLimit) {
		return super.findList(gradeLimit);
	}
	
	public Page<GradeLimit> findPage(Page<GradeLimit> page, GradeLimit gradeLimit) {
		return super.findPage(page, gradeLimit);
	}
	
	@Transactional(readOnly = false)
	public void save(GradeLimit gradeLimit) {
		super.save(gradeLimit);
	}
	
	@Transactional(readOnly = false)
	public void delete(GradeLimit gradeLimit) {
		super.delete(gradeLimit);
	}
	
	public List<GradeLimit> getGradeLimitList(String subType){
		return limitDao.gradeLimitList(subType);
	}
	
	public List<GradeLimitDetailVo> findLimitDetailList(HashMap<String,String> gradeLimit){
		return limitDao.findLimitDetailList(gradeLimit);
	}
}