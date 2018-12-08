package com.atlunk.spring.aop.xml;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//���������뵽IOC������ȥ��Ȼ������������Ϊһ������
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

	//�����÷�����һ��ǰ��֪ͨ�� ��Ŀ�귽����ʼִ��֮ǰִ��
	//���Ҫ���õķ�����@Before�������ķ���һ�µ�ʱ��AOP��ܻ��Զ���Ϊ����������ڵ��ഴ��һ���������Ȼ���ڷ�������֮ǰִ��sysout������
	
	//ֻ������add����
	//@Before("execution(public int com.atlunk.spring.aop.xml.impl.ArithmeticCalculatorImpl.add(int, int))")
	//���������з���
	//@Before("execution(public int com.atlunk.spring.aop.xml.impl.ArithmeticCalculatorImpl.*(int, int))")
	//�������η������ⷵ��ֵ�� ������µ�������
	@Before("execution(* com.atlunk.spring.aop.xml.impl.*.*(int, int))")
	public void beforeMethod(JoinPoint joinPoint) {
		//JoinPoint�� ����һЩ����ϸ��
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The method "+ methodName +" begins with" + args);
	}
	
}
