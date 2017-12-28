package com.xiuya.service;

import java.util.ArrayList;
import java.util.List;

import com.xiuya.bean.Employee;
import com.xiuya.bean.Tea;
import com.xiuya.dao.TeaDao;

public class TeaService {

	private TeaDao teaDao;
	private final int pageSize = 7;

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
	
	public List<Tea> getTeaByName(String name, Integer page)
	{
		if(name == null || "".equals(name))
		{
			return pageBy(this.teaDao.getAll(), page);
		}else
		{
			List<Tea> teas = this.teaDao.selectTeaByName(name);
			if(page == null || page == 0)
				return teas;
			return pageBy(teas, page);
		}
	}
	
	public List<Tea> pageBy(List<Tea> all, Integer page)
	{
		List<Tea> result = new ArrayList<>();
		int cur = page - 1;
		if(all.size()/pageSize < cur)
		{
			return result;
		}
		
		for(int i = cur*pageSize; i < all.size() && i < (cur + 1)*pageSize; i++)
			result.add(all.get(i));
		
		return result;
	}
	
	public void updateTea(Integer id, String name, double price)
	{
		this.teaDao.updateById(id, name, price);
	}
	
}
