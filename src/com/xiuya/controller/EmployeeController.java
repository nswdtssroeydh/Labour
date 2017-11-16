package com.xiuya.controller;

import com.xiuya.bean.Employee;
import com.xiuya.dao.EmployeeDao;
import com.xiuya.service.EmployeeService;

public class EmployeeController {

	private EmployeeService employeeService;
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}


	public void addEmployee(String name, String phone)
	{
		Employee employee = new Employee();
		employee.setName(name);
		employee.setPhone(phone);
		employeeService.add(employee);
	}
}
