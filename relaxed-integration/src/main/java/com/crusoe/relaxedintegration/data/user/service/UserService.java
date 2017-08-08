package com.crusoe.relaxedintegration.data.user.service;

import java.util.HashMap;

import com.crusoe.relaxedintegration.data.user.bean.GtEnroll;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserBean;
import com.crusoe.relaxedintegration.data.user.bean.RUser;

public interface UserService {

	RUser getRUser(String source, String userId, String feature1, String feature2, String feature3);

}
