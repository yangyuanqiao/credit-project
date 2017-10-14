package com.amass.credit.modules.quartz.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class GradeRecord {
	
	private Long  	id			;//记录编号
	private String  subType	;//主体类别
	private Long    subId		;//主体编号
	private float    score		    ;// 评定分数
	private Date    createDate	;//评定时间
	private String  status		;//评定状态
	private String  content		;//评分选项值组合
	private String  limitLevel	;//条件限制等级
	private String  finalLevel	;//最终评定等级
	private String  scoreLevel;//分数级别
	private String batch;//批次号
	
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getScoreLevel() {
		return scoreLevel;
	}

	public void setScoreLevel(String scoreLevel) {
		this.scoreLevel = scoreLevel;
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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
