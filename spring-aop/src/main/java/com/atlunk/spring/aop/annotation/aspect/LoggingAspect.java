package com.atlunk.spring.aop.annotation.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//���������뵽IOC������ȥ��Ȼ������������Ϊһ������
@Order(2)//�����������ִ��˳��ֵԽС���ȼ�Խ��
@Aspect
@Component
public class LoggingAspect {
	/*
	 * AOP����
	 * ���棨Aspect��:���й�ע�㣬��ԽӦ�ó�����ģ�飬��ģ�黯���������
	 * ֪ͨ(Advice) : �������Ҫ��ɵĹ����������������ÿһ����������֮Ϊ֪ͨ�� Ҫִ�еĴ��룩
	 * Ŀ�꣨target������֪ͨ�Ķ��󣬾������ҵ���߼����룬����������д��־����"֪ͨ"ȥ���д��־
	 * ����proxy��: ��Ŀ��Ӧ��֪ͨ�󴴽��Ķ���
	 * ���ӵ�(JoinPoint): ����ִ�е�ĳ���ض�λ�ã��������ĳ����������ǰ�����ú󣬷����ܳ��쳣��ȡ�
	 * �е㣨pointcut���� ÿ���඼�ж�����ӵ㣬����ArithmeticCalculator�е����з���ʵ���϶������ӵ㣬
	 * �����ӵ��ǳ������п͹۴��ڵ����AOPͨ���е㶨λ���ض������ӵ㡣��ȣ����ӵ��൱�����ݿ��еļ�¼���е��൱��
	 * ��ѯ������һ���е��ж�����ӵ�
	 */
	
	//1 ����һ����������������ı��ʽ
	//2ʹ��@Pointcut ����������ʽ
	@Pointcut("execution(* com.atlunk.spring.aop.annotation.impl.*.*(..))")
	public void declarJoinPoint() {}
	
	//�����÷�����һ��ǰ��֪ͨ�� ��Ŀ�귽����ʼִ��֮ǰִ��
	//���Ҫ���õķ�����@Before�������ķ���һ�µ�ʱ��AOP��ܻ��Զ���Ϊ����������ڵ��ഴ��һ���������Ȼ���ڷ�������֮ǰִ��sysout������
	//ֻ������add����
	//@Before("execution(public int com.atlunk.spring.aop.annotation.impl.ArithmeticCalculatorImpl.add(int, int))")
	//���������з���
	//@Before("execution(public int com.atlunk.spring.aop.annotation.impl.ArithmeticCalculatorImpl.*(int, int))")
	//�������η������ⷵ��ֵ�� ������µ�������
	//@Before("execution(* com.atlunk.spring.aop.annotation.impl.*.*(int, int))")
	
	//����������ʽ������ķ���
	@Before("declarJoinPoint()")
	public void beforeMethod(JoinPoint joinPoint) {
		//JoinPoint�� ����һЩ����ϸ��
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("[@Before]The method "+ methodName +" begins with" + args);
	}
	
	//����֪ͨ����Ŀ�귽��ִ����ɺ�֪ͨ�������Ƿ����쳣��
	//�ں���֪ͨ�в��ܷ���Ŀ�귽��ִ�еĽ��
	//@After("execution(* com.atlunk.spring.aop.annotation.impl.*.*(int, int))")
	
	@After("declarJoinPoint()")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[@After]The method "+ methodName +" ends");
	}
	
	
	/*
	 * 	����֪ͨ����Ŀ�귽������������ִ�к�Ĵ��룬��������쳣������ִ��
	 * 	���Է��ʵ�Ŀ�귽��ִ�еķ���ֵ
	 */
	//@AfterReturning(value=("execution(* com.atlunk.spring.aop.annotation.impl.*.*(int, int))"), returning="result")
	
	@AfterReturning(value=("declarJoinPoint()"), returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[@AfterReturning]The method "+ methodName +" ends, result is " + result);
	}
	
	/*
	 * 	�쳣֪ͨ
	 * 	��Ŀ�귽�������쳣��ʱ��ִ��,����ָ�����ֵ��쳣
	 */
	//@AfterThrowing(value=("execution(* com.atlunk.spring.aop.annotation.impl.*.*(int, int))"), throwing="ex")
	@AfterThrowing(value=("declarJoinPoint()"), throwing="ex")
	//public void afterThrowing(JoinPoint joinPoint, NullPointException ex) {
	public void afterThrowing(JoinPoint joinPoint, Object ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[@AfterThrowing]The method "+ methodName +" occurs exception " + ex);
	}
	
	
	/*
	 * 	����֪ͨ,���൱��com.atlunk.spring.aop.proxy.ArithmeticCalculatorLoggingProxy�����̬�����ȫ����
	 * 	����֪ͨ:��ҪЯ��ProceedingJoinPoint���͵Ĳ���
	 * 	����֪ͨ�����з���ֵ������ֵ��ΪĿ�귽���ķ���ֵ
	 * 
	 */
	@Around("execution(public int com.atlunk.spring.aop.annotation.impl.ArithmeticCalculatorImpl.*(int, int))")
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
