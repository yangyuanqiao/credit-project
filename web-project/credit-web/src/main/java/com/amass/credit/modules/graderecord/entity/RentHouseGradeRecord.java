package com.amass.credit.modules.graderecord.entity;

import com.amass.credit.modules.renthouse.entity.baseinfo.RenthouseBase;

public class RentHouseGradeRecord extends GradeRecord {

		/**
	 * 
	 */
	private static final long serialVersionUID = -3851502850950697496L;
		private RenthouseBase renthouseBase;

		public RenthouseBase getRenthouseBase() {
			return renthouseBase;
		}

		public void setRenthouseBase(RenthouseBase renthouseBase) {
			this.renthouseBase = renthouseBase;
		}
		
		
}
