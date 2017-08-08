package com.crusoe.relaxedintegration.data.user.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crusoe.relaxedintegration.data.user.bean.RUser;
import com.crusoe.relaxedintegration.data.user.service.UserService;
import com.crusoe.relaxedintegration.util.Const;
import com.crusoe.relaxedintegration.util.Utils;

@Controller
public class UserController {
	
	private static final Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 根据userId,source,unionId,phoneNum,role,target获取目标系统的userId
	 * @return
	 */
	@RequestMapping(value = "/getTargetUserId")
	@ResponseBody
	public Object getTargetUserId(
			@RequestParam(value="source", required=false, defaultValue=Const.SYSTEM_INTEGRATION) String source,
			@RequestParam("userId") String userId,
			@RequestParam(value="target", required=false, defaultValue=Const.SYSTEM_INTEGRATION) String target, 
			@RequestParam(value="feature1", required=false) String feature1, 
			@RequestParam(value="feature2", required=false) String feature2, 
			@RequestParam(value="feature3", required=false) String feature3, 
			HttpServletRequest request, HttpServletRequest response) throws Exception{
		log.info(Utils.toParamString(request));
//		System.out.println("feature2:"+feature2);
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		RUser rUser = userService.getRUser(source, userId, feature1, feature2, feature3);
		result.put("status", Const.STATUS_SUCCESS);
		result.put("target", target);
		result.put("targetUserId", rUser.getTargetUserId(target));
		return result;
//		Map<String, Object> result = new LinkedHashMap<String, Object>();
//		try {
//			int targetUserId=Const.INT_NULL;
////			if(Const.SYSTEM_GEEXEK_TIMER.equals(target)&&role!=Const.ROLE_COMMITTEE){
////				result.put("status", Const.STATUS_FAILURE);
////				result.put("message","无权限");
////			}else{
//			Map<String, Object> map = userService.getTargetUserId(userId, unionId, phoneNum, role, source, target, roleId);
//			targetUserId = (int) map.get("targetUserId");
//			String openId = (String) map.get("openId"); 
//			if (targetUserId == Const.INT_NULL) {
//				result.put("status", Const.STATUS_FAILURE);
//				result.put("message", "没找到且不能创建");
//			} else {
//				result.put("status", Const.STATUS_SUCCESS);
//				result.put("target", target);
//				result.put("targetUserId", targetUserId);
//				if (Const.SYSTEM_GEEXEK_ENROLL.equals(target)||Const.SYSTEM_GEEXEK_TIMER.equals(target)){
//					result.put("openId", openId);
//				}
//				if (Const.SYSTEM_INTEGRATION.equals(target)){
//					List<NeuUserRoleBean> userRoleList = roleService.getUserRoleList(targetUserId, null, null);
//					List<NeuUserRoleBean> neuUserRoles = new ArrayList<>(); 
//					boolean hasDataPermission = false;//有报名，计时数据权限
//					boolean hasChargeDataPermission = false;//有报名款数据权限
//					for (NeuUserRoleBean neuUserRole:userRoleList){
//						if (neuUserRole==null||new Date().before(neuUserRole.getDeadline())){
//							//没到过期时间
//							if (neuUserRole.getRole()==Const.ROLE_ID_CHARGE_MAN){
//								hasChargeDataPermission = true;
//							}else if (neuUserRole.getRole()==Const.ROLE_ID_ENROLL_MAN||neuUserRole.getRole()==Const.ROLE_ID_TIMER_MAN){
//								hasDataPermission = true;
//							}
//							neuUserRoles.add(neuUserRole);
//						}
//					}
//					result.put("functionalRole", map.get("functionalRole"));
//					String functionalPermission = (String) map.get("functionalPermission");
//					if (hasDataPermission){
//						//在有报名，计时数据权限时，加入一级菜单赛事管理权限
//						functionalPermission+=","+Const.PERMISSION_CMPT_MANAGEMENT;
//					}
//					if (hasChargeDataPermission){
//						//在有报名款数据权限时，加入一级菜单报名款管理权限
//						functionalPermission+=","+Const.PERMISSION_CHARGE_MANAGEMENT;
//					}
//					result.put("functionalPermission", functionalPermission);
//					result.put("gUserId", map.get("gUserId"));	
//					result.put("committeeNeuUserId", map.get("committeeNeuUserId"));	
//					result.put("committeeGUserId", map.get("committeeGUserId"));	
//					result.put("neuUserRoles", neuUserRoles);
//				}
//			}
////			}
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			result.put("status", Const.STATUS_FAILURE);
//			result.put("message", e.getMessage());
//		}
	}
	
	/**
	 * 注册账号和更新账号密码
	 * @return
	 */
	@RequestMapping(value = "/syncPhoneNumAccount")
	@ResponseBody
	public Object syncPhoneNumAccount(@RequestParam("phoneNum") String  phoneNum,
			@RequestParam("password") String password,@RequestParam("source") String source, HttpServletRequest request) {
//		log.info(Utils.toParamString(request));
//		Map<String, Object> result = new HashMap<String, Object>();
//		if( phoneNum == null  || password==null ||source ==null){
//			result.put("status", Const.STATUS_FAILURE_PARAM);
//			result.put("message", "参数有错误");
//			return result;
//		}
//		try {
//			  int role=(request.getParameter("role")!=null)?Integer.parseInt(request.getParameter("role")):Const.ROLE_COMMITTEE;
//			    userService.mergeUserAccount(phoneNum, role, password, source);
//				result.put("status", Const.STATUS_SUCCESS);
//				result.put("message","注册更新用户账号成功");	
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			result.put("status", Const.STATUS_FAILURE);
//			result.put("message", e.getMessage());
//		}
//		return result;
		throw new UnsupportedOperationException();
	}
	/**
	 * 登录时同步用户系统中的用户数据
	 * @return
	 */
	@RequestMapping(value = "/createPhoneNumAccount")
	@ResponseBody
	public Object createPhoneNumAccount(@RequestParam("phoneNum") String  phoneNum,
			@RequestParam("password") String password,@RequestParam("target") String target, HttpServletRequest request) {
//		log.info(Utils.toParamString(request));
//		Map<String, Object> result = new HashMap<String, Object>();
//		if( phoneNum == null  || password==null ||target ==null){
//			result.put("status", Const.STATUS_FAILURE_PARAM);
//			result.put("message", "参数有错误");
//			return result;
//		}
//		try {
//			int role=(request.getParameter("role")!=null)?Integer.parseInt(request.getParameter("role")):Const.ROLE_COMMITTEE;
//			boolean flag=userService.mergeTargetSysUserInfo(phoneNum, role, password, target);
//			if(flag==true){
//				result.put("status", Const.STATUS_SUCCESS);
//				result.put("message","同步用户密码成功，可以登录");
//			}else{
//				result.put("status", Const.STATUS_FAILURE);
//				result.put("message", "用户系统中不存在该用户，同步用户密码失败");
//			}
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			result.put("status", Const.STATUS_FAILURE);
//			result.put("message", e.getMessage());
//		}
//		return result;
		throw new UnsupportedOperationException();
	}
	
}


















