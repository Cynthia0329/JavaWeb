package com.wlql.service;

import java.util.List;

import com.wlql.domain.Page;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebSinger;

public interface WebSingerService {
	public ServiceToActionMsg<WebSinger> selectAllWebSingerByPage(Page<WebSinger> pager);
	public WebSinger getWebSingerBySingerId(String singer_id);
	public boolean addWebSinger(WebSinger webSinger);
	public boolean updateWebSinger(WebSinger webSinger);
	public boolean deleteWebSingerBySingerId(String singer_id);
	public List<WebSinger> getAllSingers();

}
