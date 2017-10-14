package com.amass.credit.modules.quartz.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class GradeRules {
	
	private String id	;
	private String itemId	; //选项值编号
	private String ruleType		;//规则种类
	private String ruleName		;//规则名称
	private String referTable		;//参照表
	private String referField		;//参照字段
	private String referEquation	;//计算条件公式(大于 小于 等于 包含 不包含 非空)
	private String referVal	;//计算结果
	private String referCondit;
	

	public String getReferCondit() {
		return referCondit;
	}
	public void setReferCondit(String referCondit) {
		this.referCondit = referCondit;
	}

	private String itemLevel	;//优先级别
	private String delFlag	;//删除标志
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getReferTable() {
		return referTable;
	}
	public void setReferTable(String referTable) {
		this.referTable = referTable;
	}
	public String getReferField() {
		return referField;
	}
	public void setReferField(String referField) {
		this.referField = referField;
	}
	public String getReferEquation() {
		return referEquation;
	}
	public void setReferEquation(String referEquation) {
		this.referEquation = referEquation;
	}
	public String getReferVal() {
		return referVal;
	}
	public void setReferVal(String referVal) {
		this.referVal = referVal;
	}
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemLevel() {
		return itemLevel;
	}
	public void setItemLevel(String itemLevel) {
		this.itemLevel = itemLevel;
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
