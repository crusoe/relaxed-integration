package com.crusoe.relaxedintegration.data.user.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.crusoe.relaxedintegration.data.user.bean.GCmptBean;
import com.crusoe.relaxedintegration.data.user.bean.GEnroll;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserRoleAppearBean;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserRoleBean;
import com.crusoe.relaxedintegration.data.user.mapper.EnrollMapper;
import com.crusoe.relaxedintegration.data.user.mapper.RoleMapper;
import com.crusoe.relaxedintegration.data.user.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService{
	
	private static final Logger log = Logger.getLogger(RoleServiceImpl.class);

	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private EnrollMapper enrollMap;

	@Override
	public String getFunctionalPermission(Integer neuUserId){
		return roleMapper.getFunctionalPermission(neuUserId);
	}
	/*@Override
	@Transactional
	public List<NeuUserRoleAppearBean> getNeuUserRoleList(Integer neuUserId,
			Integer cmptId, Integer userId, Integer role) {
		
		List<NeuUserRoleAppearBean> neuUserRoleAppears=new ArrayList<NeuUserRoleAppearBean>();
		List<NeuUserRoleBean> currNeuUserRoles=new ArrayList<NeuUserRoleBean>();
		List<Integer> userIds=new ArrayList<Integer>();
		// 获取组委会名下的所有用户关系信息
		List<NeuUserHierarchyBean> neuUserHierarchys=enrollMap.getNeuUserRelationList(neuUserId);
		//根据委托人的id、角色、赛事的id查询角色权限信息
		List<NeuUserRoleBean> neuUserRoles=roleMapper.getUserRoleList(userId, cmptId, role);
		if(neuUserHierarchys!=null){
			for(int i=0; i<neuUserHierarchys.size();i++){
				NeuUserHierarchyBean neuUserHierarchyBean=neuUserHierarchys.get(i);
				for(int j=0;j<neuUserRoles.size();j++){
					NeuUserRoleBean neuUserRole=neuUserRoles.get(j);
					Integer nowUserId=neuUserRole.getUserId();
					if(neuUserRole!=null && nowUserId.equals(neuUserHierarchyBean.getUserId())){
						if(userIds.contains(nowUserId)){
							continue;
						}
						userIds.add(neuUserRole.getUserId());
						currNeuUserRoles.add(neuUserRole);
					}
				}	
			}
		}
		if(currNeuUserRoles!=null){
			for(int i=0;i<currNeuUserRoles.size();i++){
				NeuUserRoleBean currNeuUserRole=currNeuUserRoles.get(i);
				Integer currUserId=currNeuUserRole.getUserId();
				//根据整合系统的userId获取在报名系统中的信息
				GEnroll gEnroll=enrollMap.getUserByGUserId(currUserId);
				//根据用户id获取赛事的id
				List<Integer> neuCmptIds=roleMapper.getNeuCmptIdsByNeuUserId(currUserId,cmptId,role);
				List<NeuUserRoleBean> neuUserRoleBeans=roleMapper.getNeuCmptsByNeuUserId(currUserId);
				for(int j=0;j<neuUserRoleBeans.size();j++){
					NeuUserRoleBean currNeuUserRoleBean=neuUserRoleBeans.get(j);
					int currNeuCmptId=currNeuUserRoleBean.getCmptId();
					GCmptBean gCmpt=enrollMap.getGCmptByNeuCmptId(currNeuCmptId);
					List<Integer> roles=roleMapper.getRoleIds(currUserId, currNeuCmptId);
					System.out.println("roles"+roles+"*****************cmptId"+currNeuCmptId+"userId******"+currUserId);
					NeuUserRoleAppearBean neuUserRoleAppearBean=new NeuUserRoleAppearBean();
					neuUserRoleAppearBean.setCmptName(gCmpt.getCmptName());
					neuUserRoleAppearBean.setDeadline(gCmpt.getDeadline());
					neuUserRoleAppearBean.setPhoneNum(gEnroll.getPhoneNum());
					neuUserRoleAppearBean.setUserName(gEnroll.getUsername());
					neuUserRoleAppearBean.setRoles(roles);
					neuUserRoleAppearBean.setCmptId(gCmpt.getCmptId());
					neuUserRoleAppearBean.setUserId(gEnroll.getUserId());
					neuUserRoleAppearBean.setId(currNeuUserRole.getId());
					neuUserRoleAppearBean.setCmptId(currNeuCmptId);
					neuUserRoleAppearBean.setUserId(currUserId);
					neuUserRoleAppearBean.setId(currNeuUserRoleBean.getId());
					neuUserRoleAppears.add(neuUserRoleAppearBean);
				}
				//根据整合系统的赛事id获取报名系统中的赛事信息
				for(int j=0;j<neuCmptIds.size();j++){
					int currNeuCmptId=neuCmptIds.get(j);
					GCmptBean gCmpt=new GCmptBean();
					if(currNeuCmptId==-3){
						Date deadline=roleMapper.getDeadLineNeuCmptId(currNeuCmptId);
						gCmpt.setDeadline(deadline);
						gCmpt.setCmptName("人隶属组委会下的所有赛事");	
					}else if(currNeuCmptId==-4){
						Date deadline=roleMapper.getDeadLineNeuCmptId(currNeuCmptId);
						gCmpt.setDeadline(deadline);
						gCmpt.setCmptName("个人名下的所有赛事");
					}else{
					    gCmpt=enrollMap.getGCmptByNeuCmptId(currNeuCmptId);
					}
					
					List<Integer> roles=roleMapper.getRoleIds(currUserId, currNeuCmptId);
					List<Integer> ids=roleMapper.getIds(currUserId, currNeuCmptId);
//					System.out.println("roles"+roles+"*****************cmptId"+currNeuCmptId+"userId******"+currUserId);
					//过滤掉赛事为-2的赛事
					if(currNeuCmptId!=-2){
						NeuUserRoleAppearBean neuUserRoleAppearBean=new NeuUserRoleAppearBean();
						neuUserRoleAppearBean.setCmptName(gCmpt.getCmptName());
						neuUserRoleAppearBean.setDeadline(gCmpt.getDeadline());
						neuUserRoleAppearBean.setPhoneNum(gEnroll.getPhoneNum());
						neuUserRoleAppearBean.setUserName(gEnroll.getUsername());
						neuUserRoleAppearBean.setRoles(roles);
						neuUserRoleAppearBean.setCmptId(currNeuCmptId);
						neuUserRoleAppearBean.setUserId(currUserId);
						if(ids.size()>0){
							neuUserRoleAppearBean.setId(ids.get(0));
						}	
						neuUserRoleAppears.add(neuUserRoleAppearBean);	
					}
					
				}
				
			}	
		}
		  Comparator<NeuUserRoleAppearBean> comparator = new Comparator<NeuUserRoleAppearBean>(){  
			     public int compare(NeuUserRoleAppearBean s1, NeuUserRoleAppearBean s2) {  
			      //先根据赛事的名称
			      if(!s1.getCmptName().equals(s2.getCmptName())){  
			    	  return s2.getCmptName().compareTo(s1.getCmptName()); 
			      }  
			      else{ //再根据用户的名字 
			       if(!s1.getUserName().equals(s2.getUserName())){  
			        return s2.getUserName().compareTo(s1.getUserName());  
			       }  
			       else{  //最后排截止日期
			        return s2.getCmptName().compareTo(s1.getCmptName());
			        return s2.getDeadline().compareTo(s1.getDeadline());
			       }  
			    }  
			  }  
	       };  
			    
	    Collections.sort(neuUserRoleAppears, comparator);
		return neuUserRoleAppears;
	}*/
	@Override
	@Transactional
	public List<NeuUserRoleAppearBean> getNeuUserRoleList(Integer neuUserId,
			Integer cmptId, Integer userId, Integer role) {
		List<NeuUserRoleAppearBean> neuUserRoleAppears=new ArrayList<NeuUserRoleAppearBean>();
		List<NeuUserRoleBean> currNeuUserRoles=new ArrayList<NeuUserRoleBean>();
		List<Integer> userIds=new ArrayList<Integer>();
		List<NeuUserRoleBean> neuUserRoles=roleMapper.getUserRoleLists(neuUserId,userId, cmptId, role);
		for(int j=0;j<neuUserRoles.size();j++){
			NeuUserRoleBean neuUserRole=neuUserRoles.get(j);
			Integer nowUserId=neuUserRole.getUserId();
			if(userIds.contains(nowUserId)){
					continue;
				}
				userIds.add(neuUserRole.getUserId());
				currNeuUserRoles.add(neuUserRole);
			
		}
		if(currNeuUserRoles!=null){
			for(int i=0;i<currNeuUserRoles.size();i++){
				NeuUserRoleBean currNeuUserRole=currNeuUserRoles.get(i);
				Integer currUserId=currNeuUserRole.getUserId();
				//根据整合系统的userId获取在报名系统中的信息
				GEnroll gEnroll=enrollMap.getUserByGUserId(currUserId);
				//根据用户id获取赛事的id
			    List<Integer> neuCmptIds=roleMapper.getNeuCmptIds(neuUserId, currUserId, cmptId, role);
				//List<Integer> neuCmptIds=roleMapper.getNeuCmptIdsByNeuUserId(currUserId,cmptId,role);
			/*	List<NeuUserRoleBean> neuUserRoleBeans=roleMapper.getNeuCmptsByNeuUserId(currUserId);
				for(int j=0;j<neuUserRoleBeans.size();j++){
					NeuUserRoleBean currNeuUserRoleBean=neuUserRoleBeans.get(j);
					int currNeuCmptId=currNeuUserRoleBean.getCmptId();
					GCmptBean gCmpt=enrollMap.getGCmptByNeuCmptId(currNeuCmptId);
					List<Integer> roles=roleMapper.getRoleIds(currUserId, currNeuCmptId);
					NeuUserRoleAppearBean neuUserRoleAppearBean=new NeuUserRoleAppearBean();
					neuUserRoleAppearBean.setCmptName(gCmpt.getCmptName());
					neuUserRoleAppearBean.setDeadline(gCmpt.getDeadline());
					neuUserRoleAppearBean.setPhoneNum(gEnroll.getPhoneNum());
					neuUserRoleAppearBean.setUserName(gEnroll.getUsername());
					neuUserRoleAppearBean.setRoles(roles);
					neuUserRoleAppearBean.setCmptId(gCmpt.getCmptId());
					neuUserRoleAppearBean.setUserId(gEnroll.getUserId());
					neuUserRoleAppearBean.setId(currNeuUserRole.getId());
					neuUserRoleAppearBean.setCmptId(currNeuCmptId);
					neuUserRoleAppearBean.setUserId(currUserId);
					neuUserRoleAppearBean.setId(currNeuUserRoleBean.getId());
					neuUserRoleAppears.add(neuUserRoleAppearBean);
				}*/
				//根据整合系统的赛事id获取报名系统中的赛事信息
			    for(int j=0;j<neuCmptIds.size();j++){
			    	//int currNeuCmptId=currNeuUserRole.getCmptId();
			    	int currNeuCmptId=neuCmptIds.get(j);
//					GCmptBean gCmpt=new GCmptBean();
//					if(currNeuCmptId==-3){
//						Date deadline=roleMapper.getDeadLineNeuCmptId(currNeuCmptId);
//						gCmpt.setDeadline(deadline);
//						gCmpt.setCmptName("人隶属组委会下的所有赛事");	
//					}else if(currNeuCmptId==-4){
//						Date deadline=roleMapper.getDeadLineNeuCmptId(currNeuCmptId);
//						gCmpt.setDeadline(deadline);
//						gCmpt.setCmptName("个人名下的所有赛事");
//					}else{
//					    gCmpt=enrollMap.getGCmptByNeuCmptId(currNeuCmptId);
//					}
					
					List<Integer> roles=roleMapper.getRoleIds(currUserId, currNeuCmptId);
					List<Integer> ids=roleMapper.getIds(currUserId, currNeuCmptId);
//					System.out.println("roles"+roles+"*****************cmptId"+currNeuCmptId+"userId******"+currUserId);
					//过滤掉赛事为-2的赛事
					if(currNeuCmptId!=-2){
						NeuUserRoleAppearBean neuUserRoleAppearBean=new NeuUserRoleAppearBean();
//						neuUserRoleAppearBean.setCmptName(gCmpt.getCmptName());
//						neuUserRoleAppearBean.setDeadline(gCmpt.getDeadline());
						neuUserRoleAppearBean.setPhoneNum(gEnroll.getPhoneNum());
						neuUserRoleAppearBean.setUserName(gEnroll.getUsername());
						neuUserRoleAppearBean.setRoles(roles);
						neuUserRoleAppearBean.setCmptId(currNeuCmptId);
						neuUserRoleAppearBean.setUserId(currUserId);
						if(ids.size()>0){
							neuUserRoleAppearBean.setId(ids.get(0));
						}	
						neuUserRoleAppears.add(neuUserRoleAppearBean);	
					}
			    }
					
			}
		}
		Comparator<NeuUserRoleAppearBean> comparator = new Comparator<NeuUserRoleAppearBean>(){  
		     public int compare(NeuUserRoleAppearBean s1, NeuUserRoleAppearBean s2) {
		    	 String cmptName1=s1.getCmptName();
		    	 String cmptName2=s2.getCmptName();
		      //先根据赛事的名称
		      if(cmptName1!=null && cmptName2!=null && !cmptName1.equals(cmptName2)){  
		    	  return cmptName2.compareTo(cmptName1); 
		      }  
		      else{ //再根据用户的名字 
		    	  String userName1=s1.getUserName();
			      String userName2=s2.getUserName();
		         if(userName1!=null && userName2!=null && !userName1.equals(userName2)){  
		              return userName2.compareTo(userName1);  
		          }  
		           else{  //最后排截止日期
		    	        Date date1=s1.getDeadline();
		    	        Date date2=s2.getDeadline();
		    	        if(date1!=null && date2!=null && !date1.equals(date2)){  
				             return date2.compareTo(date1);  
				           }else{
				    	         return 1;
				                } 
		               }  
		         }  
		  }  
      };  
		    
   Collections.sort(neuUserRoleAppears, comparator);
		return neuUserRoleAppears;		
	}
	
	
	
	
	@Override
	@Transactional
	public Integer deleteNeuUserRole(Integer id) {
		Integer flag=0;
		NeuUserRoleBean neuRole=roleMapper.getNeuUserRoleById(id);
		if(neuRole!=null){
			Integer neuUserId=neuRole.getUserId();
			Integer neuCmptId=neuRole.getCmptId();
			List<Integer> ids=roleMapper.getIds(neuUserId, neuCmptId);
			if(ids!=null && ids.size()>0){
				flag=roleMapper.deleteNeuUserRoleByIds(ids);
			}
		}
		return flag;
		
		
	}
	@Override
	/*@Transactional*/
	public Integer mergeNeuUserRole(Integer commitNeuUserId,Integer neuUserId, Integer neuCmptId,Integer prevUserId, Integer prevCmptId,
			List<Integer> roleIds, Date deadline) {
		Integer flag=0;
		if(prevUserId!=null && prevCmptId!=null){
			roleMapper.deleteNeuUserRoleByUserIdAndCmptId(prevUserId, prevCmptId);
		}
		roleMapper.deleteNeuUserRoleByUserIdAndCmptId(neuUserId, neuCmptId);
		for(int i=0;i<roleIds.size();i++){
			flag=roleMapper.mergeNeuUserRole(commitNeuUserId,neuUserId, neuCmptId, roleIds.get(i), deadline);
		}
		return flag;
	}
	@Override
	public List<NeuUserRoleBean> getUserRoleList(Integer neuUserId, Integer cmptId, Integer role){
		return 	roleMapper.getUserRoleList(neuUserId, cmptId, role);
	}
	
	@Override
	public String getPermission(Integer neuUserId, Integer neuCmptId){
		return roleMapper.getPermission(neuUserId, neuCmptId);
	} 

	@Override
	public List<NeuUserRoleBean> getNeuUserRoles(Integer userId, Integer cmptId, Integer role){
		return roleMapper.getUserRoleList(userId, cmptId, role);
	}
	
}

















