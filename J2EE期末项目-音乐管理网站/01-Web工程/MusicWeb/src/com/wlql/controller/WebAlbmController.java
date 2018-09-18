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
import com.wlql.domain.WebAlbm;
import com.wlql.domain.WebFile;
import com.wlql.domain.WebSinger;
import com.wlql.domain.WebUser;
import com.wlql.serviceImpl.WebAlbmServiceImpl;
import com.wlql.serviceImpl.WebFileServiceImpl;
import com.wlql.serviceImpl.WebSingerServiceImpl;

@Controller
@RequestMapping({ "webAlbm" })
public class WebAlbmController {
	//@Autowired是用在加载实现service实现类
	//自动帮你把bean里面引用的对象的setter/getter方法省略，它会自动帮你set/get
	//定义WebAlbmServiceImpl服务
	@Autowired 
	WebAlbmServiceImpl webAlbmServiceImpl;
	
	@Autowired
	WebSingerServiceImpl webSingerServiceImpl;
	
	@Autowired
	WebFileServiceImpl webFileServiceImpl;
	/**
	 * 删除专辑界面
	 * @return
	 */
	//设置想要跳转的父路径
	@RequestMapping({"deleteWebAlbm"})
	//注解表示该方法的返回的结果直接写入 HTTP 响应正文（ResponseBody）中，一般在异步获取数据时使用，通常是在使用 @RequestMapping 后，
	//返回值通常解析为跳转路径，加上 @Responsebody 后返回结果不会被解析为跳转路径，而是直接写入HTTP 响应正文中。
	@ResponseBody  
	 
	//@RequestParam是传递参数的.
	 //@RequestParam用于将请求参数区数据映射到功能处理方法的参数上。
	public Result deleteWebAlbm(@RequestParam String albm_id){
		//创建一个result实体对象
		 Result result=null;
		 if(webAlbmServiceImpl.deleteWebAlbmByAlbmId(albm_id)){			 
			 result=new Result("1", "删除专辑信息成功");
		 }else{
			 result=new Result("-1", "删除专辑信息失败");
			 
		 }
		return result;
		
	}
	
	/**
	 *   
	 *  
	 * 返回到webuser/viewAlbmDetail
	 * 通过ModelAndView构造方法可以指定返回的页面名称，
	 * 也可以通过setViewName()方法跳转到指定的页面 , 
	 * 使用addObject()设置需要返回的值，
	 * addObject()有几个不同参数的方法，可以默认和指定返回对象的名字。
	 * @param albm_id
	 * @return
	 */
	
	 
	@RequestMapping({"viewWebAlbmDetail"})
	//HttpServletRequest对象代表客户端的请求，当客户端通过HTTP协议访问服务器时，HTTP请求头中的所有信息都封装在这个对象中，开发人员通过这个对象的方法，可以获得客户这些信息。
	//HttpServletResponse对象代表服务器的响应。这个对象中封装了向客户端发送数据、发送响应头，发送响应状态码的方法。
	public ModelAndView viewWebAlbmDetail(HttpServletRequest req,
			  HttpServletResponse resp,@RequestParam String albm_id){
		List<WebFile> webFilelists=webFileServiceImpl.getWebFileListByPkId(albm_id); //数据列表
		WebAlbm webAlbm=null;
		//构造ModelAndView对象(ModelAndView向前台页面传值)
		//, 使用addObject()设置需要返回的值，addObject()有几个不同参数的方法，可以默认和指定返回对象的名字。
		ModelAndView modelAndView=new ModelAndView();
		webAlbm=webAlbmServiceImpl.getWebAlbmByAlbmId(albm_id);
		modelAndView.addObject("webAlbm", webAlbm);//添加模型数据 可以是任意的POJO对象
		modelAndView.addObject("webFilelists", webFilelists);
		//通过ModelAndView构造方法可以指定返回的页面名称，也可以通过setViewName()方法跳转到指定的页面 
		modelAndView.setViewName("webuser/viewAlbmDetail");
		return modelAndView;//访问jsp文件
		
	}
	
	/**
	 * 查看专辑信息（整个视图）
	 * @return
	 */
	@RequestMapping({"viewWebAlbm"})
	public ModelAndView viewWebAlbm(HttpServletRequest req,
			  HttpServletResponse resp,@RequestParam String albm_id){
		
		WebAlbm webAlbm=null;//result实体对象
		ModelAndView modelAndView=new ModelAndView();
		webAlbm=webAlbmServiceImpl.getWebAlbmByAlbmId(albm_id);
		modelAndView.addObject("webAlbm", webAlbm);//添加模型数据 可以是任意的POJO对象
		
		modelAndView.setViewName("manage/webAlbmView");//返回的文件名
		return modelAndView;//访问jsp文件
		
	}
	
	
	
	/**
	 * 取专辑新增修改界面	修改
	 * @return
	 */
	@RequestMapping({"toWebAlbmAddOrUpdate"})
	public ModelAndView toWebAlbmAddOrUpdate(HttpServletRequest req,
			  HttpServletResponse resp){
		//获取专辑id
		//获取歌手所有列表
		List<WebSinger> singers=webSingerServiceImpl.getAllSingers();
		
		WebAlbm webAlbm=null;
		ModelAndView modelAndView=new ModelAndView();//new一个model对象modelAndView
		//getParameter它是一种取参数的方法。把jsp文件中的数据读取到出来。然后就可以封装利用起来。
		String albm_id=req.getParameter("albm_id"); 
		if(albm_id!=null){
			//根据albm_id获取webAlbm对象
			webAlbm=webAlbmServiceImpl.getWebAlbmByAlbmId(albm_id);
		}
		modelAndView.addObject("webAlbm", webAlbm);
		modelAndView.addObject("singers", singers);
		modelAndView.setViewName("manage/webAlbmAddOrUpdate");
		return modelAndView;//返回model,view值
		
	}
	
	//增加专辑中的提交保存部分
	@RequestMapping({"WebAlbmAddOrUpdateSubmit"})
	@ResponseBody
	public Result WebSingerAddOrUpdateSubmit(HttpServletRequest req,
			  HttpServletResponse resp,WebAlbm webAlbm){
		//根据albm_id,判断是添加 还是修改
		Result result=null;//
		String albm_id=webAlbm.getAlbm_id();//获取
		//如果albm_id为空
		if(albm_id==null||albm_id.equals("")){
			//自动生成主键
			albm_id=UUID.randomUUID().toString();
			//赋值
			webAlbm.setAlbm_id(albm_id);
			if(webAlbmServiceImpl.addWebAlbm(webAlbm)){
				 result=new Result("1", "添加专辑成功");
			}else{
				 result=new Result("-1", "添加专辑失败");
			};
		}else{
			if(webAlbmServiceImpl.updateWebAlbm(webAlbm)){
				 result=new Result("1", "修改专辑信息成功");
			}else{
				result=new Result("-1", "修改专辑信息失败");
			};
		}	
		return result;
	}
	
	
	
	
	/**
	 * 返回到manage/webAlbmManage
	 * 通过ModelAndView构造方法可以指定返回的页面名称，
	 * 也可以通过setViewName()方法跳转到指定的页面 , 
	 * 使用addObject()设置需要返回的值，
	 * addObject()有几个不同参数的方法，可以默认和指定返回对象的名字。
	 * @return
	 */
	@RequestMapping({"toWebAlbmManage"})
	public ModelAndView toWebUserManage(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("manage/webAlbmManage");
		return modelAndView;
		
	}
	
	
	/**
	 * 分页查询操作
	 */
	@RequestMapping({"selectAllWebAlbmByPage"})
	//客户请求。页面回应，webAblm方法名，前台page值，前台row值
	  public  void  selectAllJgGyJsYdByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  WebAlbm webAlbm,@RequestParam int page,@RequestParam int rows
			  ){
		
		Page<WebAlbm> pager = new Page<WebAlbm>();//？
		HashMap<String, Object> params = new HashMap<String, Object>();//？
		if(webAlbm == null){//
			webAlbm=new WebAlbm();
		}
		// 这里设置一些常用参数,用于分页带条件查询
		// 这里设置一些常用参数,用于分页带条件查询
	
		 params.put("albm_name", webAlbm.getAlbm_name());
		 params.put("singer_id", webAlbm.getSinger_id());
	   // params.put("userzt", webUser.getUserzt());
	    //params.put("useraddress", webUser.getUseraddress());
		pager.setParams(params);
		if(page==0){
			page=1;
		}
		pager.setPageNo(page);
		pager.setPageSize(rows);
		pager.setT(webAlbm);
		ServiceToActionMsg<WebAlbm> WebAlbms = webAlbmServiceImpl.selectAllWebAlbmByPage(pager);
		if (WebAlbms.getStatusCode()) {
			Response.reSponseJson(resp,WebAlbms.getPage().toString());
		} else {
			Response.reSponseJson(resp,WebAlbms.toJson());
		}

	}
	/**
	 * 解决前段日期类型传递时,到达不了后台
	 * @param binder
	 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
		//SimpleDateFormat日期定义
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}

}
