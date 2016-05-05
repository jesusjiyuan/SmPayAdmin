package com.zero2ipo.water.shop.bizc;

import com.zero2ipo.water.shop.bo.Shop;

import java.util.List;
import java.util.Map;


public interface IShop {

	public List<Shop> findAllListPage(Map<String, Object> map, int i, int max);

	public List<Shop> findAllList(Map<String, Object> map);

	public boolean update(Shop notic);

	public int findAllListCount(Map<String, Object> map);

	public Shop findById(String id);

	public boolean add(Shop bo);

	public boolean delete(String ids);

}
