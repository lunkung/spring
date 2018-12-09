package com.atlunk.spring.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String args[]){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-aop-xml.xml");
		ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ctx.getBean("ArithmeticCalculator");

		int result = 0;
		
		//result = arithmeticCalculator.add(3, 6);
		//System.out.println("result=" +result);
		
		result = arithmeticCalculator.div(12, 6);
		System.out.println("result=" +result);
		
	}
}	
