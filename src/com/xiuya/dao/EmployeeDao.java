package com.xiuya.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.xiuya.bean.Employee;
import com.xiuya.util.Tool;

public class EmployeeDao {

	private SessionFactory sessionFactory;

	public void add(Employee employee)
	{
		this.sessionFactory = Tool.configuration.buildSessionFactory();
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(employee);
		transaction.commit();
		session.close();
		this.sessionFactory.close();
	}
	
}
