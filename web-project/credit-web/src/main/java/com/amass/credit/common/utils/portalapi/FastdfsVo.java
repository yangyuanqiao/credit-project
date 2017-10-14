package com.amass.credit.common.utils.portalapi;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;

public class FastdfsVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 上传文件生成的文件编号**/
	List<String> fastIdList;

	public List<String> getFastIdList() {
		return fastIdList;
	}

	public void setFastIdList(List<String> fastIdList) {
		this.fastIdList = fastIdList;
	}
	@Override
	public String toString() {
		Gson gson = new Gson();
		String jsonstr = gson.toJson(this);
		return jsonstr;
	}
}
