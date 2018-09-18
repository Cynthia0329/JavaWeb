package com.wlql.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlql.daoimpl.WebSingerDaoImpl;
import com.wlql.daoimpl.WebUserDaoImpl;
import com.wlql.domain.Page;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebSinger;
import com.wlql.domain.WebUser;
import com.wlql.service.WebSingerService;
@Service
public class WebSingerServiceImpl implements WebSingerService {
    @Autowired
	 WebSingerDaoImpl webSingerDaoImpl;
	 
	public ServiceToActionMsg<WebSinger> selectAllWebSingerByPage(
			Page<WebSinger> pager) {
		 ServiceToActionMsg<WebSinger> serviceToActionMsg = new ServiceToActionMsg<WebSinger>(false);
		 try {
			
			pager=webSingerDaoImpl.selectAllByPage(pager,pager.getT());
			serviceToActionMsg.setDataList(pager.getResults());
			serviceToActionMsg.setPage(pager);
			serviceToActionMsg.setStatusCode(true);
		} catch (Exception e) {
			e.printStackTrace();
			serviceToActionMsg.setErrorMsg("分页查询失败");
		   
		}
		 return serviceToActionMsg;
	
	}

	public WebSinger getWebSingerBySingerId(String singer_id) {
		
		return webSingerDaoImpl.getWebSingerBySingerId(singer_id);
	}

	public boolean addWebSinger(WebSinger webSinger) {
	
		return webSingerDaoImpl.addWebSinger(webSinger);
	}

	public boolean updateWebSinger(WebSinger webSinger) {
		return webSingerDaoImpl.updateWebSinger(webSinger);
		
	}

	public boolean deleteWebSingerBySingerId(String singer_id) {
		
		return webSingerDaoImpl.deleteWebSingerBySingerId(singer_id);
	}

	public List<WebSinger> getAllSingers() {
		return webSingerDaoImpl.getAllSingers();
	}

}
