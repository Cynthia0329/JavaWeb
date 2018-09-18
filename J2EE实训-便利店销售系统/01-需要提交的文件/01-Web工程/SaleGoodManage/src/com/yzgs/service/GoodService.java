package com.yzgs.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yzgs.domain.Good;
import com.yzgs.domain.Page;
import com.yzgs.domain.ServiceToActionMsg;



public interface  GoodService {
	
	public ServiceToActionMsg<Good> selectAllGoodByPage(Page<Good> pager);

	public boolean deleteGoodById(String id);
	
	public Good getGoodById(String id);
	
	public boolean addGood(Good good);
	
	public boolean updateGood(Good good);
   
	public List<Good> getAllGood();

	public boolean checkGoodStore(String id);

}
