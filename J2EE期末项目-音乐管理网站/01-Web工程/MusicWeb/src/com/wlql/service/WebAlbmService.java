package com.wlql.service;

import java.util.List;

import com.wlql.domain.Page;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebAlbm;

public interface WebAlbmService {
	public ServiceToActionMsg<WebAlbm> selectAllWebAlbmByPage(Page<WebAlbm> pager);
	public boolean deleteWebAlbmByAlbmId(String albm_id);
	public WebAlbm getWebAlbmByAlbmId(String albm_id);
	public boolean addWebAlbm(WebAlbm webAlbm);
	public boolean updateWebAlbm(WebAlbm webAlbm);
	public List<WebAlbm> getAllWebAlbm();

}
