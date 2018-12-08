package com.atlunk.spring.aop.proxy;

import com.atlunk.spring.aop.proxy.impl.ArithmeticCalculatorImpl;

public class Main {
	
	public static void main(String args[]){
		
		ArithmeticCalculator target = new ArithmeticCalculatorImpl();
		ArithmeticCalculator proxy = new ArithmeticCalculatorLoggingProxy(target).getLoggingProxy();
		
		int result = proxy.add(1, 2);
		System.out.println(result);
		
		result = proxy.sub(4, 2);
		System.out.println(result);
	}
}	
