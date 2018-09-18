package com.yzgs.serviceimpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzgs.dao.SaleRecordDao;
import com.yzgs.domain.SaleProperties;
import com.yzgs.domain.SaleRecord;
import com.yzgs.domain.Page;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.SaleRecordService;
@Service
public class SaleRecordServiceImpl implements SaleRecordService {
    @Autowired
	SaleRecordDao SaleRecordDao;
	 
    //分页查询
	@Override
	public ServiceToActionMsg<SaleRecord> selectAllSaleRecordByPage(Page<SaleRecord> pager) {
		 ServiceToActionMsg<SaleRecord> serviceToActionMsg = new ServiceToActionMsg<SaleRecord>(false);
		 try {
			
			pager=SaleRecordDao.selectAllByPage(pager,pager.getT());
			serviceToActionMsg.setDataList(pager.getResults());
			serviceToActionMsg.setPage(pager);
			serviceToActionMsg.setStatusCode(true);
		} catch (Exception e) {
			serviceToActionMsg.setErrorMsg("分页查询失败");
		   
		}
		 return serviceToActionMsg;
	}

	//删除单条销售记录
	@Override
	public boolean deleteSaleRecordById(String id) {
		
		return SaleRecordDao.deleteSaleRecordById(id);
	}

	//查询单条销售记录
	@Override
	public SaleRecord getSaleRecordById(String id) {
		
		return SaleRecordDao.getSaleRecordById(id);
	}

	//略
	@Override
	public boolean addSaleRecord(SaleRecord SaleRecord) {
		
		return SaleRecordDao.addSaleRecord(SaleRecord);
	}
	
	//略
	@Override
	public boolean updateSaleRecord(SaleRecord SaleRecord) {
		return SaleRecordDao.updateSaleRecord(SaleRecord);
	}

	//根据入库批号,查询该批次的属性(包括商品单价,商品折扣系数,商品名称等)	
	@Override
	public SaleProperties getSalePropertiesByStorePh(String storePh) {
		return SaleRecordDao.getSalePropertiesByStorePh(storePh);
	}

	@Override
	public boolean saveSaleRecordList(List<SaleRecord> saleRecords) {
		 boolean flag=true;
		 try{
		 for(SaleRecord saleRecord:saleRecords){	//循环批量添加销售记录
			 saleRecord.setId(UUID.randomUUID().toString());
			 SaleRecordDao.addSaleRecord(saleRecord);
		 }
		 }catch (Exception e) {
			 flag=false;
			e.printStackTrace();
		}
		 return flag;
	}

}
