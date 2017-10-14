/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.entity.gradeitems;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;
import com.amass.credit.modules.grade.entity.gradeoptions.GradeOption;

/**
 * 评分选项管理Entity
 * @author yangqy
 * @version 2017-07-04
 */
public class GradeItems extends DataEntity<GradeItems> {
	
	private static final long serialVersionUID = 1L;
	private Long fatherId;		// 父级类别
	private Long childId;		// 子级类别
	private String itemName;		// 标准名称
	private String itemCode;		// 评分项代码
	private String itemStatus;		// 状态
	private String sort;		// 选项排序
	private String remark;		// 备注
	private String subType;
	
	private String fatherName;
	
	private String childName;
	
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	private GradeOption gradeOpt;
	
	public GradeOption getGradeOpt() {
		return gradeOpt;
	}

	public void setGradeOpt(GradeOption gradeOpt) {
		this.gradeOpt = gradeOpt;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public GradeItems() {
		super();
	}

	public GradeItems(String id){
		super(id);
	}

	@NotNull(message="父级类别不能为空")
	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}
	
	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}
	
	@Length(min=1, max=40, message="标准名称长度必须介于 1 和 40 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=0, max=40, message="评分项代码长度必须介于 0 和 40 之间")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@Length(min=0, max=40, message="状态长度必须介于 0 和 40 之间")
	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	@Length(min=0, max=10, message="选项排序长度必须介于 0 和 10 之间")
	@NotNull(message="选项排序不能为空")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=40, message="备注长度必须介于 0 和 40 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}