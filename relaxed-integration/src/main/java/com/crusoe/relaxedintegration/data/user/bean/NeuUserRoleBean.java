package com.crusoe.relaxedintegration.data.user.bean;

import java.util.Date;

public class NeuUserRoleBean {
	private Date deadline;
	private Integer role;
	private Integer cmptId;
	private Integer userId;
	private Integer id;
	private String locName;
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getCmptId() {
		return cmptId;
	}
	public void setCmptId(Integer cmptId) {
		this.cmptId = cmptId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	

}
