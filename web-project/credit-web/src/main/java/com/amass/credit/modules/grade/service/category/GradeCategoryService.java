/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.service.TreeService;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.modules.grade.entity.category.GradeCategory;
import com.amass.credit.modules.sys.utils.UserUtils;
import com.amass.credit.modules.grade.dao.category.GradeCategoryDao;

/**
 * 评分选项类别Service
 * @author yangqy
 * @version 2017-06-21
 */
@Service
@Transactional(readOnly = true)
public class GradeCategoryService extends TreeService<GradeCategoryDao, GradeCategory> {

	@Autowired
	private GradeCategoryDao gradeCategoryDao;
	
	public GradeCategory get(String id) {
		return super.get(id);
	}
	
	public List<GradeCategory> findList(GradeCategory gradeCategory) {
		if (StringUtils.isNotBlank(gradeCategory.getParentIds())){
			gradeCategory.setParentIds(","+gradeCategory.getParentIds()+",");
		}
		return super.findList(gradeCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(GradeCategory gradeCategory) {
		super.save(gradeCategory);
		UserUtils.removeCache(UserUtils.CACHE_DJORG_LIST);
		UserUtils.removeCache(UserUtils.CACHE_DJORG_LIST + UserUtils.getUser().getId());
	}
	
	@Transactional(readOnly = false)
	public void delete(GradeCategory gradeCategory) {
		super.delete(gradeCategory);
		UserUtils.removeCache(UserUtils.CACHE_DJORG_LIST);
		UserUtils.removeCache(UserUtils.CACHE_DJORG_LIST + UserUtils.getUser().getId());
	}
	@Transactional(readOnly = false)
	public List<GradeCategory> findGrade(GradeCategory gradeCategory) {
		return gradeCategoryDao.findGrade(gradeCategory);
	}
	
}