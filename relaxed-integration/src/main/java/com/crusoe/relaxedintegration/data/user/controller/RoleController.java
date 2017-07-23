package com.crusoe.relaxedintegration.data.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crusoe.relaxedintegration.data.user.bean.NeuUserRoleAppearBean;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserRoleBean;
import com.crusoe.relaxedintegration.data.user.service.RoleService;
import com.crusoe.relaxedintegration.util.Const;
import com.crusoe.relaxedintegration.util.Utils;

@Controller
public class RoleController {
	
	private static final Logger log = Logger.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 添加或修改用户角色
	 * @return
	 */
	@RequestMapping(value = "/mergeNeuUserRole")
	@ResponseBody
	public Object mergeNeuUserRole(
			@RequestParam("commitNeuUserId") Integer commitNeuUserId,
			@RequestParam(value="id", required=false) Integer id,
			@RequestParam("cmptId") Integer cmptId,
			@RequestParam("neuUserId") Integer neuUserId, 
			@RequestParam("roles") String roles,
			@RequestParam(value="prevCmptId", required=false) Integer prevCmptId,
			@RequestParam(value="prevUserId", required=false) Integer prevUserId, 
			@RequestParam(value="deadline", required=false) Date deadline, 
			HttpServletRequest request) {
		log.info(Utils.toParamString(request));
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		List<Integer> roleIds=new ArrayList<Integer>();
		if(cmptId==null||neuUserId==null||roles==null){
			return null;
		}
		if(prevCmptId==null && prevUserId!=null || prevCmptId!=null && prevUserId==null){
			result.put("status", Const.STATUS_FAILURE);
			result.put("message", "修改前的赛事id和用户id必须同时传或者不传");
			return result;
		}
		String[] temRoles=roles.split(",");
		for(int i=0;i<temRoles.length;i++){
			roleIds.add(Integer.parseInt(temRoles[i]));
		}
		Integer flag=roleService.mergeNeuUserRole(commitNeuUserId,neuUserId, cmptId,prevUserId,prevCmptId,roleIds, deadline);
		if(flag!=0){
			result.put("status", Const.STATUS_SUCCESS);
			result.put("message", "success");
		}else{
			result.put("status", Const.STATUS_FAILURE);
			result.put("message", "fail");
		}
		
		return result;
	}
	
	/**
	 * 获取授权人员名单列表
	 * @return
	 */
	@RequestMapping(value = "/getNeuUserRoleList")
	@ResponseBody
	public Object getNeuUserRoleList(
			@RequestParam("neuUserId") Integer neuUserId,
			@RequestParam(value="cmptId", required=false) Integer cmptId,
			@RequestParam(value="userId", required=false) Integer userId, 
			@RequestParam(value="role", required=false) Integer role,
			HttpServletRequest request) {
		log.info(Utils.toParamString(request));
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		if(neuUserId==null){
			result.put("status", Const.STATUS_FAILURE_PARAM);
			result.put("message", "组委会的id必须填写");
		}else{
			List<NeuUserRoleAppearBean> neuUserRoleAppearBean=roleService.getNeuUserRoleList(neuUserId, cmptId, userId, role);
		    result.put("status", Const.STATUS_SUCCESS);
		    result.put("message", "success");
		    result.put("personRoleList", neuUserRoleAppearBean);
			}
		
		return result;
	}
	/**
	 * 删除角色人员
	 * @return
	 */
	@RequestMapping(value = "/deleteNeuUserRole")
	@ResponseBody
	public Object deleteNeuUserRole(
			@RequestParam("id") Integer id,
			HttpServletRequest request) {
		log.info(Utils.toParamString(request));
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Integer flag=roleService.deleteNeuUserRole(id);
		if(flag==0){
			result.put("status", Const.STATUS_FAILURE);
			result.put("message", "fail");
			
		}else{
			result.put("status", Const.STATUS_SUCCESS);
			result.put("message", "success");
		}
		return result;
	}
	
	@RequestMapping(value = "/getNeuUserRoles")
	@ResponseBody
	public Object getNeuUserRoles(
			@RequestParam(value="cmptId", required=false) Integer cmptId,
			@RequestParam(value="userId", required=false) Integer userId, 
			@RequestParam(value="role", required=false) Integer role,
			HttpServletRequest request) {
		log.info(Utils.toParamString(request));
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		List<NeuUserRoleBean> neuUserRoles=roleService.getNeuUserRoles(cmptId, userId, role);
		result.put("status", Const.STATUS_SUCCESS);
		result.put("neuUserRoles", neuUserRoles);
		
		return result;
	}

}


















