package com.crusoe.relaxedintegration.data;

/**
 * 基于特征值的id对应适配器
 * @author cruzoe
 *
 */
public interface IdMapAdapter {
	/**
	 * 依次根据特征值1，特征值2，特征值3...获取子系统中对应的id
	 * @param subSys 子系统标识
	 * @param features 特征值1，特征值2，特征值3...
	 * @return 子系统中对应的id
	 */
	Object getIdByFeature(String subSys, Object... features);
	
	/**
	 * 如果找不到子系统中对应的id的情况下，用features去子系统创建这条记录，并获取id
	 * @param subSys 子系统标识
	 * @param features 特征值1，特征值2，特征值3...
	 * @return 子系统中新建数据的id
	 */
	Object saveIdByFeature(String subSys, Object... features);
}
