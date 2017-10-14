/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.cow.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 牛行经营场所Entity
 * @author yangyq
 * @version 2017-08-18
 */
public class CrdCowOperation extends DataEntity<CrdCowOperation> {
	
	private static final long serialVersionUID = 1L;
	private String subName;		// 经营场所名称
	private String creditNo;		// 统一信用代码
	private String taxNo;		// 税务登记号
	private String orgRegistry;		// 注册号
	private String orgCode;		// 组织机构代码
	private String regAddr;		// 工商登记地址
	private String setupDate;		// 经营开始时间
	private String telNo;		// 联系电话
	private String telMan;		// 联系人
	private String licenseName;		// 经营牌照名称
	private String areaTotal;		// 经营场所面积
	private String numOfPeople;		// 员工人数
	private Date creatDate;		// 同步时间
	private String isCheck;		// 是否年报
	private String subFlag;		// 主体标志
	
	public CrdCowOperation() {
		super();
	}

	public CrdCowOperation(String id){
		super(id);
	}

	@Length(min=1, max=40, message="经营场所名称长度必须介于 1 和 40 之间")
	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}
	
	@Length(min=0, max=40, message="统一信用代码长度必须介于 0 和 40 之间")
	public String getCreditNo() {
		return creditNo;
	}

	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}
	
	@Length(min=0, max=40, message="税务登记号长度必须介于 0 和 40 之间")
	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	
	@Length(min=0, max=40, message="注册号长度必须介于 0 和 40 之间")
	public String getOrgRegistry() {
		return orgRegistry;
	}

	public void setOrgRegistry(String orgRegistry) {
		this.orgRegistry = orgRegistry;
	}
	
	@Length(min=0, max=40, message="组织机构代码长度必须介于 0 和 40 之间")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Length(min=0, max=40, message="工商登记地址长度必须介于 0 和 40 之间")
	public String getRegAddr() {
		return regAddr;
	}

	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}
	
	@Length(min=0, max=40, message="经营开始时间长度必须介于 0 和 40 之间")
	public String getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(String setupDate) {
		this.setupDate = setupDate;
	}
	
	@Length(min=0, max=40, message="联系电话长度必须介于 0 和 40 之间")
	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	
	@Length(min=0, max=40, message="联系人长度必须介于 0 和 40 之间")
	public String getTelMan() {
		return telMan;
	}

	public void setTelMan(String telMan) {
		this.telMan = telMan;
	}
	
	@Length(min=0, max=40, message="经营牌照名称长度必须介于 0 和 40 之间")
	public String getLicenseName() {
		return licenseName;
	}

	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}
	
	@Length(min=0, max=40, message="经营场所面积长度必须介于 0 和 40 之间")
	public String getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(String areaTotal) {
		this.areaTotal = areaTotal;
	}
	
	@Length(min=0, max=40, message="员工人数长度必须介于 0 和 40 之间")
	public String getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(String numOfPeople) {
		this.numOfPeople = numOfPeople;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	
	@Length(min=0, max=10, message="是否年报长度必须介于 0 和 10 之间")
	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	
	@Length(min=0, max=10, message="主体标志长度必须介于 0 和 10 之间")
	public String getSubFlag() {
		return subFlag;
	}

	public void setSubFlag(String subFlag) {
		this.subFlag = subFlag;
	}
	
}