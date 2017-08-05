package com.crusoe.relaxedintegration.data.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crusoe.relaxedintegration.data.user.bean.GEnroll;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserHierarchyBean;
import com.crusoe.relaxedintegration.data.user.bean.RUser;

@Repository
public interface EnrollMapper {
	
	/**
	 * 添加赛事执行人员
	 * @param neuUserId 用户的id
	 * @param parentNeuUserId 组委会的id
	 * 
	 */
	Integer saveNeuUserRelation(@Param("neuUserId") Integer neuUserId, @Param("parentNeuUserId") Integer parentNeuUserId);
	/**
	 * 删除赛事执行人员
	 * @param neuUserHierarchyId 用户的关系id
	 * 
	 */
	Integer deleteNeuUserRelation(@Param("neuUserHierarchyId") Integer neuUserHierarchyId);
	/**
	 *删除赛事执行人员在该组委会下的权限
	 * @param neuUserHierarchyId 用户的关系id
	 */
	Integer deleteNeuUserRole(@Param("neuUserHierarchyId") Integer neuUserHierarchyId);
	
	
	/**
	 * 获取组委会名下的人员列表
	 * @param neuUserHierarchyId 用户的关系id
	 * 
	 */
//	List<NeuUserHierarchyBean> getNeuUserRelationList(@Param("neuUserId") Integer neuUserId , @Param("keyword") String keyword);
	/**
	 *根据报名的赛事名称获取报名整合系统的赛事id(模糊)
	 * @param neuUserHierarchyId 用户的关系id
	 * 
	 */
	List<Integer> getNeuCmptIdsByGCmptName(@Param("cmptName") String cmptName);
	/**
	 *根据报名的用户手机号获取报名整合系统的用户id 
	 * @param neuUserHierarchyId 用户的关系id
	 * 
	 */
	Integer getNeuUserIdByGPhoneNum(@Param("phoneNum") String phoneNum);
	/**
	 *根据报名的用户手机号获取报名整合系统的用户id (模糊查询)
	 * @param neuUserHierarchyId 用户的关系id
	 * 
	 */
	List<Integer> getNeuUserIdsByGPhoneNum(@Param("phoneNum") String phoneNum);
//	/**
//	 *根据整合赛事id获取报名的赛事
//	 * @param neuUserHierarchyId 用户的关系id
//	 * 
//	 */
//	List<GCmptBean> getGCmptByNeuCmptIds(@Param("neuCmptIds") List<Integer> neuCmptIds);
//	/**
//	 *根据整合赛事id获取报名的赛事
//	 * @param neuUserHierarchyId 用户的关系id
//	 * 
//	 */
//	GCmptBean getGCmptByNeuCmptId(@Param("neuCmptId") Integer neuCmptId);
	/**
	 *根据整合的用户id获取报名用户
	 * @param neuUserHierarchyId 用户的关系id
	 * 
	 */
	GEnroll getUserByGUserId(@Param("neuUserId") Integer neuUserId);
	/**
	 *获取关系列表
	 * @param neuUserId 用户的id
	 * @param parentNeuUserId 组委会的id
	 */
	 NeuUserHierarchyBean getNeuUserRelation(@Param("neuUserId") Integer neuUserId,
			@Param("parentNeuUserId") Integer parentNeuUserId);
	/**
	 *根据组委会id获取关系列表
	 * @param parentNeuUserId 组委会的id
	 * 
	 */
	List<NeuUserHierarchyBean> getNeuUserRelationList(@Param("parentNeuUserId") Integer parentNeuUserId);
	
	/**
	 *获取关系列表
	 * @param neuUserId 用户的id
	 * @param parentNeuUserId 组委会的id
	 */
	 List<NeuUserHierarchyBean> getNeuUserRelations(@Param("neuUserIds") List<Integer> neuUserIds,
			@Param("parentNeuUserId") Integer parentNeuUserId);
	
	List<NeuUserHierarchyBean> getNeuUserHierarchys(@Param("neuUserId") Integer neuUserId);
	
	 

}






















