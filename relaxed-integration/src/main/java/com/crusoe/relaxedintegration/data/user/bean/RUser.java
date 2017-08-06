package com.crusoe.relaxedintegration.data.user.bean;

import java.lang.reflect.Field;

public class RUser {
	private Integer rUserId;
	private String s1UserId;
	private String s2UserId;
	private String s3UserId;
	private String feature1;
	private String feature2;
	private String feature3;
	public Integer getrUserId() {
		return rUserId;
	}
	public void setrUserId(Integer rUserId) {
		this.rUserId = rUserId;
	}
	public String getS1UserId() {
		return s1UserId;
	}
	public void setS1UserId(String s1UserId) {
		this.s1UserId = s1UserId;
	}
	public String getS2UserId() {
		return s2UserId;
	}
	public void setS2UserId(String s2UserId) {
		this.s2UserId = s2UserId;
	}
	public String getS3UserId() {
		return s3UserId;
	}
	public void setS3UserId(String s3UserId) {
		this.s3UserId = s3UserId;
	}
	public String getFeature1() {
		return feature1;
	}
	public void setFeature1(String feature1) {
		this.feature1 = feature1;
	}
	public String getFeature2() {
		return feature2;
	}
	public void setFeature2(String feature2) {
		this.feature2 = feature2;
	}
	public String getFeature3() {
		return feature3;
	}
	public void setFeature3(String feature3) {
		this.feature3 = feature3;
	}
	
	public String getTargetUserId(String subSys) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
        Field declaredField = this.getClass().getDeclaredField(subSys+"UserId");
        declaredField.setAccessible(true);
        return (String) declaredField.get(this);
	}
}










