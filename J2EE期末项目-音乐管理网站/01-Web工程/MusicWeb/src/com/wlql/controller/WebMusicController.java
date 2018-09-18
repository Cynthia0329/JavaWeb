package com.wlql.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.wlql.domain.WebMusic;
import com.wlql.domain.WebUser;
import com.wlql.serviceImpl.WebAlbmServiceImpl;
import com.wlql.serviceImpl.WebFileServiceImpl;
import com.wlql.serviceImpl.WebMusicServiceImpl;
/**
 * 歌曲信息管理的控制器
 * @author jcy
 *
 */
//将class定义为一个controller类，注解用以定义URL 请求和Controller 方法之间的映射
@Controller
//在类前面定义，将url:webMusic和WebMusicController绑定
@RequestMapping({ "webMusic" })
public class WebMusicController {
	//自动帮你把bean里面引用的对象的setter/getter方法省略，它会自动帮你set/get
	//定义webMusicServiceImpl服务
	@Autowired
	WebMusicServiceImpl webMusicServiceImpl;
	//定义 webAlbmServiceImpl服务
	@Autowired
	WebAlbmServiceImpl webAlbmServiceImpl;
	
	//定义webFileServiceImpl服务
	@Autowired
	WebFileServiceImpl webFileServiceImpl;
	
	
	/**
	 * 删除歌曲方法
	 * @return
	 */
	//将url:"deleteWebMusic"和 deleteWebAlbm方法绑定
	//设置想要跳转的父路径
	@RequestMapping({"deleteWebMusic"})
	//@Responsebody 注解表示该方法的返回的结果直接写入 HTTP 响应正文（ResponseBody）中
	//加上 @Responsebody 后返回结果不会被解析为跳转路径，而是直接写入HTTP 响应正文中。
	@ResponseBody
	//从@RequestParam中提取形参
	public Result deleteWebAlbm(@RequestParam String music_id){
		//创建一个result对象
		 Result result=null;
		 if(webMusicServiceImpl.deleteWebMusicByMusicId(music_id)){
			 
			 result=new Result("1", "删除专辑信息成功");
		 }else{
			 result=new Result("-1", "删除专辑信息失败");
			 
		 }
		return result;
		
	}
	
	
	
	
	
	/**
	 * 取歌曲新增或者修改界面确定保存方法
	 * 
	 * 
	 * @return
	 */
	@RequestMapping({"WebMusicAddOrUpdateSubmit"})
	@ResponseBody
	public Result WebSingerAddOrUpdateSubmit(HttpServletRequest req,
			  HttpServletResponse resp,WebMusic webMusic){
		//HttpServletRequest对象代表客户端的请求，当客户端通过HTTP协议访问服务器时，HTTP请求头中的所有信息都封装在这个对象中，开发人员通过这个对象的方法，可以获得客户这些信息。
		//HttpServletResponse对象代表服务器的响应。这个对象中封装了向客户端发送数据、发送响应头，发送响应状态码的方法。
		Result result=null;
		String music_id=webMusic.getMusic_id();
		if(music_id==null||music_id.equals("")){
			//一个自动生成主键的方法
			music_id=UUID.randomUUID().toString();
			//生成主键
			webMusic.setMusic_id(music_id);
			if(webMusicServiceImpl.addWebMusic(webMusic)){
				 result=new Result("1", "添加专辑成功");
			}else{
				 result=new Result("-1", "添加专辑失败");
			};
		}else{
			if(webMusicServiceImpl.updateWebMusic(webMusic)){
				 result=new Result("1", "修改专辑信息成功");
			}else{
				result=new Result("-1", "修改专辑信息失败");
			};
		}	
		return result;
	}
	
	
	
	
	/**
	 * 分页查询操作方法								
	 */
	@RequestMapping({"selectAllWebMusicByPage"})
	//客户请求。页面回应，webAblm方法名，前台page值，前台row值
	  public  void  selectAllWebMusicByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  WebMusic webMusic,@RequestParam int page,@RequestParam int rows
			  ){
		
		Page<WebMusic> pager = new Page<WebMusic>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		if(webMusic == null){
			webMusic=new WebMusic();
		}
		// 这里设置一些常用参数,用于分页带条件查询
	
		 params.put("music_name", webMusic.getMusic_name());
		 params.put("music_albm_id", webMusic.getMusic_albm_id());
	   // params.put("userzt", webUser.getUserzt());
	    //params.put("useraddress", webUser.getUseraddress());
		pager.setParams(params);
		if(page==0){
			page=1;
		}
		pager.setPageNo(page);
		pager.setPageSize(rows);
		pager.setT(webMusic);
		ServiceToActionMsg<WebMusic> WebMusic = webMusicServiceImpl.selectAllWebMusicByPage(pager);
		if (WebMusic.getStatusCode()) {
			Response.reSponseJson(resp,WebMusic.getPage().toString());
		} else {
			Response.reSponseJson(resp,WebMusic.toJson());
		}

	}
	/**
	 * 解决前段日期类型传递时,到达不了后台
	 * @param binder
	 */
	//时间属性的编辑器，时间类型直接会报错
	@InitBinder  
	public void initBinder(WebDataBinder binder) { 
       //设置格式
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    //严格限制日期转换
	    dateFormat.setLenient(false);  
	  //注册一个时间编辑器，类型、转换类型、  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
/**
 * 播放音乐
 * @param music_id
 * @param response
 * @param request
 */
	@RequestMapping({"bofangMusic"})
	public void bofangMusic(@RequestParam String music_id,
			HttpServletResponse response,
			HttpServletRequest request){
		 //播放服务器的音频文件
		try{
			//得到音频的路径
		 WebFile webFile=webFileServiceImpl.getWebFileByPkId(music_id);
		 //音频流读取
		 OutputStream os = response.getOutputStream(); 
		 //使用FileInputStream来读取文件自身
		 //创建字节输入流
		 FileInputStream fis = new FileInputStream(webFile.getFilepath());
		 //获取文件大小
		 long length = webFile.getFilesize();
		 //添加头文件的长度为指定文件的长度
		 response.addHeader("Content-Length", length + "");        
		 //添加格式为音频文件
		 response.addHeader("Content-Type", "audio/mpeg;charset=UTF-8");   
		 int len = 0; 
		 //创建一个长度为1024的内存空间，1024足够大，可以一次将文件读取完，不会出现中文注释乱码问题 
		 byte[] b = new byte[1024]; 
		 //对于流来说，一般都会产生异常，所以要有异常处理程序
		 ////接收屏幕输入，存入b,同时读取的个数赋值给len，一个字节一个字节读这个文件，读到最后一个停止
		 while ((len = fis.read(b)) != -1) 
		 { 
			 // //屏幕输出b
			 os.write(b, 0, len);
		 };
		 //关闭输入流
		 fis.close(); 
		 //关闭输出流
		 os.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		
	}

	//下载歌曲
	@RequestMapping({"downloadMusic"})
	public ResponseEntity<byte[]> downloadMusic(HttpServletRequest request,@RequestParam String music_id
			) throws Exception{
		//得到音频的路径
		 WebFile webFile=webFileServiceImpl.getWebFileByPkId(music_id);
		//将该文件加入到输入流之中
		 InputStream in=new FileInputStream(new File(webFile.getFilepath()));
		//设置缓存
         byte[] body=null;
       // 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
         body=new byte[in.available()];
         in.read(body);//读入到输入流里面
        //防止中文乱码，字节流读取String数据
        String fileName=new String(webFile.getFilename().getBytes("gbk"),"iso8859-1");
        HttpHeaders headers=new HttpHeaders();//设置响应头
        headers.add("Content-Disposition", "attachment;filename="+fileName);
       //设置请求状态
        HttpStatus statusCode = HttpStatus.OK;//设置响应吗
        //用ResponseEntity返回文件给前端
        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
		
	}
	
	//视图
	/**
	 * 查看webMusicView界面与webMusicView.jsp对应
	 * @return
	 */
	@RequestMapping({"viewWebMusic"})
	public ModelAndView viewWebAlbm(HttpServletRequest req,
			  HttpServletResponse resp,@RequestParam String music_id){
		
	    
		WebMusic webMusic=null;
		ModelAndView modelAndView=new ModelAndView();
		//构造ModelAndView对象(ModelAndView向前台页面传值)
		//调用服务，查询歌曲详情,根据music_id获取WebMusic对象
		webMusic=webMusicServiceImpl.getWebMusicByMusicId(music_id);
		//向ModelAndView中添加数据
		//使用addObject()设置需要返回的值，addObject()有几个不同参数的方法，可以默认和指定返回对象的名字。
		modelAndView.addObject("webMusic", webMusic);
		//设置返回的视图名称
		//通过ModelAndView构造方法可以指定返回的页面名称，也可以通过setViewName()方法跳转到指定的页面 
		modelAndView.setViewName("manage/webMusicView");
		return modelAndView;//访问jsp文件
		
	}
	
	
	
	/**
	 * 取歌曲新增或者修改界面，webMusicAddOrUpdate界面与webMusicAddOrUpdate.jsp对应
	 * @return
	 */
	@RequestMapping({"toWebMusicAddOrUpdate"})
	public ModelAndView toWebMusicAddOrUpdate(HttpServletRequest req,
			  HttpServletResponse resp){
		//获取所有专辑列表
		List<WebAlbm> webAlbms=webAlbmServiceImpl.getAllWebAlbm();
		//定义实体类
		WebMusic webMusic=null;
		//定义一个新视图
		ModelAndView modelAndView=new ModelAndView();
		//传入形参
		String music_id=req.getParameter("music_id");
		if(music_id!=null){
			//根据music_id获取WebMusic对象
			webMusic=webMusicServiceImpl.getWebMusicByMusicId(music_id);
		}
		//向ModelAndView中添加WebMusic对象数据
		modelAndView.addObject("webMusic", webMusic);
		//向ModelAndView中添加WebAlbms对象数据，添加模型数据 可以是任意的POJO对象
		modelAndView.addObject("webAlbms", webAlbms);
		//设置返回的视图名称//返回的文件名
		modelAndView.setViewName("manage/webMusicAddOrUpdate");
		return modelAndView;//访问jsp文件
		
	}
	/**
	 * 返回到manage/webMusicManage
	 * 通过ModelAndView构造方法可以指定返回的页面名称，
	 * 也可以通过setViewName()方法跳转到指定的页面 , 
	 * 使用addObject()设置需要返回的值，
	 * addObject()有几个不同参数的方法，可以默认和指定返回对象的名字。
	 * @return
	 */
	@RequestMapping({"toWebMusicManage"})
	public ModelAndView toWebMusicManage(){
		
		ModelAndView modelAndView=new ModelAndView();
		//设置返回的视图名称
		modelAndView.setViewName("manage/webMusicManage");
		return modelAndView;		
	}
	
	
	/**
	 * 
	 * 返回到webuser/viewMusic
	 * 通过ModelAndView构造方法可以指定返回的页面名称，
	 * 也可以通过setViewName()方法跳转到指定的页面 , 
	 * 使用addObject()设置需要返回的值，
	 * addObject()有几个不同参数的方法，可以默认和指定返回对象的名字。
	 * @param music_id
	 * @return
	 */
	@RequestMapping({"toWebMusicDetail"})
	public ModelAndView toWebMusicDetail(@RequestParam String music_id){
		ModelAndView modelAndView=new ModelAndView();
		WebMusic webMusic=webMusicServiceImpl.getWebMusicByMusicId(music_id);
		//给webMusic添加数据
		modelAndView.addObject("webMusic", webMusic);
		//设置返回的视图名称
		modelAndView.setViewName("webuser/viewMusic");
		return modelAndView;
		
	}
	
	

}
