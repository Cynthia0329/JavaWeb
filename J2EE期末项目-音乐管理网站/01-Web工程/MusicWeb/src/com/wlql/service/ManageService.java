package com.wlql.service;

import com.wlql.domain.Manage;


public  interface ManageService {
     /**
      * 通过账号,查询Manage对象,
      * @param accout
      * @return
      */
     public Manage getManageByAccount(String accout);		//返回管理员账户信息
       
     public boolean updateManagePass(Manage manage);		//修改管理员密码

}
