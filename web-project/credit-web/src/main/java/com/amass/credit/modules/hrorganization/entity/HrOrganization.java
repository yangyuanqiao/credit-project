/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.hrorganization.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.amass.credit.modules.sys.entity.Area;

import com.amass.credit.common.persistence.TreeEntity;

/**
 * 社区组织Entity
 * @author yangyq
 * @version 2017-04-25
 */
public class HrOrganization extends TreeEntity<HrOrganization> {
	
	private static final long serialVersionUID = 1L;
	/*private String name;		// 组织结构名称
	private HrOrganization parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String sort;		// 排序
*/	private Area area;		// 归属区域
	private String orgAddress;		// 组织地址
	
	public HrOrganization() {
		super();
	}

	public HrOrganization(String id){
		super(id);
	}

	@Length(min=1, max=100, message="组织结构名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonBackReference
	public HrOrganization getParent() {
		return parent;
	}

	public void setParent(HrOrganization parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="所有父级编号长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	
	
	@Length(min=0, max=255, message="组织地址长度必须介于 0 和 255 之间")
	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}