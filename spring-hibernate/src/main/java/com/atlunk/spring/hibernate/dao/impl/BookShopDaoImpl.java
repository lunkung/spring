package com.atlunk.spring.hibernate.dao.impl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atlunk.spring.hibernate.dao.BookShopDao;
import com.atlunk.spring.hibernate.exception.BookStockException;
import com.atlunk.spring.hibernate.exception.UserAccountException;

@Repository
public class BookShopDaoImpl implements BookShopDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		/*
		 * 获取跟当前线程绑定的session
		 */
		return sessionFactory.getCurrentSession();
	}

	public int findBookPriceByIsbn(String isbn){
		String hql = "select b.price from Book b where b.isbn = :isbn";
		Query query = getSession().createQuery(hql);
		query.setParameter("isbn", isbn);
		int result = (Integer) query.getSingleResult();
		return result;
	}

	public void updateBookStock(String isbn) {
		// 验证书的库存是否充足
		String hql2 = "select b.stock from Book b where b.isbn = :isbn";
		Query query2 = getSession().createQuery(hql2);
		query2.setParameter("isbn", isbn);
		int result = (Integer) query2.getSingleResult();
		if (0 == result) {
			throw new BookStockException("库存不足！");
		}
		// 更新库存
		int stock = result - 1;
		String hql = "update Book b set b.stock = :stock where b.isbn = :isbn";
		Query query = getSession().createQuery(hql);
		query.setParameter("stock", stock);
		query.setParameter("isbn", isbn);
		query.executeUpdate();
	}

	public void updateUserAccount(String userName, int price) {
		// 验证余额是否充足
		String hql2 = "select a.balance from Account a where a.userName = :userName";
		Query query2 = getSession().createQuery(hql2);
		query2.setParameter("userName", userName);
		int result = (Integer) query2.getSingleResult();
		if (result < price) {
			throw new UserAccountException("余额不足！");
		}

		// 更新余额
		int balance = result - price;
		String hql = "update Account a set a.balance = :balance where a.userName = :userName";
		Query query = getSession().createQuery(hql);
		query.setParameter("balance", balance);
		query.setParameter("userName", userName);
		query.executeUpdate();

	}

}
