package com.zero2ipo.water.shop.bizc.impl;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.water.shop.bizc.IShop;
import com.zero2ipo.water.shop.bo.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("Shop")
public class ShopImpl implements IShop {
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
public List<Shop> findAllListPage(Map<String, Object> map, int i, int max){
	List<Shop> list = new ArrayList<Shop>();
	try {
		//设置数据库类型: 网站全局库(01)
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
		list = (List<Shop>)baseDao.findForList("findShopList", map,i,max);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

	public List<Shop> findAllList(Map<String, Object> map){
		List<Shop> list = new ArrayList<Shop>();
		try {
			//设置数据库类型: 网站全局库(01)
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
			list = (List<Shop>)baseDao.findForList("findShopList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean update(Shop car){
		boolean flag=false;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("updateShop", car);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	public boolean add(Shop bo){
		boolean flag=false;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("insertShop", bo);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	/*删除
	*@author zhengYunFei
	*@date Mon Jan 26 11:54:24 GMT+08:00 2015
	*/
	public boolean delete(String id){
		boolean flag=false;
		try{
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id",id.split(","));
				baseDao.delObject("deleteShopByIds", map);
				flag=true;
			}catch(Exception e){;
				flag=false;
				e.printStackTrace();
				throw new BaseException("删除！",e);
		}
	return flag;
}
	public int findAllListCount(Map<String, Object> map){
		int count=0;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
			count = (Integer)baseDao.findForObject("findShopListCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public Shop findById(String id){
			Shop car=null;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
				Map<String,Object> queryMap=new HashMap<String,Object>();
				queryMap.put("id", id);
				car = (Shop) baseDao.findForObject("findShop", queryMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return car;
	}








}
