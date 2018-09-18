package com.yzgs.daoimpl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzgs.dao.TotalDao;
import com.yzgs.domain.MonthGoodGuoQi;
@Repository
public class TotalDaoImpl implements TotalDao {
   
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	@Override
	public String getZSRByMonth(String i) {
		
		return sqlSessionTemplate.selectOne("com.yzgs.domain.Total.getZSRByMonth",i);
	}

	@Override
	public String getZZCByMonth(String i) {
		return sqlSessionTemplate.selectOne("com.yzgs.domain.Total.getZZCByMonth",i);
	}
    /**
     * 查询单个商品月总销售
     */
	@Override
	public String getZSRByMonthAndGoodId(Map maps) {
		return sqlSessionTemplate.selectOne("com.yzgs.domain.Total.getZXSByMonthAndGoodId",maps);
	}
    /**
     * 查询单个商品月进货总额
     */
	@Override
	public String getZJHZEByMonthAndGoodId(Map maps) {
		return sqlSessionTemplate.selectOne("com.yzgs.domain.Total.getZJJByMonthAndGoodId",maps);
	}
    /**
     * 单个商品的过期成本,查询过期表
     */
	@Override
	public String getZGQZEByMonthAndGoodId(Map maps) {
		return sqlSessionTemplate.selectOne("com.yzgs.domain.Total.getZGQByMonthAndGoodId",maps);
	}

	@Override
	public List<MonthGoodGuoQi> getMonthGoodGuoQi(Map maps) {
		return sqlSessionTemplate.selectList("com.yzgs.domain.Total.getMonthGoodGuoQi",maps);
	}

}
