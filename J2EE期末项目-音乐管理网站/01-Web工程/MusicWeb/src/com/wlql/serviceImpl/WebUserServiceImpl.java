package com.wlql.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlql.daoimpl.WebUserDaoImpl;
import com.wlql.domain.Page;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebUser;
import com.wlql.service.WebUserService;
@Service
public class WebUserServiceImpl implements WebUserService{
    @Autowired
	WebUserDaoImpl webUserDaoImpl;
	
	public  ServiceToActionMsg<WebUser> selectAllWebUserByPage(
			Page<WebUser> pager) {
		 ServiceToActionMsg<WebUser> serviceToActionMsg = new ServiceToActionMsg<WebUser>(false);
		 try {
			
			pager=webUserDaoImpl.selectAllByPage(pager,pager.getT());
			serviceToActionMsg.setDataList(pager.getResults());
			serviceToActionMsg.setPage(pager);
			serviceToActionMsg.setStatusCode(true);
		} catch (Exception e) {
			serviceToActionMsg.setErrorMsg("分页查询失败");
		   
		}
		 return serviceToActionMsg;
		
	}

	public boolean deleteWebUserByUserId(String userid) {

		return webUserDaoImpl.deleteWebUserByUserId(userid);
	}

	public WebUser getWebUserByUserId(String userid) {
		return webUserDaoImpl.getWebUserByUserId(userid);
	}

	public boolean shWebUserByUserId(String userid) {
		return webUserDaoImpl.shWebUserByUserId(userid);
	}

	public boolean insertWebUser(WebUser webUser) {
		return webUserDaoImpl.insertWebUser(webUser);
	}

	public WebUser getWebUserByAccount(String account) {
		return webUserDaoImpl.getWebUserByAccount(account);
	}

	public boolean updateWebUser(WebUser webUser) {
		return webUserDaoImpl.updateWebUser(webUser);
	}

	
	
	
	
}
