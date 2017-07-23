package com.crusoe.relaxedintegration.data.user.service;


import java.util.List;

import com.crusoe.relaxedintegration.data.user.bean.NeuUserHierarchyAppearBean;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserHierarchyBean;


public interface EnrollService {

	/**
	 * 添加赛事执行人员
	 * @param neuUserId 用户的id
	 * @param parentNeuUserId 组委会的id
	 * 
	 */
	Integer saveNeuUserRelation(Integer neuUserId, Integer parentNeuUserId);
	/**
	 * 删除赛事执行人员
	 * @param neuUserId 用户的id
	 * 
	 */
	Integer deleteNeuUserRelation(Integer neuUserHierarchyId);
	/**
	 * 获取用户关系展示
	 * @param neuUserId 用户的id
	 * @param keyword 用户的注册手机号或者赛事名称
	 * 
	 */
	List<NeuUserHierarchyAppearBean> getNeuUserRelationList(String keyword,Integer parentNeuUserId);
	
	
	
	NeuUserHierarchyBean getLastestNeuUserHierarchy(Integer neuUserId);
	
}

















