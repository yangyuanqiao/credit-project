package com.amass.credit.modules.graderecord.entity;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


public class GradeRecordDetailVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fatherCatName;
	
	private String fatherCatId;
	
	
	private List<GradeItemsVo> gradeItemsVo ;


	public String getFatherCatName() {
		return fatherCatName;
	}


	public void setFatherCatName(String fatherCatName) {
		this.fatherCatName = fatherCatName;
	}


	public String getFatherCatId() {
		return fatherCatId;
	}


	public void setFatherCatId(String fatherCatId) {
		this.fatherCatId = fatherCatId;
	}


	public List<GradeItemsVo> getGradeItemsVo() {
		return gradeItemsVo;
	}


	public void setGradeItemsVo(List<GradeItemsVo> gradeItemsVo) {
		this.gradeItemsVo = gradeItemsVo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
	
	

}
