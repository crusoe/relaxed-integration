package com.crusoe.relaxedintegration.data.user.bean;

import java.util.Date;
import java.util.List;

public class NeuUserRoleAppearBean {
	private String cmptName;
	private String phoneNum;
	private String userName;
	private Integer userId;
	private Integer cmptId;
	private Date deadline;
	private List<Integer> roles;
	private Integer id;
	public String getCmptName() {
		return cmptName;
	}
	public void setCmptName(String cmptName) {
		this.cmptName = cmptName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Integer> getRoles() {
		return roles;
	}
	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCmptId() {
		return cmptId;
	}
	public void setCmptId(Integer cmptId) {
		this.cmptId = cmptId;
	}


}
