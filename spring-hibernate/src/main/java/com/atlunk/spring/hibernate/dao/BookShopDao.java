package com.atlunk.spring.hibernate.dao;

public interface BookShopDao {
	
	public int findBookPriceByIsbn(String isbn);
	public void updateBookStock(String isbn);
	public void updateUserAccount(String userName, int price);
}
