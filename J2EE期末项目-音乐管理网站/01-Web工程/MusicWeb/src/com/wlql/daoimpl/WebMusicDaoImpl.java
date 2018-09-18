package com.wlql.daoimpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wlql.dao.WebMusicDao;
import com.wlql.domain.Page;
import com.wlql.domain.WebAlbm;
import com.wlql.domain.WebMusic;
import com.wlql.domain.WebSinger;
@Repository
public class WebMusicDaoImpl implements WebMusicDao {
    //获取到SqlSessionTemplate 注入以后就可以直接使用sqlSessionTemplate
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	//分页
	public Page<WebMusic> selectAllByPage(Page<WebMusic> pager, WebMusic t) {
		if(t==null){
			t=new WebMusic();
		}
		List<WebMusic> list = sqlSessionTemplate.selectList(t.getClass().getCanonicalName()+".selectForPage", pager);
		pager.setResults(list);
		return pager;
	}
//用sqlSessionTemplate去操作数据库 ，删除歌曲的信息
	public boolean deleteWebMusicByMusicId(String music_id) {
		return sqlSessionTemplate.delete(WebMusic.class.getCanonicalName()+".delete", music_id)>0?true:false;
	}
	//用sqlSessionTemplate去操作数据库 ，查询歌曲的信息
	public WebMusic getWebMusicByMusicId(String music_id) {
		return sqlSessionTemplate.selectOne(WebMusic.class.getCanonicalName()+".selectByPk", music_id);
	 
	}
	//用sqlSessionTemplate去操作数据库 ，添加歌曲的信息
	public boolean addWebMusic(WebMusic webMusic) {
		return sqlSessionTemplate.insert(WebMusic.class.getCanonicalName()+".create", webMusic)>0?true:false;
	}
	//用sqlSessionTemplate去操作数据库 ，修改歌曲的信息
	public boolean updateWebMusic(WebMusic webMusic) {
		return sqlSessionTemplate.update(WebMusic.class.getCanonicalName()+".update", webMusic)>0?true:false;
	}

}
