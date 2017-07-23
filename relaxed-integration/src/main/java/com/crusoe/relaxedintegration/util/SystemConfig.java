package com.crusoe.relaxedintegration.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;


public final class SystemConfig implements InitializingBean {
	private static Logger log = LoggerFactory.getLogger(SystemConfig.class);

	/**
	 * 配置文件属性键值对
	 */
	private static Map<String, String> systemProperties = new HashMap<String, String>();

	// 资源文件位置
	private String[] propertiesLocation;

	public String[] getPropertiesLocation() {
		return propertiesLocation;
	}

	public void setPropertiesLocation(String[] propertiesLocation) {
		this.propertiesLocation = propertiesLocation;
	}

	/**
	 * 根据key获取value
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(final String key) {
		return systemProperties.get(key);
	}

	/**
	 * 根据key获取value
	 * 
	 * @param key
	 * @return
	 */
	public static String getTrimProperty(final String key) {
		return StringUtils.trimToEmpty((String) systemProperties.get(key));
	}

	/**
	 * 根据key获取value,获取为空则返回默认值
	 * 
	 * @param key
	 * @param defaultString
	 * @return
	 */
	public static String getProperty(final String key, final String defaultString) {
		String prop = getProperty(key);
		return StringUtils.isBlank(prop) ? defaultString : prop;
	}

	/**
	 * 根据key获取Integer类型的value
	 * 
	 * @param key
	 *            参数名称.
	 * @return 参数值.
	 */
	public static int getIntProperty(final String key) {
		return getIntProperty(key, 0);
	}

	/**
	 * 根据key获取Integer类型的value,获取为空则返回默认值
	 * 
	 * @param key
	 *            参数名称.
	 * @param defaultValue
	 *            默认值.
	 * @return 参数值.
	 */
	public static int getIntProperty(final String key, int defaultValue) {
		String property = StringUtils.trimToNull(getProperty(key));
		if (property == null)
			return defaultValue;
		int value;
		try {
			value = Integer.parseInt(property);
		} catch (NumberFormatException e) {
			value = defaultValue;
			log.warn(e.getMessage());
		}
		return value;
	}

	/**
	 * 根据key获取Long类型的value,获取为空则返回默认值
	 * 
	 * @param key
	 *            参数名称.
	 * @param defaultValue
	 *            默认值.
	 * @return 参数值.
	 */
	public static long getLongProperty(final String key, long defaultValue) {
		String property = StringUtils.trimToNull(getProperty(key));
		if (property == null) {
			return defaultValue;
		}
		long value;
		try {
			value = Long.parseLong(property);
		} catch (NumberFormatException e) {
			value = defaultValue;
			log.warn(e.getMessage());
		}
		return value;
	}

	/**
	 * 获取配置文件中的整型参数值.
	 * 
	 * @param key
	 *            参数名称.
	 * @return 整型参数值.
	 */
	public static Integer getIntegerProperty(final String key) {
		return getIntegerProperty(key, null);
	}

	/**
	 * 获取配置文件中的整型参数值.
	 * 
	 * @param key
	 *            参数名称.
	 * @param defaultValue
	 *            缺省值.
	 * @return 整型参数值.
	 */
	public static Integer getIntegerProperty(final String key, final Integer defaultValue) {
		String strVal = StringUtils.trimToNull(getProperty(key));
		Integer value = defaultValue;
		if (strVal != null) {
			try {
				value = Integer.parseInt(strVal);
			} catch (NumberFormatException e) {
				log.warn(e.getMessage());
			}
		}
		return value;
	}

	/**
	 * 获取配置文件中的布尔参数值.
	 * 
	 * @param key
	 *            参数名称.
	 * @return 布尔参数值.
	 */
	public static boolean getBooleanProperty(final String key) {
		return getBooleanProperty(key, false);
	}

	/**
	 * 获取配置文件中的布尔参数.如果文件中没有该参数就返回defaultValue.
	 * 
	 * @param key
	 *            参数名称.
	 * @param defaultValue
	 *            参数默认值.
	 * @return 布尔参数值.
	 */
	public static boolean getBooleanProperty(final String key, final boolean defaultValue) {
		if (systemProperties == null) {
			return defaultValue;
		}

		Object value = systemProperties.get(key);

		if (null != value) {
			String strValue = StringUtils.trimToNull(value.toString());

			return "true".equalsIgnoreCase(strValue) || "on".equalsIgnoreCase(strValue) || "yes".equalsIgnoreCase(strValue) || "1".equalsIgnoreCase(strValue);
		} else {
			return defaultValue;
		}
	}

	/**
	 * 默认获取classpath下config文件中所有的properties文件
	 * 
	 * @throws Exception
	 */
	public void afterPropertiesSet() throws Exception {
		log.debug("initialize properties files...");
		if (null == systemProperties) {
			systemProperties = new HashMap<String, String>();
		}
		if (null != propertiesLocation && propertiesLocation.length > 0) {
			for (String filePath : propertiesLocation) {
				InputStream inStream = this.getClass().getResourceAsStream("/" + filePath);
				if (null == inStream) {
					continue;
				}
				Properties prop = new Properties(); // 实例化
				prop.load(inStream);

				// 返回Properties中包含的key-value的Set视图
				Set<Entry<Object, Object>> set = prop.entrySet();
				// 返回在此Set中的元素上进行迭代的迭代器
				Iterator<Map.Entry<Object, Object>> it = set.iterator();
				String key = null, value = null;
				// 循环取出key-value
				while (it.hasNext()) {
					Entry<Object, Object> entry = it.next();
					key = String.valueOf(entry.getKey());
					value = String.valueOf(entry.getValue());
					if (StringUtils.isNotBlank(key)) {
						value = StringUtils.isNotBlank(value) ? value : "";
						systemProperties.put(key, value);
					}
				}
			}
		}
	}
}

