package com.amass.credit.modules.quartz.entity;

import java.io.Serializable;


public class EnterpriseBase implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long  id				;//企业编号
	private String  subName		;//企业名称
	private String  creditNo		;//统一信用代码
	private String  taxNo			;//税务登记号
	private String  orgRegistry	;//注册号
	private String  orgCode		;//组织机构代码
	private String  regAddr		;//工商登记地址
	private String  businessScop	;//经营范围
	private String  businessWay	;//经营方式
	private String  businessType	;//数据字典SUBJECT_INDUSTRY
	private String  setupDate		;//成立日期
	private String  telNo			;//企业联系电话
	private String  licenseName	;//经营牌照名称
	private String  areaTotal		;//经营场所面积
	private String  numOfPeople	;//企业人数
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getCreditNo() {
		return creditNo;
	}
	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public String getOrgRegistry() {
		return orgRegistry;
	}
	public void setOrgRegistry(String orgRegistry) {
		this.orgRegistry = orgRegistry;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getRegAddr() {
		return regAddr;
	}
	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}
	public String getBusinessScop() {
		return businessScop;
	}
	public void setBusinessScop(String businessScop) {
		this.businessScop = businessScop;
	}
	public String getBusinessWay() {
		return businessWay;
	}
	public void setBusinessWay(String businessWay) {
		this.businessWay = businessWay;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getSetupDate() {
		return setupDate;
	}
	public void setSetupDate(String setupDate) {
		this.setupDate = setupDate;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getLicenseName() {
		return licenseName;
	}
	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}
	public String getAreaTotal() {
		return areaTotal;
	}
	public void setAreaTotal(String areaTotal) {
		this.areaTotal = areaTotal;
	}
	public String getNumOfPeople() {
		return numOfPeople;
	}
	public void setNumOfPeople(String numOfPeople) {
		this.numOfPeople = numOfPeople;
	}
	
	

}
