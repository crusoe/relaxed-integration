package com.crusoe.relaxedintegration.util.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageUtils {
	private static final int DEFAULTSIZE = 10;
	/**
	 * 初始化页面大小
	 * 
	 * @param pageSize
	 * @return
	 */
	public static Integer initPageSize(Integer pageSize) {
		if (null == pageSize || pageSize < 1) {
			return DEFAULTSIZE;
		}
		return pageSize;
	}

	public static Integer initPageNo(Integer pageNo) {
		if (null == pageNo) {
			return 0;
		}
		if (pageNo < 0) {
			return 0;
		}
		return pageNo;
	}

	public static Pageable initPage(Integer pageNo, Integer pageSize) {
		Pageable pageable = new PageRequest(initPageNo(pageNo), initPageSize(pageSize));
		return pageable;
	}
}
