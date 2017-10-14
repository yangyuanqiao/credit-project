/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.gradelimit.entity.limitrule;

import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 规则Entity
 * @author yangyq
 * @version 2017-06-27
 */
public class GradeRule extends DataEntity<GradeRule> {
	
	private static final long serialVersionUID = 1L;
	private String itemId;		// 选项值编号(外键参考评分选项id)
	private String ruleType;		// 规则种类;参考数据字典RULE_TYPE
	private String ruleName;		// 规则描述
	private String referTable;		// 参照表
	private String referField;		// 参照字段名称
	private String referEquation;		// 计算条件公式(参考数据字典EQUATION_TYPE)
	private String referVal;		// 参考结果值
	private String referCondit;		// 参考字段条件
	private String itemLevel;		// 优先级别
	private String ext1;		// 扩展1
	private String ext2;		// 扩展2
	private String ext3;		// 扩展3
	
	public GradeRule() {
		super();
	}

	public GradeRule(String id){
		super(id);
	}

	
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Length(min=0, max=40, message="规则种类;参考数据字典RULE_TYPE长度必须介于 0 和 40 之间")
	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	
	@Length(min=0, max=40, message="规则描述长度必须介于 0 和 40 之间")
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	@Length(min=0, max=40, message="参照表长度必须介于 0 和 40 之间")
	public String getReferTable() {
		return referTable;
	}

	public void setReferTable(String referTable) {
		this.referTable = referTable;
	}
	
	@Length(min=0, max=40, message="参照字段名称长度必须介于 0 和 40 之间")
	public String getReferField() {
		return referField;
	}

	public void setReferField(String referField) {
		this.referField = referField;
	}
	
	@Length(min=0, max=40, message="计算条件公式(参考数据字典EQUATION_TYPE)长度必须介于 0 和 40 之间")
	public String getReferEquation() {
		return referEquation;
	}

	public void setReferEquation(String referEquation) {
		this.referEquation = referEquation;
	}
	
	@Length(min=0, max=40, message="参考结果值长度必须介于 0 和 40 之间")
	public String getReferVal() {
		return referVal;
	}

	public void setReferVal(String referVal) {
		this.referVal = referVal;
	}
	
	@Length(min=0, max=255, message="参考字段条件长度必须介于 0 和 255 之间")
	public String getReferCondit() {
		return referCondit;
	}

	public void setReferCondit(String referCondit) {
		this.referCondit = referCondit;
	}
	
	@Length(min=0, max=10, message="优先级别长度必须介于 0 和 10 之间")
	public String getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(String itemLevel) {
		this.itemLevel = itemLevel;
	}
	
	@Length(min=0, max=40, message="扩展1长度必须介于 0 和 40 之间")
	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	
	@Length(min=0, max=40, message="扩展2长度必须介于 0 和 40 之间")
	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	
	@Length(min=0, max=40, message="扩展3长度必须介于 0 和 40 之间")
	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	
}