package com.crusoe.relaxedintegration.data.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crusoe.relaxedintegration.data.user.bean.AccountBean;
import com.crusoe.relaxedintegration.data.user.bean.GEnroll;
import com.crusoe.relaxedintegration.data.user.bean.GUserBean;
import com.crusoe.relaxedintegration.data.user.bean.GtEnroll;
import com.crusoe.relaxedintegration.data.user.bean.GtUserBean;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserBean;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserHierarchyBean;
import com.crusoe.relaxedintegration.data.user.mapper.RoleMapper;
import com.crusoe.relaxedintegration.data.user.mapper.UserMapper;
import com.crusoe.relaxedintegration.data.user.service.EnrollService;
import com.crusoe.relaxedintegration.data.user.service.UserService;
import com.crusoe.relaxedintegration.util.Const;
import com.crusoe.relaxedintegration.util.Utils;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper neuAdminMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
//	@Autowired
//	private CmptService cmptService;
	
	@Autowired
	private EnrollService enrollService;
	
	@Override
	@Transactional
	public NeuUserBean getNeuAdmin(int userId, String unionId, String phoneNum, int role, String source, String target, Integer roleId) {
		NeuUserBean admin = null;
		List<AccountBean> accounts = new ArrayList<AccountBean>(); 
		//逐级短路得到admin
		if ((admin = neuAdminMapper.searchAdminInfo(userId, source)) != null 
				|| (admin = neuAdminMapper.searchNeuAdminByUnionId(unionId)) != null
				|| (admin = neuAdminMapper.searchNeuAdminByPhoneNum(phoneNum, role)) != null) {
			// 逐级查找找到
			int neuUserId = admin.getNeuUserId();
			neuAdminMapper.updateAdminIdInfo(neuUserId, userId, source, role);
		} else {
			// 逐级查找没找到
			if (Const.SYSTEM_INTEGRATION.equals(source)){
				//通过neuUserId查找
				return null;
			}
			//产生用户信息的相关新纪录
			NeuUserBean newAdmin = new NeuUserBean();
			if (Const.SYSTEM_GEEXEK_TIMER.equals(source)) {
				newAdmin.setGtUserId(userId);
			} else if (Const.SYSTEM_GEEXEK_ENROLL.equals(source)) {
				newAdmin.setgUserId(userId);
			} else if (Const.SYSTEM_WEIRUN.equals(source)) {
				newAdmin.setwUserId(userId);
			}
			newAdmin.setRole(role);
			// 插入neuUser表中信息
			neuAdminMapper.insertNeuAdminInfo(newAdmin);
			admin = newAdmin;
		}
		if (roleId!=null){
			//将功能权限插入neuUserRole表
			if (Const.ROLE_ID_COMMITTEE_MAN==roleId){
				//组管
				roleMapper.mergeNeuUserRoleInitFunctional(admin.getNeuUserId(), Const.SCOPE_COMMITTEE_CMPT, roleId);
			}else if (Const.ROLE_ID_SUPER_MAN==roleId){
				//超管
				roleMapper.mergeNeuUserRoleInitFunctional(admin.getNeuUserId(), Const.SCOPE_ALL_CMPT, roleId);
			}
		}
		admin.setAccount(accounts);
		int neuUserId = Const.INT_NULL;
		// 插入neuUser表中信息
		neuUserId = admin.getNeuUserId();
		if (!StringUtils.isBlank(unionId)) {
			// 如果unionId存在
			// 处理account表中信息
			if (neuUserId != Const.INT_NULL) {
				// 如果neuUserId存在，插入account表
				AccountBean account = new AccountBean();
				account.setUsername(unionId);
				account.setType(10);
				account.setNeuUserId(neuUserId);
				admin.getAccount().add(account);
				neuAdminMapper.insertAccountInfo(account);
			}
		}
		if (!StringUtils.isBlank(phoneNum)) {
			// 如果phoneNum存在
			// 处理account表中信息
			if (neuUserId != Const.INT_NULL) {
				// 如果neuUserId存在，插入account表
				AccountBean account = new AccountBean();
				account.setUsername(phoneNum);
				account.setType(20);
				account.setNeuUserId(neuUserId);
				admin.getAccount().add(account);
				neuAdminMapper.insertAccountInfo(account);
			}
		}
		return admin;
	}
	@Override
	@Transactional
	public int getUserIdFromSys(String unionId,String phoneNum,String target,int neuUsreId,int role){
		int targetUserId=Const.INT_NULL;
		if(Const.SYSTEM_GEEXEK_TIMER.equals(target)){
			GtUserBean gtUser=new GtUserBean();
			gtUser.setPhoneNum(phoneNum);
			gtUser.setUnionId(unionId);
			Integer userId=null;
			if((userId=neuAdminMapper.getgtUserId(gtUser))!=null ){
				targetUserId=userId;
			}else if(unionId!=null||phoneNum!=null){
				//保存
				neuAdminMapper.creategtUserId(gtUser);
				targetUserId=gtUser.getId();
			}
		}else if(Const.SYSTEM_GEEXEK_ENROLL.equals(target)){
			GUserBean gUser=new GUserBean();
			gUser.setCallPhone(phoneNum);
			gUser.setUnionId(unionId);
			Integer userId=null;
			if((userId=neuAdminMapper.getgUserId(gUser))!=null){
				targetUserId=userId;
			}else if(unionId!=null||phoneNum!=null){
				neuAdminMapper.creategUserId(gUser);
				targetUserId=gUser.getUserId();
			}	
		}else if(Const.SYSTEM_WEIRUN.equals(target)){
			throw new UnsupportedOperationException();
		}
		//微跑目前不用
		/*else if("w".equals(target)){
				GUserBean gUser=new GUserBean();
		  Integer aUserId=neuAdminMapper.getgUserIdByUnionId(unionId);
			if(aUserId!=null){
				targetUserId=aUserId;
			}else{
				Integer bUserId=neuAdminMapper.getgUserIdByPhoneNum(phoneNum);
				if(bUserId!=null){
					targetUserId=bUserId;
				}else if(unionId!=null){
					gUser.setUnionId(unionId);
					targetUserId=Integer.toString(neuAdminMapper.creategUserIdByUnionId(unionId));
					neuAdminMapper.creategUserId(gUser);
					targetUserId=gUser.getUserId();
				}else if(phoneNum!=null){
					gUser.setCallPhone(phoneNum);
					neuAdminMapper.creategUserId(gUser);
					targetUserId=gUser.getUserId();
					/*targetUserId=Integer.toString(neuAdminMapper.creategUserIdByPhoneNum(phoneNum));
					
				}
			}
		}*/
		if (targetUserId!=Const.INT_NULL){
			neuAdminMapper.updateAdminIdInfo(neuUsreId, targetUserId,target,role);
		}
		return targetUserId;
	}
	
	@Override
	@Transactional
	public HashMap<String,Object> getTargetUserId(int userId, String unionId, String phoneNum,int role, String source,String target, Integer roleId){
		NeuUserBean admin=getNeuAdmin(userId, unionId, phoneNum,role,source,target, roleId);
        HashMap<String,Object> map=new HashMap<String, Object>();
		if (admin==null){
			//没找到且不能创建
			map.put("targetUserId", Const.INT_NULL);
			return map;
		}
/*		NeuUserBean admin=neuAdminMapper.searchNeuUser(phoneNum, role, source, unionId, userId,target);
*/		int targetUserId=Const.INT_NULL;
        String openId=null;
		if(Const.SYSTEM_GEEXEK_TIMER.equals(target)){
			targetUserId=admin.getGtUserId();
		}else if(Const.SYSTEM_GEEXEK_ENROLL.equals(target)){
			targetUserId=admin.getgUserId();
		}else if(Const.SYSTEM_WEIRUN.equals(target)){
			targetUserId=admin.getwUserId();
		}else if(Const.SYSTEM_INTEGRATION.equals(target)){
			targetUserId=admin.getNeuUserId();
		}
		int neuUserId=admin.getNeuUserId();
		if(targetUserId==Const.INT_NULL){
			//从neuUser表中不能获取targetUserId
			targetUserId=getUserIdFromSys(unionId, phoneNum, target,neuUserId,role);
		}
		if(Const.SYSTEM_GEEXEK_ENROLL.equals(target)){
			openId=neuAdminMapper.getOpenIdByGuserId(targetUserId);
			map.put("openId", openId);
		}
		if(Const.SYSTEM_INTEGRATION.equals(target)){
			String functionalRole = roleMapper.getFunctionalRole(neuUserId);
			map.put("functionalRole", functionalRole);
			String functionalPermission = roleMapper.getFunctionalPermission(neuUserId);
			map.put("functionalPermission", functionalPermission);
			map.put("gUserId", admin.getgUserId());
			Integer committeeNeuUserId;
			if (functionalRole.contains(Const.ROLE_ID_SUPER_MAN+"")){
				committeeNeuUserId = null;
			}else if (functionalRole.contains(Const.ROLE_ID_COMMITTEE_MAN+"")){
				committeeNeuUserId = admin.getNeuUserId();
			}else{
				//协助管理员
				NeuUserHierarchyBean lastestNeuUserHierarchy = enrollService.getLastestNeuUserHierarchy(neuUserId);
				committeeNeuUserId = lastestNeuUserHierarchy==null?null:lastestNeuUserHierarchy.getParentNeuUserId();
			}
			map.put("committeeNeuUserId", committeeNeuUserId);
			if (committeeNeuUserId!=null){
				NeuUserBean neuUser = neuAdminMapper.searchAdminInfo(committeeNeuUserId, Const.SYSTEM_INTEGRATION);
				map.put("committeeGUserId", neuUser.getgUserId());
			}
		}
		
		map.put("targetUserId", targetUserId);
		return map;
		
	}
	@Override
	@Transactional
	public String getOrgIdByGtUserId(int gtUserId){
		return neuAdminMapper.getOrgIdByGtUserId(gtUserId);
	}
	
	@Override
	@Transactional
	public boolean mergeUserAccount(String phoneNum, int role, String password,String source){
		boolean flag=true;
		int userId=Const.INT_NULL;
		Integer subUserId=null;
		if(Const.SYSTEM_GEEXEK_TIMER.equals(source)){
			 subUserId=neuAdminMapper.getgtUserIdByphoneNum(phoneNum);
		}else if(Const.SYSTEM_GEEXEK_ENROLL.equals(source)){
			subUserId=neuAdminMapper.getgUserIdByphoneNum(phoneNum);	
		}else if(Const.SYSTEM_WEIRUN.equals(source)){
			throw new UnsupportedOperationException();
		}
		if(subUserId!=null){
			userId=subUserId;
		}
		NeuUserBean admin=neuAdminMapper.searchNeuAdminByPhoneNum(phoneNum,role);
		if(admin!=null){
			//更新用户系统中相关用户信息
			int neuUserId=admin.getNeuUserId();	
			neuAdminMapper.updateAdminIdInfo(neuUserId, userId, source, role);
			AccountBean account=new AccountBean();
			account.setUsername(phoneNum);
			account.setPassword(password);
			account.setNeuUserId(neuUserId);
			neuAdminMapper.updateAccountInfo(account);
		}else{
			//在用户系统中插入新的数据
			NeuUserBean neuUser=new NeuUserBean();
			neuUser.setRole(role);
			if(Const.SYSTEM_GEEXEK_TIMER.equals(source)){
				neuUser.setGtUserId(userId);
			}else if(Const.SYSTEM_GEEXEK_ENROLL.equals(source)){
				neuUser.setgUserId(userId);
			}
			neuAdminMapper.insertNeuAdminInfo(neuUser);
			AccountBean account=new AccountBean();
			account.setUsername(phoneNum);
			account.setPassword(password);
			account.setType(20);
			account.setNeuUserId(neuUser.getNeuUserId());
			neuAdminMapper.insertAccountInfo(account);
			
		}
		return flag;
		
	}
	
	
	@Override
	@Transactional
	public boolean mergeTargetSysUserInfo(String phoneNum,int role,String password,String target){
		boolean flag=true;
		NeuUserBean neuUser=neuAdminMapper.getNeuUserByPhoneAndPWD(phoneNum, password, role);
		if(neuUser!=null){
			if(Const.SYSTEM_GEEXEK_TIMER.equals(target)){
				GtUserBean gtUser=new GtUserBean();
				gtUser.setPassword(password);
				gtUser.setPhoneNum(phoneNum);
				neuAdminMapper.saveOrUpdateGtUser(gtUser);
			}else if(Const.SYSTEM_GEEXEK_ENROLL.equals(target)){
				GUserBean gUser=new GUserBean();
				gUser.setCallPhone(phoneNum);
				gUser.setPassword(password);
				neuAdminMapper.saveOrUpdateGUser(gUser);		
			}else if(Const.SYSTEM_WEIRUN.equals(target)){
				throw new UnsupportedOperationException();
			}
			
		}else{
			flag=false;
		}
		return flag;
		
	}
	@Override
	@Transactional
	public GtEnroll getGtEnrollFromG(int cmptId, int userId) {
		GtEnroll gtEnroll=null;
		GEnroll gEnroll=neuAdminMapper.getgRnrollInfo(userId);
//		NeuCmpt neuCmpt=cmptService.getCmpt(Const.SYSTEM_GEEXEK_ENROLL, cmptId);
//		int cmpt_id=cmptService.getTargetCmptId(Const.SYSTEM_GEEXEK_ENROLL, cmptId, Const.SYSTEM_GEEXEK_TIMER);
//		if(gEnroll!=null && neuCmpt!=null){
//			String username=gEnroll.getUsername();
//			String idcard=gEnroll.getIdcard();
//			String phoneNum=gEnroll.getPhoneNum();
//			gtEnroll=neuAdminMapper.getgtRnrollInfo(username, idcard, phoneNum,cmpt_id);
//			gtEnroll.setgOpenId(gEnroll.getOpenId());
//		}	
		return gtEnroll;
	}
	
	
} 
