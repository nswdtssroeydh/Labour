package com.xiuya.service;

import java.util.ArrayList;
import java.util.List;

import com.xiuya.bean.Tea;
import com.xiuya.dao.TeaDao;

public class TeaService {

	private TeaDao teaDao;

	public TeaDao getTeaDao() {
		return teaDao;
	}

	public void setTeaDao(TeaDao teaDao) {
		this.teaDao = teaDao;
	}
	
	public void addTea(Tea tea)
	{
		this.teaDao.add(tea);
	}
	
	public void deleteTea(Integer id)
	{
		this.teaDao.deleteById(id);
	}
	
	public List<Tea> getTea(Integer id)
	{
		List<Tea> teas = new ArrayList<>();
		if(id == null || id == 0)
		{
			teas =  this.teaDao.getAll();
		}else
		{
			Tea tea = this.teaDao.getById(id);
			teas.add(tea);
		}
		return teas;
	}
	
	public void updateTea(Integer id, String name, double price)
	{
		this.teaDao.updateById(id, name, price);
	}
	
}
