package com.xiuya.test;

import java.util.List;

import com.xiuya.bean.Employee;
import com.xiuya.controller.EmployeeController;
import com.xiuya.util.HibernateUtils;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeController employeeController = (EmployeeController) HibernateUtils.context.getBean("employeeController");
		List<Employee> employees = employeeController.selectEmployee("", "g"); 
		
		for(Employee e:employees)
		{
			System.out.println(e);
		}
		
//		employeeController.updateEmployee(5, "huangkai", "ggg");
		
		HibernateUtils.closeResources();
	}

}
