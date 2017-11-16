package com.xiuya.test;

import com.xiuya.controller.EmployeeController;
import com.xiuya.util.Tool;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeController employeeController = (EmployeeController) Tool.context.getBean("employeeController");
		employeeController.addEmployee("舸舸巫", "18581325132");
	}

}
