package com.amass.credit.common.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.amass.credit.common.config.Global;

/**
 * 文件上传工具类
 * 
 * @author liangzw
 * 
 * @generalFormFields 普通表单的集合
 * @filesToBeUploaded 表单中的文件集合
 * 
 */
public class UploadUtils {
	
	public  static final String UPLOAD_API = "/api/fastdfs/upload";
	
	public  static final String DOWNLOAD_API = "/api/fastdfs/download";
	
	private static final String BOUNDARY = "--------------------HV2ymHFg03ehbqgZCaK06jyH";
	
	public String sendHttpPostRequest(ArrayList<FormFileKeyValuePair> generalFormFields,
			ArrayList<UploadFileItem> filesToBeUploaded,String urlstr) throws Exception{
		URL url = new URL(urlstr);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setRequestProperty("Charset", "UTF-8");
		connection.setRequestProperty("Content-Type", "multipart/form-data; boundary="+BOUNDARY);//分离各个参
		
		String boundary = BOUNDARY;
		
		StringBuffer contentBody = new StringBuffer("--" + BOUNDARY);
		
		String endBoundary = "\r\n--" + boundary + "--\r\n";
		OutputStream out = connection.getOutputStream();
		
		//文字
		for(FormFileKeyValuePair ffkvp : generalFormFields){
			contentBody.append("\r\n")
			.append("Content-Disposition:form-data; name=\"")
			.append(ffkvp.getKey()+"\"")
			.append("\r\n")
			.append("\r\n")
			.append(ffkvp.getValue())
			.append("\r\n")
			.append("--")
			.append(boundary);
		}
		String boundaryMessage1 = contentBody.toString();
		out.write(boundaryMessage1.getBytes("utf-8"));
		//文件
		for(UploadFileItem ufi : filesToBeUploaded){
			contentBody = new StringBuffer();
			contentBody.append("\r\n")
			.append("Content-Disposition:form-data; name=\"")
			.append(ufi.getFormFileName() + "\";")//filename
			.append("filename=\"")
			.append(ufi.getFileName() + "\"")//上传文件名，包含目录
			.append("\r\n")
			.append("Content-Type:application/octet-stream")
			.append("\r\n\r\n");
			String boundaryMessage2 = contentBody.toString();
			out.write(boundaryMessage2.getBytes("utf-8"));
			//向服务器写文件
			File file = null;
			if(ufi.getFile()==null)
				file = new File(ufi.getFileName());
			else
				file = ufi.getFile();
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[(int)file.length()];
			bytes = dis.read(bufferOut);
			out.write(bufferOut,0,bytes);
			dis.close();
			contentBody.append("--------------------HV2ymHFg03ehbqgZCaK06jyH");
			String boundaryMessage = contentBody.toString();
			out.write(boundaryMessage.getBytes("utf-8"));
		}
		out.write("--------------------HV2ymHFg03ehbqgZCaK06jyH--\r\n".getBytes("utf-8"));
		//报文结尾
		out.write(endBoundary.getBytes("utf-8"));
		out.flush();
		out.close();
		//获取应答
		String strLine = "";
		String strResponse = "";
		InputStream in = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		while((strLine = reader.readLine()) != null){
			strResponse += strLine + "\n";
		}
		System.out.println(strResponse);
		return strResponse;
		
	}
}
