package com.crusoe.relaxedintegration.util;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
	
	public static final String APP_SECRET = "2016q4test_data";
	
	public static byte[] getHmacSHA1(String src)  
	        throws NoSuchAlgorithmException, UnsupportedEncodingException,  
	        InvalidKeyException {  
	    Mac mac = Mac.getInstance("HmacSHA1");  
	    SecretKeySpec secret = new SecretKeySpec(  
	            APP_SECRET.getBytes("UTF-8"), mac.getAlgorithm());  
	    mac.init(secret);  
	    return mac.doFinal(src.getBytes());
	}  
	
	public static String getKey(String url){
		byte[] hmac;
		try {
			hmac = getHmacSHA1(url);
			return URLEncoder.encode(Base64.encode(hmac), "UTF-8");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(getKey("http://webapi.weather.com.cn/data/?areaid=101090205&type=forecast7d&date=201608292150&appid=35074891a78bf08f"));
		System.out.println(getKey("http://localhost:8080/integration/getGtEnrollIdByG?gCmptId=334&gUserId=4561"));

	}

}
