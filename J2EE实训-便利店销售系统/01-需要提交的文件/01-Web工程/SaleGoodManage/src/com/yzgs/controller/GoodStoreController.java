package com.yzgs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.yzgs.domain.GoodStore;
import com.yzgs.domain.Page;
import com.yzgs.domain.Response;
import com.yzgs.domain.Result;
import com.yzgs.domain.SaleProperties;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.GoodService;
import com.yzgs.service.GoodStoreService;
import com.yzgs.service.SaleRecordService;
/**
 *  创建springmvc控制器
 * @author jcy
 *
 */
@Controller
@RequestMapping({ "goodStore" })
public class GoodStoreController {
	//@Autowired是用在加载实现service实现类
   //自动帮你把bean里面引用的对象的setter/getter方法省略，它会自动帮你set/get
	@Autowired
	GoodStoreService goodStoreService;
	@Autowired
	GoodService goodService;
	

	/**
	 * 取库存管理主界面
	 * @return
	 */
	//设置想要跳转的父路径
	@RequestMapping({"toGoodStoreManage"})
	public ModelAndView toWebUserManage(){
		//需要查询所有商品
		List<Good> lists=goodService.getAllGood();
		//构造ModelAndView对象
		ModelAndView modelAndView=new ModelAndView();
		//转发到goodStoreManage.jsp文件
		modelAndView.setViewName("admin/goodStoreManage");
		//封装数据，用于页面渲染
		modelAndView.addObject("lists", lists);
		//返回视图
		return modelAndView;
		
	}
	
	/**
	 * 分页查询操作
	 */
	@RequestMapping({"selectAllGoodStoreByPage"})
	  public  void  selectAllGoodStoreByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  GoodStore GoodStore,@RequestParam int page,@RequestParam int rows
			  ){
		//pager实体
		Page<GoodStore> pager = new Page<GoodStore>();
		//params对象，HashMap容器，调用put方法，
		HashMap<String, Object> params = new HashMap<String, Object>();
		//如果库存商品为空
		if(GoodStore == null){
			//new一个库存商品
			GoodStore=new GoodStore();
		}
		//将 GoodStore中获得的StorePh赋值给storePh
		 params.put("storePh", GoodStore.getStorePh());
		 //将 GoodStore中获得的goodId赋值给goodId
		 params.put("goodId", GoodStore.getGoodId());
	  
		pager.setParams(params);
		if(page==0){
			page=1;
		}
		//赋值
		pager.setPageNo(page);
		pager.setPageSize(rows);
		pager.setT(GoodStore);
		//得到数据库中的分页查询的数据
		ServiceToActionMsg<GoodStore> GoodStores = goodStoreService.selectAllGoodStoreByPage(pager);
		if (GoodStores.getStatusCode()) {
			Response.reSponseJson(resp,GoodStores.getPage().toString());
		} else {
			Response.reSponseJson(resp,GoodStores.toJson());
		}

	}
	
	
	/**
	 * 删除库存商品
	 * 
	 * @Responsebody 后返回结果不会被解析为跳转路径，而是直接写入HTTP 响应正文中。
	 * @RequestParam用于将请求参数区数据映射到功能处理方法的参数上，传递参数id。
	 * 
	 * @return
	 */
	@RequestMapping({"deleteGoodStore"})
	@ResponseBody
	public Result deleteGoodStore(@RequestParam String id){
		//定义一个返回对象
		 Result result=null;
		 //调用goodStoreService里面的删除方法，通过id执行数据库删除操作
		 if(goodStoreService.deleteGoodStoreById(id)){
			 
			 result=new Result("1", "删除库存成功");
		 }else{
			 result=new Result("-1", "删除库存失败");
			 
		 }
		//返回结果
		return result;
		
	}
	
	/**
	 * 清理库存,写过期信息表 cleanGoodStore
	 * ?goodId='+goodId+'&goodNum='+goodNum+'&goodJprice='+goodJprice+'&storePh='+storePh,
	 */
	@RequestMapping({"cleanGoodStore"})
	@ResponseBody
	public Result cleanGoodStore(@RequestParam String goodId,
			@RequestParam String goodNum,
			@RequestParam String goodJprice,
			@RequestParam String storePh
			){
		//定义返回对象
		 Result result=null;
		 //把goodNum转化为数值进行计算
		 if(Integer.parseInt(goodNum)==0){
			 
			 result=new Result("-1", "库存商品为0,不需要清除");
		 }else{
		 //HashMap存放：Key(键):为Sring类型的，Value(值)：为Object类型的数据（任意类型）的数据
		 Map<String, Object> maps=new HashMap<String, Object>();
		 //给id设置一个主键
		 maps.put("id", UUID.randomUUID().toString());
		 //goodId的值复制给goodId的键
		 maps.put("goodId", goodId);
		//goodNum的值复制给goodNum的键
		 maps.put("goodNum", goodNum);
		//goodJprice的值复制给goodJprice的键
		 maps.put("goodJprice", goodJprice);
		//storePh的值复制给storePh的键
		 maps.put("storePh", storePh);
		//计算过后的值复制给sumMoney的键
		 maps.put("sumMoney",Double.parseDouble(goodJprice)*Integer.parseInt(goodNum));
		//new Date()的值复制给goodGuoQiDate的键
		 maps.put("goodGuoQiDate", new Date());
		//调用goodStoreService中的清理库存方法
		 if(goodStoreService.cleanGoodStore(maps)){
			 //goodStoreService返回的结果为真，清理库存成功
			 result=new Result("1", "清理库存成功");
		 }else{
			 //goodStoreService返回的结果为假，清理库存失败
			 result=new Result("-1", "清理库存失败");
			 
		 }
		 }
		 //返回结果
		return result;
		
	}
	
	
	
	
	/**
	 * 取库存新增或者修改界面
	 * @return
	 */
	@RequestMapping({"toGoodStoreAddOrUpdate"})
	public ModelAndView toGoodStoreAddOrUpdate(HttpServletRequest req,
			  HttpServletResponse resp){
		//需要查询所有库存商品类型
		List<Good> lists=goodService.getAllGood();
		//goodstore实体对象
		GoodStore goodStore=null;
		//构造ModelAndView对象
		ModelAndView modelAndView=new ModelAndView();
		//获得的就是发过来参数页面的id
		String id=req.getParameter("id");
		//判断id对象是否存在，id对象值是否为空
		if(id!=null){
			//根据id获取goodStore对象
			goodStore=goodStoreService.getGoodStoreById(id);
		}
		//addObject封装数据，用于页面渲染
		modelAndView.addObject("goodStore", goodStore);
		modelAndView.addObject("lists", lists);
		//setViewName转发到goodstoreAddOrUpdate.jsp文件
		modelAndView.setViewName("admin/goodStoreAddOrUpdate");
		//返回视图和数据
		return modelAndView;
		
	}
	
	
	//新增或者修改商品提交操作
			@RequestMapping({"GoodStoreAddOrUpdateSubmit"})
			@ResponseBody
			public Result GoodStoreAddOrUpdateSubmit(HttpServletRequest req,
					  HttpServletResponse resp,GoodStore goodStore){
				//根据webSinger对象的singer_id,判断是添加 还是修改
				Good g=goodService.getGoodById(goodStore.getGoodId());
				//定义一个返回对象
				Result result=null;
				//通过goodStore.getId()获得id的值并且赋值给goodStore_id
				String goodStore_id=goodStore.getId();
				//如果goodStore_id的值为空，id对象是否存在，id对象值是否为空
				if(goodStore_id==null||goodStore_id.equals("")){
					//自动生成主键
					goodStore_id=UUID.randomUUID().toString();
					//给id赋值goodStore_id
					goodStore.setId(goodStore_id);
					//将计算后的值赋值给setGoodSumMoney
					goodStore.setGoodSumMoney(goodStore.getStoreNum()*goodStore.getGoodJPrice());
					//将new Date()当前时间设置为入库时间
					goodStore.setStoreDate(new Date());
					//库存提示判断		
					//添加库存,校验商品数量 是否低于 该物品数量该商品数据表物品数量下限（库存下限信息比较判断）
					if(goodStore.getStoreNum()<g.getStoreMin()){
						
						 result=new Result("-1", "添加物品数量少于商品下限");
						
					}else{
				 //调用goodStoreService中的添加库存商品的方法					
					if(goodStoreService.addGoodStore(goodStore)){
						//goodStoreService返回的结果为真，添加库存成功
						 result=new Result("1", "添加库存成功");
					}else{
						//goodStoreService返回的结果为真，添加库存失败
						 result=new Result("-1", "添加库存失败");
					};
					}
				}else{
					//库存提示判断		
					//添加库存,校验商品数量 是否低于 该物品数量该商品数据表物品数量下限（库存下限信息比较判断）
					
						//调用goodStoreService中的修改库存商品的方法
					if(goodStoreService.updateGoodStore(goodStore)){
						//goodStoreService返回的结果为真，修改库存成功
						 result=new Result("1", "修改库存成功");
					}else{
						//goodStoreService返回的结果为真，修改库存成功
						result=new Result("-1", "修改库存失败");
					};
				}	
				return result;
			}
		/**
		 * 去入库信息查看页面
		 * @return
		 */
		@RequestMapping({"viewGoodStore"})
		public ModelAndView viewWebAlbmDetail(HttpServletRequest req,
				  HttpServletResponse resp,@RequestParam String id){
			//需要查询所有库存商品类型
			List<Good> lists=goodService.getAllGood();
			//goodstore实体对象
			GoodStore goodStore=null;
			//构造ModelAndView对象
			ModelAndView modelAndView=new ModelAndView();
			//通过id得到数据库信息
			goodStore=goodStoreService.getGoodStoreById(id);
			//addObject封装数据，用于页面渲染
			modelAndView.addObject("goodStore", goodStore);
			modelAndView.addObject("lists", lists);
			//setViewName转发到goodStoreView.jsp文件
			modelAndView.setViewName("admin/goodStoreView");
			//返回视图和数据
			return modelAndView;
			
		}
	
		/**
		 * 解决前段日期类型传递时,到达不了后台
		 * 
		 * 在需要日期转换的Controller中使用SpringMVC的注解@initbinder和Spring自带的WebDateBinder类来操作
		 * 
		 * @param binder
		 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
		//SimpleDateFormat日期定义，设置日期格式
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  //严格限制时间转换
	    dateFormat.setLenient(false);  
	  //注册一个时间编辑器
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}

}
