/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.enterprise.entity.operator;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 企业经营者信息Entity
 * @author yangyq
 * @version 2017-06-20
 */
public class EnterpriseOperator extends DataEntity<EnterpriseOperator> {
	
	private static final long serialVersionUID = 1L;
	private Long subId;		// 企业编号
	private String operatorName;		// 姓名
	private String operatorEdu;		// 文化程度
	private String operatorTel;		// 联系电话
	private String operatorAddr;		// 联系地址
	private String workExperience;		// 工作年限
	private String professional;		// 职称
	private String nativePlace;		// 籍贯
	private String cardType;		// 证件类型
	private String cardNo;		// 证件号
	private String politics;		// 政治面貌
	private String remark;		// 备注
	private String ext1;		// 扩展1
	private String ext2;		// 扩展2
	private String ext3;		// 扩展3
	
	public EnterpriseOperator() {
		super();
	}

	public EnterpriseOperator(String id){
		super(id);
	}

	@NotNull(message="企业编号不能为空")
	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}
	
	@Length(min=0, max=40, message="姓名长度必须介于 0 和 40 之间")
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	@Length(min=0, max=40, message="文化程度长度必须介于 0 和 40 之间")
	public String getOperatorEdu() {
		return operatorEdu;
	}

	public void setOperatorEdu(String operatorEdu) {
		this.operatorEdu = operatorEdu;
	}
	
	@Length(min=0, max=40, message="联系电话长度必须介于 0 和 40 之间")
	public String getOperatorTel() {
		return operatorTel;
	}

	public void setOperatorTel(String operatorTel) {
		this.operatorTel = operatorTel;
	}
	
	@Length(min=0, max=40, message="联系地址长度必须介于 0 和 40 之间")
	public String getOperatorAddr() {
		return operatorAddr;
	}

	public void setOperatorAddr(String operatorAddr) {
		this.operatorAddr = operatorAddr;
	}
	
	@Length(min=0, max=40, message="工作年限长度必须介于 0 和 40 之间")
	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}
	
	@Length(min=0, max=40, message="职称长度必须介于 0 和 40 之间")
	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}
	
	@Length(min=0, max=40, message="籍贯长度必须介于 0 和 40 之间")
	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	
	@Length(min=0, max=10, message="证件类型长度必须介于 0 和 10 之间")
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	@Length(min=0, max=40, message="证件号长度必须介于 0 和 40 之间")
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	@Length(min=0, max=40, message="政治面貌长度必须介于 0 和 40 之间")
	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}
	
	@Length(min=0, max=40, message="备注长度必须介于 0 和 40 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=10, message="扩展1长度必须介于 0 和 10 之间")
	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	
	@Length(min=0, max=10, message="扩展2长度必须介于 0 和 10 之间")
	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	
	@Length(min=0, max=10, message="扩展3长度必须介于 0 和 10 之间")
	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	
}