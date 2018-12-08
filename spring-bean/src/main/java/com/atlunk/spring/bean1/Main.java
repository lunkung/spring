package com.atlunk.spring.bean1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	//1传统赋值方法
	public static void test1() {
		// 1. 创建对象
		Person person = new Person();
		// 2.为属性赋值
		person.setName2("atlunk");
		// 3.调用方法
		person.sayHello();
	}

	//2通过spring赋值
	public static void test2() {
		//1创建Spring ioc容器
		/*
		 *	 创建ioc容器的时候会调用类的无参构造方法，同时调用set方法对属性赋值
		 */
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-bean1.xml");
		//2从ioc容器获取bean
		Person person = (Person) ctx.getBean("person");
		//3调用方法
		person.sayHello();
	}

	public static void main(String args[]) {
		//test1();
		test2();
	}

}
