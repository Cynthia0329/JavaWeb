package com.wlql.dao;

import com.wlql.domain.Page;
import com.wlql.domain.WebUser;

public interface WebUserDao {
	
	public Page<WebUser> selectAllByPage(Page<WebUser> pager, WebUser t);
	public boolean deleteWebUserByUserId(String userid);
	public WebUser getWebUserByUserId(String userid);
	public boolean shWebUserByUserId(String userid);
	public boolean insertWebUser(WebUser webUser);
	public WebUser getWebUserByAccount(String account);
	public boolean updateWebUser(WebUser webUser);

}
