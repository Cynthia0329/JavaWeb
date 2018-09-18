package com.wlql.service;

import com.wlql.domain.Page;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebMusic;

public interface WebMusicService {

	public ServiceToActionMsg<WebMusic> selectAllWebMusicByPage(Page<WebMusic> pager);
	public boolean deleteWebMusicByMusicId(String music_id);
	public WebMusic getWebMusicByMusicId(String music_id);
	public boolean addWebMusic(WebMusic webMusic);
	public boolean updateWebMusic(WebMusic webMusic);
}
