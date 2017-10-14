/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.entity.category;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.TreeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 评分选项类别Entity
 * @author yangqy
 * @version 2017-06-21
 */
public class GradeCategory extends TreeEntity<GradeCategory> {
	
	private static final long serialVersionUID = 1L;
	private String subType;		// 评分主体类别
	private GradeCategory parent;		// 上一级类别编号
	private String parentIds;		// 所有父级
	private String cateName;		// 类别名称
	private String status;		// 状态
//	private String sort;		// 排序
	private String proportion;		// 权重
	private String transferPropotion; //
	private String remark;		// 备注
	private String createByName;
	private String categoryId;
	
	public String getTransferPropotion() {
		if(proportion == null || "".equals(proportion.trim())){
			return "";
		}else{
			return proportion+"%";
		}
	}

	private String fatherFlag;//顶级标志
	
	public String getFatherFlag() {
		return fatherFlag;
	}

	public void setFatherFlag(String fatherFlag) {
		this.fatherFlag = fatherFlag;
	}

	private String childId;
	
	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public GradeCategory() {
		super();
	}

	public GradeCategory(String id){
		super(id);
	}

	@Length(min=1, max=40, message="评分主体类别长度必须介于 1 和 40 之间")
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	@JsonBackReference
	public GradeCategory getParent() {
		return parent;
	}

	public void setParent(GradeCategory parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=100, message="所有父级长度必须介于 0 和 100 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=40, message="类别名称长度必须介于 1 和 40 之间")
	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	@Length(min=0, max=40, message="状态长度必须介于 0 和 40 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
//	@Length(min=0, max=10, message="排序长度必须介于 0 和 10 之间")
//	public String getSort() {
//		return sort;
//	}
//
//	public void setSort(String sort) {
//		this.sort = sort;
//	}
	
	@Length(min=0, max=40, message="权重长度必须介于 0 和 40 之间")
	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	
	@Length(min=0, max=400, message="备注长度必须介于 0 和 400 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


}