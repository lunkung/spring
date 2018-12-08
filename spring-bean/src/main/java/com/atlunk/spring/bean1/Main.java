package com.atlunk.spring.bean1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	//1��ͳ��ֵ����
	public static void test1() {
		// 1. ��������
		Person person = new Person();
		// 2.Ϊ���Ը�ֵ
		person.setName2("atlunk");
		// 3.���÷���
		person.sayHello();
	}

	//2ͨ��spring��ֵ
	public static void test2() {
		//1����Spring ioc����
		/*
		 *	 ����ioc������ʱ����������޲ι��췽����ͬʱ����set���������Ը�ֵ
		 */
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-bean1.xml");
		//2��ioc������ȡbean
		Person person = (Person) ctx.getBean("person");
		//3���÷���
		person.sayHello();
	}

	public static void main(String args[]) {
		//test1();
		test2();
	}

}
