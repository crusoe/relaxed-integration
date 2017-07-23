package com.crusoe.relaxedintegration.data.user.service;

import java.util.HashMap;

import com.crusoe.relaxedintegration.data.user.bean.GtEnroll;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserBean;

public interface UserService {
	/**
	 * 根据userId、unionId、phoneNum依次查找neuAdmin，一旦查到即停止。并且合并数据到account
	 * @param phoneNum
	 * @return 找到或能创建-NeuUserBean/没找到且不能创建-null
	 */
	NeuUserBean getNeuAdmin(int userId, String unionId, String phoneNum,
			int role, String source,String target, Integer roleId); 
	/**
	 * 根据unionId、phoneNum依次查寻子系统的userId，一旦查到即停止
	 * @param unionId、phoneNum
	 * @return null-创建失败/String-创建成功
	 */
	int getUserIdFromSys(String unionId, String phoneNum, String target,
			int neuUsreId,int role);
	/**
	 * 根据userId、unionId、phoneNum、target source 获取目标系统的userid
	 * @param userId、unionId、phoneNum、target source 
	 * @return
	 */
	HashMap<String,Object> getTargetUserId(int userId, String unionId, String phoneNum,
			int role, String source,String target, Integer roleId);
	/**
	 * 获取组织id
	 * @param gtUserId 计时userId
	 * @return
	 */
	String getOrgIdByGtUserId(int gtUserId);
	/**
	 * 更新或插入用户系统中的用户信息
	 * @param 
	 * @return
	 */
	boolean mergeUserAccount(String phoneNum, int role, String password, String source);
	/**
	 * 更新或插入目标子系统中的相关用户信息
	 * @param 
	 * @return
	 */
	boolean mergeTargetSysUserInfo(String phoneNum, int role,
			String password, String target);
	/**
	 * 根据报名的赛事Id和用户的userId获取在计时系统中的相关信息
	 * @param 
	 * @return
	 */
	GtEnroll getGtEnrollFromG(int cmptId,int userId);


}
