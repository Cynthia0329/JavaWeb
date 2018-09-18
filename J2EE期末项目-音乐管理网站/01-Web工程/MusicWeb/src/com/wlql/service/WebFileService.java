package com.wlql.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wlql.domain.Page;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebFile;

public interface WebFileService {
	
	public ServiceToActionMsg<WebFile> selectAllWebFileByPage(Page<WebFile> pager);
	public boolean FileUpload(MultipartFile[] files, String file_pk_id);
	public boolean deleteWebFile(String file_pk_id);		
	public WebFile getWebFileByPkId(String music_id);
	public List<WebFile> getWebFileListByPkId(String music_id);
	public WebFile getWebFileById(String fileid);

}
