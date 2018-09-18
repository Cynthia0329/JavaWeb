package com.wlql.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.wlql.domain.Manage;
import com.wlql.domain.Page;
import com.wlql.domain.Response;
import com.wlql.domain.Result;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebSinger;
import com.wlql.domain.WebUser;
import com.wlql.serviceImpl.WebUserServiceImpl;
import com.wlql.util.Md5Util;

@Controller
@RequestMapping({"webUser"})
public class WebUserController {
	@Autowired
	WebUserServiceImpl webUserServiceImpl;
	
	//进入用户管理页面
	@RequestMapping({"toWebUserManage"})
	public ModelAndView toWebUserManage(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("manage/webUserManage");
		return modelAndView;
		
	}
	
	
	/**
	 * 分页查询操作
	 */
	@RequestMapping({"selectAllWebUserByPage"})
	  public  void  selectAllWebUserByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  WebUser webUser,@RequestParam int page,@RequestParam int rows
			  ){
		
		Page<WebUser> pager = new Page<WebUser>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		// 这里设置一些常用参数,用于分页带条件查询

	    params.put("username", webUser.getUsername());
	    params.put("userzt", webUser.getUserzt());
	    params.put("useraddress", webUser.getUseraddress());
		pager.setParams(params);
		pager.setPageNo(page);
		pager.setPageSize(rows);
		pager.setT(webUser);
		ServiceToActionMsg<WebUser> WebUsers = webUserServiceImpl.selectAllWebUserByPage(pager);
		if (WebUsers.getStatusCode()) {
			Response.reSponseJson(resp,WebUsers.getPage().toString());
		} else {
			Response.reSponseJson(resp,WebUsers.toJson());
		}

	}
	
	/**
	 * 删除歌手界面
	 * @return
	 */
	@RequestMapping({"deleteWebUser"})
	@ResponseBody
	public Result deleteWebAlbm(@RequestParam String userid){
		 Result result=null;
		 if(webUserServiceImpl.deleteWebUserByUserId(userid)){
			 
			 result=new Result("1", "删除用户信息成功");
		 }else{
			 result=new Result("-1", "删除用户信息失败");
			 
		 }
		return result;
		
	}
	
	/**
	 * 取歌手新增或者修改界面
	 * @return
	 */
	@RequestMapping({"viewWebUser"})
	public ModelAndView viewWebUser(HttpServletRequest req,
			  HttpServletResponse resp,@RequestParam String userid){
		
		WebUser webUser=null;
		ModelAndView modelAndView=new ModelAndView();
		webUser=webUserServiceImpl.getWebUserByUserId(userid);
		modelAndView.addObject("webUser", webUser);
		modelAndView.setViewName("manage/webUserView");
		return modelAndView;
		
	}
	/**
	 * 审核用户状态
	 * @return
	 */
	@RequestMapping({"shWebUser"})
	@ResponseBody
	public Result shWebUser(@RequestParam String userid){
		 Result result=null;
		 if(webUserServiceImpl.shWebUserByUserId(userid)){
			 
			 result=new Result("1", "审核用户信息成功");
		 }else{
			 result=new Result("-1", "审核用户信息失败");
			 
		 }
		return result;
		
	}
	
	
	/**
	 * 注册用户控制器
	 * 审核用户状态
	 * @return
	 */
	@RequestMapping({"WebUserRegister"})
	@ResponseBody
	public Result webUserRegister(WebUser webUser){
		 Result result=null;
		 webUser.setUserid(UUID.randomUUID().toString());
		 webUser.setUserzt("0");//默认为待审核
		 webUser.setPassword(Md5Util.getMD5(webUser.getPassword()));
		 if(webUserServiceImpl.insertWebUser(webUser)){
			 
			 result=new Result("1", "注册用户信息成功");
		 }else{
			 result=new Result("-1", "注册用户信息失败");
			 
		 }
		return result;
		
	}
	
	/** 
	 * 用户登录控制器
	 * @return
	 */
	@RequestMapping({"login"})
	@ResponseBody
	public Result manageLogin(HttpServletRequest req,HttpServletResponse resp,
			@RequestParam String account,@RequestParam String password,@RequestParam String code){
		  
		       Result result=null;
		     //第一步,进行验证吗校验
		     if(!req.getSession().getAttribute("indentifyCode").equals(code)){
		    	 //验证码不一致
		    	 result=new Result("-1","验证码错误");
		     }else{
		    	 //根据账号获取Manage实体
		    	 WebUser webUser=webUserServiceImpl.getWebUserByAccount(account);
		    	 if(webUser==null){
		    		 //账号不存在
		    		 result=new Result("-1","账号不存在");
		    	 }else{
		    		 //进行密码判断
		    		 if(!webUser.getPassword().equals(Md5Util.getMD5(password))){
		    			 
		    			 //密码错误
		    			result=new Result("-1","密码错误");
		    		 }else if(webUser.getUserzt().equals("0")){
		    			 result=new Result("-1","账号未审核");
		    			 
		    		 } else{
		    			 
		    			//登录成功,将Manage对象放人session里面
		    			 req.getSession().setAttribute("webUser", webUser);
		    			 result=new Result("1","登录成功");
		    		 }
		    	 }
		    	 
		     }
		  return result;
		
	}
	
	
	/**
	 * 主页面
	 */
	@RequestMapping({"home"})
	public ModelAndView home(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		 ModelAndView modelAndView=new ModelAndView();
		 modelAndView.setViewName("webuser/home");
		 return modelAndView;
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

	
	/**
	 * 进入歌曲列表页面
	 * @return
	 */
	@RequestMapping({"toSearchAllMusic"})
	public ModelAndView toSearchAllMusic(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("webuser/searchAllMusic");
		return modelAndView;
		
	}
	
	/**
	 * 进入专辑列表页面
	 * @return
	 */
	@RequestMapping({"toSearchAllAlbm"})
	public ModelAndView toSearchAllAlbm(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("webuser/searchAllAlbm");
		return modelAndView;
		
	}
	
	/**
	 * 进入歌手列表页面
	 * @return
	 */
	@RequestMapping({"toSearchAllSinger"})
	public ModelAndView toSearchAllSinger(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("webuser/searchAllSinger");
		return modelAndView;
		
	}
	
	/**
	 * 进入用户信息修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping({"toWebUserUpdate"})
	public ModelAndView toWebUserUpdate(HttpServletRequest request){
		ModelAndView modelAndView=new ModelAndView();
		WebUser webUser=(WebUser) request.getSession().getAttribute("webUser");
		modelAndView.addObject("webUser", webUser);
		modelAndView.setViewName("webuser/webUserUpdate");
		return modelAndView;
		
	}
	
	
	/**
	 * 用户信息更新
	 * @return
	 */
	@RequestMapping({"updateWebUserSubmit"})
	@ResponseBody
	public Result webUserUpdatsubmit(WebUser webUser,HttpServletRequest request){
		 Result result=null;
		 if(!webUser.getPassword().equals("")){
			  //密码不为空,说明用户要修改密码
			 webUser.setPassword(Md5Util.getMD5(webUser.getPassword()));
		 }
		 if(webUserServiceImpl.updateWebUser(webUser)){
			 request.getSession().setAttribute("webUser", webUser);
			 result=new Result("1", "修改用户信息成功");
		 }else{
			 result=new Result("-1", "修改用户信息失败");
			 
		 }
		return result;
		
	}
	
	/**
	 * 退出登录,清除session里面的值
	 * @param req
	 * @param resp
	 */
	@RequestMapping({"logout"})
	public void logout(HttpServletRequest req,HttpServletResponse resp){
		try{
		    req.getSession().removeAttribute("webUser");
		    resp.sendRedirect(req.getContextPath()+"/userlogin.jsp"); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
