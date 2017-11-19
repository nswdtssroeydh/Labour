package com.xiuya.service;

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

	public void add(Employee employee)
	{
		employeeDao.add(employee);
	}
	
	public void delete(Employee employee)
	{
		employeeDao.delete(employee);
	}
}
