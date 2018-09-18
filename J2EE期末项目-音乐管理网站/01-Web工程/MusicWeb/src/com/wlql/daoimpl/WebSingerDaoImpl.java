package com.wlql.daoimpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wlql.dao.WebSingerDao;
import com.wlql.domain.Page;
import com.wlql.domain.WebSinger;
import com.wlql.domain.WebUser;
@Repository
public class WebSingerDaoImpl implements WebSingerDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public Page<WebSinger> selectAllByPage(Page<WebSinger> pager, WebSinger t) {

		if(t==null){
			t=new WebSinger();
		}
		List<WebSinger> list = sqlSessionTemplate.selectList(t.getClass().getCanonicalName()+".selectForPage", pager);
		pager.setResults(list);
		return pager;
	}

	public WebSinger getWebSingerBySingerId(String singer_id) {
		
		return sqlSessionTemplate.selectOne(WebSinger.class.getCanonicalName()+".selectByPk", singer_id);
	}

	public boolean addWebSinger(WebSinger webSinger) {
		return sqlSessionTemplate.insert(WebSinger.class.getCanonicalName()+".create", webSinger)>0?true:false;
	
	}

	public boolean updateWebSinger(WebSinger webSinger) {
	
		return sqlSessionTemplate.update(WebSinger.class.getCanonicalName()+".update", webSinger)>0?true:false;
	}

	public boolean deleteWebSingerBySingerId(String singer_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete(WebSinger.class.getCanonicalName()+".delete", singer_id)>0?true:false;
	}

	public List<WebSinger> getAllSingers() {
		
		return sqlSessionTemplate.selectList(WebSinger.class.getCanonicalName()+".queryAllSinger");
	}

}
