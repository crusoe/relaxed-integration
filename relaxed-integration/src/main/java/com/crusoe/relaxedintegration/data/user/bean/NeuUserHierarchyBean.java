package com.crusoe.relaxedintegration.data.user.bean;

import java.sql.Timestamp;

public class NeuUserHierarchyBean {
	private Integer userId;
	private Timestamp addTime;
	private Integer neuUserHierarchyId;
	private Integer parentNeuUserId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public Integer getNeuUserHierarchyId() {
		return neuUserHierarchyId;
	}
	public void setNeuUserHierarchyId(Integer neuUserHierarchyId) {
		this.neuUserHierarchyId = neuUserHierarchyId;
	}
	public Integer getParentNeuUserId() {
		return parentNeuUserId;
	}
	public void setParentNeuUserId(Integer parentNeuUserId) {
		this.parentNeuUserId = parentNeuUserId;
	}
	


}
