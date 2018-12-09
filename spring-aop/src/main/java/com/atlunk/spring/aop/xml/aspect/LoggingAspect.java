package com.atlunk.spring.aop.xml.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {
	public void declarJoinPoint() {}
	
	public void beforeMethod(JoinPoint joinPoint) {
		//JoinPoint： 访问一些连接细节
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("[@Before]The method "+ methodName +" begins with" + args);
	}
	
	
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[@After]The method "+ methodName +" ends");
	}
	
	
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[@AfterReturning]The method "+ methodName +" ends, result is " + result);
	}
	
	public void afterThrowing(JoinPoint joinPoint, Object ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[@AfterThrowing]The method "+ methodName +" occurs exception " + ex);
	}
	
	
	public Object around(ProceedingJoinPoint pjd) {
		Object obj = null;
		String methodName = pjd.getSignature().getName();
		
		//1 执行目标方法
		try {
			//前置通知
			System.out.println("[@Around]The method "+ methodName +" begins with" + Arrays.asList(pjd.getArgs()));
			obj = pjd.proceed();
			//返回通知
			System.out.println("[@Around]The method "+ methodName +" ends, result is " + obj);
			
		} catch (Throwable e) {
			e.printStackTrace();
			//前置通知
			System.out.println("[@Around]The method "+ methodName +" occurs exception: " + e);
			throw new RuntimeException(e);
		}
		//后置通知
		System.out.println("[@Around]The method "+ methodName +" ends");
		return obj;
	}
	
}
