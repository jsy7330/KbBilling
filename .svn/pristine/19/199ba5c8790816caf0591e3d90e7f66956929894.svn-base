package com.ntels.ccbs.system.controller.common.common;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ntels.ccbs.common.util.FileUtil;

@Controller
@RequestMapping(value = "/system/common/common/fileMng")
public class FileMngController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${file.path}")
	private String filePath;
	
	@RequestMapping(value = "fileInsertAction", method = RequestMethod.POST)
	public void fileInsertAction(
	      Model model
	      , MultipartHttpServletRequest request
		  ) throws Exception {

		MultipartFile mf = request.getFile("fileName");
		
		String uuid = "";
		
		if(!mf.isEmpty()){
			
			try {
				if(!StringUtils.isEmpty(mf.getOriginalFilename())){
					uuid = FileUtil.writeUploadedFile(filePath,mf);
				}else{
					uuid="false";
				}
				
			} catch (Exception e) {
				logger.debug("FILE MAKE ERROR \t : {}",e.getMessage());
			}

		}
		
		model.addAttribute("uuid", uuid);
		
	}
	
	@RequestMapping("getFileAction")//URL호출
	public void getFileAction(@RequestParam("uuid") String uuid, @RequestParam("fileNm") String fileNm, HttpServletResponse response, HttpServletRequest request) throws Exception {
	     
		File file = new File(filePath+uuid);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		
		String header = getBrowser(request);
		if (header.contains("MSIE")) {
		       String docName = URLEncoder.encode(fileNm,"UTF-8").replaceAll("\\+", "%20");
		       response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
		} else if (header.contains("Firefox")) {
		       String docName = new String(fileNm.getBytes("UTF-8"), "ISO-8859-1");
		       response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
		} else if (header.contains("Opera")) {
		       String docName = new String(fileNm.getBytes("UTF-8"), "ISO-8859-1");
		       response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
		} else if (header.contains("Chrome")) {
		       String docName = new String(fileNm.getBytes("UTF-8"), "ISO-8859-1");
		       response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
		}
		
		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		byte b [] = new byte[1024];
		int data = 0;
		
		while((data=(fileInputStream.read(b, 0, b.length))) != -1)
		{
			servletOutputStream.write(b, 0, data);
		}
		
		servletOutputStream.flush();
		servletOutputStream.close();
		fileInputStream.close();
	 
	}
	
	private String getBrowser(HttpServletRequest request) {
        String header =request.getHeader("User-Agent");
        if (header.contains("MSIE")) {
               return "MSIE";
        } else if(header.contains("Chrome")) {
               return "Chrome";
        } else if(header.contains("Opera")) {
               return "Opera";
        }
        return "Firefox";
  }
}
