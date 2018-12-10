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
	 * 	测试事务的传播行为 
	 * 	默认使用同一个事务，即如果余额只够买一本，买另外一本书失败的时候，就会全部回滚
	 * 	
	 * 	可在此处配置事务的传播行为，每次买一本书都会新启动一个事务，可以允许第一本书买成功，然后余额不足，第二本书抛出异常
	 * 	<!-- 配置事务的传播行为，会new一个事务2，当事务1调用一个事务2的时候，事务1会先挂起，执行事务2，事务2执行成功，在执行事务1 -->
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
