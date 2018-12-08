package com.atlunk.spring.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorLoggingProxy {
	
	//Ҫ�����Ķ���
	private ArithmeticCalculator target;
	
	public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
		this.target = target;
	}
	
	public ArithmeticCalculator getLoggingProxy() {
		ArithmeticCalculator proxy = null;
		
		//�����������ĸ���������������
		ClassLoader loader = target.getClass().getClassLoader();
		//������������ͣ�����������Щ����
		Class[] interfaces = new Class[] {ArithmeticCalculator.class};
		//�����ô����������еķ�������ִ�еĴ���
		InvocationHandler h = new InvocationHandler() {
			/*
			 *	proxy :���ڷ��ص��Ǹ���������һ������£���invoke�����ж���ʹ�øö��� 
			 * 	method : ���ڱ����õķ���
			 *  args �� ���÷���ʱ����Ĳ���
			 */
			public Object invoke(Object proxy, Method method, Object[] args) 
					throws Throwable {
				String methodName = method.getName();
				//��־
				System.out.println("The method " + methodName + "begins with " + Arrays.asList(args));
				//ִ�з���
				Object result = method.invoke(target, args);
				//��־
				System.out.println("The method " + methodName + "end with " + result);
				return result;
			}
		};
		
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}
	
}