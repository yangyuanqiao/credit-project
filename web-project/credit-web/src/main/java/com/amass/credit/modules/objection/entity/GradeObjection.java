/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.objection.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 异议申请Entity
 * @author yangyq
 * @version 2017-07-07
 */
public class GradeObjection extends DataEntity<GradeObjection> {
	
	private static final long serialVersionUID = 1L;
	private String subId;		// 主体对象
	private String applyUser;		// 申请人
	private String applyTel;		// 申请人手机号
	private String appEmail;		// 申请人邮箱地址
	private String applyType;		// 异议申请类别
	private String applyReason;		// 异议申请原因
	private Date applyTime;		// 申请时间
	private String applyProve;		// 证明材料
	private String remark;		// 备注
	private String applyStatus;		// 处理状态
	
	public GradeObjection() {
		super();
	}

	public GradeObjection(String id){
		super(id);
	}

	@Length(min=1, max=40, message="主体对象长度必须介于 1 和 40 之间")
	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}
	
	@Length(min=1, max=40, message="申请人长度必须介于 1 和 40 之间")
	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	
	@Length(min=1, max=40, message="申请人手机号长度必须介于 1 和 40 之间")
	public String getApplyTel() {
		return applyTel;
	}

	public void setApplyTel(String applyTel) {
		this.applyTel = applyTel;
	}
	
	@Length(min=0, max=40, message="申请人邮箱地址长度必须介于 0 和 40 之间")
	public String getAppEmail() {
		return appEmail;
	}

	public void setAppEmail(String appEmail) {
		this.appEmail = appEmail;
	}
	
	@Length(min=1, max=10, message="异议申请类别长度必须介于 1 和 10 之间")
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	@Length(min=1, max=400, message="异议申请原因长度必须介于 1 和 400 之间")
	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	@Length(min=1, max=400, message="证明材料长度必须介于 1 和 400 之间")
	public String getApplyProve() {
		return applyProve;
	}

	public void setApplyProve(String applyProve) {
		this.applyProve = applyProve;
	}
	
	@Length(min=0, max=400, message="备注长度必须介于 0 和 400 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=40, message="处理状态长度必须介于 0 和 40 之间")
	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	
}