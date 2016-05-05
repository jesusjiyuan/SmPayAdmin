package com.zero2ipo.water.coupon.bizc.impl;

import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.water.coupon.bizc.ICoupon;
import com.zero2ipo.water.coupon.bo.Coupon;
import com.zero2ipo.water.coupon.bo.VipCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
@Service("coupon")
public class CouponImpl implements ICoupon {
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;


public List<Coupon> findAllListPage(Map<String, Object> map, int i, int max){
	List<Coupon> list = new ArrayList<Coupon>();
	try {
		//设置数据库类型: 网站全局库(01)
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
		list = (List<Coupon>)baseDao.findForList("findCouponList", map,i,max);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

	public List<Coupon> findAllList(Map<String, Object> map){
		List<Coupon> list = new ArrayList<Coupon>();
		try {
			//设置数据库类型: 网站全局库(01)
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
			list = (List<Coupon>)baseDao.findForList("findCouponList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean update(Coupon car){
		boolean flag=false;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("updateCoupon", car);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	public boolean add(Coupon bo){
		boolean flag=false;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			if(StringUtil.isNullOrEmpty(bo.getCode())){//如果优惠券编码为空，那么则自动生成
				bo.setCode(StringUtil.ShortText(new Date().getTime()+"", new Date().getTime()+""));
			}
			baseDao.updObject("insertCoupon", bo);
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
				baseDao.delObject("deleteCouponByIds", map);
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
			count = (Integer)baseDao.findForObject("findCouponListCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public Coupon findById(String id){
			Coupon car=null;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
				Map<String,Object> queryMap=new HashMap<String,Object>();
				queryMap.put("id", id);
				car = (Coupon) baseDao.findForObject("findCoupon", queryMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return car;
	}

	@Override
	public boolean addVipCoupon(VipCoupon bo) {
		boolean flag=false;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("addVipCoupon", bo);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int findVipCouponList(Map<String, Object> map) {
		int count=0;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
			count = (Integer)baseDao.findForObject("findVipCouponListCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<VipCoupon> findVipCouponListPage(Map<String, Object> map, int i,
			int max) {
		// TODO Auto-generated method stub

		List<VipCoupon> list = new ArrayList<VipCoupon>();
		try {
			//设置数据库类型: 网站全局库(01)
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
			list = (List<VipCoupon>)baseDao.findForList("findVipCouponList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean deleteVipCoupon(VipCoupon bo) {
		boolean flg=false;
		try {
			if(!StringUtil.isNullOrEmpty(bo)){
				String ids=bo.getId()+"";
				String values[]=ids.split(",");
				for(int i=0;i<values.length;i++){
					VipCoupon vip=new VipCoupon();
					vip.setId(Long.parseLong(values[i]));
					baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
					baseDao.delObject("deleleteVipCoupon", vip);
				}
			}
			flg=true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flg;

	}


}
