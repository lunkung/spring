package com.atlunk.spring.aop.annotation.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class ValidationAspect {
	
	//@Before("execution(* com.atlunk.spring.aop.annotation.impl.*.*(..))")
	@Before("LoggingAspect.declarJoinPoint()")
	public void validation(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("Do validate for "+ methodName +", parameters is: " + Arrays.asList(joinPoint.getArgs()));
	}
}
