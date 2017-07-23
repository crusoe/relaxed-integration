package com.crusoe.relaxedintegration.aspect;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.crusoe.relaxedintegration.util.Const;
import com.crusoe.relaxedintegration.util.CryptoUtil;
import com.crusoe.relaxedintegration.util.Utils;

@Component
public class ValidationAspect {
	
	private static final Logger log = Logger.getLogger(ValidationAspect.class);
	public Object beforMethod(JoinPoint joinPoint){
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();       
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();      
        
        String serverName=request.getServerName();
        int serverPort=request.getServerPort();	
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        String requestUrl=request.getRequestURI();
        String queryUrl=request.getQueryString();
        String key=request.getParameter("key");
         try {
        	 String url="http://"+serverName+":"+serverPort+requestUrl;
        	 if(null!=queryUrl){
        		 url+="?"+Utils.getClearUrlParam(queryUrl);
        	 }
        	 String keyVal= URLDecoder.decode(CryptoUtil.getKey(url),"utf-8");
        	 if(key!=null && keyVal.equals(key) || "is".equals(Const.open)){
        		 System.err.println("*****hello****"+key); 
        	 }else{
        		 System.err.println("*****hello你好****"+key);
        		 /*response.sendRedirect("http://timer.geexek.com");*/
        		 /*response.sendError(403);*/
        		 result.put("status", 403);
        		 result.put("message", "没有权限！！！");
        		 response.sendError(403, "没有权限"); 
        	 }
            
        }  catch (Exception e) {  
        	log.error(e.getMessage(), e); 
        	try {
				response.sendRedirect("http://timer.geexek.com");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	return result;
        }
         return result;
    }
	public void validationPrilvilege(ProceedingJoinPoint joinPoint){
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        /*HttpSession session = request.getSession();    
        //读取session中的用户    
        User user = (User) session.getAttribute(WebConstants.CURRENT_USER); */   
        //请求的IP    
        String ip = request.getRemoteAddr();
        String url=request.getServletPath();
        String url1=request.getRequestURI();
        String url2=request.getContextPath();
        String queryurl=request.getQueryString();
        String gcmptId=request.getParameter("gCmptId");
        String serverName=request.getServerName();
        int serverPort=request.getServerPort();
        String serverPath=request.getServletPath();
         try {    	
        	 System.out.println(url);
        	 System.out.println(url1);
        	 System.out.println(url2);
        	 System.out.println(queryurl);
        	 System.out.println(serverName);
        	 System.out.println(serverPort);
        	 System.out.println(serverPath);
        	 System.out.println(gcmptId);
            String key=CryptoUtil.getKey(ip);
            System.err.println("*****hello****"+key);
            if("HhaG09s2K%2FJ8e3590ns5g%2BQtEUY%3D".equals(key)){
            	
					joinPoint.proceed();
				
            }else{
            	System.out.println("*************权限不通过**************");
            }
        }  catch (Exception e ) {  
        	log.error(e.getMessage(), e);  
        }catch (Throwable e){
        	e.printStackTrace();
        	}
/*    	joinPoint.getClass().getSigners().
*/        /*String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("ValidationAspect this method "+methodName+" begin. param<"+ args+">");*/
    	System.out.println("*****************请求前操作**************");
    }
	
    /**
     * 后置通知（无论方法是否发生异常都会执行,所以访问不到方法的返回值）
     * @param joinPoint
     */
	/*@After("myMethod()")*/
    public void afterMethod(){
        System.out.println("ValidationAspect this method "+1111+" end.");
        System.out.println("*******************请求后操作*******************");
    }
    /**
     * 返回通知（在方法正常结束执行的代码）
     * 返回通知可以访问到方法的返回值！
     * @param joinPoit
     */
    public void afterReturnMethod(){
        }
    /**
     * 异常通知（方法发生异常执行的代码）
     * 可以访问到异常对象；且可以指定在出现特定异常时执行的代码
     * @param joinPoint
     * @param ex
     */
    public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
       /* String methodName = joinPoint.getSignature().getName()*/;
        System.out.println("ValidationAspect this method "+1111+" end.ex message<"+ex+">");
        System.out.println("**************************************************************");
    }

}
