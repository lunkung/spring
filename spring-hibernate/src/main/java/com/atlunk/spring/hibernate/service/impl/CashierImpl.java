package com.atlunk.spring.hibernate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlunk.spring.hibernate.service.BookShopService;
import com.atlunk.spring.hibernate.service.Cashier;

@Service
public class CashierImpl implements Cashier{
	
	@Autowired
	private BookShopService bookShopService;
	
	public void checkout(String userName, List<String> isbns) {
		for(String isbn : isbns) {
			bookShopService.purchase(userName, isbn);
		}
	}

}
