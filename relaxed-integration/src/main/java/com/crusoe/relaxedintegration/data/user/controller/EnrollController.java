package com.crusoe.relaxedintegration.data.user.controller;

import java.io.UnsupportedEncodingException;
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

import com.crusoe.relaxedintegration.data.user.bean.NeuUserHierarchyAppearBean;
import com.crusoe.relaxedintegration.data.user.service.EnrollService;
import com.crusoe.relaxedintegration.util.Const;
import com.crusoe.relaxedintegration.util.Utils;

@Controller
public class EnrollController {
	
	private static final Logger log = Logger.getLogger(EnrollController.class);
	
	@Autowired
	private EnrollService enrollService;
	
	/**
	 * 添加赛事执行人员
	 * @return
	 */
	@RequestMapping(value = "/saveNeuUserRelation")
	@ResponseBody
	public Object saveNeuUserRelation(
			@RequestParam("neuUserId") Integer neuUserId,
			@RequestParam("parentNeuUserId") Integer parentNeuUserId,
			HttpServletRequest request) {
		log.info(Utils.toParamString(request));
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Integer flag=enrollService.saveNeuUserRelation(neuUserId, parentNeuUserId);
		if(flag==1){
			result.put("status", Const.STATUS_SUCCESS);
			result.put("message", "success");
		}else{
			result.put("status", Const.STATUS_FAILURE);
			result.put("message", "fail");
		}
		
		return result;
	}
	
	/**
	 * 删除赛事执行人员
	 * @return
	 */
	@RequestMapping(value = "/deleteNeuUserRelation")
	@ResponseBody
	public Object deleteNeuUserRelation(
			@RequestParam("id") Integer id,
			HttpServletRequest request) {
		log.info(Utils.toParamString(request));
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Integer flag=enrollService.deleteNeuUserRelation(id);
		if(flag==1){
			result.put("status", Const.STATUS_SUCCESS);
			result.put("message", "success");
		}else{
			result.put("status", Const.STATUS_FAILURE);
			result.put("message", "fail");
		}
		
		return result;
	}
	
	
	/**
	 * 根据注册手机号或者赛事名称搜索人员
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/getNeuUserRelationList")
	@ResponseBody
	public Object getNeuUserRelationList(
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam("parentNeuUserId") Integer parentNeuUserId,
			HttpServletRequest request) throws UnsupportedEncodingException {
		log.info(Utils.toParamString(request));
		Map<String, Object> result = new LinkedHashMap<String, Object>();
//		String nKeyword = new String(request.getParameter("keyword").getBytes("iso-8859-1"), "utf-8");
		if(parentNeuUserId==null){
			result.put("status", Const.STATUS_FAILURE_PARAM);
			result.put("message", "组委会的id必须填写");
		}else{
			List<NeuUserHierarchyAppearBean> neuUserHierarchyAppearBean=enrollService.getNeuUserRelationList(keyword, parentNeuUserId);
		    result.put("status", Const.STATUS_SUCCESS);
		    result.put("message", "success");
		    result.put("personList", neuUserHierarchyAppearBean);
			}
		return result;
	}
	
	
}


















