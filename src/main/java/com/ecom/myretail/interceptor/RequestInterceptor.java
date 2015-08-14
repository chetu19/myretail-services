package com.ecom.myretail.interceptor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * This is an Interceptor class that intercepts all the incoming requests and prints the access logs in the below format
 *  Client IP Address : {} | Server name : {} | Request Time : {} | Execution Time in ms: {} | Request URI : {} | Status code : {}
 * @author nshetty
 *
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

	private static final Logger accessLog = LoggerFactory.getLogger(RequestInterceptor.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private static final ThreadLocal<Long> startTimeLocal = new ThreadLocal<Long>(){
		 @Override
		 protected Long initialValue() {
		      return System.currentTimeMillis();
		 }
	};

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		startTimeLocal.get(); // initialise start time
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		
		long startTime = startTimeLocal.get();
		startTimeLocal.remove();
		long endTime = System.currentTimeMillis();
		
		long executionTime = endTime - startTime;
		
		System.out.println("Start Time :" + startTime );
		
		String dateTime = sdf.format(Calendar.getInstance().getTime());
		
		accessLog.info("\n==== Request details ==\n"
				+ " Client IP Address : {} | Server name : {} | Request Time : {} | Execution Time in ms: {} | Request URI : {} | Status code : {}  | User Agent : {} \n=======================" ,
				request.getRemoteAddr() , 
				request.getServerName() , 
				dateTime , 
				executionTime, 
				request.getRequestURI(), 
				response.getStatus() , 
				request.getHeader("User-Agent") );

		super.afterCompletion(request, response, handler, ex);
	}
	

}
