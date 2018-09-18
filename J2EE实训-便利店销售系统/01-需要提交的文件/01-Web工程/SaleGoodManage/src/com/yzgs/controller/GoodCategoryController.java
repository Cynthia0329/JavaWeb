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
import com.yzgs.domain.GoodCategory;
import com.yzgs.domain.Page;
import com.yzgs.domain.Response;
import com.yzgs.domain.Result;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.GoodCategoryService;

@Controller
@RequestMapping({ "goodCategory" })
public class GoodCategoryController {
	@Autowired
	GoodCategoryService goodCategoryService;
	
	/**
	 * 取商品类型管理主界面
	 * @return
	 */

	@RequestMapping({"toGoodCategoryManage"})
	//跳转到商品类型管理页面
	public ModelAndView toWebUserManage(){
		//实例化一个视图
		ModelAndView modelAndView=new ModelAndView();
		//设置视图名
		modelAndView.setViewName("admin/goodCategoryManage");
		//返回视图
		return modelAndView;
		
	}
	
	
	
	/**
	 * 分页查询操作
	 */
	@RequestMapping({"selectAllGoodCategoryByPage"})
	//跳转到分页查询
	  public  void  selectAllGoodCategoryByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  GoodCategory GoodCategory,@RequestParam int page,@RequestParam int rows
			  ){//获取页数和行数
		//实例化一个pager
		Page<GoodCategory> pager = new Page<GoodCategory>();
		//实例化一个params
		HashMap<String, Object> params = new HashMap<String, Object>();
		//商品类型为空
		if(GoodCategory == null){
			//实例化一个新的商品类型
			GoodCategory=new GoodCategory();
		}
		//否则获取这个商品类型名称
		 params.put("categoryName", GoodCategory.getCategoryName());
	
		 //setParams：可以接受任何类型的参数
		pager.setParams(params);
		//页数为0
		if(page==0){
			//返回页数1
			page=1;
		}
		//设置此页的页数、行数、商品类型
		pager.setPageNo(page);
		pager.setPageSize(rows);
		pager.setT(GoodCategory);
		ServiceToActionMsg<GoodCategory> GoodCategorys = goodCategoryService.selectAllGoodCategoryByPage(pager);
		//如果商品类型返回静态码不为空
		if (GoodCategorys.getStatusCode()) {
			//则松台响应对应商品类型页码
			Response.reSponseJson(resp,GoodCategorys.getPage().toString());
			//Response对象用于动态响应客户端请示，控制发送给用户的信息，并将动态生成响应
			//reSponseJson 在servlet填充Response的时候，做JSON格式的数据转换
		} else {
			Response.reSponseJson(resp,GoodCategorys.toJson());
			//toJson：将格式化为 JSON 数据格式
		}

	}
	
	/**
	 * 删除商品分类
	 * @return
	 */
	@RequestMapping({"deleteGoodCategory"})
	@ResponseBody
	public Result deleteGoodCategory(@RequestParam String id){
		 Result result=null;
		 //先进行校验,看该分类是否有商品存在库存中
         if(goodCategoryService.checkGoodCategoryStore(id)){
			 //库存存在，不能删除，返回-1
			 result=new Result("-1", "库存中存在该商品分类物品,不能删除");
			
		 }else{
		 
		 //库存不存在，执行删除操作
			 if(goodCategoryService.deleteGoodCategoryById(id)){
				 //删除成功，返回1
				 result=new Result("1", "删除商品分类成功");
			 }else{
				 //删除失败，返回-1
				 result=new Result("-1", "删除商品分类失败");
				 
			 }
		 }
		return result;
		
	}
	
	/**
	 * 取商品分类新增或者修改界面
	 * @return toGoodCategoryAddOrUpdate
	 */
	@RequestMapping({"toGoodCategoryAddOrUpdate"})
	public ModelAndView toGoodCategoryAddOrUpdate(HttpServletRequest req,
			  HttpServletResponse resp){
		//实例化一个goodCategory商品分类
		GoodCategory goodCategory=null;
		//建立视图
		ModelAndView modelAndView=new ModelAndView();
		//获取id
		String id=req.getParameter("id");
		//id不为空
		if(id!=null&&!id.equals("")){
			//获取商品分类该商品id
			goodCategory=goodCategoryService.getGoodCategoryById(id);
		}
		//添加此分类
		modelAndView.addObject("goodCategory", goodCategory);
		//跳转到添加或更新界面
		modelAndView.setViewName("admin/goodCategoryAddOrUpdate");
		return modelAndView;
		
	}
	
	
	
	
	
	//新增或者修改商品分类提交操作
	@RequestMapping({"GoodCategoryAddOrUpdateSubmit"})
	@ResponseBody
	public Result GoodCategoryAddOrUpdateSubmit(HttpServletRequest req,
			  HttpServletResponse resp,GoodCategory goodCategory){
		//根据webSinger对象的singer_id,判断是添加 还是修改
		//实例化result
		Result result=null;
		//获取商品分类id
		String goodCategory_id=goodCategory.getId();
		//id为空
		if(goodCategory_id==null||goodCategory_id.equals("")){
			//生成主键id
			goodCategory_id=UUID.randomUUID().toString();
			//创建商品分类id
			goodCategory.setId(goodCategory_id);
			//若为添加操作
			if(goodCategoryService.addGoodCategory(goodCategory)){
				//添加成功，返回1
				 result=new Result("1", "添加商品分类成功");
			}else{
				//添加失败，返回-1
				 result=new Result("-1", "添加商品分类失败");
			};
		}else{
			//若为更新操作
			if(goodCategoryService.updateGoodCategory(goodCategory)){
				//修改成功，返回1
				 result=new Result("1", "修改商品分类成功");
			}else{
				//修改失败，返回-1
				result=new Result("-1", "修改商品分类失败");
			};
		}	
		return result;
	}
	
	
	/**
	 * 查看商品分类详情
	 * @return
	 */
	@RequestMapping({"viewGoodCategory"})
	public ModelAndView viewWebAlbm(HttpServletRequest req,
			  HttpServletResponse resp,@RequestParam String id){
		//实例化一个goodCategory
		GoodCategory goodCategory=null;
		//创建视图
		ModelAndView modelAndView=new ModelAndView();
		//获取商品分类id
		goodCategory=goodCategoryService.getGoodCategoryById(id);
		//添加此商品分类
		modelAndView.addObject("goodCategory", goodCategory);
		//跳转到goodCategoryView页面
		modelAndView.setViewName("admin/goodCategoryView");
		return modelAndView;
		
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
	    dateFormat.setLenient(false);  
	  //判断字符串是不是一个合法的日期格式
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}

}
