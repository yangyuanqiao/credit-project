/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.gradelimit.entity.detail;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 条件明细Entity
 * @author yangyq
 * @version 2017-06-29
 */
public class GradeLimitDetail extends DataEntity<GradeLimitDetail> {
	
	private static final long serialVersionUID = 1L;
	private Long subId;		// 主体编号(外键参考主体对象)
	private Long limitId;		// 选项代码(外键参照评分选项表id)
	private String levelId;		// 限制级别
	private String batch;		// 批次
	
	private String limitResult;
	
	public String getLimitResult() {
		return limitResult;
	}

	public void setLimitResult(String limitResult) {
		this.limitResult = limitResult;
	}

	public GradeLimitDetail() {
		super();
	}

	public GradeLimitDetail(String id){
		super(id);
	}

	@NotNull(message="主体编号(外键参考主体对象)不能为空")
	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}
	
	@NotNull(message="选项代码(外键参照评分选项表id)不能为空")
	public Long getLimitId() {
		return limitId;
	}

	public void setLimitId(Long limitId) {
		this.limitId = limitId;
	}
	
	@Length(min=0, max=40, message="限制级别长度必须介于 0 和 40 之间")
	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	
	@Length(min=0, max=40, message="批次长度必须介于 0 和 40 之间")
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}
	
}