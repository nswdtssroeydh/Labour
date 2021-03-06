package com.xiuya.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.xiuya.bean.Labour;
import com.xiuya.service.LabourService;
import com.xiuya.util.HibernateUtils;

public class LabourController {

	private LabourService labourService;
	private static LabourController instance;

	public LabourService getLabourService() {
		return labourService;
	}

	public static LabourController getInstance()
	{
		if(null == instance)
			return (LabourController) HibernateUtils.context.getBean("labourController");
		else
			return instance;
	}
	
	public void setLabourService(LabourService labourService) {
		this.labourService = labourService;
	}

	public void addLabour(String teaIdStr, String teaName, String employeeIdStr, String employeeName, String amountStr, String priceStr, 
			String salaryStr, String year, String month, String day)
	{
		Integer teaId;
		Integer employeeId;
		Double amount;
		Double price;
		Double salary;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date;
		try {
			date = format.parse(year + "-" + month + "-" + day);
			teaId = Integer.parseInt(teaIdStr);
			employeeId = Integer.parseInt(employeeIdStr);
			amount = Double.parseDouble(amountStr);
			price = Double.parseDouble(priceStr);
			salary = Double.parseDouble(salaryStr);
			labourService.addLabour(teaId, teaName, employeeId, employeeName, amount, price, salary, date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("add error");
			e.printStackTrace();
		}
	}
	
//	public void addLabour(Integer employeeId, Integer teaId, Double amount, String year, String month, String day)
//	{
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		java.util.Date date;
//		try {
//			date = format.parse(year + "-" + month + "-" + day);
//			labourService.addLabour(teaId, employeeId, amount, date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			System.out.println("add error");
//			e.printStackTrace();
//		}
//	}
	
	public void deleteLabour(String idStr)
	{
		Integer id = Integer.parseInt(idStr);
		labourService.deleteLabour(id);
	}
	
	public void update(String idStr, String amountStr, String salaryStr, String year, String month, String day)
	{
		Integer id;
		Double amount = null;
		Double salary = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			id = Integer.parseInt(idStr);
			
			if(!"".equals(amountStr))
				amount = Double.parseDouble(amountStr);
			
			if(!"".equals(salaryStr))
				salary = Double.parseDouble(salaryStr);
			
			if(!"".equals(year) && !"".equals(month) && !"".equals(day))
				date = format.parse(year + "-" + month + "-" + day);
			labourService.updateLabour(id, amount, salary, date);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("update error");
			e.printStackTrace();
		}
	}
	
	public List<Labour> getLabourByName(String employeeName, String yearFrom, String monthFrom, String dayFrom, String yearTo, String monthTo, String dayTo, String pageStr)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateFrom;
		java.util.Date dateTo;
		int page = Integer.parseInt(pageStr);
		List<Labour> result = new ArrayList<Labour>();
		try {
			dateFrom = format.parse(yearFrom + "-" + monthFrom + "-" + dayFrom);
			dateTo = format.parse(yearTo + "-" + monthTo + "-" + dayTo);
			result = labourService.getLabourByName(employeeName, dateFrom, dateTo, page);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("query error");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int getLabourByNameCount(String employeeName, String yearFrom, String monthFrom, String dayFrom, String yearTo, String monthTo, String dayTo)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateFrom;
		java.util.Date dateTo;
		List<Labour> result = new ArrayList<Labour>();
		try {
			dateFrom = format.parse(yearFrom + "-" + monthFrom + "-" + dayFrom);
			dateTo = format.parse(yearTo + "-" + monthTo + "-" + dayTo);
			result = labourService.getLabourByName(employeeName, dateFrom, dateTo);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("query error");
			e.printStackTrace();
		}
		
		return result.size();
	}
	
	public double getLabourByNameCountSalary(String employeeName, String yearFrom, String monthFrom, String dayFrom, String yearTo, String monthTo, String dayTo)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateFrom;
		java.util.Date dateTo;
		List<Labour> result = new ArrayList<Labour>();
		double count = 0;
		try {
			dateFrom = format.parse(yearFrom + "-" + monthFrom + "-" + dayFrom);
			dateTo = format.parse(yearTo + "-" + monthTo + "-" + dayTo);
			result = labourService.getLabourByName(employeeName, dateFrom, dateTo);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("query error");
			e.printStackTrace();
		}
		
		for(int i = 0; i < result.size(); i++)
			count += result.get(i).getSalary();
		
		return count;
	}
	
}
