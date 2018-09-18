package com.yzgs.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzgs.dao.GoodStoreDao;
import com.yzgs.domain.GoodStore;
import com.yzgs.domain.Page;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.GoodStoreService;
@Service
public class GoodStoreServiceImpl  implements GoodStoreService{
	
	 @Autowired
	//调用库存管理
		GoodStoreDao goodStoreDao;
	//分页查询 
		@Override
		public ServiceToActionMsg<GoodStore> selectAllGoodStoreByPage(Page<GoodStore> pager) {
			 ServiceToActionMsg<GoodStore> serviceToActionMsg = new ServiceToActionMsg<GoodStore>(false);
			 try {
				
				pager=goodStoreDao.selectAllByPage(pager,pager.getT());
				serviceToActionMsg.setDataList(pager.getResults());
				serviceToActionMsg.setPage(pager);
				serviceToActionMsg.setStatusCode(true);
			} catch (Exception e) {
				serviceToActionMsg.setErrorMsg("分页查询失败");
			   
			}
			 return serviceToActionMsg;
		}
		//删除商品
		@Override
		public boolean deleteGoodStoreById(String id) {
			
			return goodStoreDao.deleteGoodStoreById(id);
		}
		//得到一条记录
		@Override
		public GoodStore getGoodStoreById(String id) {
			
			return goodStoreDao.getGoodStoreById(id);
		}
		//添加商品
		@Override
		public boolean addGoodStore(GoodStore goodStore) {
			
			return goodStoreDao.addGoodStore(goodStore);
		}
		//修改商品
		@Override
		public boolean updateGoodStore(GoodStore goodStore) {
			return goodStoreDao.updateGoodStore(goodStore);
		}
		//清理库存
		@Override
		public boolean cleanGoodStore(Map<String, Object> maps) {
			return goodStoreDao.cleanGoodStore(maps);
		}


}
