package com.yzgs.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzgs.dao.GoodStoreDao;
import com.yzgs.domain.GoodStore;
import com.yzgs.domain.Page;
import com.yzgs.util.DateUtil;
/**
 * 数据访问层，增删查改方法
 * @author jcy
 *
 */
@Repository
public class GoodStoreDaoImpl implements GoodStoreDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	 /**
	   * 根据id 查询GoodStore对象
	   * @param id
	   * @return
	   */
	@Override
	public GoodStore getGoodStoreById(String id) {
		
		return sqlSessionTemplate.selectOne(GoodStore.class.getCanonicalName()+".selectByPk",id);
	}
	 /**
	   * 根据id 删除GoodStore对象
	   * @param id
	   * @return
	   */
	@Override
	public boolean deleteGoodStoreById(String id) {
		return sqlSessionTemplate.delete(GoodStore.class.getCanonicalName()+".delete",id)>0?true:false;
	}
	 /**
	   * 更新商品库存
	   * @param GoodStore
	   * @return
	   */
	@Override
	public boolean updateGoodStore(GoodStore GoodStore) {
		return sqlSessionTemplate.update(GoodStore.class.getCanonicalName()+".update",GoodStore)>0?true:false;
	}
	/**
	   * 新增库存商品
	   * @param GoodStore
	   * @return
	   */
	@Override
	public boolean addGoodStore(GoodStore GoodStore) {
		return sqlSessionTemplate.insert(GoodStore.class.getCanonicalName()+".create",GoodStore)>0?true:false;
	}
	
	 /**
	   * 分页查询
	   * @param pager
	   * @param t
	   * @return
	   */
	  public Page<GoodStore> selectAllByPage(Page<GoodStore> pager, GoodStore t) {
		    List<GoodStore> list1=new ArrayList<GoodStore>();
			if(t==null){
				t=new GoodStore();
			}
			List<GoodStore> list = sqlSessionTemplate.selectList(t.getClass().getCanonicalName()+".selectForPage", pager);
			for(GoodStore g:list){
				  System.out.println(DateUtil.getDayByTwoDate(g.getGoodValidDate(),new Date()));
				  if( DateUtil.checkTwoDate(g.getGoodValidDate(), new Date())&&DateUtil.getDayByTwoDate(g.getGoodValidDate(),new Date())>=5){
					  //商品有效期时间,大于当前日期
					  g.setBs(0);
					//商品有效期时间距当前日期0-5天
				  }else if(DateUtil.getDayByTwoDate(g.getGoodValidDate(),new Date())>=0&&DateUtil.getDayByTwoDate(g.getGoodValidDate(),new Date())<=5){
					  g.setBs(1);
					  
				  }else{
					//商品有效期时间小于当前日期
					  g.setBs(2);
				  }
				 list1.add(g);
			}
			
			pager.setResults(list1);
			return pager;
		}
	  /**
	   * 清理库存
	   * @param maps 库存信息集合对象
	   * @return
	   */
	@Override
	public boolean cleanGoodStore(Map<String, Object> maps) {
		//sqlSessionTemplate进行数据库操作
		//获取类名
		return sqlSessionTemplate.insert(GoodStore.class.getCanonicalName()+".cleanStore",maps)>0?true:false;
	}


}
