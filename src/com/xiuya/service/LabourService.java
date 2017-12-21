package com.xiuya.service;

import java.util.Date;
import java.util.List;

import com.xiuya.bean.Labour;
import com.xiuya.dao.LabourDao;

public class LabourService {
	
	private LabourDao labourDao;

	public LabourDao getLabourDao() {
		return labourDao;
	}

	public void setLabourDao(LabourDao labourDao) {
		this.labourDao = labourDao;
	}
	
	//增
	public void addLabour(int teaId, String teaName, int employeeId, String employeeName, double amount, double price, double salary, Date date) {
		
		Labour labour = new Labour();
		labour.setAmount(amount);
		labour.setDate(date);
		labour.setEmployeeId(employeeId);
		labour.setEmployeeName(employeeName);
		labour.setPrice(price);
		labour.setSalary(salary);
		labour.setTeaId(teaId);
		labour.setTeaName(teaName);
		
		labourDao.add(labour);
	}
	
	//删
	public void deleteLabour(int labourId)
	{
		labourDao.deleteLabour(labourId);
	}
	
	//改
	public void updateLabour(int id, double amount, double salary, Date date)
	{
		labourDao.update(id, amount, salary, date);
	}
	
	//查
	public List<Labour> getLabourByName(String employeeName, Date dateFrom, Date dateTo)
	{
		return labourDao.getLabourByName(employeeName, dateFrom, dateTo);
	}

}
