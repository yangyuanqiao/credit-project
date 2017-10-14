package com.amass.credit.modules.graderecord.entity;

import com.amass.credit.modules.quartz.entity.EnterpriseBase;

public class EnterPriseGradeRecord extends GradeRecord{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7966637314237746372L;
	
	private EnterpriseBase enterpriseBase;
	
	public EnterpriseBase getEnterpriseBase() {
		return enterpriseBase;
	}

	public void setEnterpriseBase(EnterpriseBase enterpriseBase) {
		this.enterpriseBase = enterpriseBase;
	}

}
