package com.wlql.dao;

import com.wlql.domain.Page;
import com.wlql.domain.WebMusic;

public interface WebMusicDao {
	public Page<WebMusic> selectAllByPage(Page<WebMusic> pager, WebMusic t);
	public boolean deleteWebMusicByMusicId(String music_id);
	public WebMusic getWebMusicByMusicId(String music_id);
	public boolean addWebMusic(WebMusic webMusic);
	public boolean updateWebMusic(WebMusic webMusic);

}
