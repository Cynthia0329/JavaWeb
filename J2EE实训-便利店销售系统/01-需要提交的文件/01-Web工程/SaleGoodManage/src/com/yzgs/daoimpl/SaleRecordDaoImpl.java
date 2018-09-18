package com.yzgs.daoimpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzgs.dao.SaleRecordDao;
import com.yzgs.domain.SaleProperties;
import com.yzgs.domain.SaleRecord;
import com.yzgs.domain.Page;
@Repository
public class SaleRecordDaoImpl implements SaleRecordDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//查询单条销售记录
	@Override
	public SaleRecord getSaleRecordById(String id) {
		
		return sqlSessionTemplate.selectOne(SaleRecord.class.getCanonicalName()+".selectByPk",id);
	}

	//删除单条销售记录
	@Override
	public boolean deleteSaleRecordById(String id) {
		return sqlSessionTemplate.delete(SaleRecord.class.getCanonicalName()+".delete",id)>0?true:false;
	}

	//略
	@Override
	public boolean updateSaleRecord(SaleRecord SaleRecord) {
		return sqlSessionTemplate.update(SaleRecord.class.getCanonicalName()+".update",SaleRecord)>0?true:false;
	}

	//略
	@Override
	public boolean addSaleRecord(SaleRecord SaleRecord) {
		return sqlSessionTemplate.insert(SaleRecord.class.getCanonicalName()+".create",SaleRecord)>0?true:false;
	}
	
	 /**
	   * 分页查询
	   * @param pager
	   * @param t
	   * @return
	   */
	  public Page<SaleRecord> selectAllByPage(Page<SaleRecord> pager, SaleRecord t) {
			if(t==null){
				t=new SaleRecord();
			}
			List<SaleRecord> list = sqlSessionTemplate.selectList(t.getClass().getCanonicalName()+".selectForPage", pager);
			pager.setResults(list);
			return pager;
		}

	  
	//根据入库批号,查询该批次的属性(包括商品单价,商品折扣系数,商品名称等)	
	@Override
	public SaleProperties getSalePropertiesByStorePh(String storePh) {
		return sqlSessionTemplate.selectOne(SaleRecord.class.getCanonicalName()+".selectSalePropertiesByStorePh",storePh);
	}


}
