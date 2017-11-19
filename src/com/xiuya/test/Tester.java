package com.xiuya.test;

import com.xiuya.controller.EmployeeController;
import com.xiuya.util.HibeinateUtils;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeController employeeController = (EmployeeController) HibeinateUtils.context.getBean("employeeController");
//		employeeController.addEmployee("123456", "123");
		
		employeeController.deleteEmployee("123", "123");
	}

}
