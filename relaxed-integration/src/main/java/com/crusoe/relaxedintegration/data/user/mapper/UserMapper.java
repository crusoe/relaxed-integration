package com.crusoe.relaxedintegration.data.user.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crusoe.relaxedintegration.data.user.bean.AccountBean;
import com.crusoe.relaxedintegration.data.user.bean.GEnroll;
import com.crusoe.relaxedintegration.data.user.bean.GUserBean;
import com.crusoe.relaxedintegration.data.user.bean.GtEnroll;
import com.crusoe.relaxedintegration.data.user.bean.GtUserBean;
import com.crusoe.relaxedintegration.data.user.bean.NeuUserBean;
import com.crusoe.relaxedintegration.data.user.bean.RUser;

@Repository
public interface UserMapper {

	RUser getRUser(String source, String userId, String feature1, String feature2, String feature3);

	
	
	
	//old code below
	/**
	 * 更新用户信息
	 * @param admin
	 * @return
	 */
	public int updateNeuAdminInfo(NeuUserBean admin); 
	/**
	 * 插入用户信息
	 * @param admin
	 * @return
	 */
	public Integer insertNeuAdminInfo(NeuUserBean admin);
	/**
	 * 根据unionId查找用户信息
	 * @param unionId
	 * @return
	 */
	public NeuUserBean searchNeuAdminByUnionId(String unionId);
	/**
	 * 根据phoneNum查找用户信息
	 * @param phoneNum
	 * @return
	 */
	public NeuUserBean searchNeuAdminByPhoneNum(@Param("phoneNum") String phoneNum,@Param("role") int role);
	/**
	 * 根据gUser在报名系统创建用户并返回该用户id
	 * @param gUser
	 * @return
	 */
	public Integer getgUserId(GUserBean gUser);
	/**
	 * 根据GUser在报名系统创建用户并返回该用户id
	 * @param gUser
	 * @return
	 */
	public int creategUserId(GUserBean gUser);

	/**
	 * 根据gtUser获取报名系统的用户id
	 * @param gtUser
	 * @return
	 */
	public Integer getgtUserId(GtUserBean gtUser);
	/**
	 * 根据GtUserBean在报名系统创建用户并返回该用户id
	 * @param GtUserBean
	 * @return
	 */
	public int creategtUserId(GtUserBean gtUser);

	/**
	 * 根据子用户的id查找用户
	 * @param admin
	 * @return
	 */
	public NeuUserBean searchAdminInfo(@Param("userId")int userId,@Param("mark")String mark);
	/**
	 * 更新用户系统的neuUserId和userId、source
	 * @param admin
	 * @return
	 */
	public int updateAdminIdInfo(@Param("neuUserId") int neuUserId,@Param("userId") int userId,@Param("source") String source,@Param("role") int role);
	/**
	 * 根据neuUserId查找用户信息 
	 * @param unionId
	 * @return
	 */
	public NeuUserBean searchNeuAdminByNeuUserId(int neuUserId);
	/**
	 * 如果不存在则插入用户系统账号相关数据，按type+username为逻辑主键
	 * @param admin
	 * @return
	 */
	public int insertAccountInfo(AccountBean account);
	/**
	 * 更新用户系统账号相关数据
	 * @param admin
	 * @return
	 */
	public int updateAccountInfo(AccountBean account);
	/**
	 * 用gtUserId获取orgId
	 * @param gtUserId 赛客计时的userId
	 * @return
	 */
	public String getOrgIdByGtUserId(int gtUserId);
	
	//登录部分的相关
	
	//目前在service层实现
/*	public NeuUserBean saveOrUpdateNeuUser(NeuUserBean neuUser);
	public NeuUserBean saveOrUpdateAccount(AccountBean account);*/
	public int saveOrUpdateGtUser(GtUserBean gtUser);
	public int saveOrUpdateGUser(GUserBean gUser);
	
	public NeuUserBean getNeuUserByPhoneAndPWD(@Param("phoneNum") String phoneNum,@Param("password") String password,@Param("role") int role);

	
	//从赛客报名gUserId(用户id)获取赛客计时中对应的gtEnrollId(名单id)
	/**
	 * 用gtUserId获取orgId
	 * @param gtUserId 赛客计时的userId
	 * @return
	 */
	public GEnroll getgRnrollInfo(@Param("userId") int userId);
	/**
	 * 用gtUserId获取orgId
	 * @param gtUserId 赛客计时的userId
	 * @return
	 */
	public GtEnroll getgtRnrollInfo(@Param("username") String username,@Param("idcard") String idcard,@Param("phoneNum") String phoneNum,@Param("cmpt_id") int cmpt_id);
	
	
	/**
	 * 用phoneNum获取报名系统的userId
	 * @param gtUserId 赛客计时的userId
	 * @return
	 */
	public Integer getgUserIdByphoneNum(@Param("phoneNum") String phoneNum);
	/**
	 * phoneNum获取计时系统的userId
	 * @param gtUserId 赛客计时的userId
	 * @return
	 */
	public Integer getgtUserIdByphoneNum(@Param("phoneNum") String phoneNum); 
	
	
	
	/**
	 * 根据unionId查找用户信息
	 * @param unionId
	 * @return
	 */
	public NeuUserBean searchNeuUser(@Param("phoneNum") String phoneNum,@Param("role") int role,
			@Param("source") String source,@Param("unionId") String unionId,@Param("userId") int userId,@Param("target") String target);
	
	
	/**
	 * 获取报名用户的openId
	 * @param gUserId 赛客报名的的userId
	 * @return
	 */
	public String getOpenIdByGuserId(@Param("userId") int userId);
	
}







