package com.zero2ipo.water.coupon.bo;

import java.io.Serializable;

public class Coupon implements Serializable {
	private String id;//主键
	private String name;//优惠券名称
	private String money;//优惠券金额
	private String remark;//备注
	private String status;//使用状态
	private String startTime;//有效期开始时间
	private String endTime;//有效期结束时间
	private String code;//优惠券独立的编码
	private String flag;//有效标识
	private float min;//限制条件：订单满多少元可以使用

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


}
