/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.djorg.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.amass.credit.common.persistence.TreeEntity;
import com.amass.credit.modules.sys.entity.Area;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 组织Entity
 * @author lzw
 * @version 2015-07-28
 */
public class DjOrganization extends TreeEntity<DjOrganization> {
	
	private static final long serialVersionUID = 1L;
	//private String name;		// 组织结构名称
	//private DjOrganization parent;		// 父级编号
	//private String parentIds;		// 所有父级编号
	//private String sort;		// 排序

	//private User primaryPerson;//主负责人
	//private User deputyPerson;//副负责人
	private Area area;//所在区域
	
	public DjOrganization() {
		super();
	}

	public DjOrganization(String id){
		super(id);
	}

	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}

	@Override
	public DjOrganization getParent() {
		return parent;
	}

	@Override
	public void setParent(DjOrganization parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return name;
	}
	@NotNull
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Override
	public Date getUpdateDate() {
		// TODO Auto-generated method stub
		return super.getUpdateDate();
	}
	
}