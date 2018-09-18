package com.wlql.daoimpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wlql.dao.WebUserDao;
import com.wlql.domain.Page;
import com.wlql.domain.WebSinger;
import com.wlql.domain.WebUser;
@Repository
public class WebUserDaoImpl implements WebUserDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public Page<WebUser> selectAllByPage(Page<WebUser> pager, WebUser t) {
		
		if(t==null){
			t=new WebUser();
		}
		List<WebUser> list = sqlSessionTemplate.selectList(t.getClass().getCanonicalName()+".selectForPage", pager);
		pager.setResults(list);
		return pager;
	}

	public boolean deleteWebUserByUserId(String userid) {
		return sqlSessionTemplate.delete(WebUser.class.getCanonicalName()+".delete", userid)>0?true:false;
	}

	public WebUser getWebUserByUserId(String userid) {
		return sqlSessionTemplate.selectOne(WebUser.class.getCanonicalName()+".selectByPk", userid);
	}

	public boolean shWebUserByUserId(String userid) {
		return sqlSessionTemplate.update(WebUser.class.getCanonicalName()+".updateUserZt", userid)>0?true:false;
	}

	public boolean insertWebUser(WebUser webUser) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert(WebUser.class.getCanonicalName()+".create", webUser)>0?true:false;
	}

	public WebUser getWebUserByAccount(String account) {
		return sqlSessionTemplate.selectOne(WebUser.class.getCanonicalName()+".selectByAccount", account);
	}

	public boolean updateWebUser(WebUser webUser) {
		return sqlSessionTemplate.insert(WebUser.class.getCanonicalName()+".update", webUser)>0?true:false;
	}

}
