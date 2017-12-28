package com.xiuya.controller;

import java.util.ArrayList;
import java.util.List;

import com.xiuya.bean.Tea;
import com.xiuya.service.TeaService;
import com.xiuya.util.HibernateUtils;

public class TeaController {

	private TeaService teaService;
	private static TeaController instance;

	public TeaService getTeaService() {
		return teaService;
	}

	public void setTeaService(TeaService teaService) {
		this.teaService = teaService;
	}

	public static TeaController getInstance() {
		if (null == instance)
			return (TeaController) HibernateUtils.context.getBean("teaController");
		else
			return instance;
	}

	public boolean addTea(String name, String priceStr) {
		Tea tea = new Tea();
		tea.setName(name);
		try {
			double price = Double.parseDouble(priceStr);
			tea.setPrice(price);
			this.teaService.addTea(tea);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void deleteTea(String idStr) {
		Integer id = Integer.parseInt(idStr);
		this.teaService.deleteTea(id);
	}

	public void updateTea(String idStr, String name, String priceStr) {
		if ("".equals(idStr)) {
			System.out.println("id can not be null!!!");
			return;
		}
		double price = Double.parseDouble(priceStr);
		Integer id = Integer.parseInt(idStr);
		this.teaService.updateTea(id, name, price);
	}

	public List<Tea> getTea(String idStr) {
		if (!"".equals(idStr)) {
			try {
				Integer id = Integer.parseInt(idStr);
				return this.teaService.getTea(id);
			} catch (Exception e) {
				return new ArrayList<Tea>();
			}
		} else {
			return this.teaService.getTea(null);
		}
	}

	public List<Tea> getTea(String name, String pageStr) {
		if ("".equals(pageStr))
			return this.teaService.getTea(null);
		int page = Integer.parseInt(pageStr);
		return this.teaService.getTeaByName(name, page);
	}

}
