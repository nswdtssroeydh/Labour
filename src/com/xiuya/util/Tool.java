package com.xiuya.util;


import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tool {

	public static ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
	public static Configuration configuration = new Configuration();
	static {
		configuration.configure();
		System.out.println("build");
	}
	
}
