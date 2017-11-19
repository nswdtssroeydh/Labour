package com.xiuya.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.xiuya.bean.Employee;
import com.xiuya.util.HibeinateUtils;

public class EmployeeDao {

	private Session session;
	private Session getCurrentSession()
	{
		if(this.session == null)
			this.session = HibeinateUtils.sessionFactory.openSession();
			
		return this.session;
	}
	
	public void closeSession()
	{
		this.session.close();
	}

	public void add(Employee employee)
	{
		Session session = getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(employee);
		transaction.commit();
	}
	
	public void delete(Employee employee)
	{
		Session session = getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(employee);;
		transaction.commit();
	}
	
}
