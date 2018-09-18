package com.wlql.daoimpl;
import com.wlql.dao.ManageDao;
import com.wlql.domain.Manage;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ManageDaoImpl implements ManageDao{
   
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	//返回管理员账户信息
	@Override
	public Manage getManageByAccount(String account) {
		
		return sqlSessionTemplate.selectOne(getName()+".getManageByAccount", account);
	}
	
	
	
	public String getName(){
		
		return Manage.class.getCanonicalName();			//获取所传类从java语言规范定义的格式输出。
	}


	//修改管理员密码
	public boolean updateManagePass(Manage manage) {
		if(sqlSessionTemplate.update(getName()+".updateMm", manage)>0){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	
	

}
