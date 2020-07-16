package com.ntels.ccbs.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {
	
	public DownloadView() {
		//setContentType("application/download; charset=utf-8");
		super.setContentType("application/octet-stream");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		File file = (File)model.get("downloadFile");
		String fileName = (String)model.get("downloadFileName");
		
		System.out.println("8888888");
		System.out.println(file);
		System.out.println(fileName);
		System.out.println("9999999");

		
		if (file.exists()){
			response.setContentType(getContentType());
			response.setContentLength((int)file.length());
			
			String userAgent = request.getHeader("User-Agent");
			boolean ie = userAgent.indexOf("MSIE") > -1;
			
			if(ie) {
				fileName = URLEncoder.encode(fileName, "utf-8");
			} else {
				fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
			}
			
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			OutputStream out = response.getOutputStream();
			FileInputStream fis = null;
			
			try {
				fis = new FileInputStream(file);
				FileCopyUtils.copy(fis, out);
			} finally {
				if(fis != null) {
					try {
						fis.close();
					} catch(IOException ioe) {}
				}
			}
			out.flush();
		}
	}

}
