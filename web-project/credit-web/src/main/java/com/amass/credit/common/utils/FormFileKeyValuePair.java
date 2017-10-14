package com.amass.credit.common.utils;

public class FormFileKeyValuePair {
	private static final long serialVersionUID = 1L;
	private String key;
	private String value;
	public FormFileKeyValuePair(String key,String value){
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
