package com.amass.credit.common.utils.portalapi;

import java.io.Serializable;

public class ResponseMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2192737849564000061L;
	
	private String data;
	private String code;
	private String errorMessage;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
