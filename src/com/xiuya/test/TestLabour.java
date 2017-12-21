package com.xiuya.test;

import java.util.List;

import com.xiuya.bean.Labour;
import com.xiuya.controller.LabourController;
import com.xiuya.util.HibernateUtils;

public class TestLabour {

	public static void main(String[] args) {

		LabourController labourController = (LabourController) HibernateUtils.context.getBean("labourController");
		
		labourController.update("10", "11", "1320", "", "", "");
		
		List<Labour> labours = labourController.getLabourByName("", "2017", "6", "1", "2017", "12", "20");
		for(Labour labour:labours)
		{
			System.out.println(labour);
		}
		
		HibernateUtils.closeResources();
	}

}
