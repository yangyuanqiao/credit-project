/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.creditact.entity.punishment;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.amass.credit.common.persistence.DataEntity;
import com.amass.credit.common.utils.excel.annotation.ExcelField;

/**
 * 处罚行为信息Entity
 * @author yangyq
 * @version 2017-06-21
 */
public class RecordsPunishment extends DataEntity<RecordsPunishment> {
	
	private static final long serialVersionUID = 1L;
	private String subType;		// 主体类别
	private Long subId;		// 主体编号
	private String punishFile;		// 处罚文书编号
	private String punishType;		// 处罚类别
	private String punishTitle;		// 处罚标题
	private String punishDesc;		// 处罚内容
	private String punishDepart;		// 处罚机构
	private Date punishDate;		// 处罚时间
	private String punishStatus;		// 处罚状态
	private String remark;		// 备注
	
	private String creditNo;
	
	private String registryNo;
	private String subName;
	
	
	@ExcelField(title="统一信用代码", align=2, sort=2, type=0)
	public String getCreditNo() {
		return creditNo;
	}

	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}
	@ExcelField(title="企业注册号", align=2, sort=2, type=0)
	public String getRegistryNo() {
		return registryNo;
	}

	public void setRegistryNo(String registryNo) {
		this.registryNo = registryNo;
	}
	@ExcelField(title="主体名称", align=2, sort=2, type=0)
	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public RecordsPunishment() {
		super();
	}

	public RecordsPunishment(String id){
		super(id);
	}

	@Length(min=0, max=40, message="主体类别长度必须介于 0 和 40 之间")
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	public Long getSubId() {
		return subId;
	}
	@ExcelField(title="主体编号", align=2, sort=1, type=0)
	public void setSubId(Long subId) {
		this.subId = subId;
	}
	
	@Length(min=0, max=40, message="处罚文书编号长度必须介于 0 和 40 之间")
	@ExcelField(title="处罚文书编号", align=2, sort=2, type=0)
	public String getPunishFile() {
		return punishFile;
	}

	public void setPunishFile(String punishFile) {
		this.punishFile = punishFile;
	}
	
	@Length(min=0, max=40, message="处罚类别长度必须介于 0 和 40 之间")
	@ExcelField(title="处罚类别", align=2, sort=2, type=0)
	public String getPunishType() {
		return punishType;
	}

	public void setPunishType(String punishType) {
		this.punishType = punishType;
	}
	
	@Length(min=0, max=40, message="处罚标题长度必须介于 0 和 40 之间")
	@ExcelField(title="处罚标题", align=2, sort=2, type=0)
	public String getPunishTitle() {
		return punishTitle;
	}

	public void setPunishTitle(String punishTitle) {
		this.punishTitle = punishTitle;
	}
	
	@Length(min=0, max=40, message="处罚内容长度必须介于 0 和 40 之间")
	@ExcelField(title="处罚内容", align=2, sort=2, type=0)
	public String getPunishDesc() {
		return punishDesc;
	}

	public void setPunishDesc(String punishDesc) {
		this.punishDesc = punishDesc;
	}
	
	@Length(min=0, max=40, message="处罚机构长度必须介于 0 和 40 之间")
	@ExcelField(title="处罚机构", align=2, sort=2, type=0)
	public String getPunishDepart() {
		return punishDepart;
	}

	public void setPunishDepart(String punishDepart) {
		this.punishDepart = punishDepart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="处罚日期", align=2, sort=2, type=0)
	public Date getPunishDate() {
		return punishDate;
	}

	public void setPunishDate(Date punishDate) {
		this.punishDate = punishDate;
	}
	
	@Length(min=0, max=40, message="处罚状态长度必须介于 0 和 40 之间")
	public String getPunishStatus() {
		return punishStatus;
	}

	public void setPunishStatus(String punishStatus) {
		this.punishStatus = punishStatus;
	}
	
	@Length(min=0, max=40, message="备注长度必须介于 0 和 40 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}