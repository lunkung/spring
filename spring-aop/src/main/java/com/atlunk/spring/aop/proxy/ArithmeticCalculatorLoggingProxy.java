package com.atlunk.spring.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorLoggingProxy {
	
	//要代理的对象
	private ArithmeticCalculator target;
	
	public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
		this.target = target;
	}
	
	public ArithmeticCalculator getLoggingProxy() {
		ArithmeticCalculator proxy = null;
		
		//代理对象由哪个类加载器负责加载
		ClassLoader loader = target.getClass().getClassLoader();
		//代理对象的类型，即其中有哪些方法
		Class[] interfaces = new Class[] {ArithmeticCalculator.class};
		//当调用代理对象其中的方法，该执行的代码
		InvocationHandler h = new InvocationHandler() {
			/*
			 *	proxy :正在返回的那个代理对象，一般情况下，在invoke方法中都不使用该对象 
			 * 	method : 正在被调用的方法
			 *  args ： 调用方法时传入的参数
			 */
			public Object invoke(Object proxy, Method method, Object[] args) 
					throws Throwable {
				String methodName = method.getName();
				Object result = null;
				try {
					//前置通知
					result = method.invoke(target, args);
					//返回通知通知
				}catch(Exception e) {
					e.printStackTrace();
					//异常通知
				}
				//后置同志
				
				
				return result;
			}
		};
		
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}
	
}
