package com.xiuya.controller;

import java.util.ArrayList;
import java.util.List;

import com.xiuya.bean.Employee;
import com.xiuya.service.EmployeeService;
import com.xiuya.util.HibernateUtils;

public class EmployeeController {

	private EmployeeService employeeService;
	private static EmployeeController instance;
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	
	public static EmployeeController getInsance()
	{
		if(instance == null)
			return (EmployeeController) HibernateUtils.context.getBean("employeeController");
		else
			return instance;
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
	
	public List<Employee> selectEmployee(String idStr, String username, String pageStr)
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
			Integer page = null;
			if("".equals(pageStr))
				return this.employeeService.getEmployeeByName(username, 1);
			try {
				page = Integer.parseInt(pageStr);
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("input error!!!");
				return new ArrayList<Employee>();
			}
			return this.employeeService.getEmployeeByName(username, page);
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
