package com.atlunk.spring.hibernate.entities;

import java.io.Serializable;

public class Account implements Serializable{
	
	private Integer id;
	private String userName;
	private int balance;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
