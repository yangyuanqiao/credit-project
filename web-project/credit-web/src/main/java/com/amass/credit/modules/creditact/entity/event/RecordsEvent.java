/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.creditact.entity.event;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;
import com.amass.credit.common.utils.excel.annotation.ExcelField;

/**
 * 事件行为信息Entity
 * @author yangyq
 * @version 2017-06-21
 */
public class RecordsEvent extends DataEntity<RecordsEvent> {
	
	private static final long serialVersionUID = 1L;
	private String subType;		// 主体类别
	private String subId;		// 主体编号
	private String eventType;		// 事件类别
	private String eventName;		// 事件标题
	private String eventDetail;		// 事件具体内容
	private String eventTime;		// 事件发生时间
	private String deparrment;		// 事件来源
	private String dealTime;		// 要求处理日期
	private String result;		// 事件处理结果
	private String createTime;		// 同步时间
	private String remark;		// 备注
	
	private String registryNo;//企业注册号
	private String creditNo;//企业统一信用代码
	

	@ExcelField(title="企业注册号", align=2, sort=2, type=0)
	public String getRegistryNo() {
		return registryNo;
	}

	public void setRegistryNo(String registryNo) {
		this.registryNo = registryNo;
	}
	@ExcelField(title="统一信用代码", align=2, sort=2, type=0)
	public String getCreditNo() {
		return creditNo;
	}

	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}

	public RecordsEvent() {
		super();
	}

	public RecordsEvent(String id){
		super(id);
	}

	@Length(min=0, max=50, message="主体类别长度必须介于 0 和 50 之间")
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	@Length(min=0, max=50, message="主体编号长度必须介于 0 和 50 之间")
	@ExcelField(title="主体编号", align=2, sort=1, type=0)
	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}
	
	@Length(min=0, max=50, message="事件类别长度必须介于 0 和 50 之间")
	@ExcelField(title="事件类别", align=2, sort=12,dictType="EVENT_TYPE",type=0)
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	@Length(min=0, max=50, message="事件标题长度必须介于 0 和 50 之间")
	@ExcelField(title="事件标题", align=2, sort=3, type=0)
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	@Length(min=0, max=50, message="事件具体内容长度必须介于 0 和 50 之间")
	@ExcelField(title="事件具体内容", align=2, sort=2, type=0)
	public String getEventDetail() {
		return eventDetail;
	}

	public void setEventDetail(String eventDetail) {
		this.eventDetail = eventDetail;
	}
	
	@Length(min=0, max=50, message="事件发生时间长度必须介于 0 和 50 之间")
	@ExcelField(title="事件发生时间", align=2, sort=3, type=0)
	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	
	@Length(min=0, max=50, message="事件来源长度必须介于 0 和 50 之间")
	@ExcelField(title="事件来源", align=2, sort=4, type=0)
	public String getDeparrment() {
		return deparrment;
	}

	public void setDeparrment(String deparrment) {
		this.deparrment = deparrment;
	}
	
	@Length(min=0, max=50, message="要求处理日期长度必须介于 0 和 50 之间")
	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}
	
	@Length(min=0, max=50, message="事件处理结果长度必须介于 0 和 50 之间")
	@ExcelField(title="事件处理结果", align=2, sort=4, type=0)
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@Length(min=0, max=50, message="同步时间长度必须介于 0 和 50 之间")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=50, message="备注长度必须介于 0 和 50 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}