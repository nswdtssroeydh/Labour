package com.xiuya.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibeinateUtils {

	public static final ApplicationContext context;
	public static final Configuration configuration;
	public static final SessionFactory sessionFactory;
	static {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
		System.out.println("build");
	}
	
}
