package com.crusoe.relaxedintegration.data.user.bean;

public class AccountBean {
	private int id;
	private int type; //登录类型(1:微信、2：手机号、3：邮箱、4：微博、5：用户名......)
	private String username;//标识（手机号，邮箱，用户名或第三方应用的唯一标识）
	private String password;//手机密码或者微博的token，微信为空
	private int neuUserId;//东软统一用户id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNeuUserId() {
		return neuUserId;
	}
	public void setNeuUserId(int neuUserId) {
		this.neuUserId = neuUserId;
	}

}
