package com.crusoe.relaxedintegration.data.user.bean;

public class GEnroll {
	private int userId;
	private String phoneNum;
	private int cmpt_id;
	private String username;
	private String idcard;
	private String openId;
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public int getCmpt_id() {
		return cmpt_id;
	}
	public void setCmpt_id(int cmpt_id) {
		this.cmpt_id = cmpt_id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
