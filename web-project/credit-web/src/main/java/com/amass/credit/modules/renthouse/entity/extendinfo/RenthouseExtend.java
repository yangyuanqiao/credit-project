/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.renthouse.entity.extendinfo;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 出租屋扩展信息Entity
 * @author yangyq
 * @version 2017-06-22
 */
public class RenthouseExtend extends DataEntity<RenthouseExtend> {
	
	private static final long serialVersionUID = 1L;
	private Long subId;		// 主体编号
	private String extendName;		// 属性名称
	private String extendCode;		// 属性代码
	private String resultCode;		// 属性值
	private String resultDesc;		// 属性值描述
	private String remark;		// 备注
	private String channel;		// 来源渠道
	
	public RenthouseExtend() {
		super();
	}

	public RenthouseExtend(String id){
		super(id);
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}
	
	@Length(min=1, max=40, message="属性名称长度必须介于 1 和 40 之间")
	public String getExtendName() {
		return extendName;
	}

	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}
	
	@Length(min=1, max=40, message="属性代码长度必须介于 1 和 40 之间")
	public String getExtendCode() {
		return extendCode;
	}

	public void setExtendCode(String extendCode) {
		this.extendCode = extendCode;
	}
	
	@Length(min=1, max=40, message="属性值长度必须介于 1 和 40 之间")
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	@Length(min=0, max=255, message="属性值描述长度必须介于 0 和 255 之间")
	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	
	@Length(min=0, max=40, message="备注长度必须介于 0 和 40 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=40, message="来源渠道长度必须介于 0 和 40 之间")
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}