package com.wlql.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wlql.domain.Page;
import com.wlql.domain.Response;
import com.wlql.domain.Result;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebFile;
import com.wlql.domain.WebUser;
import com.wlql.serviceImpl.WebFileServiceImpl;

@Controller
@RequestMapping({"webUpload"})
public class WebUploadController {
	@Autowired
	WebFileServiceImpl webFileServiceImpl;
	

	/**
	 * 删除文件
	 * @return
	 */
	@RequestMapping({"deleteFile"})
	@ResponseBody
	public Result deleteWebFile(@RequestParam String file_pk_id){
		Result result=null;
		if(webFileServiceImpl.deleteWebFile(file_pk_id)){
			
			result=new Result("1","文件删除成功");
		}else{
			
			result=new Result("-1","文件删除失败");
			
		}
		return result;
	}
	
	
	/**
	 * 传入外键,去上传界面
	 * @return
	 */
	@RequestMapping({"toUploadFile"})
	public ModelAndView toWebUpload(@RequestParam String file_pk_id){
		 ModelAndView modelAndView=new ModelAndView();
		 modelAndView.setViewName("manage/common_uploadfy");
		 modelAndView.addObject("file_pk_id", file_pk_id);
		
		 return modelAndView;
	}
	
	
	/**
	 * 分页查询操作
	 */
	@RequestMapping({"selectAllWebFileByPage"})
	  public  void  selectAllWebFileByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  WebFile WebFile,@RequestParam int page,@RequestParam int rows,@RequestParam String file_pk_id
			  ){
		
		Page<WebFile> pager = new Page<WebFile>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("file_pk_id", file_pk_id);

		pager.setParams(params);
		pager.setPageNo(page);
		pager.setPageSize(rows);
		pager.setT(WebFile);
		ServiceToActionMsg<WebFile> WebFiles = webFileServiceImpl.selectAllWebFileByPage(pager);
		if (WebFiles.getStatusCode()) {
			Response.reSponseJson(resp,WebFiles.getPage().toString());
		} else {
			Response.reSponseJson(resp,WebFiles.toJson());
		}

	}
	
	/**
	 * 处理文件上传
	 */
	@RequestMapping({"FileUpload"})
	@ResponseBody
	public Result FileUpload(@RequestParam("fileupload") MultipartFile[] file,@RequestParam String file_pk_id){
		Result result=null;
		if(webFileServiceImpl.FileUpload(file,file_pk_id)){
			
			result=new Result("1","文件上传成功");
		}else{
			result=new Result("-1","文件上传失败");
			
		};
		
		return result;
	}
	
	
	

}
