package com.atlunk.spring.bean1;

public class Person {

	private String name;

	public String getName() {
		return name;
	}

	public void setName2(String name) {
		System.out.println("setName2£º" + name);
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
	public void sayHello() {
		System.out.println("Hello: " + getName());
	}

	public Person() {
		System.out.println("Person's Constructor...");
	}
	
	
}
