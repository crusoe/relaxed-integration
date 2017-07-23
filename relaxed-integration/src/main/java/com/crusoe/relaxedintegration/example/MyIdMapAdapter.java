package com.crusoe.relaxedintegration.example;

import org.springframework.stereotype.Component;

import com.crusoe.relaxedintegration.data.IdMapAdapter;
import com.crusoe.relaxedintegration.data.user.UserId;

@UserId
@Component
public class MyIdMapAdapter implements IdMapAdapter {

	@Override
	public Object getIdByFeature(String subSys, Object... features) {
		// 实现依次根据特征值1，特征值2，特征值3...获取子系统中对应的id
		//dev提交
		return 123;
	}

	@Override
	public Object saveIdByFeature(String subSys, Object... features) {
		// 实现如果找不到子系统中对应的id的情况下，用features去子系统创建这条记录，并获取id 
		return 456;
	}

}
