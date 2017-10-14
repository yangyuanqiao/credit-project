/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.announce.entity.scope;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;
import com.amass.credit.modules.announce.entity.content.AnnounceContent;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 公示范围Entity
 * @author yangyq
 * @version 2017-06-23
 */
public class AnnounceScope extends DataEntity<AnnounceScope> {
	
	private static final long serialVersionUID = 1L;
	private String subType;		// 主体类别
	private String announSub;		// 公示对象
	private String announDate;		// 公示期限
	private String announContent;		// 公示内容
	private String remark;		// 备注
	
	private Date beginTime;
	private Date endTime;
	
	private List<AnnounceContent> contList;
	
	
	public List<AnnounceContent> getContList() {
		return contList;
	}

	public void setContList(List<AnnounceContent> contList) {
		this.contList = contList;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public AnnounceScope() {
		super();
	}

	public AnnounceScope(String id){
		super(id);
	}

	@Length(min=1, max=40, message="主体类别长度必须介于 1 和 40 之间")
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	@Length(min=1, max=40, message="公示对象长度必须介于 1 和 40 之间")
	public String getAnnounSub() {
		return announSub;
	}

	public void setAnnounSub(String announSub) {
		this.announSub = announSub;
	}
	
	@Length(min=0, max=40, message="公示期限长度必须介于 0 和 40 之间")
	public String getAnnounDate() {
		return announDate;
	}

	public void setAnnounDate(String announDate) {
		this.announDate = announDate;
	}
	
	@Length(min=0, max=40, message="公示内容长度必须介于 0 和 40 之间")
	public String getAnnounContent() {
		return announContent;
	}

	public void setAnnounContent(String announContent) {
		this.announContent = announContent;
	}
	
	@Length(min=0, max=40, message="备注长度必须介于 0 和 40 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}