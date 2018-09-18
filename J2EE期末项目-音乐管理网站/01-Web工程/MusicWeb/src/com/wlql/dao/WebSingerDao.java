package com.wlql.dao;

import java.util.List;

import com.wlql.domain.Page;
import com.wlql.domain.WebSinger;

public interface WebSingerDao {
	public Page<WebSinger> selectAllByPage(Page<WebSinger> pager, WebSinger t);
	public WebSinger getWebSingerBySingerId(String singer_id);
	public boolean addWebSinger(WebSinger webSinger);
	public boolean updateWebSinger(WebSinger webSinger);
	public boolean deleteWebSingerBySingerId(String singer_id);
	public List<WebSinger> getAllSingers();

}
