package com.crusoe.relaxedintegration.data.user.bean;

import java.util.ArrayList;
import java.util.List;

import com.crusoe.relaxedintegration.util.Const;


public class NeuUserBean {
	private int neuUserId;	//东软统一用户id
	private int gUserId=Const.InitValue;    //赛客报名系统的用户id
	private int wUserId=Const.InitValue;    //微跑系统的用户id
	private int gtUserId=Const.InitValue;//赛客 计时系统的用户id
	private int role;       //用户账号的角色 (1为跑者、2为组委会)
	private List<AccountBean> account = new ArrayList<>();
	
	public int getNeuUserId() {
		return neuUserId;
	}
	public void setNeuUserId(int neuUserId) {
		this.neuUserId = neuUserId;
	}
	public int getgUserId() {
		return gUserId;
	}
	public void setgUserId(int gUserId) {
		this.gUserId = gUserId;
	}
	public int getwUserId() {
		return wUserId;
	}
	public void setwUserId(int wUserId) {
		this.wUserId = wUserId;
	}
	public int getGtUserId() {
		return gtUserId;
	}
	public void setGtUserId(int gtUserId) {
		this.gtUserId = gtUserId;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public List<AccountBean> getAccount() {
		return account;
	}
	public void setAccount(List<AccountBean> account) {
		this.account = account;
	}
	

	

	
}
