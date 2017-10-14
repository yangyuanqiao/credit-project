/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.announce.entity.content;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 公示内容Entity
 * @author yangyq
 * @version 2017-06-23
 */
public class AnnounceContent extends DataEntity<AnnounceContent> {
	
	private static final long serialVersionUID = 1L;
	private String subType;		// 主体类别
	private String contentName;		// 内容名称
	private String contentTemplate;		// 内容模板
	private String contentDesc;		// 内容描述
	private String remark;		// 备注
	private String sort;		// 排序
	
	public AnnounceContent() {
		super();
	}

	public AnnounceContent(String id){
		super(id);
	}

	@Length(min=1, max=40, message="主体类别长度必须介于 1 和 40 之间")
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	@Length(min=1, max=40, message="内容名称长度必须介于 1 和 40 之间")
	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	
	@Length(min=0, max=500, message="内容模板长度必须介于 0 和 500 之间")
	public String getContentTemplate() {
		return contentTemplate;
	}

	public void setContentTemplate(String contentTemplate) {
		this.contentTemplate = contentTemplate;
	}
	
	@Length(min=0, max=225, message="内容描述长度必须介于 0 和 225 之间")
	public String getContentDesc() {
		return contentDesc;
	}

	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	
	@Length(min=0, max=40, message="备注长度必须介于 0 和 40 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=40, message="排序长度必须介于 0 和 40 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}