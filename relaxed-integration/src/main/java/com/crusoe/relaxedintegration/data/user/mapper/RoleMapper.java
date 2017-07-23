package com.crusoe.relaxedintegration.data.user.mapper;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crusoe.relaxedintegration.data.user.bean.NeuUserRoleBean;


@Repository
public interface RoleMapper {
	/**
	 * 添加或者修改角色的权限
	 * @param id  neuUserRole的id
	 * @param cmptId 赛事的id
	 * @param neuUserId 委托人的id
	 * @param roles 角色
	 * @param deadline 截止日期
	 * 
	 */
	Integer mergeNeuUserRole(@Param("commitNeuUserId") Integer commitNeuUserId , @Param("neuUserId") Integer neuUserId , @Param("neuCmptId") Integer neuCmptId , 
			@Param("roleId") Integer roleId, @Param("deadline") Date deadline);
	
	/**
	 * 添加或者修改角色的初始功能权限
	 * @param cmptId 赛事的id
	 * @param neuUserId 委托人的id
	 * @param roleId 角色
	 * 
	 */
	Integer mergeNeuUserRoleInitFunctional(@Param("neuUserId") Integer neuUserId , @Param("neuCmptId") Integer neuCmptId, @Param("roleId") Integer roleId);
	
	/**
	 * 根据neuUserId、neuCmptId、role组合查找NeuUserRoleBean
	 * @param neuUserId 委托人的id
	 * @param neuCmptId 赛事的id
	 * @param roleId 角色的id
	 * 
	 */
	List<NeuUserRoleBean> getUserRoleList( @Param("neuUserId") Integer neuUserId , @Param("neuCmptId") Integer neuCmptId , 
			@Param("roleId") Integer roleId);
	

	/**
	 * 
	 * @param neuUserId
	 * @return
	 */
	String getFunctionalPermission(Integer neuUserId);
	
	
	
	String getFunctionalRole(Integer neuUserId);
	/**
	 * 删除角色人员
	 * @param id neuUserRole的id
	 * 
	 */
	Integer deleteNeuUserRole(@Param("id") Integer id);
	
	/**
	 * 删除多角色人员
	 * @param id neuUserRole的id
	 * 
	 */
	Integer deleteNeuUserRoleByIds(@Param("ids") List<Integer> ids);
	/**
	 * 删除角色人员
	 * @param id neuUserRole的id
	 * 
	 */
	NeuUserRoleBean getNeuUserRoleById(@Param("id") Integer id);
	
	/**
	 * 获取授权人员的名单列表
	 *  @param neuUserId 组委会的id
	 * @param cmptId 赛事的id
	 * @param userId 委托人的id
	 * @param role 角色
	 * 
	 */
	Integer getNeuUserRoleList(@Param("neuUserId") Integer neuUserId , @Param("cmptId") Integer cmptId,
			@Param("userId") Integer userId , @Param("role") Integer role );
	/**
	 * 根据赛事的id获取委托用户的id
	 *  @param neuCmptId 赛事的id
	 * 
	 */
	List<Integer> getNeuUserIdsByNeuCmptId(@Param("neuCmptId") Integer neuCmptId);
	/**
	 * 根据赛事的id获取委托用户的id
	 *  @param neuCmptId 赛事的id
	 * 
	 */
	List<Integer> getIds(@Param("neuUserId") Integer neuUserId,@Param("neuCmptId") Integer neuCmptId);
	
	/**
	 * 根据赛事的id获取委托用户的id
	 *  @param neuCmptId 赛事的id
	 * 
	 */
	List<Integer> getNeuCmptIdsByNeuUserId(@Param("neuUserId") Integer neuUserId,@Param("neuCmptId") Integer neuCmptId,@Param("roleId") Integer roleId);
	/**
	 * 根据赛事的组委会id和用户的id获取委托用
	 *  @param neuCmptId 赛事的id
	 * 
	 */
	List<Integer> getNeuCmptIds(@Param("neuUserId") Integer neuUserId,@Param("userId") Integer userId,@Param("neuCmptId") Integer neuCmptId,@Param("roleId") Integer roleId);
	
	
	/**
	 * 根据赛事的id
	 *  @param neuCmptId 赛事的id
	 * 
	 */
	List<NeuUserRoleBean> getNeuCmptsByNeuUserId(@Param("neuUserId") Integer neuUserId);
	/**
	 * 根据赛事的id获取赛事的deadline
	 *  @param neuCmptId 赛事的id
	 * 
	 */
	Date getDeadLineNeuCmptId(@Param("neuCmptId") Integer neuCmptId);
	/**
	 * 根据用户的id以及的赛事Id获取roles
	 *  @param neuCmptId 赛事的id
	 *  @param neuUserId 用户的id
	 * 
	 */
	List<Integer> getRoleIds(@Param("neuUserId") Integer neuUserId,@Param("neuCmptId") Integer neuCmptId);
	
	String getPermission(@Param("neuUserId")Integer neuUserId, @Param("neuCmptId")Integer neuCmptId);  

	Integer deleteNeuUserRoleByUserIdAndCmptId(@Param("neuUserId") Integer neuUserId,@Param("neuCmptId") Integer neuCmptId);
	/**
	 * 根据neuUserId、neuCmptId、role组合查找NeuUserRoleBean
	 * @param neuUserId 委托人的id
	 * @param neuCmptId 赛事的id
	 * @param roleId 角色的id
	 * @param commitNeuUserId 组委会的id
	 */
	List<NeuUserRoleBean> getUserRoleLists(@Param("commitNeuUserId") Integer commitNeuUserId, @Param("neuUserId") Integer neuUserId , @Param("neuCmptId") Integer neuCmptId , 
			@Param("roleId") Integer roleId);
	
	/**
	 * 根据neuCmptId逻辑删除NeuUserRole中与该赛事有关的role
	 
	 * @param neuCmptId 赛事的id
	 
	 */
	int deleteNeuRole(@Param("neuCmptId") Integer neuCmptId);
}






















