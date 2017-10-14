/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.graderecord.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;
import com.amass.credit.modules.quartz.entity.EnterpriseBase;
import com.amass.credit.modules.renthouse.entity.baseinfo.RenthouseBase;

/**
 * 评分记录信息Entity
 * @author yangyq
 * @version 2017-06-19
 */
public class GradeRecord extends DataEntity<GradeRecord> {
	
	private static final long serialVersionUID = 1L;
	private String subType;		// 主体类别
	private Long 	subId;		// 主体编号
	private String score;		// 评定分数
	private String status;		// 评定状态
	private String content;		// 评分选项值组合
	private String limitLevel;		// 条件限制等级
	private String finalLevel;		// 最终评定等级
	private String batch;  //批次
	//新增视图的属性
	private String beginTime;
	private String endTime;
	

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	private String scoreLevel;//分数等级
	
	public String getScoreLevel() {
		return scoreLevel;
	}

	public void setScoreLevel(String scoreLevel) {
		this.scoreLevel = scoreLevel;
	}

	private EnterpriseBase enterpriseBase;
	
	public EnterpriseBase getEnterpriseBase() {
		return enterpriseBase;
	}

	public void setEnterpriseBase(EnterpriseBase enterpriseBase) {
		this.enterpriseBase = enterpriseBase;
	}

	private RenthouseBase houseBase;
	
	
	public RenthouseBase getHouseBase() {
		return houseBase;
	}

	public void setHouseBase(RenthouseBase houseBase) {
		this.houseBase = houseBase;
	}

	public GradeRecord() {
		super();
	}

	public GradeRecord(String id){
		super(id);
	}

	@Length(min=1, max=40, message="主体类别长度必须介于 1 和 40 之间")
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	@NotNull(message="主体编号不能为空")
	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}
	
	@NotNull(message="评定分数不能为空")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=0, max=40, message="评定状态长度必须介于 0 和 40 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=500, message="评分选项值组合长度必须介于 0 和 500 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLimitLevel() {
		return limitLevel;
	}

	public void setLimitLevel(String limitLevel) {
		this.limitLevel = limitLevel;
	}

	public String getFinalLevel() {
		return finalLevel;
	}

	public void setFinalLevel(String finalLevel) {
		this.finalLevel = finalLevel;
	}
	
	
	
	
	
}