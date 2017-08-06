package com.crusoe.relaxedintegration.util;

public class Const {
	
	/**表示整形值的null意义*/
	public static final int INT_NULL = -1;
	/**接口状态成功*/
	public static final int STATUS_SUCCESS = 1;
	/**接口状态失败*/
	public static final int STATUS_FAILURE = 0;
	/**接口状态参数错*/
	public static final int STATUS_FAILURE_PARAM = -1;
	/**整合系统自己*/
	public static final String SYSTEM_INTEGRATION = "r";
	/**赛客报名*/
	public static final String SYSTEM_GEEXEK_ENROLL = "g";
	/**赛客计时*/
	public static final String SYSTEM_GEEXEK_TIMER = "gt";
	/**微跑APP*/
	public static final String SYSTEM_WEIRUN = "w";
	/**赛客计时的db名*/
	public static final String GEEXEK_TIMER_DB = SystemConfig.getProperty("geexek.timer.db");
	/**赛客报名的db名*/
	public static final String GEEXEK_ENROLL_DB = SystemConfig.getProperty("geexek.enroll.db");
	/**角色跑者*/
	public static final int ROLE_RUNNER = 1;
	/**角色组委会*/
	public static final int ROLE_COMMITTEE = 2;
	/**类成员变量int类型的未初始化的默认值*/
	public static final int InitValue = -1;
	/**接口安全是否打开的status*/
	public static final String open = "is";
	/**赛客的组织id*/
	public static final String GEEXEK_ORG_ID = "1-3";

	/**超级管理员*/
	public static final int ROLE_ID_SUPER_MAN = 1;
	/**组委会管理员*/
	public static final int ROLE_ID_COMMITTEE_MAN = 2;
	/**报名管理员*/
	public static final int ROLE_ID_ENROLL_MAN = 3;
	/**领包计时员*/
	public static final int ROLE_ID_TIMER_MAN = 4;
	/**报名款管理员*/
	public static final int ROLE_ID_CHARGE_MAN = 5;
	
	/**所有赛事*/
	public static final int SCOPE_ALL_CMPT = -2;
	/**组委会下所有赛事*/
	public static final int SCOPE_COMMITTEE_CMPT = -3;
	/**个人下所有赛事*/
	public static final int SCOPE_USER_CMPT = -4;

	/**显示赛事管理权限*/
	public static final String PERMISSION_CMPT_MANAGEMENT = "cmptManagement";
	/**显示报名款管理权限*/
	public static final String PERMISSION_CHARGE_MANAGEMENT = "chargeManagement";

	/**无期限*/
	public static final String NO_LIMIT_DATE = "2099-12-31 23:59:59";

}































