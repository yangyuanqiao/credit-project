package com.amass.credit.common.utils.portalapi;

import java.util.ArrayList;

import com.amass.credit.common.utils.FormFileKeyValuePair;
import com.amass.credit.common.utils.UploadUtils;
import com.amass.credit.common.config.Global;
import com.google.gson.Gson;

public class DownloadAPI extends BaseAPI {
	public ResponseMsg download(String type,FastdfsVo fastdfsvo) throws Exception{
		UploadUtils uploadUtils = new UploadUtils();
		FormFileKeyValuePair ffkvptype = new FormFileKeyValuePair("type", type);
		FormFileKeyValuePair ffkvpFastdfsVo = new FormFileKeyValuePair("FastdfsVo", fastdfsvo.toString());
		ffkvpAlist.add(ffkvptype);
		ffkvpAlist.add(ffkvpFastdfsVo);
		//调用接口，获得结果
		String msg = uploadUtils.sendHttpPostRequest(ffkvpAlist, ufis,Global.getConfig("portal.server")+UploadUtils.DOWNLOAD_API);
		Gson gson = new Gson();
		//发送请求
		ResponseMsg responseMsg = gson.fromJson(msg, ResponseMsg.class);
		DownloadFile df = null;
		/*if("000".equals(responseMsg.getCode())){
			//解析data，转换为DownloadFile实例
			 df = gson.fromJson(responseMsg.getData(), DownloadFile.class);
		}*/
		return responseMsg;
		
	}
}
