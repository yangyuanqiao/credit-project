package com.amass.credit.common.utils.portalapi;

import java.util.ArrayList;

import com.amass.credit.common.utils.FormFileKeyValuePair;
import com.amass.credit.common.utils.UploadFileItem;
import com.amass.credit.common.config.Global;

public abstract class BaseAPI {

	/**
	 * 需要上传的文件集合
	 */
	protected ArrayList<UploadFileItem> ufis = new ArrayList<UploadFileItem>();
	
	/**
	 * 需要上传的表单文本集合
	 */
	protected ArrayList<FormFileKeyValuePair> ffkvpAlist = new ArrayList<FormFileKeyValuePair>();
	
	/**
	 * 系统通行证（工作密钥)
	 */
	
	//protected FormFileKeyValuePair ffkvp_workKey = new FormFileKeyValuePair("syscode", Global.getSyscode());
	/**
	 * 设备号
	 */
	protected FormFileKeyValuePair ffkvp_deviceSn = new FormFileKeyValuePair("deviceSn", Global.DEVICESN);
	/**
	 * sim卡号
	 */
	protected FormFileKeyValuePair ffkvp_sim = new FormFileKeyValuePair("sim", Global.SIMNUM);
	
	/********************somemethod**************************/
	public BaseAPI(){
		ffkvpAlist.add(ffkvp_deviceSn);
		ffkvpAlist.add(ffkvp_sim);
		//ffkvpAlist.add(ffkvp_workKey);
	}
}
