package com.amass.credit.common.servlet;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

//import com.amass.credit.common.utils.dfs.DfsFilemaneger;
import com.amass.credit.modules.sys.utils.UserUtils;
import com.amass.credit.common.config.Global;

/**
 * 查看CK上传的图片
 * @author ThinkGem
 * @version 2014-06-25
 */
public class UserfilesDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());

	public synchronized void fileOutputStream(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String filepath = req.getRequestURI();
		int index = filepath.indexOf(Global.USERFILES_BASE_URL);
		if(index >= 0) {
			filepath = filepath.substring(index + Global.USERFILES_BASE_URL.length());
		}
		//判断登录
		if(null==UserUtils.getUser().getId()){

			req.setAttribute("exception", "用户未登录！");
			req.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(req, resp);
			return;
		}
		//DfsFilemaneger dfsmanager = new DfsFilemaneger();
		Map<String, byte[]> bmap = new HashMap<String, byte[]>();
		try {
			//bmap = dfsmanager.getfile(filepath);
		} catch (Exception e1) {
			req.setAttribute("exception", e1);
			req.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(req, resp);
			return ;
		}
		byte[] bytes = bmap.get(filepath);
		try {
			if(null == bytes ||bytes.length == 0)
				throw new FileNotFoundException("文件不存在");
			FileCopyUtils.copy(new ByteArrayInputStream(bytes), resp.getOutputStream());//保存
			resp.setHeader("Content-Type", "application/octet-stream");
			return;
		} catch (FileNotFoundException e) {
			req.setAttribute("exception", "请求的文件不存在");
			req.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		fileOutputStream(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		fileOutputStream(req, resp);
	}
}
