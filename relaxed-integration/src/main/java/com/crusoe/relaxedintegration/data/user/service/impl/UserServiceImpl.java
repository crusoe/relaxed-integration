package com.crusoe.relaxedintegration.data.user.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crusoe.relaxedintegration.data.user.bean.RUser;
import com.crusoe.relaxedintegration.data.user.mapper.UserMapper;
import com.crusoe.relaxedintegration.data.user.service.UserService;
import com.crusoe.relaxedintegration.util.Utils;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public RUser getRUser(String source, String userId, String feature1, String feature2, String feature3) {
		return userMapper.getRUser(source, userId, Utils.toQuotedStr(feature1), Utils.toQuotedStr(feature2), 
				Utils.toQuotedStr(feature3));
	}
	
	
	
} 
