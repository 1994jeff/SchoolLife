package com.my.schoollife.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 把这个类声明为一个切面：需要把该类先放入到IOC容器中，再声明为一个切面
 */
@Aspect
@Component
public class SpringAop {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 设置切入点，即需要代理的方法
	 */
	@Pointcut("execution(* com.jeff.data.service.impl.*.*(..))")
	public void aspect(){
	}
	
	/*
     * 配置前置通知,使用在方法aspect()上注册的切入点
     * 同时接受JoinPoint切入点对象,可以没有该参数
     */
    @Before("aspect()")
    public void before(JoinPoint joinPoint){
    	log.debug("---------前置通知---------");
    }
    
    //配置后置通知,使用在方法aspect()上注册的切入点
    @After("aspect()")
    public void after(JoinPoint joinPoint){
        log.debug("---------后置通知---------");
    }
    
    //配置环绕通知,使用在方法aspect()上注册的切入点
    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint){
        long start = System.currentTimeMillis();
        Object retValue = null;//返回值
        log.debug("---------环绕通知开始---------");
        try {
			retValue = joinPoint.proceed();
		} catch (Throwable e) {
			log.debug(e.getMessage());
		}
        log.debug("---------环绕通知结束---------");
        return retValue;//一定要记住将返回值返回出去！！
    }

    
    //配置后置返回通知,使用在方法aspect()上注册的切入点
    @AfterReturning(pointcut="aspect()",returning="retValue")
    public void afterReturn(JoinPoint joinPoint,Object retValue){
        log.debug("---------返回通知---------");
    }
    
    //配置抛出异常后通知,使用在方法aspect()上注册的切入点
    @AfterThrowing(pointcut="aspect()", throwing="ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex){
        log.debug("---------异常通知---------");
    }
}
