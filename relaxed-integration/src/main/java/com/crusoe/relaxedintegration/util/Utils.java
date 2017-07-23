package com.crusoe.relaxedintegration.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


public class Utils {
	private static final Logger log = Logger.getLogger(Utils.class);
	
	/**
	 * 判别字符串是否是数字字符串
	 */
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 }
	//短语或
	public static Object sco(Object first,Object second){
		if(first!=null){
			return first;
		}else if(second!=null){
			return second;
		}else{
			return null;
		}
	}
	
	public static String toParamString(HttpServletRequest request){
		Enumeration<?> enums = request.getParameterNames();
		Map<String, String> map = new HashMap<>();
		while (enums.hasMoreElements()) {
			String key = (String)enums.nextElement();
			map.put(key, request.getParameter(key));
		}
		return map.toString();
	}
//出生日期字符串转化成Date对象  
    public static Date parse(String strDate) throws ParseException {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        return sdf.parse(strDate);  
    } 
  //由出生日期获得年龄  
    public static int getAge(Date birthDay) {  
        Calendar cal = Calendar.getInstance();  
  
        if (cal.before(birthDay)) {  
            throw new IllegalArgumentException(  
                    "The birthDay is before Now.It's unbelievable!");  
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthDay);  
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth) age--;  
            }else{  
                age--;  
            }  
        }  
        return age;  
    } 
    
    public static String getClearUrlParam(String param){
    	String urlParam="";
    	if(param!=null && !"".equals(param)){
    		ArrayList<String> newParams=new ArrayList<String>();
    		String[] subParams=param.split("&");
    		for(String s:subParams){
    			if(s.indexOf("key")==-1){
    				newParams.add(s);
    			}
    		}
    		for(int i=0;i<newParams.size();i++){
    			urlParam+=newParams.get(i)+"&";
    		}
    		urlParam=urlParam.substring(0, urlParam.length()-1);
    	}
    	return urlParam;
    	
    }
    
    public static Date strToDate(String dateStr) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			date = sdf.parse(dateStr);
			return date;
		} catch (Exception e) {
			log.warn(e.getMessage());
			return null;
		}
    }
    
    public static void main(String[] args) {
		String url=getClearUrlParam("gCmptId=334&gUserId=4561&key=Pc4oSYS2Tsm7HNbVzdrWAMrIicw%3D");
		System.out.println(url);
		System.out.println(strToDate(Const.NO_LIMIT_DATE));
	}

}
















