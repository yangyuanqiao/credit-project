package com.amass.credit.modules.sys.entity.subpro;
/**
 * 主体属性信息Entity
 * @author yangyq
 * @version 2017-08-16
 */
public class SubPropertyVo extends SubProperty {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3805540513944372701L;

	private String itemValue;
	
	private String subId;

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
}
