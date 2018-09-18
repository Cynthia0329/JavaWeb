package com.wlql.daoimpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wlql.dao.WebFileDao;
import com.wlql.domain.Page;
import com.wlql.domain.WebFile;


@Repository
public class WebFileDaoImpl implements WebFileDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public Page<WebFile> selectAllByPage(Page<WebFile> pager, WebFile t) {
		if(t==null){
			t=new WebFile();
		}
		List<WebFile> list = sqlSessionTemplate.selectList(t.getClass().getCanonicalName()+".selectForPage", pager);
		pager.setResults(list);
		return pager;
	}

	public void insertWebFile(WebFile webFile) {
		sqlSessionTemplate.insert(webFile.getClass().getCanonicalName()+".create", webFile);
		
	}

	public boolean deleteWebFile(String file_pk_id) {
		return sqlSessionTemplate.insert(WebFile.class.getCanonicalName()+".delete", file_pk_id)>0?true:false;
		
	}

	public WebFile getWebFileByPkId(String music_id) {
		return sqlSessionTemplate.selectOne(WebFile.class.getCanonicalName()+".getWebFileByPkId", music_id);
	}

	public List<WebFile> getWebFileListByPkId(String music_id) {
		return sqlSessionTemplate.selectList(WebFile.class.getCanonicalName()+".getWebFileByPkId", music_id);
	}

	public WebFile getWebFileById(String fileid) {
		return sqlSessionTemplate.selectOne(WebFile.class.getCanonicalName()+".getWebFileById", fileid);
	}

}
