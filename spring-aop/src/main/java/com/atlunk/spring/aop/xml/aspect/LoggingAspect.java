package com.atlunk.spring.aop.xml.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {
	public void declarJoinPoint() {}
	
	public void beforeMethod(JoinPoint joinPoint) {
		//JoinPoint�� ����һЩ����ϸ��
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
		
		//1 ִ��Ŀ�귽��
		try {
			//ǰ��֪ͨ
			System.out.println("[@Around]The method "+ methodName +" begins with" + Arrays.asList(pjd.getArgs()));
			obj = pjd.proceed();
			//����֪ͨ
			System.out.println("[@Around]The method "+ methodName +" ends, result is " + obj);
			
		} catch (Throwable e) {
			e.printStackTrace();
			//ǰ��֪ͨ
			System.out.println("[@Around]The method "+ methodName +" occurs exception: " + e);
			throw new RuntimeException(e);
		}
		//����֪ͨ
		System.out.println("[@Around]The method "+ methodName +" ends");
		return obj;
	}
	
}
