/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.entity.baseinfo;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 模具主体信息Entity
 * @author yangyq
 * @version 2017-06-20
 */
public class EnterpriseBase extends DataEntity<EnterpriseBase> {
	
	private static final long serialVersionUID = 1L;
	private String subName;		// 企业名称
	private String creditNo;		// 统一信用代码
	private String taxNo;		// 税务登记号
	private String orgRegistry;		// 注册号
	private String orgCode;		// 组织机构代码
	private String regAddr;		// 工商登记地址
	private String businessScop;		// 经营范围
	private String businessWay;		// 经营方式
	private String businessType;		// 经营类别
	private Date setupDate;		// 成立日期
	private String telNo;		// 企业联系电话
	private String licenseName;		// 经营牌照名称
	private String areaTotal;		// 经营场所面积
	private String numOfPeople;		// 企业人数
	private Date creatDate;		// 同步时间
	private String isCheck;		// 是否年报
	private String subFlag;		// 行业类别
	
	public EnterpriseBase() {
		super();
	}

	public EnterpriseBase(String id){
		super(id);
	}

	@Length(min=1, max=40, message="企业名称长度必须介于 1 和 40 之间")
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
	
	@Length(min=0, max=10, message="经营范围长度必须介于 0 和 10 之间")
	public String getBusinessScop() {
		return businessScop;
	}

	public void setBusinessScop(String businessScop) {
		this.businessScop = businessScop;
	}
	
	@Length(min=0, max=40, message="经营方式长度必须介于 0 和 40 之间")
	public String getBusinessWay() {
		return businessWay;
	}

	public void setBusinessWay(String businessWay) {
		this.businessWay = businessWay;
	}
	
	@Length(min=0, max=40, message="经营类别长度必须介于 0 和 40 之间")
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
	}
	
	@Length(min=0, max=40, message="企业联系电话长度必须介于 0 和 40 之间")
	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
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
	
	@Length(min=0, max=40, message="企业人数长度必须介于 0 和 40 之间")
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
	
	@Length(min=0, max=10, message="行业类别长度必须介于 0 和 10 之间")
	public String getSubFlag() {
		return subFlag;
	}

	public void setSubFlag(String subFlag) {
		this.subFlag = subFlag;
	}
	
}