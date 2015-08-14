package com.ecom.myretail.interceptor;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


/**
 * This is AOP class that intercepts the methods for tracing the execution flow and execution time
 * @author nshetty
 *
 */
@Component
@Aspect
public class MethodInterceptor {
	
	private static Logger log = LoggerFactory.getLogger(MethodInterceptor.class);
	
	/**
	 * Point Cut on Controller/DAO/Service layer methods
	 */
	@Pointcut("execution(public * com.ecom.myretail.controller.*.*(..)) "
			+ "|| execution(public * com.ecom.myretail.service.*.*(..)) "
			+ "|| execution(public * com.ecom.myretail.dao.*.*(..))")
	public void allMethods(){ }
	
	
	/**
	 * This an Advice that logs the entry and exit of methods
	 */
	@Around("allMethods()")
	public Object methodEntryExitTrace(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		log.trace("Entering {}:{}",className,methodName);
		obj = pjp.proceed();
		log.trace("Exiting {}:{}",className,methodName);
		return obj;
	}
	
	/**
	 * Point Cut on DAO layer methods
	 */
	@Pointcut(" execution(public * com.ecom.myretail.dao.*.*(..))")
	public void daoMethodsToProfiled(){ }
	
	/**
	 * Advice to log  method execution time
	 */
	@Around("daoMethodsToProfiled()")
    public Object profileDaoMethods(ProceedingJoinPoint pjp) throws Throwable {
		
		if(log.isTraceEnabled()){ // do this only if trace level is enabled
			Object obj = null;
			String className = pjp.getTarget().getClass().getName();
			String methodName = pjp.getSignature().getName();
			StopWatch sw = new StopWatch(className);
            sw.start(methodName); // Timer starts
            obj = pjp.proceed(); // Proceed with the method execution
            sw.stop();  // Stop Timer 
            log.trace("Time Taken by  {}:{} :: {}" , className, methodName, sw.shortSummary());     
	        return obj;
		}else{
			return pjp.proceed();
		}
    }
}
