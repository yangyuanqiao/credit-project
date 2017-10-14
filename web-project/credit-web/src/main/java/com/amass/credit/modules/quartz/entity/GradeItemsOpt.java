package com.amass.credit.modules.quartz.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GradeItemsOpt {
	
	
	private String id			;//明细编号
	private String subId		;//主体编号
	private String itemId	;//选项代码
	private String optId		;//选项值代码
	private String statue		;//记录状态
	private String batch		;//批次号
	private String  score;
	private Date createDate ;   
	
	
	
	
	public String getScore() {
		return score;
	}


	public void setScore(String score) {
		this.score = score;
	}


	public String getBatch() {
		return batch;
	}


	public void setBatch(String batch) {
		this.batch = batch;
	}


	private Date updateDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSubId() {
		return subId;
	}


	public void setSubId(String subId) {
		this.subId = subId;
	}




	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public String getOptId() {
		return optId;
	}


	public void setOptId(String optId) {
		this.optId = optId;
	}


	public String getStatue() {
		return statue;
	}


	public void setStatue(String statue) {
		this.statue = statue;
	}


	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
