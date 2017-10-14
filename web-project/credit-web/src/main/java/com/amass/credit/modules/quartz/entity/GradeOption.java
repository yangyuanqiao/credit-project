package com.amass.credit.modules.quartz.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class GradeOption {
	private String id			;//编号
	private String itemsId	;//评分选项代码
	private String optionName	;//评分选项值名称
	private String optionCode	;//评分选项值代码
	private String score		;//分值
	private String sort		;//排序
	private String remark		;//备注
	private String delFlag	;//删除标志
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemsId() {
		return itemsId;
	}
	public void setItemsId(String itemsId) {
		this.itemsId = itemsId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionCode() {
		return optionCode;
	}
	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
