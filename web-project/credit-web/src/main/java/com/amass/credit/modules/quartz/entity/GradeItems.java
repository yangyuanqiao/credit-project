package com.amass.credit.modules.quartz.entity;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class GradeItems {
	
    private String id;
	private String 	categoryId	;  //	评分类别	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String 	itemName	;  //	标准名称	   
	private String 	itemCode	;  //	评分项代码	
	private String itemStatus	;  //	状态		gradeOptions
	private String sort			;  //	选项排序	
	private String   remark		;  //	备注		
	private String delFlag		;  //删除标志	
	
	private List<GradeOption>  optionList ;
	
	private List<GradeRules>  ruleList;
	
	
	public List<GradeRules> getRuleList() {
		return ruleList;
	}
	public void setRuleList(List<GradeRules> ruleList) {
		this.ruleList = ruleList;
	}
	
	
	public List<GradeOption> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<GradeOption> optionList) {
		this.optionList = optionList;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
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
