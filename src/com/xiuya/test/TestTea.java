package com.xiuya.test;

import java.util.List;
import com.xiuya.bean.Tea;
import com.xiuya.controller.TeaController;
import com.xiuya.util.HibernateUtils;

public class TestTea {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TeaController controller = (TeaController) HibernateUtils.context.getBean("teaController");
		
		controller.deleteTea("3");
		
		List<Tea> teas = controller.getTea("");
		
		for(Tea tea:teas)
		{
			System.out.println(tea);
		}
		HibernateUtils.closeResources();
	}

}
