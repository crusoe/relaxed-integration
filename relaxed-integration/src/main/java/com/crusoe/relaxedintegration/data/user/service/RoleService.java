package com.crusoe.relaxedintegration.data.user.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crusoe.relaxedintegration.data.user.bean.NeuUserRoleAppearBean;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserRoleBean;




public interface RoleService {

	
	
	String getFunctionalPermission(Integer neuUserId);

	/**
	 * 获取NeuCmpt
	 * @param subSys 子系统标识
	 * @param cmptId 子系统赛事id
	 * @return null-没找到/NeuCmpt-找到
	 */
	public List<NeuUserRoleAppearBean> getNeuUserRoleList(Integer neuUserId, Integer cmptId,
			Integer userId, Integer role);
	
	/**
	 * 删除角色人员
	 * @param id neuUserRole的id
	 * 
	 */
	Integer deleteNeuUserRole(Integer id);
	/**
	 * 添加或者修改角色的权限
	 * @param id  neuUserRole的id
	 * @param cmptId 赛事的id
	 * @param neuUserId 委托人的id
	 * @param roles 角色
	 * @param deadline 截止日期
	 * 
	 */
	Integer mergeNeuUserRole(Integer commitNeuUserId ,Integer neuUserId ,Integer neuCmptId , Integer prevUserId ,Integer prevCmptId ,List<Integer> roleIds, Date deadline);

	List<NeuUserRoleBean> getUserRoleList(Integer neuUserId, Integer cmptId, Integer role);

	String getPermission(Integer neuUserId, Integer neuCmptId);

	List<NeuUserRoleBean> getNeuUserRoles(Integer userId, Integer cmptId, Integer role);
}

















