/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.license.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 许可证信息Entity
 * @author yangyq
 * @version 2017-06-23
 */
public class SubjectLicense extends DataEntity<SubjectLicense> {
	
	private static final long serialVersionUID = 1L;
	private Long subId;		// 市场主体id
	private String subType;		// 市场主体类型，数据字典SUB_TYPE
	private String licCode;		// 许可代码
	private String licName;		// 许可名称
	private String licContent;		// 许可范围/许可项目
	private String licNum;		// 许可证号
	private Date giveDate;		// 发放日期
	private Date validityDate;		// 有效日期
	private String status;		// 许可证状态（0注销，1变更，2换证）
	private Date handleDate;		// 办理日期
	private Long deptId;		// 所属部门
	
	public SubjectLicense() {
		super();
	}

	public SubjectLicense(String id){
		super(id);
	}

	@NotNull(message="市场主体id不能为空")
	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}
	
	@Length(min=1, max=64, message="市场主体类型，数据字典SUB_TYPE长度必须介于 1 和 64 之间")
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	@Length(min=1, max=20, message="许可代码长度必须介于 1 和 20 之间")
	public String getLicCode() {
		return licCode;
	}

	public void setLicCode(String licCode) {
		this.licCode = licCode;
	}
	
	@Length(min=1, max=20, message="许可名称长度必须介于 1 和 20 之间")
	public String getLicName() {
		return licName;
	}

	public void setLicName(String licName) {
		this.licName = licName;
	}
	
	@Length(min=1, max=100, message="许可范围/许可项目长度必须介于 1 和 100 之间")
	public String getLicContent() {
		return licContent;
	}

	public void setLicContent(String licContent) {
		this.licContent = licContent;
	}
	
	@Length(min=1, max=20, message="许可证号长度必须介于 1 和 20 之间")
	public String getLicNum() {
		return licNum;
	}

	public void setLicNum(String licNum) {
		this.licNum = licNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="发放日期不能为空")
	public Date getGiveDate() {
		return giveDate;
	}

	public void setGiveDate(Date giveDate) {
		this.giveDate = giveDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="有效日期不能为空")
	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
	
	@Length(min=1, max=11, message="许可证状态（0注销，1变更，2换证）长度必须介于 1 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="办理日期不能为空")
	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}
	
	@NotNull(message="所属部门不能为空")
	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	
}