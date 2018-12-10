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
	 * 	spring hibernate ��������
	 * 	1. �ڷ�����ʼ֮ǰ
	 * 		��ȡsession
	 * 		��session�͵�ǰ�̰߳󶨣������Ϳ�����Dao��ʹ��SessionFactory��getCurrenctSession()��������ȡSession��
	 * 		��������
	 * 	2. ������������������û�г����쳣����
	 * 		�ύ����
	 * 		ʹ�͵�ǰ�̰߳󶨵�session�Ӵ���
	 * 		�ر�session
	 *  3.�����������쳣����
	 *  	�ع�����
	 *  	ʹ�͵�ǰ�̰߳󶨵�session�Ӵ���
	 * 		�ر�session
	 */
	public void purchase(String userName, String isbn) {
		//�õ�����
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		//���¿��
		bookShopDao.updateBookStock(isbn);
		//�����û������
		bookShopDao.updateUserAccount(userName, price);
		
	}

}
