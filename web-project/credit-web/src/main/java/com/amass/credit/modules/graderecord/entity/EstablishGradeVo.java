package com.amass.credit.modules.graderecord.entity;

import com.amass.credit.modules.estatehousing.entity.basicinfo.HousingEstate;

public class EstablishGradeVo extends GradeRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1246073755068432534L;

	
	public HousingEstate getEstate() {
		return estate;
	}


	public void setEstate(HousingEstate estate) {
		this.estate = estate;
	}


	private HousingEstate estate;
	
}
