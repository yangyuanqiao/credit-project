/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.gradelimit.service.limitrule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.gen.dao.GenDataBaseDictDao;
import com.amass.credit.modules.gen.entity.GenTable;
import com.amass.credit.modules.gen.entity.GenTableColumn;
import com.amass.credit.modules.gradelimit.dao.limitrule.GradeRuleDao;
import com.amass.credit.modules.gradelimit.entity.limitrule.GradeRule;

/**
 * 规则Service
 * @author yangyq
 * @version 2017-06-27
 */
@Service
@Transactional(readOnly = true)
public class GradeRuleService extends CrudService<GradeRuleDao, GradeRule> {

	@Autowired
	private GenDataBaseDictDao genDataBaseDictDao;
	@Autowired
	private GradeRuleDao ruleDao;
	
	public GradeRule get(String id) {
		GradeRule gradeRule = super.get(id);
		return gradeRule;
	}
	
	public List<GradeRule> findList(GradeRule gradeRule) {
		return super.findList(gradeRule);
	}
	
	public Page<GradeRule> findPage(Page<GradeRule> page, GradeRule gradeRule) {
		return super.findPage(page, gradeRule);
	}
	
	@Transactional(readOnly = false)
	public void save(GradeRule gradeRule) {
		super.save(gradeRule);
	}
	
	@Transactional(readOnly = false)
	public void delete(GradeRule gradeRule) {
		super.delete(gradeRule);
	}
	
	public List<GenTableColumn> findTableColumnList(GenTable genTable){
		return genDataBaseDictDao.findTableColumnList(genTable);
	}
	
	public ArrayList findTableColumnMap(String tableName){
		return ruleDao.findTableColumnMap(tableName);
	}
}