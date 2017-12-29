package com.xiuya.dao;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xiuya.bean.Employee;
import com.xiuya.bean.Labour;
import com.xiuya.bean.Tea;
import com.xiuya.controller.EmployeeController;
import com.xiuya.controller.TeaController;
import com.xiuya.util.HibernateUtils;

public class LabourDao {
	
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
	
	//增
	public void add(Labour labour)
	{
		Session session = null;
		try {
			session = getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.save(labour);
			transaction.commit();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("add error");
		}
	}
	
	//删
	public void deleteLabour(Integer id)
	{
		Session session = null;
		try {
			session = getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Labour labour = session.get(Labour.class, id);
			session.delete(labour);
			
			transaction.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("delete error");
		}
	}
	
	//改
	public void update(Integer id, Double amount, Double salary, Date date)
	{
		Session session = null;
		try {
			session = getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Labour labour = session.get(Labour.class, id);
			if(amount != null)
				labour.setAmount(amount);
			
			if(date != null)
				labour.setDate(date);
			
			if(salary != null)
				labour.setSalary(salary);
			
			transaction.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("update error");
		}
	}
	
	//查
	public List<Labour> getLabourByName(String employeeName, Date dateFrom, Date dateTo)
	{
		Session session = null;
		List<Labour> result = new ArrayList<>();
		boolean flag = false;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		try {
			session = getCurrentSession();
			String sql = "from Labour where date >= ? and date <= ?";
			if(!"".equals(employeeName))
			{
				flag = true;
				sql = sql + " and employeeName like ?";
			}
			
			sql = sql + " order by date desc";
			
			Query query = session.createQuery(sql);
			if(dateFrom == null)
			{
				java.util.Date javaDate = format.parse("1900-01-01");
				Date date = new Date(javaDate.getTime());
				query.setParameter(0, date);
			}else
			{
				query.setParameter(0, dateFrom);
			}
			
			if(dateTo == null)
			{
				java.util.Date javaDate = format.parse("2050-01-01");
				Date date = new Date(javaDate.getTime());
				query.setParameter(1, date);
			}else
			{
				query.setParameter(1, dateTo);
			}
			if(flag)
				query.setParameter(2, "%" + employeeName + "%");
			
			result = query.list();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("query error");
		}
		return result;
	}
	
	public static void main(String[] args)
	{
		LabourDao dao = new LabourDao();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date from;
		try {
			from = format.parse("2017-1-1");
			java.util.Date to = format.parse("2017-12-31");
			Date from1 = new Date(from.getTime());
			Date to1 = new Date(to.getTime());
			
			
			List<Labour> labours = dao.getLabourByName("", null, null);
			System.out.println(to1.getYear() + ":" + to1.getMonth() + ":" + to1.getDate());
			for(Labour labour:labours)
				System.out.println(labour);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	
}
