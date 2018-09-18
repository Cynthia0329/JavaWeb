package com.wlql.dao;

import java.util.List;

import com.wlql.domain.Page;
import com.wlql.domain.WebFile;

public interface WebFileDao {
	public Page<WebFile> selectAllByPage(Page<WebFile> pager, WebFile t);
	public void insertWebFile(WebFile webFile);
	public boolean deleteWebFile(String file_pk_id);
	public WebFile getWebFileByPkId(String music_id);
	public List<WebFile> getWebFileListByPkId(String music_id);
	public WebFile getWebFileById(String fileid);

}
