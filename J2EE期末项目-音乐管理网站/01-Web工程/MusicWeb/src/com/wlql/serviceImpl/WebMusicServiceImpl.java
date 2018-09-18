package com.wlql.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlql.daoimpl.WebMusicDaoImpl;
import com.wlql.domain.Page;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebMusic;
import com.wlql.domain.WebSinger;
import com.wlql.domain.WebUser;
import com.wlql.service.WebMusicService;
@Service
public class WebMusicServiceImpl implements WebMusicService {
    @Autowired
	WebMusicDaoImpl webMusicDaoImpl;
	
	public ServiceToActionMsg<WebMusic> selectAllWebMusicByPage(
			Page<WebMusic> pager) {
		 ServiceToActionMsg<WebMusic> serviceToActionMsg = new ServiceToActionMsg<WebMusic>(false);
		 try {
			
			pager=webMusicDaoImpl.selectAllByPage(pager,pager.getT());
			serviceToActionMsg.setDataList(pager.getResults());
			serviceToActionMsg.setPage(pager);
			serviceToActionMsg.setStatusCode(true);
		} catch (Exception e) {
			e.printStackTrace();
			serviceToActionMsg.setErrorMsg("分页查询失败");
		   
		}
		 return serviceToActionMsg;
	}
/**
 * 删除歌曲信息
 */
	public boolean deleteWebMusicByMusicId(String music_id) {
		
		return webMusicDaoImpl.deleteWebMusicByMusicId(music_id);
	}
/**
 * 得到歌曲信息
 * @param music_id
 * @return
 */
	public WebMusic getWebMusicByMusicId(String music_id) {
		return webMusicDaoImpl.getWebMusicByMusicId(music_id);
	}
	/**
	 * 添加歌曲信息
	 * @param music_id
	 * @return
	 */
	public boolean addWebMusic(WebMusic webMusic) {
		return webMusicDaoImpl.addWebMusic(webMusic);
	}
	/**
	 * 修改歌曲信息
	 * @param music_id
	 * @return
	 */
	public boolean updateWebMusic(WebMusic webMusic) {
		return webMusicDaoImpl.updateWebMusic(webMusic);
	}

}
