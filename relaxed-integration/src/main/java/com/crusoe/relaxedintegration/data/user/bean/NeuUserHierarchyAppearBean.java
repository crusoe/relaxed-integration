package com.crusoe.relaxedintegration.data.user.bean;

import java.sql.Timestamp;
import java.util.List;

public class NeuUserHierarchyAppearBean {
	private String userName;
	private Integer userId;
	private String phoneNum;
	private Timestamp addTime;
	private Integer execCmptCount;
//	private List<GCmptBean> cmpts;
	private Integer id;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
//	public List<GCmptBean> getCmpts() {
//		return cmpts;
//	}
//	public void setCmpts(List<GCmptBean> cmpts) {
//		this.cmpts = cmpts;
//	}
	public Integer getExecCmptCount() {
		return execCmptCount;
	}
	public void setExecCmptCount(Integer execCmptCount) {
		this.execCmptCount = execCmptCount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
