package com.xiuya.service;

import java.util.ArrayList;
import java.util.List;

import com.xiuya.bean.Employee;
import com.xiuya.dao.EmployeeDao;

public class EmployeeService {

	private EmployeeDao employeeDao;
	private static final int pageSize = 7;
	
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	//增
	public void add(String name, String phone)
	{
		Employee employee = new Employee();
		employee.setName(name);
		employee.setPhone(phone);
		employeeDao.add(employee);
	}
	
	//删
	public void deleteById(int id)
	{
		employeeDao.deleteById(id);
	}
	
	//查
	public Employee getEmployeeById(int id)
	{
		return employeeDao.selectById(id);
	}
	
	//查
	public List<Employee> getEmployeeByName(String name)
	{
		if(!"".equals(name))
			return employeeDao.selectByName(name);
		else
			return employeeDao.getAllEmployees();
	}
	
	public List<Employee> getEmployeeByName(String name, Integer page)
	{
		if(!"".equals(name))
		{
			List<Employee> all = employeeDao.selectByName(name);
			return pageBy(all, page);
		}
		else
		{
			List<Employee> all = employeeDao.getAllEmployees();
			return pageBy(all, page);
		}
	}
	
	public void updateEmployee(int id, String name, String phone)
	{
		employeeDao.updateEmployee(id, name, phone);
	}
	
	public List<Employee> pageBy(List<Employee> all, Integer page)
	{
		List<Employee> result = new ArrayList<>();
		int cur = page - 1;
		if(all.size()/pageSize < cur)
		{
			return result;
		}
		
		for(int i = cur*pageSize; i < all.size() && i < (cur + 1)*pageSize; i++)
			result.add(all.get(i));
		
		return result;
	}
}
