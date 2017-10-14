/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//import com.amass.credit.common.utils.dfs.DfsFilemaneger;
import com.amass.credit.common.web.BaseController;
import com.amass.credit.common.config.Global;
import com.facegarden.base.common.utils.Str;

/**
 * 用户Controller
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/ckeditor")
public class CkeditorController extends BaseController {

	// 定义允许上传的文件扩展名
		private Map<String, String> extMap = new HashMap<String, String>();
		
		
		

		/**
		 * 图片，上传method已改为由fastDFS
		 * @param multipartFile
		 * @param request
		 * @param response
		 * @throws Exception 
		 */
		@RequestMapping(value="imgupload")
	    public void imgupload(@RequestParam("upload") MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) throws Exception{
	        response.setContentType("text/html;charset=UTF-8");
	        String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
	        PrintWriter out = null;
	        
	        String url = "";
	        
	        try {
	            out = response.getWriter();
	            
	        	//上传dfs
	        	//DfsFilemaneger dfsman = new DfsFilemaneger();
	        	MultipartFile[] files  = {
	        			multipartFile
	        	};
	        	Map<String,Object> result = null;
	        	//若上传错误，返回错误信息
	        	if(!Str.IsEmpty(result.get("errormsg")+"")){
	        		out.print(result.get("errormsg"));
	                out.flush();
	                return;
	        	}
	        	
	        	url = "http://" + Global.getConfig("tracker.host") +":"+Global.getConfig("TrackerStorage.port") + "/" + result.get("url");
	        	String s = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+", '"+ url +"');</script>";
		        
	            
	            out.print(s);
	            out.flush();
	        } finally {
	            if(out != null)
	            {
	            	out.close();
	            }
	        }
	         
	         
	    }
}
