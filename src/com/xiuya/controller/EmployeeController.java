package com.xiuya.controller;

import java.util.ArrayList;
import java.util.List;

import com.xiuya.bean.Employee;
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
		employeeService.add(name, phone);
	}
	
	public void deleteEmployee(String idStr)
	{
		try {
			Integer id = Integer.parseInt(idStr);
			employeeService.deleteById(id);
		}catch (Exception e) {
			System.out.println("error");
		}
		
	}
	
	public List<Employee> selectEmployee(String idStr, String username)
	{
		
		if(!"".equals(idStr))
		{
			Integer id = null;
			try {
				id = Integer.parseInt(idStr);
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("input error!!!");
				return new ArrayList<Employee>();
			}
			
			List<Employee> result = new ArrayList<>();
			Employee employee = this.employeeService.getEmployeeById(id);
			result.add(employee);
			return result;
		}else
		{
			return this.employeeService.getEmployeeByName(username);
		}
		
	}
	
	public void updateEmployee(String idStr, String name, String phone)
	{
		try{
			Integer id = Integer.parseInt(idStr);
			employeeService.updateEmployee(id, name, phone);
		}catch(Exception e)
		{
			System.out.println("error!!!");
		}
		
	}
	
}
