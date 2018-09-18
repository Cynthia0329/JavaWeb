package com.wlql.daoimpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wlql.dao.WebAlbmDao;
import com.wlql.domain.Page;
import com.wlql.domain.WebAlbm;
import com.wlql.domain.WebMusic;
import com.wlql.domain.WebSinger;
@Repository
public class WebAlbmDaoImpl implements WebAlbmDao {

	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public Page<WebAlbm> selectAllByPage(Page<WebAlbm> pager, WebAlbm t) {
		if(t==null){
			t=new WebAlbm();
		}
		List<WebAlbm> list = sqlSessionTemplate.selectList(t.getClass().getCanonicalName()+".selectForPage", pager);
		pager.setResults(list);
		return pager;
	}

	public boolean deleteWebAlbmByAlbmId(String albm_id) {
		
		return sqlSessionTemplate.delete(WebAlbm.class.getCanonicalName()+".delete", albm_id)>0?true:false;
	}

	public WebAlbm getWebAlbmByAlbmId(String albm_id) {
		return sqlSessionTemplate.selectOne(WebAlbm.class.getCanonicalName()+".selectByPk", albm_id);
	}

	public boolean addWebAlbm(WebAlbm webAlbm) {
		
		return sqlSessionTemplate.insert(WebAlbm.class.getCanonicalName()+".create", webAlbm)>0?true:false;
	}

	public boolean updateWebAlbm(WebAlbm webAlbm) {
		
		return sqlSessionTemplate.update(WebAlbm.class.getCanonicalName()+".update", webAlbm)>0?true:false;
	}

	public List<WebAlbm> getAllWebAlbm() {
		return sqlSessionTemplate.selectList(WebAlbm.class.getCanonicalName()+".getAllWebAlbm");
	}
}
