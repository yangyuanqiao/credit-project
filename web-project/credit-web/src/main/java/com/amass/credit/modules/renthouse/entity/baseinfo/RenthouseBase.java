/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.renthouse.entity.baseinfo;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 出租屋基础信息Entity
 * @author yangyq
 * @version 2017-06-22
 */
public class RenthouseBase extends DataEntity<RenthouseBase> {
	
	private static final long serialVersionUID = 1L;
	private String houseCode;		// 出租屋编码
	private String houseNum;		// 门牌号
	private String rentCode;		// 出租屋备案登记号
	private String isSafeCivilized;		// 是否安全文明出租屋
	private String houseAddr;		// 出租屋地址
	private String hasMonitor;		// 是否安装监控
	private String hasInsurance;		// 是否购买意外保险
	private String subjectType;		// 主体类型
	private String levelNum;		// 出租层数
	
	public RenthouseBase() {
		super();
	}

	public RenthouseBase(String id){
		super(id);
	}

	@Length(min=0, max=40, message="出租屋编码长度必须介于 0 和 40 之间")
	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}
	
	@Length(min=0, max=40, message="门牌号长度必须介于 0 和 40 之间")
	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}
	
	@Length(min=0, max=40, message="出租屋备案登记号长度必须介于 0 和 40 之间")
	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}
	
	@Length(min=0, max=40, message="是否安全文明出租屋长度必须介于 0 和 40 之间")
	public String getIsSafeCivilized() {
		return isSafeCivilized;
	}

	public void setIsSafeCivilized(String isSafeCivilized) {
		this.isSafeCivilized = isSafeCivilized;
	}
	
	@Length(min=0, max=40, message="出租屋地址长度必须介于 0 和 40 之间")
	public String getHouseAddr() {
		return houseAddr;
	}

	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}
	
	@Length(min=0, max=40, message="是否安装监控长度必须介于 0 和 40 之间")
	public String getHasMonitor() {
		return hasMonitor;
	}

	public void setHasMonitor(String hasMonitor) {
		this.hasMonitor = hasMonitor;
	}
	
	@Length(min=0, max=40, message="是否购买意外保险长度必须介于 0 和 40 之间")
	public String getHasInsurance() {
		return hasInsurance;
	}

	public void setHasInsurance(String hasInsurance) {
		this.hasInsurance = hasInsurance;
	}
	
	@Length(min=0, max=40, message="主体类型长度必须介于 0 和 40 之间")
	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	
	@Length(min=0, max=40, message="出租层数长度必须介于 0 和 40 之间")
	public String getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(String levelNum) {
		this.levelNum = levelNum;
	}
	
}