package com.wlql.dao;

import com.wlql.domain.Manage;

public  interface ManageDao {
	
	public Manage getManageByAccount(String account);		//返回管理员账户信息
	
	public boolean updateManagePass(Manage manage);			//修改管理员密码

}
