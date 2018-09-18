package com.wlql.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wlql.domain.Page;
import com.wlql.domain.Response;
import com.wlql.domain.Result;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebFile;
import com.wlql.domain.WebSinger;
import com.wlql.serviceImpl.WebFileServiceImpl;
import com.wlql.serviceImpl.WebSingerServiceImpl;

@Controller
@RequestMapping({"webSinger"})
public class WebSingerController {
	@Autowired
	WebSingerServiceImpl webSingerServiceImpl;
	@Autowired
    WebFileServiceImpl webFileServiceImpl;
	/**
	 * 删除歌手界面
	 * @return
	 */
	@RequestMapping({"deleteWebSinger"})
	@ResponseBody
	public Result deleteWebSinger(@RequestParam String singer_id){
		//创建一个实体类
		 Result result=null;
		 if(webSingerServiceImpl.deleteWebSingerBySingerId(singer_id)){
			 
			 result=new Result("1", "删除歌手信息成功");
		 }else{
			 result=new Result("-1", "删除歌手信息失败");
			 
		 }
		return result;
		
	}
	
	
	
	/**
	 * 查看歌手信息
	 * @return
	 */
	@RequestMapping({"viewWebSinger"})
	//使用ModelAndView类用来存储处理完后的结果数据，以及显示该数据的视图
	//ModelAndView中的Model代表模型，View代表视图
	public ModelAndView viewWebSinger(HttpServletRequest req,
			  HttpServletResponse resp,@RequestParam String singer_id){
		
		WebSinger webSinger=null;
		ModelAndView modelAndView=new ModelAndView();
		webSinger=webSingerServiceImpl.getWebSingerBySingerId(singer_id);
		modelAndView.addObject("webSinger", webSinger);
		modelAndView.setViewName("manage/webSingerView");
		return modelAndView;
		
	}
	
	
	
	/**
	 * 取歌手新增或者修改界面
	 * @return
	 */
	@RequestMapping({"toWebSingerAddOrUpdate"})
	public ModelAndView toWebSingerAddOrUpdate(HttpServletRequest req,
			  HttpServletResponse resp){
		//获取歌手id
		WebSinger webSinger=null;
		ModelAndView modelAndView=new ModelAndView();
		String singer_id=req.getParameter("singer_id");
		if(singer_id!=null){
			//根据singer_id获取WebSinger对象
			webSinger=webSingerServiceImpl.getWebSingerBySingerId(singer_id);
		}
		modelAndView.addObject("webSinger", webSinger);
		modelAndView.setViewName("manage/webSingerAddOrUpdate");
		return modelAndView;
		
	}
	
	
	@RequestMapping({"WebSingerAddOrUpdateSubmit"})
	@ResponseBody
	public Result WebSingerAddOrUpdateSubmit(HttpServletRequest req,
			  HttpServletResponse resp,WebSinger webSinger){
		//根据webSinger对象的singer_id,判断是添加 还是修改
		Result result=null;
		String singer_id=webSinger.getSinger_id();
		if(singer_id==null||singer_id.equals("")){
			//UUID.randomUUID().toString()是javaJDK提供的一个自动生成主键的方法
			singer_id=UUID.randomUUID().toString();
			webSinger.setSinger_id(singer_id);
			if(webSingerServiceImpl.addWebSinger(webSinger)){
				 result=new Result("1", "添加歌手成功");
			}else{
				 result=new Result("-1", "添加歌手失败");
			};
		}else{
			if(webSingerServiceImpl.updateWebSinger(webSinger)){
				 result=new Result("1", "修改歌手信息成功");
			}else{
				result=new Result("-1", "修改歌手信息失败");
			};
		}	
		return result;
	}
	
	
	/**
	 * 去专辑管理界面
	 * @return
	 */
	@RequestMapping({"toWebSingerManage"})
	public ModelAndView toWebUserManage(){
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("manage/webSingerManage");
		return modelAndView;
		
	}
	
	
	/**
	 * 去歌手查询详情界面
	 * @return
	 */
	@RequestMapping({"toViewSinger"})
	public ModelAndView toWebSingerView(@RequestParam String singer_id){
		WebSinger webSinger=webSingerServiceImpl.getWebSingerBySingerId(singer_id);
		List<WebFile> webFilelists=webFileServiceImpl.getWebFileListByPkId(singer_id);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("webSinger",webSinger);
		modelAndView.addObject("webFilelists",webFilelists);
		modelAndView.setViewName("webuser/viewSingerDetail");
		return modelAndView;
		
	}
	
	
	/**
	 * 分页查询操作,还需要接受页面传过来的参数
	 */
	@RequestMapping({"selectAllWebSingerByPage"})
	  public  void  selectAllWebSingerByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  WebSinger webSinger,@RequestParam int page,@RequestParam int rows
			  ){
		
		Page<WebSinger> pager = new Page<WebSinger>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		
	
	    
		params.put("singer_name", webSinger.getSinger_name());
		pager.setParams(params);
		if(page==0){
			page=1;
		}
		pager.setPageNo(page);
		pager.setPageSize(rows);
		pager.setT(webSinger);
		ServiceToActionMsg<WebSinger> WebSinger = webSingerServiceImpl.selectAllWebSingerByPage(pager);
		if (WebSinger.getStatusCode()) {
			Response.reSponseJson(resp,WebSinger.getPage().toString());
		} else {
			Response.reSponseJson(resp,WebSinger.toJson());
		}

	}
	/**
	 * 解决前段日期类型传递时,到达不了后台
	 * @param binder
	 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}

}
