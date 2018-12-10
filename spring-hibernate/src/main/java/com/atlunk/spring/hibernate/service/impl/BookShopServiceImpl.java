package com.atlunk.spring.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlunk.spring.hibernate.dao.BookShopDao;
import com.atlunk.spring.hibernate.service.BookShopService;

@Service
public class BookShopServiceImpl implements BookShopService{
	
	@Autowired
	private BookShopDao bookShopDao;
	
	/*
	 * 	spring hibernate 事务流程
	 * 	1. 在方法开始之前
	 * 		获取session
	 * 		把session和当前线程绑定，这样就可以在Dao中使用SessionFactory的getCurrenctSession()方法来获取Session了
	 * 		开启事务
	 * 	2. 若方法正常结束，即没有出现异常，则
	 * 		提交事务
	 * 		使和当前线程绑定的session接触绑定
	 * 		关闭session
	 *  3.若方法出现异常，则：
	 *  	回滚事务
	 *  	使和当前线程绑定的session接触绑定
	 * 		关闭session
	 */
	public void purchase(String userName, String isbn) {
		//得到单价
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		//更新库存
		bookShopDao.updateBookStock(isbn);
		//更新用户的余额
		bookShopDao.updateUserAccount(userName, price);
		
	}

}
