package com.xiuya.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.xiuya.bean.Employee;
import com.xiuya.util.HibernateUtils;

public class EmployeeDao {

	private Session session;
	private Session getCurrentSession()
	{
		if(this.session == null)
			this.session = HibernateUtils.sessionFactory.openSession();
			
		return this.session;
	}
	
	public void closeSession()
	{
		this.session.close();
	}

	public void add(Employee employee)
	{
		Session session = null;
		Transaction transaction = null;
		try {
			session = getCurrentSession();
			transaction = session.beginTransaction();
			session.save(employee);
			transaction.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			transaction.rollback();
		}
	}
	
	public void deleteById(int id)
	{
		Session session = null;
		Transaction transaction = null;
		try {
			session = getCurrentSession();
			transaction = session.beginTransaction();
			Employee temp = session.get(Employee.class, id);
			if(temp == null)
			{
				System.out.println("not that person");
				return;
			}
			session.delete(temp);
			transaction.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			transaction.rollback();
		}
	}
	
	public Employee selectById(int id)
	{
		Session session = null;
		try{
			session = getCurrentSession();
			Employee employee = session.get(Employee.class, id);
			return employee;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Employee> selectByName(String name)
	{
		Session session = null;
		try{
			session = getCurrentSession();
			String sql = "from Employee where name like ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, "%" + name + "%");
			List<Employee> employees = query.list();
			
			return employees;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Employee> getAllEmployees()
	{
		Session session = null;
		try{
			session = getCurrentSession();
			String sql = "from Employee";
			Query query = session.createQuery(sql);
			List<Employee> employees = query.list();
			
			return employees;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateEmployee(int id, String name, String phone)
	{
		Session session = null;
		Transaction transaction = null;
		try {
			session = getCurrentSession();
			transaction = session.beginTransaction();
			Employee temp = session.get(Employee.class, id);
			temp.setName(name);
			temp.setPhone(phone);
			session.saveOrUpdate(temp);
			transaction.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
		}
	}
}
