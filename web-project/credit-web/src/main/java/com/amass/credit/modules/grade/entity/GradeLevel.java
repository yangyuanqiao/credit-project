/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.entity;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 信用等级信息管理Entity
 * @author yangqy
 * @version 2017-06-20
 */
public class GradeLevel extends DataEntity<GradeLevel> {
	
	private static final long serialVersionUID = 1L;
	private String subjectType;		// 主体类型 参考数据字典 SUB_TYPE
	private String levelName;		// 信用等级名称
	private String levelCode;		// 信用等级代码
	private String minScore;		// 最低分值
	private String maxScore;		// 最高分值
	private String remark;		// 备注
	private Long sort;		// 排序
	
	public GradeLevel() {
		super();
	}

	public GradeLevel(String id){
		super(id);
	}

	@Length(min=1, max=40, message="主体类型 参考数据字典 SUB_TYPE长度必须介于 1 和 40 之间")
	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	
	@Length(min=1, max=40, message="信用等级名称长度必须介于 1 和 40 之间")
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	@Length(min=0, max=40, message="信用等级代码长度必须介于 0 和 40 之间")
	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	
	@Length(min=1, max=10, message="最低分值长度必须介于 1 和 10 之间")
	public String getMinScore() {
		return minScore;
	}

	public void setMinScore(String minScore) {
		this.minScore = minScore;
	}
	
	@Length(min=1, max=10, message="最高分值长度必须介于 1 和 10 之间")
	public String getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}
	
	@Length(min=0, max=400, message="备注长度必须介于 0 和 400 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	
	
}