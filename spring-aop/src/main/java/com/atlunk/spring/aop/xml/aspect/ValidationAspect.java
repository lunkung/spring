package com.atlunk.spring.aop.xml.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

public class ValidationAspect {
	
	public void validation(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("Do validate for "+ methodName +", parameters is: " + Arrays.asList(joinPoint.getArgs()));
	}
}
