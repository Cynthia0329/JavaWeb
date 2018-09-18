package com.wlql.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlql.daoimpl.WebAlbmDaoImpl;
import com.wlql.domain.Page;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebAlbm;
import com.wlql.service.WebAlbmService;
@Service
public class WebAlbmServiceImpl implements WebAlbmService {
     @Autowired
	 WebAlbmDaoImpl webAlbmDaoImpl;
	public ServiceToActionMsg<WebAlbm> selectAllWebAlbmByPage(
			Page<WebAlbm> pager) {
		 ServiceToActionMsg<WebAlbm> serviceToActionMsg = new ServiceToActionMsg<WebAlbm>(false);
		 try {
			
			pager=webAlbmDaoImpl.selectAllByPage(pager,pager.getT());
			serviceToActionMsg.setDataList(pager.getResults());
			serviceToActionMsg.setPage(pager);
			serviceToActionMsg.setStatusCode(true);
		} catch (Exception e) {
			serviceToActionMsg.setErrorMsg("分页查询失败");
		   
		}
		 return serviceToActionMsg;
	}
	public boolean deleteWebAlbmByAlbmId(String albm_id) {
		
		return webAlbmDaoImpl.deleteWebAlbmByAlbmId(albm_id);
	}
	public WebAlbm getWebAlbmByAlbmId(String albm_id) {
		return webAlbmDaoImpl.getWebAlbmByAlbmId(albm_id);
		
	}
	public boolean addWebAlbm(WebAlbm webAlbm) {
		
		return webAlbmDaoImpl.addWebAlbm(webAlbm);
	}
	public boolean updateWebAlbm(WebAlbm webAlbm) {
		
		return webAlbmDaoImpl.updateWebAlbm(webAlbm);
	}
	public List<WebAlbm> getAllWebAlbm() {
		
		return webAlbmDaoImpl.getAllWebAlbm();
	}

}
