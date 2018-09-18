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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yzgs.domain.SaleProperties;
import com.yzgs.domain.SaleRecord;
import com.yzgs.domain.Page;
import com.yzgs.domain.Response;
import com.yzgs.domain.Result;
import com.yzgs.domain.SaleRecordList;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.SaleRecordService;
import com.yzgs.util.DateUtil;

/**
 * 商品结算和销售记录
 * @author Administrator
 *
 */


@Controller
@RequestMapping({ "saleRecord" })
public class SaleRecordController {
	
	@Autowired
	SaleRecordService saleRecordService;
	
	/**
	 * 进入便利店销售记录查看主界面
	 * @return
	 */
	@RequestMapping({"toSaleRecordManage"})
	public ModelAndView toWebUserManage(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("admin/saleRecordManage");
		return modelAndView;
		
	}
	
	/**
	 * 分页查询操作，返回销售记录信息
	 */
	@RequestMapping({"selectAllSaleRecordByPage"})
	  public  void  selectAllSaleRecordByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  SaleRecord saleRecord,@RequestParam int page,@RequestParam int rows
			  ){
		
		Page<SaleRecord> pager = new Page<SaleRecord>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		//判断页面是否有查询的值传入
		if(saleRecord == null){
			saleRecord=new SaleRecord();
		}
		
		//否则获取页面传入的数据
		 params.put("saleDate_Min", saleRecord.getSaleDate_min());
		 params.put("saleDate_Max", saleRecord.getSaleDate_max());
	  
		pager.setParams(params);		//存入上面的日期信息
		if(page==0){
			page=1;
		}
		pager.setPageNo(page);
		pager.setPageSize(rows);
		pager.setT(saleRecord);
		
		//
		ServiceToActionMsg<SaleRecord> SaleRecords = saleRecordService.selectAllSaleRecordByPage(pager);
		if (SaleRecords.getStatusCode()) {	//通过状态码
			Response.reSponseJson(resp,SaleRecords.getPage().toString());
		} else {
			Response.reSponseJson(resp,SaleRecords.toJson());
		}

	}
	
	/**
	 * 删除销售记录
	 * @return
	 */
	@RequestMapping({"deleteSaleRecord"})
	@ResponseBody
	public Result deleteSaleRecord(@RequestParam String id){
		 Result result=null;
		 if(saleRecordService.deleteSaleRecordById(id)){
			 
			 result=new Result("1", "删除消费记录成功");
		 }else{
			 result=new Result("-1", "删除消费记录失败");
			 
		 }
		return result;
		
	}
	
	
	/**
	 * 查询相关信息并进入销售记录单条详情页面
	 * @return
	 */
	@RequestMapping({"viewSaleRecordDetail"})
	public ModelAndView viewSaleRecordDetail(HttpServletRequest req,
			  HttpServletResponse resp,@RequestParam String id){
		
		SaleRecord saleRecord=null;
		ModelAndView modelAndView=new ModelAndView();
		
		saleRecord=saleRecordService.getSaleRecordById(id);		//查询单条销售记录
		modelAndView.addObject("saleRecord", saleRecord);
	
		//进入销售记录单条详情页面
		modelAndView.setViewName("admin/saleRecordView");
		return modelAndView;
		
	}
	
	
	/**
	 * 进入商品结账界面
	 * @return
	 */
	@RequestMapping({"toSaleRecordAddOrUpdate"})
	public ModelAndView toSaleRecordAddOrUpdate(HttpServletRequest req,
			  HttpServletResponse resp){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
	    //获取当前日期
		String currentDate=simpleDateFormat.format(new Date());
		ModelAndView modelAndView=new ModelAndView();
		
	    modelAndView.addObject("currentDate", currentDate);
		modelAndView.setViewName("admin/saleRecordAddOrUpdate");
		return modelAndView;
		
	}
	
	
	
	
		//新增或者修改商品提交操作
		@RequestMapping({"SaleRecordAddOrUpdateSubmit"})
		@ResponseBody
		public Result SaleRecordAddOrUpdateSubmit(HttpServletRequest req,
				  HttpServletResponse resp,SaleRecordList saleRecords){
			
			 Result result=null;
			
			//批量保存结账信息
			if(saleRecordService.saveSaleRecordList(saleRecords.getSaleRecords())){
				
				 result=new Result("1", "结账成功");
			}else{
				
				 result=new Result("-1", "结账失败");
				
			}
			return result;
			
		}
		
   /**
    * 根据入库批号,查询该批次的属性(包括商品单价,商品折扣系数,商品名称等)		
    */
		@RequestMapping({"getSalePropertiesByStorePh"})
		@ResponseBody
		public SaleProperties getSalePropertiesByStorePh(HttpServletRequest req,
				  HttpServletResponse resp,@RequestParam String storePh){
			
			SaleProperties p=saleRecordService.getSalePropertiesByStorePh(storePh);
			if(p!=null){
			if(DateUtil.checkTwoDate(p.getGoodValidDate(), new Date())){	//对比商品的有效日期和当前日期，判别商品是否过期
				p.setBs(0);		//未过期返回0
				
			}else{
				p.setBs(1);		//过期返回1
			}
			}else{
				p=new SaleProperties();		//创建新的商品销售实体示例
				
			}
			return p;	//返回新的商品销售实体示例
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
