package com.xiuya.service;

import java.util.List;

import com.xiuya.bean.Employee;
import com.xiuya.dao.EmployeeDao;

public class EmployeeService {

	private EmployeeDao employeeDao;
	
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
	
	public void updateEmployee(int id, String name, String phone)
	{
		employeeDao.updateEmployee(id, name, phone);
	}
}
