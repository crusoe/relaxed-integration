 package com.crusoe.relaxedintegration.data.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.crusoe.relaxedintegration.data.user.bean.GCmptBean;
import com.crusoe.relaxedintegration.data.user.bean.GEnroll;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserHierarchyAppearBean;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserHierarchyBean;
import com.crusoe.relaxedintegration.data.user.mapper.EnrollMapper;
import com.crusoe.relaxedintegration.data.user.mapper.RoleMapper;
import com.crusoe.relaxedintegration.data.user.service.EnrollService;






@Service
public class EnrollServiceImpl implements EnrollService{
	
	private static final Logger log = Logger.getLogger(EnrollServiceImpl.class);
	@Autowired
	private EnrollMapper enrollMap;
	@Autowired
	private RoleMapper roleMap;

	@Override
	public Integer saveNeuUserRelation(Integer neuUserId,
			Integer parentNeuUserId) {
		return enrollMap.saveNeuUserRelation(neuUserId, parentNeuUserId);
	}

	@Override
	public Integer deleteNeuUserRelation(Integer neuUserHierarchyId) {
		int retnums = enrollMap.deleteNeuUserRole(neuUserHierarchyId);
		int returnnum = enrollMap.deleteNeuUserRelation(neuUserHierarchyId);
		if(retnums>=0&&returnnum>0){//删除成功
			return 1;
		}else{
			return 0; // 删除失败
		}
		
	}

	@Override
	public List<NeuUserHierarchyAppearBean> getNeuUserRelationList(String keyword,Integer parentNeuUserId) {
		List<NeuUserHierarchyAppearBean> neuUserHierarchyAppears=new ArrayList<NeuUserHierarchyAppearBean>();
		List<NeuUserHierarchyBean> neuUserHierarchys=new ArrayList<NeuUserHierarchyBean>();
		if("".equals(keyword) || keyword==null){
			//查询组委会下的所有执行人员信息
			neuUserHierarchys=enrollMap.getNeuUserRelationList(parentNeuUserId);
//			//TODO 为了测试直接返回
//			return neuUserHierarchys;
		}else{
			//keyword是赛事名
			List<Integer> neuCmptIds=enrollMap.getNeuCmptIdsByGCmptName(keyword);
			if(neuCmptIds!=null && neuCmptIds.size()>0){
				List<Integer> neuEnrollIds=new ArrayList<Integer>();
				for(int i=0;i<neuCmptIds.size();i++){
					List<Integer> curNeuEnrollIds=roleMap.getNeuUserIdsByNeuCmptId(neuCmptIds.get(i));
					neuEnrollIds.addAll(curNeuEnrollIds);
				}
				
				neuUserHierarchys=enrollMap.getNeuUserRelations(neuEnrollIds, parentNeuUserId);
			}else{
				//keyword是用户注册手机号码
				/*Integer neuEnrollId=enrollMap.getNeuUserIdByGPhoneNum(keyword); 
				NeuUserHierarchyBean neuUserHierarchyBean=enrollMap.getNeuUserRelation(neuEnrollId, parentNeuUserId);
				neuUserHierarchys.add(neuUserHierarchyBean);*/	
				List<Integer> neuEnrollIds=enrollMap.getNeuUserIdsByGPhoneNum(keyword);
				neuUserHierarchys=enrollMap.getNeuUserRelations(neuEnrollIds, parentNeuUserId);
			}
		}
		if(neuUserHierarchys!=null && neuUserHierarchys.size()>0){
			for(int i=0;i<neuUserHierarchys.size();i++){
				NeuUserHierarchyBean currNeuUserHierarchyBean=neuUserHierarchys.get(i);
				if(currNeuUserHierarchyBean!=null){
					Integer userId=currNeuUserHierarchyBean.getUserId();
					//根据整合系统的userId获取在报名系统中的信息
					GEnroll gEnroll=enrollMap.getUserByGUserId(userId);
					//根据用户id获取赛事的id
					List<Integer> neuCmptIds=roleMap.getNeuCmptIdsByNeuUserId(userId,0,0);
					//根据整合系统的赛事id获取报名系统中的赛事信息
//					List<GCmptBean> gCmpts=new ArrayList<GCmptBean>();
//					if(neuCmptIds!=null && neuCmptIds.size()>0){
//						gCmpts=enrollMap.getGCmptByNeuCmptIds(neuCmptIds);
//					}
					NeuUserHierarchyAppearBean neuUserHierarchyAppearBean=new NeuUserHierarchyAppearBean();
					if(gEnroll!=null){
						neuUserHierarchyAppearBean.setPhoneNum(gEnroll.getPhoneNum());
						neuUserHierarchyAppearBean.setUserName(gEnroll.getUsername());
					}	
					neuUserHierarchyAppearBean.setAddTime(currNeuUserHierarchyBean.getAddTime());
//					neuUserHierarchyAppearBean.setCmpts(gCmpts);
					neuUserHierarchyAppearBean.setUserId(userId);
//					neuUserHierarchyAppearBean.setExecCmptCount(gCmpts.size());
					neuUserHierarchyAppearBean.setId(currNeuUserHierarchyBean.getNeuUserHierarchyId());
					neuUserHierarchyAppears.add(neuUserHierarchyAppearBean);
				}
				
			}	
		}
		return neuUserHierarchyAppears;
	}

	
	@Override
	public NeuUserHierarchyBean getLastestNeuUserHierarchy(@Param("neuUserId") Integer neuUserId){
		List<NeuUserHierarchyBean> neuUserHierarchys = enrollMap.getNeuUserHierarchys(neuUserId);
		if (!neuUserHierarchys.isEmpty()){
			return neuUserHierarchys.get(0);
		}
		return null;
	}

	
}

















