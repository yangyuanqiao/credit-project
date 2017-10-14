package com.amass.credit.common.utils;

import java.io.File;
import java.io.Serializable;

/**
 * 任意filename或 file有值就行
 * @author liangzw
 *
 */
public class UploadFileItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String formFileName;
	private String fileName;//文件路径
	
	private File file;
	
	public UploadFileItem(String formFileName,String fileName){
		this.fileName = fileName;
		this.formFileName = formFileName;
	}
	/**
	 * 直接将File对象推送到接口
	 * @param formFileName
	 * @param file
	 * @param filename 这里就充当，
	 */
	public UploadFileItem(String formFileName,File file,String filename){
		this.file = file;
		this.formFileName = formFileName;
		this.fileName = filename;
	}

	public String getFormFileName() {
		return formFileName;
	}

	public void setFormFileName(String formFileName) {
		this.formFileName = formFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
