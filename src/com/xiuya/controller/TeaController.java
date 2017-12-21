package com.xiuya.controller;

import java.util.List;

import com.xiuya.bean.Tea;
import com.xiuya.service.TeaService;

public class TeaController {

	private TeaService teaService;

	public TeaService getTeaService() {
		return teaService;
	}

	public void setTeaService(TeaService teaService) {
		this.teaService = teaService;
	}
	
	public void addTea(String name, String priceStr)
	{
		Tea tea = new Tea();
		tea.setName(name);
		double price = Double.parseDouble(priceStr);
		tea.setPrice(price);
		this.teaService.addTea(tea);
	}
	
	public void deleteTea(String idStr)
	{
		Integer id = Integer.parseInt(idStr);
		this.teaService.deleteTea(id);
	}
	
	public void updateTea(String idStr, String name, String priceStr)
	{
		if("".equals(idStr))
		{
			System.out.println("id can not be null!!!");
			return;
		}
		double price  = Double.parseDouble(priceStr);
		Integer id = Integer.parseInt(idStr);
		this.teaService.updateTea(id, name, price);
	}
	
	public List<Tea> getTea(String idStr)
	{
		if(!"".equals(idStr))
		{
			Integer id = Integer.parseInt(idStr);
			return this.teaService.getTea(id);
		}else
		{
			return this.teaService.getTea(null);
		}
	}
	
}
