package com.zero2ipo.water.coupon.bizc;

import com.zero2ipo.water.coupon.bo.Coupon;
import com.zero2ipo.water.coupon.bo.VipCoupon;

import java.util.List;
import java.util.Map;


public interface ICoupon {

	public List<Coupon> findAllListPage(Map<String, Object> map, int i, int max);

	public List<Coupon> findAllList(Map<String, Object> map);

	public boolean update(Coupon notic);

	public int findAllListCount(Map<String, Object> map);

	public Coupon findById(String id);

	public boolean add(Coupon bo);

	public boolean delete(String ids);

	public boolean addVipCoupon(VipCoupon bo);

	public int findVipCouponList(Map<String, Object> map);

	public List<VipCoupon> findVipCouponListPage(Map<String, Object> map, int i,
												 int max);

	public boolean deleteVipCoupon(VipCoupon bo);
}
