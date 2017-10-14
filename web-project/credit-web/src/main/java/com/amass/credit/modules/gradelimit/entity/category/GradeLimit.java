/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.gradelimit.entity.category;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;
import com.amass.credit.modules.grade.entity.GradeLevel;
import com.amass.credit.modules.quartz.entity.GradeRules;

/**
 * 条件限制Entity
 * @author yangyq
 * @version 2017-06-27
 */
public class GradeLimit extends DataEntity<GradeLimit> {
	
	private static final long serialVersionUID = 1L;
	private String subType;		// 主体类别
	private String conditName;		// 条件名称
	private String conditCode;		// 条件类别代码
	private String conditDesc;		// 条件类别描述
	private String levelId;		// 信用限制等级
	private String conditSort;		// 优先级别
	
	private GradeLevel gradeLevel;
	
	private List<GradeRules> gradeRule;
	

	public List<GradeRules> getGradeRule() {
		return gradeRule;
	}

	public void setGradeRule(List<GradeRules> gradeRule) {
		this.gradeRule = gradeRule;
	}

	public GradeLevel getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(GradeLevel gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public GradeLimit() {
		super();
	}

	public GradeLimit(String id){
		super(id);
	}

	@Length(min=0, max=40, message="主体类别长度必须介于 0 和 40 之间")
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	@Length(min=0, max=40, message="条件名称长度必须介于 0 和 40 之间")
	public String getConditName() {
		return conditName;
	}

	public void setConditName(String conditName) {
		this.conditName = conditName;
	}
	
	@Length(min=0, max=40, message="条件类别代码长度必须介于 0 和 40 之间")
	public String getConditCode() {
		return conditCode;
	}

	public void setConditCode(String conditCode) {
		this.conditCode = conditCode;
	}
	
	@Length(min=0, max=250, message="条件类别描述长度必须介于 0 和 250 之间")
	public String getConditDesc() {
		return conditDesc;
	}

	public void setConditDesc(String conditDesc) {
		this.conditDesc = conditDesc;
	}
	
	
	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	@Length(min=0, max=40, message="优先级别长度必须介于 0 和 40 之间")
	public String getConditSort() {
		return conditSort;
	}

	public void setConditSort(String conditSort) {
		this.conditSort = conditSort;
	}
	
}