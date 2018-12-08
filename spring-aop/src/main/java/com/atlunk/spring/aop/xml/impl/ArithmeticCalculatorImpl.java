package com.atlunk.spring.aop.xml.impl;

import org.springframework.stereotype.Component;

import com.atlunk.spring.aop.xml.ArithmeticCalculator;

@Component("ArithmeticCalculator")
public class ArithmeticCalculatorImpl implements ArithmeticCalculator{

	public int add(int i, int j) {
		int result = i + j;
		return result;
	}

	public int sub(int i, int j) {
		int result = i - j;
		return result;
	}

	public int mul(int i, int j) {
		int result = i * j;
		return result;
	}

	public int div(int i, int j) {
		int result = i / j;
		return result;
	}

}
