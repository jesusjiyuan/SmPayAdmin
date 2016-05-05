package com.zero2ipo.water.shop.webc;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.water.shop.bizc.IShop;
import com.zero2ipo.water.shop.bo.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 优惠券功能
 * @author zhengyunfei
 *
 */
@Controller
@RequestMapping("/Shop")
public class ShopAction extends BaseCtrl{

	@Autowired @Qualifier("Shop")
	private IShop shop;
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping("forInit.shtml")
    public ModelAndView forInit(){
        return new ModelAndView("/s9/shop/list.jsp");
    }
    /**
     * 修改页面
     * @param id
     * @return
     */
   @RequestMapping("forUpdateInitPage.shtml")
   public ModelAndView forUpdateInitPage(String id) {
   	ModelAndView mv = new ModelAndView("/s9/shop/shop_upd.jsp");
   	Shop bo=null;
	try {
		bo=shop.findById(id);
		if(StringUtil.isNullOrEmpty(bo)){
			bo=new Shop();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	mv.addObject("bo",bo);
	return mv;
   }
   /**
    * 新增页面
    * @param id
    * @return
    */
  @RequestMapping("forAddInitPage.shtml")
  public ModelAndView forUpdateInitPage() {
  	ModelAndView mv = new ModelAndView("/s9/shop/shop_add.jsp");
	return mv;
  }
  /**
   * 保存功能
   * @param curNo
   * @param curSize
   * @return
   */

  @RequestMapping("add.shtml")
  public ModelAndView addSave(Shop bo) {
	Map<String,Object> resultMap=new HashMap<String,Object>();
	  ModelAndView mv=new ModelAndView();
  	try{
  		boolean flag=false;
  		flag=shop.add(bo);
		resultMap.put("backInfo", 1);
  	}catch (Exception e) {
  		e.printStackTrace();
  		resultMap.put("backInfo", 0);
	}
	  mv.setViewName("/s9/shop/shop_add.jsp");
	return mv;
  }
  	/**
  	 * ajax查询所有功能
  	 * @param curNo
  	 * @param curSize
  	 * @return
  	 */
    @RequestMapping("findAllList.shtml")
    @ResponseBody
    public Map<String,Object>  findAllList(String curNo, String curSize,String name,String mobile){
    	 Map<String,Object> jsonMap = new HashMap<String, Object>();
    	 try {
    		 /************* 分页处理 ************/
 			int skip;
 			int max;
 			if (StringUtil.isNullOrEmpty(curNo))
 				curNo = "0";
 			if (StringUtil.isNullOrEmpty(curSize))
 				curSize = "20";
 			skip = Integer.parseInt(curNo);
 			max = Integer.parseInt(curSize);
 			/************  分页处理结束 ***********/
			Map<String, Object> map = new HashMap<String, Object>();
			if(!StringUtil.isNullOrEmpty(name)){
				map.put("name",name);
			}
			 if(!StringUtil.isNullOrEmpty(mobile)){
				 map.put("mobile",mobile);
			 }
        	int total=0;
        	total=shop.findAllListCount(map);
        	List<Shop> list= null;
        	if(total>0){
        		list = shop.findAllListPage(map, (skip-1)*max, max);
        	}
        	jsonMap.put("Rows", list);
    		jsonMap.put("Total", total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMap;
    }
    /**
     * 修改功能
     * @return
     */
    @RequestMapping("update.shtml")
    public ModelAndView updShop(Shop bo) {
 	Map<String,Object> resultMap=new HashMap<String, Object>();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/s9/shop/shop_upd.jsp");
    	try{
    		boolean flag=false;
    		flag=shop.update(bo);
			mv.addObject("backInfo", 1);
    	}catch (Exception e) {
    		e.printStackTrace();
    		mv.addObject("backInfo", 0);
 		}
 		return mv;
    }
    /**删除功能
     * @return
     */
    @RequestMapping("delShop.shtml")
    @ResponseBody
    public Map<String,Object> delShop(String ids) {
 	Map<String,Object> resultMap=new HashMap<String, Object>();
    	try{
    		boolean flag=false;
    		flag=shop.delete(ids);
 			resultMap.put("success", flag);
    	}catch (Exception e) {
    		e.printStackTrace();
    		resultMap.put("success", false);
 		}
 		return resultMap;
    }

}
