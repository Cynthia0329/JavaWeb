package com.yzgs.controller;

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
import com.yzgs.domain.Good;
import com.yzgs.domain.GoodCategory;
import com.yzgs.domain.OtherProject;
import com.yzgs.domain.Page;
import com.yzgs.domain.Response;
import com.yzgs.domain.Result;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.GoodService;
import com.yzgs.service.OtherProjectService;
import com.yzgs.serviceimpl.OtherProjectServiceImpl;

/**
 * 其他支出项目结算管理界面
 * 
 */

@Controller
@RequestMapping({ "otherProject" })
//跳转到其他支出项目界面
public class OtherProjectController {
	
	@Autowired
	//@Autowired可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 
	//引入otherProjectService
	OtherProjectService otherProjectService;
	
	/**
	 * 取商品管理主界面
	 * @return
	 */
	@RequestMapping({"toOtherProjectManage"})
	public ModelAndView toOtherProjectManage(){
		//实例化一个视图
		ModelAndView modelAndView=new ModelAndView();
		//通过setViewName跳转到制定界面
		modelAndView.setViewName("admin/otherProjectManage");
		//返回视图
		return modelAndView;
		
	}
	
	/**
	 * 分页查询操作
	 */
	@RequestMapping({"selectAllOtherProjectByPage"})
	//跳转到分页查询操作页
	  public  void  selectAllOtherProjectByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  OtherProject otherProject,@RequestParam int page,@RequestParam int rows
			  ){
				//传入页数和行数两参数
		//实例化一个pager
		Page<OtherProject> pager = new Page<OtherProject>();
		//一个HashMap聚集,接受key值类型为String,value值类型为Object的数据.
		HashMap<String, Object> params = new HashMap<String, Object>();
		//其他支出项目为空
		if(otherProject == null){
			//创建一个新对象
			otherProject=new OtherProject();
		}
		//params.put():覆盖先前存在的 param 与现有的键
		//不为空，获取其他项目名称
		 params.put("projectName", otherProject.getProjectName());
	  
		pager.setParams(params);
		//若页数为0
		if(page==0){
			//返回页数1
			page=1;
		}
		//创建pager的页数
		pager.setPageNo(page);
		//创建pager的行数
		pager.setPageSize(rows);
		//创建pager的其他支出项目
		pager.setT(otherProject);
		//通过otherProjectService类中的selectAllOtherProjectByPage方法查询所有其他支出项目赋值到otherProjects
		ServiceToActionMsg<OtherProject> otherProjects = otherProjectService.selectAllOtherProjectByPage(pager);
		//若存在其他支出项目的状态码
		if (otherProjects.getStatusCode()) {
			//获取其他支出项目页数
			Response.reSponseJson(resp,otherProjects.getPage().toString());
		} else {
			//否则格式化为 JSON 数据格式
			Response.reSponseJson(resp,otherProjects.toJson());
		}

	}
	
	/**
	 * 删除其他支出项目
	 * @return
	 */
	@RequestMapping({"deleteOtherProject"})
	//跳转到删除其他支出项目页面
	@ResponseBody
	public Result deleteOtherProject(@RequestParam String id){
		//实例化一个空的result
		 Result result=null;
		 //若所删除其他支出项目id存在
		 if(otherProjectService.deleteOtherProjectById(id)){
			 //删除成功，返回1
			 result=new Result("1", "删除其他支出项目成功");
		 }else{
			 //否则删除失败，返回-1
			 result=new Result("-1", "删除其他支出项目失败");
			 
		 }
		 //返回result
		return result;
		
	}
	
	
	/**
	 * 去支出项目信息查看页面
	 * @return
	 */
	@RequestMapping({"viewOtherProjectDetail"})
	//跳转到其他项目支出详情页界面
	public ModelAndView viewOtherProjectDetail(HttpServletRequest req,
			  HttpServletResponse resp,@RequestParam String id){
		//实例化一个otherProject
		OtherProject otherProject=null;
		//业务处理器调用模型层处理完用户请求后，把结果数据存储在该类的model属性中，
		//把要返回的视图信息存储在该类的view属性中，然后让该ModelAndView返回该Spring MVC框架。
		//框架通过调用配置文件中定义的视图解析器，对该对象进行解析，最后把结果数据显示在指定的页面上。 
		
		//实例化一个modelAndView类
		ModelAndView modelAndView=new ModelAndView();
		//获取其他支出项目id
		otherProject=otherProjectService.getOtherProjectById(id);
		//将此项目添加进modelAndView类
		modelAndView.addObject("otherProject", otherProject);
		//通过setViewName方法跳转到指定界面
		modelAndView.setViewName("admin/otherProjectView");
		//返回modelAndView
		return modelAndView;
		
	}
	
	/**
	 * 取其他项目支出新增或者修改界面
	 * @return
	 */
	//RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上
	@RequestMapping({"toOtherProjectAddOrUpdate"})
	//跳转到其他支出项目更新或修改界面
	public ModelAndView toOtherProjectAddOrUpdate(HttpServletRequest req,
			  HttpServletResponse resp){
		//实例化一个otherProject
		OtherProject otherProject=null;
		//创建视图
		ModelAndView modelAndView=new ModelAndView();
		//获取对应id
		String id=req.getParameter("id");
		//若id不为空
		if(id!=null&&!id.equals("")){
			//获取此项目id
			otherProject=otherProjectService.getOtherProjectById(id);
		}
		//将此项目添加进modelAndView类
		modelAndView.addObject("otherProject", otherProject);
		//设置指定跳转界面
		modelAndView.setViewName("admin/otherProjectAddOrUpdate");
		//返回类
		return modelAndView;
		
	}
	
	
	
	//新增或者修改商品提交操作操作
			@RequestMapping({"OtherProjectAddOrUpdateSubmit"})
			@ResponseBody
			public Result OtherProjectAddOrUpdateSubmit(HttpServletRequest req,
					  HttpServletResponse resp,OtherProject otherProject){
				//实例化一个result
				Result result=null;
				//获取此项目id
				String otherProject_id=otherProject.getId();
				//若项目id为空
				if(otherProject_id==null||otherProject_id.equals("")){
					//UUID.randomUUID().toString()是javaJDK提供的一个自动生成主键的方法
					//使用主键方法生成项目id
					otherProject_id=UUID.randomUUID().toString();
					//设置项目id
					otherProject.setId(otherProject_id);
					//如果添加操作返回treu
					if(otherProjectService.addOtherProject(otherProject)){
						//则添加成功，返回1
						 result=new Result("1", "添加其他支出项目信息成功");
					}else{
						//否则失败，返回-1
						 result=new Result("-1", "添加其他支出项目信息失败");
					};
				}else{
					//如果更新操作返回true
					if(otherProjectService.updateOtherProject(otherProject)){
						//更新成功，返回1
						 result=new Result("1", "修改其他支出项目信息成功");
					}else{
						//否则更新失败，返回-1
						result=new Result("-1", "修改其他支出项目信息失败");
					};
				}	
				//返回result
				return result;
			}
		
		
	/**
	 * 解决前段日期类型传递时,到达不了后台
	 * @param binder
	 */
	@InitBinder  
	//实现表单中的日期字符串和JavaBean的Date类型的转换
	public void initBinder(WebDataBinder binder) {  
		//实例化一个dateFormat
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    //判断字符串是不是一个合法的日期格式
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}

}
