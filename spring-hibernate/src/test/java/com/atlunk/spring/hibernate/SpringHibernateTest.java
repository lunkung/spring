package com.atlunk.spring.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHibernateTest {

	private ApplicationContext ctx = null;

	{
		ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}

	@Test
	public void testDataSOurce2() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
		System.out.println(connection);
	}

	@Test
	public void testDataSOurce() throws SQLException {
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println("dataSource is: " + dataSource.getConnection());
	}

}
