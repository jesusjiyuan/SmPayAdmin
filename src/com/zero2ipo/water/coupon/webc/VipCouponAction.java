package com.zero2ipo.water.coupon.webc;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.contants.CodeContants;
import com.zero2ipo.plugins.user.bo.User;
import com.zero2ipo.water.coupon.bizc.ICoupon;
import com.zero2ipo.water.coupon.bo.Coupon;
import com.zero2ipo.water.coupon.bo.VipCoupon;
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
 * 优惠券和会员关系对应表
 * @author zhengyunfei
 *
 */
@Controller
@RequestMapping("/vc")
public class VipCouponAction extends BaseCtrl{

	@Autowired @Qualifier("coupon")
	private ICoupon coupon;


  /**
   * 保存优惠券和会员的关系表
   * @param curNo
   * @param curSize
   * @return
   */

  @RequestMapping("addSave.shtml")
  @ResponseBody
  public Map<String,Object> addSave(VipCoupon bo,String couponId) {
	  Map<String,Object> resultMap=new HashMap<String,Object>();
	  String sMobile="";
	  try{
		  //当前登录的账号是发放者
		  User u= (User) session.getAttribute("user");
		  if(!StringUtil.isNullOrEmpty(u)) {
			  sMobile=u.getMobile();
		  }
			  boolean flag=false;
		    if(!StringUtil.isNullOrEmpty(bo)){
			  String rMobile=bo.getrMobile();
			  Coupon c=coupon.findById(couponId);
			  String [] values=rMobile.split(",");
			  for(int i=0;i<values.length;i++){
				  String uid=values[i];
				  if(!StringUtil.isNullOrEmpty(c)){
					  bo.setCouponCode(c.getCode());
					  bo.setCouponEndTime(c.getEndTime());
					  bo.setCouponStartTime(c.getStartTime());
					  bo.setCouponMoney(c.getMoney());
					  bo.setCouponName(c.getName());
					  bo.setCouponRemark(c.getRemark());
					  bo.setrMobile(uid);
					  bo.setsMobile(sMobile);
					  bo.setStatus(CodeContants.FLAG_0+"");
					  flag=coupon.addVipCoupon(bo);
				  }
			  }
		  }
		  resultMap.put("success", flag);
	  }catch (Exception e) {
		  e.printStackTrace();
		  resultMap.put("success", false);
	  }
	  return resultMap;

  }
  /**
   * 会员优惠券页面
   * @return
   */
  @RequestMapping("vipCouponInit.shtml")
  public ModelAndView forUpdateInitPage(String userId) {
  	ModelAndView mv = new ModelAndView("/s9/vip/vipcoupon.jsp");
  	mv.addObject("userId", userId);
	return mv;
  }
  /**
   * 查询会员拥有的优惠券列表
   * @param curNo
   * @param curSize
   * @return
   */
  @RequestMapping("findVipCouponList.shtml")
  @ResponseBody
  public Map<String,Object>  findAllList(String curNo, String curSize,String userId){
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
			if(!StringUtil.isNullOrEmpty(userId)){
				map.put("userId", userId);
			}
      	int total=0;
      	total=coupon.findVipCouponList(map);
      	List<VipCoupon> list= null;
      	if(total>0){
      		list = coupon.findVipCouponListPage(map, (skip-1)*max, max);
      	}
      	jsonMap.put("Rows", list);
  		jsonMap.put("Total", total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMap;
  }
  /**
   * 删除已发送的优惠券
   * @param curNo
   * @param curSize
   * @return
   */
  @RequestMapping("delVipCoupon.shtml")
  @ResponseBody
  public Map<String,Object> delCoupon(VipCoupon  ids) {
	Map<String,Object> resultMap=new HashMap<String, Object>();
  	try{
  		boolean flag=false;
  		flag=coupon.deleteVipCoupon(ids);
			resultMap.put("success", flag);
  	}catch (Exception e) {
  		e.printStackTrace();
  		resultMap.put("success", false);
		}
		return resultMap;
  }
}
