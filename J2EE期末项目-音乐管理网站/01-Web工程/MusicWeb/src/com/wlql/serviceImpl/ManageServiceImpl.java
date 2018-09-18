package com.wlql.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlql.daoimpl.ManageDaoImpl;
import com.wlql.domain.Manage;
import com.wlql.service.ManageService;

@Service
public class ManageServiceImpl  implements ManageService {
    @Autowired
	ManageDaoImpl manageDaoImpl;

    //返回管理员账户信息
	@Override
	public Manage getManageByAccount(String account) {
		
		return manageDaoImpl.getManageByAccount(account);
	}
	
	//修改管理员密码
	@Override
	public boolean updateManagePass(Manage manage) {
		return manageDaoImpl.updateManagePass(manage);
		
	}

	
	
	
   

}
