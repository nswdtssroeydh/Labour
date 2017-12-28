package com.xiuya.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xiuya.bean.Employee;
import com.xiuya.bean.Tea;
import com.xiuya.util.HibernateUtils;

public class TeaDao {

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
	
	public void add(Tea tea)
	{
		Session session = null;
		Transaction transaction = null;
		try {
			session = getCurrentSession();
			transaction = session.beginTransaction();
			session.save(tea);
			transaction.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("add error");
			transaction.rollback();
		}
	}
	
	public List<Tea> getAll()
	{
		try {
			Session session = getCurrentSession();
			List<Tea> teas = new ArrayList<>();
			
			String sql = "from Tea";
			Query query = session.createQuery(sql);
			teas = query.list();
			
			return teas;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return new ArrayList<Tea>();
		}
	}
	
	public void deleteById(Integer id)
	{
		Session session = null;
		Transaction transaction = null;
		try {
			session = getCurrentSession();
			transaction = session.beginTransaction();
			Tea tea = session.get(Tea.class, id);
			if(tea == null)
			{
				System.out.println("not that tea");
				transaction.commit();
				return;
			}
			session.delete(tea);
			
			transaction.commit();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			transaction.rollback();
			System.out.println("delete error!!!");
		}
	}
	
	public Tea getById(Integer id)
	{
		Session session = null;
		try {
			session = getCurrentSession();
			Tea tea = session.get(Tea.class, id);
			return tea;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("select error!!!");
			return null;
		}
	}
	
	public void updateById(Integer id, String name, double price)
	{
		Session session = null;
		Transaction transaction = null;
		try {
			session = getCurrentSession();
			transaction = session.beginTransaction();
			Tea tea = session.get(Tea.class, id);
			tea.setName(name);
			tea.setPrice(price);
			session.saveOrUpdate(tea);
			transaction.commit();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			transaction.rollback();
			System.out.println("update error!!!");
		}
	}
	
	public List<Tea> selectTeaByName(String name)
	{
		Session session = null;
		try {
			session = getCurrentSession();
			String sql = "from Tea where name like ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, "%" + name + "%");
			List<Tea> teas = query.list();
			
			return teas;
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("select error!!!");
			return null;
		}
	}
}
