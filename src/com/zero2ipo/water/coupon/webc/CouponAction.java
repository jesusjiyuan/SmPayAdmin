package com.zero2ipo.water.coupon.webc;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.water.coupon.bizc.ICoupon;
import com.zero2ipo.water.coupon.bo.Coupon;
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
@RequestMapping("/coupon")
public class CouponAction extends BaseCtrl{

	@Autowired @Qualifier("coupon")
	private ICoupon coupon;
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping("forInit.shtml")
    public ModelAndView forInit(){
        return new ModelAndView("/s9/YouHuiQuan/list.jsp");
    }
    /**
     * 修改页面跳转
     * @param id
     * @return
     */
   @RequestMapping("forUpdateInitPage.shtml")
   public ModelAndView forUpdateInitPage(String id) {
   	ModelAndView mv = new ModelAndView("/s9/YouHuiQuan/update.jsp");
   	Coupon bo=null;
	try {
		bo=coupon.findById(id);
		if(StringUtil.isNullOrEmpty(bo)){
			bo=new Coupon();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	mv.addObject("bo",bo);
	return mv;
   }
   /**
    * 新增页面跳转
    * @param id
    * @return
    */
  @RequestMapping("forAddInitPage.shtml")
  public ModelAndView forUpdateInitPage() {
  	ModelAndView mv = new ModelAndView("/s9/YouHuiQuan/add.jsp");
	return mv;
  }
  /**
   * 保存洗车券
   * @param curNo
   * @param curSize
   * @return
   */

  @RequestMapping("addSave.shtml")
  @ResponseBody
  public Map<String,Object> addSave(Coupon bo) {
	Map<String,Object> resultMap=new HashMap<String,Object>();
  	try{
  		boolean flag=false;
  		flag=coupon.add(bo);
		resultMap.put("success", flag);
  	}catch (Exception e) {
  		e.printStackTrace();
  		resultMap.put("success", false);
	}
	return resultMap;
  }
  	/**
  	 * ajax查询洗车卷
  	 * @param curNo
  	 * @param curSize
  	 * @return
  	 */
    @RequestMapping("findAllList.shtml")
    @ResponseBody
    public Map<String,Object>  findAllList(String curNo, String curSize){
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
        	int total=0;
        	total=coupon.findAllListCount(map);
        	List<Coupon> list= null;
        	if(total>0){
        		list = coupon.findAllListPage(map, (skip-1)*max, max);
        	}
        	jsonMap.put("Rows", list);
    		jsonMap.put("Total", total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMap;
    }
    /**
     * 修改洗车券
     * @param curNo
     * @param curSize
     * @return
     */
    @RequestMapping("updCoupon.shtml")
    @ResponseBody
    public Map<String,Object> updCoupon(Coupon bo) {
 	Map<String,Object> resultMap=new HashMap<String, Object>();
    	try{
    		boolean flag=false;
    		flag=coupon.update(bo);
 			resultMap.put("success", flag);
    	}catch (Exception e) {
    		e.printStackTrace();
    		resultMap.put("result", false);
 		}
 		return resultMap;
    }
    /**
     * 删除洗车券
     * @param curNo
     * @param curSize
     * @return
     */
    @RequestMapping("delCoupon.shtml")
    @ResponseBody
    public Map<String,Object> delCoupon(String ids) {
 	Map<String,Object> resultMap=new HashMap<String, Object>();
    	try{
    		boolean flag=false;
    		flag=coupon.delete(ids);
 			resultMap.put("success", flag);
    	}catch (Exception e) {
    		e.printStackTrace();
    		resultMap.put("success", false);
 		}
 		return resultMap;
    }

}
