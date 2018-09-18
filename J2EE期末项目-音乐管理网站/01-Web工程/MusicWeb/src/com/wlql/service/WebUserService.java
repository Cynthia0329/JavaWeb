package com.wlql.service;

import com.wlql.domain.Page;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebUser;

public interface WebUserService {
	public  ServiceToActionMsg<WebUser> selectAllWebUserByPage(Page<WebUser> pager);
	public boolean deleteWebUserByUserId(String userid);
	public WebUser getWebUserByUserId(String userid);
	public boolean shWebUserByUserId(String userid);
	public boolean insertWebUser(WebUser webUser);
	public WebUser getWebUserByAccount(String account);
	public boolean updateWebUser(WebUser webUser);

}
