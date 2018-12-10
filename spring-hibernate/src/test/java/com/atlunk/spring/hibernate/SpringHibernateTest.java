package com.atlunk.spring.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atlunk.spring.hibernate.service.BookShopService;
import com.atlunk.spring.hibernate.service.Cashier;

public class SpringHibernateTest {

	private ApplicationContext ctx = null;
	private BookShopService  bookShopService = null;
	private Cashier cashier  = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		bookShopService = (BookShopService) ctx.getBean(BookShopService.class);
		cashier = (Cashier) ctx.getBean(Cashier.class);
	}
	
	@Test
	public void testBookShopService() {
		bookShopService.purchase("aa", "1001");
	}
	
	
	/*
	 * 	��������Ĵ�����Ϊ 
	 * 	Ĭ��ʹ��ͬһ�����񣬼�������ֻ����һ����������һ����ʧ�ܵ�ʱ�򣬾ͻ�ȫ���ع�
	 * 	
	 * 	���ڴ˴���������Ĵ�����Ϊ��ÿ����һ���鶼��������һ�����񣬿��������һ������ɹ���Ȼ�����㣬�ڶ������׳��쳣
	 * 	<!-- ��������Ĵ�����Ϊ����newһ������2��������1����һ������2��ʱ������1���ȹ���ִ������2������2ִ�гɹ�����ִ������1 -->
		<tx:method name="purchase" propagation="REQUIRES_NEW"/>
	 */
	@Test
	public void testBookCashier() {
		cashier.checkout("aa", Arrays.asList("1001","1002"));
	}
	

	@Test
	public void testDataSOurce2() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
		System.out.println(connection);
	}

	@Test
	public void testDataSOurce() throws SQLException {
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println("dataSource is: " + dataSource.getConnection());
	}
	
	
	

}
