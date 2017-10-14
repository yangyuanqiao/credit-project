/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.creditact.entity.appraise;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;
import com.amass.credit.common.utils.excel.annotation.ExcelField;

/**
 * 评价行为信息Entity
 * @author yangyq
 * @version 2017-06-21
 */
public class RecordsAppraise extends DataEntity<RecordsAppraise> {
	
	private static final long serialVersionUID = 1L;
	private Long subId;		// 主体编号
	private String subType;		// 主体类别
	private String subName;		// 主体名称
	private String actType;		// 被评定类别
	private String gainTitle;		// 评定标题
	private String gainContent;		// 评定内容
	private String gainResult;		// 评定结果
	private String gainDate;		// 评定时间
	private String department;		// 评定部门
	private String statue;		// 评定状态
	private String remark;		// 备注
	private String creditNo;   
	private String registryNo; //企业注册号
	
	
	@ExcelField(title="企业注册号", align=2, sort=2, type=0)
	public String getRegistryNo() {
		return registryNo;
	}

	public void setRegistryNo(String registryNo) {
		this.registryNo = registryNo;
	}

	@ExcelField(title="统一信用代码", align=2, sort=2, type=0)
	public String getCreditNo() {
		return creditNo;
	}

	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}

	public RecordsAppraise() {
		super();
	}

	public RecordsAppraise(String id){
		super(id);
	}

	public Long getSubId() {
		return subId;
	}
	@ExcelField(title="主体编号", align=2, sort=1, type=0)
	public void setSubId(Long subId) {
		this.subId = subId;
	}
	
	@Length(min=0, max=40, message="主体类别长度必须介于 0 和 40 之间")
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	@Length(min=0, max=40, message="主体名称长度必须介于 0 和 40 之间")
	@ExcelField(title="主体名称", align=2, sort=2, type=0)
	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}
	
	@Length(min=0, max=40, message="被评定类别长度必须介于 0 和 40 之间")
	@ExcelField(title="评定类别", align=2, sort=2, type=0)
	public String getActType() {
		return actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
	}
	
	@Length(min=0, max=40, message="评定标题长度必须介于 0 和 40 之间")
	@ExcelField(title="评定标题", align=2, sort=2, type=0)
	public String getGainTitle() {
		return gainTitle;
	}

	public void setGainTitle(String gainTitle) {
		this.gainTitle = gainTitle;
	}
	
	@Length(min=0, max=40, message="评定内容长度必须介于 0 和 40 之间")
	@ExcelField(title="评定内容", align=2, sort=2, type=0)
	public String getGainContent() {
		return gainContent;
	}

	public void setGainContent(String gainContent) {
		this.gainContent = gainContent;
	}
	
	@Length(min=0, max=40, message="评定结果长度必须介于 0 和 40 之间")
	public String getGainResult() {
		return gainResult;
	}

	public void setGainResult(String gainResult) {
		this.gainResult = gainResult;
	}
	
	@Length(min=0, max=40, message="评定时间长度必须介于 0 和 40 之间")
	public String getGainDate() {
		return gainDate;
	}

	public void setGainDate(String gainDate) {
		this.gainDate = gainDate;
	}
	
	@Length(min=0, max=40, message="评定部门长度必须介于 0 和 40 之间")
	@ExcelField(title="评定来源", align=2, sort=2, type=0)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Length(min=0, max=40, message="评定状态长度必须介于 0 和 40 之间")
	@ExcelField(title="评定状态", align=2, sort=2, type=0)
	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}
	
	@Length(min=0, max=40, message="备注长度必须介于 0 和 40 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}