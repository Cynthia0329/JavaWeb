package com.wlql.dao;

import java.util.List;

import com.wlql.domain.Page;
import com.wlql.domain.WebAlbm;

public interface WebAlbmDao {
	public Page<WebAlbm> selectAllByPage(Page<WebAlbm> pager, WebAlbm t);
	public boolean deleteWebAlbmByAlbmId(String albm_id);
	public WebAlbm getWebAlbmByAlbmId(String albm_id);
	public boolean addWebAlbm(WebAlbm webAlbm);
	public boolean updateWebAlbm(WebAlbm webAlbm);
	public List<WebAlbm> getAllWebAlbm();
}
