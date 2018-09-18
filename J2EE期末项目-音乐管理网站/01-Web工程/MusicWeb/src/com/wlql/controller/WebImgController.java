package com.wlql.controller;

import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wlql.domain.WebFile;
import com.wlql.serviceImpl.WebFileServiceImpl;
/**
 * 
 * @author Administrator
 * 显示歌手或专辑照片
 * 返回流给前端
 *
 */
@Controller
@RequestMapping({ "webImg" })
public class WebImgController {
	@Autowired
	WebFileServiceImpl webFileServiceImpl;
	
	@RequestMapping({ "showImg" })
	public void  showImg(@RequestParam String fileid,
			HttpServletResponse response) throws Exception{
		   WebFile webFile=webFileServiceImpl.getWebFileById(fileid);
		  
		   FileInputStream inputStream = new FileInputStream(webFile.getFilepath());
	        byte[] data = new byte[inputStream.available()];
	        int length = inputStream.read(data);
	        inputStream.close();


	        response.setContentType("image/png");


	        OutputStream stream = response.getOutputStream();
	        stream.write(data);
	        stream.flush();
	        stream.close();
		
		
	}

}
