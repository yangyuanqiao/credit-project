/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.service.gradeoptions;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.grade.entity.gradeoptions.GradeOption;
import com.amass.credit.modules.grade.dao.gradeoptions.GradeOptionDao;

/**
 * 评分选项值Service
 * @author yangqy
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class GradeOptionService extends CrudService<GradeOptionDao, GradeOption> {

	public GradeOption get(String id) {
		return super.get(id);
	}
	
	public List<GradeOption> findList(GradeOption gradeOption) {
		return super.findList(gradeOption);
	}
	
	public Page<GradeOption> findPage(Page<GradeOption> page, GradeOption gradeOption) {
		return super.findPage(page, gradeOption);
	}
	
	@Transactional(readOnly = false)
	public void save(GradeOption gradeOption) {
		super.save(gradeOption);
	}
	
	@Transactional(readOnly = false)
	public void delete(GradeOption gradeOption) {
		super.delete(gradeOption);
	}
	
}