/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.sys.entity.subpro;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 主体属性信息Entity
 * @author yangyq
 * @version 2017-08-16
 */
public class SubProperty extends DataEntity<SubProperty> {
	
	private static final long serialVersionUID = 1L;
	private Long subType;		// 主体类别
	private String itemName;		// 项目名称
	private String itemCode;		// 项目代码
	private String remark;		// 备注
	
	public SubProperty() {
		super();
	}

	public SubProperty(String id){
		super(id);
	}

	public Long getSubType() {
		return subType;
	}

	public void setSubType(Long subType) {
		this.subType = subType;
	}
	
	@Length(min=1, max=40, message="项目名称长度必须介于 1 和 40 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=1, max=40, message="项目代码长度必须介于 1 和 40 之间")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@Length(min=0, max=40, message="备注长度必须介于 0 和 40 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}