package com.crusoe.relaxedintegration.data.user.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crusoe.relaxedintegration.data.user.bean.RUser;

@Repository
public interface UserMapper {

	RUser getRUser(@Param("source") String source, @Param("userId") String userId, @Param("feature1") String feature1, 
			@Param("feature2") String feature2, @Param("feature3") String feature3);

	
	
	
	
}







