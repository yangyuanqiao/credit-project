package com.amass.credit.common.utils.portalapi;

import java.io.File;
import java.util.ArrayList;

import com.amass.credit.common.utils.FormFileKeyValuePair;
import com.amass.credit.common.utils.UploadFileItem;
import com.amass.credit.common.utils.UploadUtils;
import com.amass.credit.common.config.Global;
import com.facegarden.base.common.utils.Str;
import com.google.gson.Gson;

public class UploadAPI extends BaseAPI {
	/**
	 * 调用文件上传接口,请求路径：http://localhost:8089/api/fastdfs/upload
	 * ;需要填写syscode通行证号
	 * @param filepaths 需要文件的路径,使用","隔开
	 * @return
	 */
	public ResponseMsg upload(String filepaths) throws Exception{
		UploadUtils uploadUtils = new UploadUtils();
		//需要上传的文件集合
		String[] filepath = filepaths.split(",");
		for(int i=0,length=filepath.length;i<length;i++){
			UploadFileItem ufi = new UploadFileItem("file", filepath[i]);
			ufis.add(ufi);
		}
		String msg = uploadUtils.sendHttpPostRequest(ffkvpAlist, ufis, Global.getConfig("portal.server")+UploadUtils.UPLOAD_API);
		Gson gson = new Gson();
		ResponseMsg responseMsg = gson.fromJson(msg, ResponseMsg.class);
		//只取data中的Calendar
		if(responseMsg.getData().contains("\":\"")){
			String data = responseMsg.getData().substring(responseMsg.getData().indexOf("\":\""))+3;
			responseMsg.setData(data);
		}
		return responseMsg;
	}
	/**
	 * 调用文件上传接口,请求路径：http://localhost:8089/api/fastdfs/upload
	 * ;需要填写syscode通行证号
	 * @param filepaths 需要文件的路径,使用","隔开
	 * @return
	 */
	public ResponseMsg upload(ArrayList<File> files) throws Exception{
		UploadUtils uploadUtils = new UploadUtils();
		//通行证号
		FormFileKeyValuePair ffkvptype = new FormFileKeyValuePair("type", "1");
		ffkvpAlist.add(ffkvptype);
		//需要上传的文件集合
		for(int i=0,length=files.size();i<length;i++){
			UploadFileItem ufi = new UploadFileItem("file", files.get(i),files.get(i).getName());
			ufis.add(ufi);
		}
		String msg = uploadUtils.sendHttpPostRequest(ffkvpAlist, ufis, Global.getConfig("portal.server")+UploadUtils.UPLOAD_API);
		Gson gson = new Gson();
		ResponseMsg responseMsg = gson.fromJson(msg, ResponseMsg.class);
		//只取data中的Calendar
		if(!Str.IsEmpty(responseMsg.getData())&&responseMsg.getData().contains("\":\"")){
			String data = responseMsg.getData().substring(responseMsg.getData().indexOf("\":\""))+3;
			responseMsg.setData(data);
		}
		return responseMsg;
	}
}
