/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.entity.gradeoptions;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.amass.credit.common.persistence.DataEntity;

/**
 * 评分选项值Entity
 * @author yangqy
 * @version 2017-07-04
 */
public class GradeOption extends DataEntity<GradeOption> {
	
	private static final long serialVersionUID = 1L;
	private Long itemsId;		// 评分选项代码
	private String optionName;		// 评分选项值名称
	private String optionCode;		// 评分选项值代码
	private String score;		// 分值
	private String sort;		// 排序
	private String remark;		// 备注
	
	//视图层删除状态
	private String optDel;
	
	
	
	public String getOptDel() {
		return optDel;
	}

	public void setOptDel(String optDel) {
		this.optDel = optDel;
	}

	public GradeOption() {
		super();
	}

	public GradeOption(String id){
		super(id);
	}

	@NotNull(message="评分选项代码不能为空")
	public Long getItemsId() {
		return itemsId;
	}

	public void setItemsId(Long itemsId) {
		this.itemsId = itemsId;
	}
	
	@Length(min=1, max=40, message="评分选项值名称长度必须介于 1 和 40 之间")
	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	
	@Length(min=0, max=40, message="评分选项值代码长度必须介于 0 和 40 之间")
	public String getOptionCode() {
		return optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}
	
	@Length(min=1, max=10, message="分值长度必须介于 1 和 10 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=0, max=40, message="排序长度必须介于 0 和 40 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=40, message="备注长度必须介于 0 和 40 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}