package com.xiuya.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import com.xiuya.bean.Employee;
import com.xiuya.bean.Tea;
import com.xiuya.controller.EmployeeController;
import com.xiuya.controller.LabourController;
import com.xiuya.controller.TeaController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InputLabourWindow {

	private JFrame frame;
	private JTable employeeTable;
	private DefaultTableModel employeeTableModel;
	private JTable teaTable;
	private DefaultTableModel teaTableModel;
	private JButton addButton;
	private EmployeeController employeeController;
	private LabourController labourController;
	private TeaController teaController;
	private JLabel label;
	private JTextField employeeNameField;
	private JButton searchEmployeeButton;
	private JLabel label_1;
	private JTextField teaNameField;
	private JButton searchTeaButton;
	private JLabel label_2;
	private JTextField employeeIdField;
	private JLabel label_3;
	private JTextField teaIdField;
	private JTextField dateField;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField amountField;
	private JTextField textField;
	private JTextField textField_1;
	private static InputLabourWindow instance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputLabourWindow window = new InputLabourWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static InputLabourWindow getInstance()
	{
		if(null == instance)
			return new InputLabourWindow();
		else
			return instance;
	}
	
	public LabourController getLabourController() {
		return labourController;
	}

	public void setLabourController(LabourController labourController) {
		this.labourController = labourController;
	}

	public DefaultTableModel getEmployeeTableModel() {
		return employeeTableModel;
	}

	public void setEmployeeTableModel(DefaultTableModel employeeTableModel) {
		this.employeeTableModel = employeeTableModel;
	}

	public DefaultTableModel getTeaTableModel() {
		return teaTableModel;
	}

	public void setTeaTableModel(DefaultTableModel teaTableModel) {
		this.teaTableModel = teaTableModel;
	}

	public TeaController getTeaController() {
		return teaController;
	}

	public void setTeaController(TeaController teaController) {
		this.teaController = teaController;
	}
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	/**
	 * Create the application.
	 */
	public InputLabourWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.employeeController = EmployeeController.getInsance();
		this.teaController = TeaController.getInstance();
		this.labourController = LabourController.getInstance();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 634, 352);
		frame.getContentPane().setLayout(null);
		
		String[][] data = {  
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""}
			};
		String[] columnNames = {"blank",  "编号",  "姓名",  "电话"};
		employeeTableModel = new DefaultTableModel(columnNames, 7);
		employeeTableModel.setDataVector(data, columnNames);
		
		String[][] data2 = {  
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""}
			};
		String[] columnNames2 = {"blank",  "编号",  "茶叶名",  "单价"};
		teaTableModel = new DefaultTableModel(columnNames2, 7);
		teaTableModel.setDataVector(data2, columnNames2);
		
		employeeTable = new JTable(employeeTableModel);
		employeeTable.setBackground(new Color(204, 204, 204));
		employeeTable.setSurrendersFocusOnKeystroke(true);
		employeeTable.setFillsViewportHeight(true);
		employeeTable.setCellSelectionEnabled(true);
		employeeTable.setColumnSelectionAllowed(true);
		javax.swing.table.TableColumn tc = employeeTable.getColumn("blank"); 
		tc.setPreferredWidth(1);
		employeeTable.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		employeeTable.setBounds(18, 74, 265, 121);
		
//		employeeTable.setBounds(104, 116, 1, 1);
		frame.getContentPane().add(employeeTable);
		
		teaTable = new JTable(teaTableModel);
		teaTable.setSurrendersFocusOnKeystroke(true);
		teaTable.setFillsViewportHeight(true);
		teaTable.setColumnSelectionAllowed(true);
		teaTable.setCellSelectionEnabled(true);
		teaTable.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		teaTable.setBackground(new Color(204, 204, 204));
		teaTable.setBounds(334, 74, 265, 121);
		frame.getContentPane().add(teaTable);
		
		addButton = new JButton("添加");
		addButton.setBounds(249, 280, 117, 29);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addButtonClicked(e);
			}
		});
		frame.getContentPane().add(addButton);
		
		label = new JLabel("员工姓名");
		label.setBounds(39, 19, 61, 16);
		frame.getContentPane().add(label);
		
		employeeNameField = new JTextField();
		employeeNameField.setBounds(99, 13, 72, 28);
		frame.getContentPane().add(employeeNameField);
		employeeNameField.setColumns(10);
		
		searchEmployeeButton = new JButton("查找");
		searchEmployeeButton.setBounds(179, 14, 86, 29);
		searchEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployeeButtonClicked(e);
			}
		});
		frame.getContentPane().add(searchEmployeeButton);
		
		label_1 = new JLabel("茶叶名");
		label_1.setBounds(351, 17, 61, 16);
		frame.getContentPane().add(label_1);
		
		teaNameField = new JTextField();
		teaNameField.setColumns(10);
		teaNameField.setBounds(411, 11, 72, 28);
		frame.getContentPane().add(teaNameField);
		
		searchTeaButton = new JButton("查找");
		searchTeaButton.setBounds(491, 12, 86, 29);
		searchTeaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTeaButtonClicked(e);
			}
		});
		frame.getContentPane().add(searchTeaButton);
		
		label_2 = new JLabel("员工编号");
		label_2.setBounds(18, 234, 61, 16);
		frame.getContentPane().add(label_2);
		
		employeeIdField = new JTextField();
		employeeIdField.setColumns(10);
		employeeIdField.setBounds(71, 228, 72, 28);
		frame.getContentPane().add(employeeIdField);
		
		label_3 = new JLabel("茶叶编号");
		label_3.setBounds(162, 234, 61, 16);
		frame.getContentPane().add(label_3);
		
		teaIdField = new JTextField();
		teaIdField.setColumns(10);
		teaIdField.setBounds(213, 228, 72, 28);
		frame.getContentPane().add(teaIdField);
		
		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(493, 228, 106, 28);
		frame.getContentPane().add(dateField);
		
		label_4 = new JLabel("日期");
		label_4.setBounds(460, 234, 34, 16);
		frame.getContentPane().add(label_4);
		
		label_5 = new JLabel("数量（重量）");
		label_5.setBounds(305, 234, 80, 16);
		frame.getContentPane().add(label_5);
		
		amountField = new JTextField();
		amountField.setColumns(10);
		amountField.setBounds(376, 228, 72, 28);
		frame.getContentPane().add(amountField);
		
		textField = new JTextField();
		textField.setText("                    编号           姓名        电话");
		textField.setColumns(10);
		textField.setBounds(18, 47, 265, 28);
		frame.getContentPane().add(textField);
		
		JButton returnButton = new JButton("返回");
		returnButton.setBounds(18, 280, 86, 29);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnButtonClicked(e);
			}
		});
		frame.getContentPane().add(returnButton);
		
		textField_1 = new JTextField();
		textField_1.setText("                    编号         茶叶名        单价");
		textField_1.setColumns(10);
		textField_1.setBounds(334, 47, 265, 28);
		frame.getContentPane().add(textField_1);
	}
	
	private void showEmployeeOnTable(String name)
	{
		String[][] data = new String[7][4];
		List<Employee> employees = employeeController.selectEmployee("", name, "1");
		System.out.println(employees.size());
		for(int i = 0; i < employees.size() && i < 7; i++)
		{
			Employee employee = employees.get(i);
			data[i][0] = "";
			data[i][1] = employee.getId() + "";
			data[i][2] = employee.getName();
			data[i][3] = employee.getPhone();
			System.out.println(employee.toString());
		}
		String[] columnNames = {"blank", "编号", "姓名", "电话"};
		getEmployeeTableModel().setDataVector(data, columnNames);
	}

	private void showTeaOnTable(String name)
	{
		String[][] data = new String[7][4];
		List<Tea> teas = teaController.getTea(name, "1");
		for(int i = 0; i < teas.size() && i < 7; i++)
		{
			Tea tea = teas.get(i);
			data[i][0] = "";
			data[i][1] = tea.getId() + "";
			data[i][2] = tea.getName();
			data[i][3] = tea.getPrice() + "";
		}
		String[] columnNames = {"blank", "编号", "茶叶名", "单价"};
		getTeaTableModel().setDataVector(data, columnNames);
	}
	
	private void searchEmployeeButtonClicked(ActionEvent e)
	{
		String name = this.employeeNameField.getText();
		showEmployeeOnTable(name);
	}
	
	private void searchTeaButtonClicked(ActionEvent e)
	{
		String name = this.teaNameField.getText();
		showTeaOnTable(name);
	}
	
	private void addButtonClicked(ActionEvent e)
	{
		String employeeId = this.employeeIdField.getText();
		String teaId = this.teaIdField.getText();
		
//		Employee employee = this.employeeController.selectEmployee(employeeId, "").get(0);
//		if(null == employee)
//		{
//			
//		}
		
		List<Employee> employees = this.employeeController.selectEmployee(employeeId, "");
		System.out.println("size:" + employees.size() + "," + employees);
		if(employees.get(0) == null || "".equals(employeeId))
		{
			JOptionPane.showMessageDialog(null, "用户编号输入错误！", "错误！", JOptionPane.ERROR_MESSAGE);
			return;
		}
		List<Tea> teas = this.teaController.getTea(teaId);
		if(teas.get(0) == null || "".equals(teaId))
		{
			JOptionPane.showMessageDialog(null, "茶叶编号输入错误！", "错误！", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String amountStr = this.amountField.getText();
		Double amount = null;
		try {
			amount = Double.parseDouble(amountStr);
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "数量输入错误！", "错误！", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String dateStr = this.dateField.getText();
		if(!"".equals(dateStr))
		{
			String[] tokens = dateStr.split("-");
			if(tokens.length != 3)
			{
				JOptionPane.showMessageDialog(null, "日期输入错误！请输入=>   年-月-日", "错误！", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				int year = Integer.parseInt(tokens[0]);
				int month = Integer.parseInt(tokens[1]);
				int day = Integer.parseInt(tokens[2]);
				System.out.println(year + "," + month + "," + day);
				if(year <= 1900 || year >= 2200 || month <= 0 || month >= 13 || day <= 0 || day >= 32)
				{
					JOptionPane.showMessageDialog(null, "日期超出范围！", "错误！", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Employee employee = employees.get(0);
				Tea tea = teas.get(0);
				double price = tea.getPrice();
				double salary = price*amount;
				this.labourController.addLabour(teaId, tea.getName(), employeeId, employee.getName(), amountStr, tea.getPrice() + "", salary + "", tokens[0], tokens[1], tokens[2]);
				JOptionPane.showMessageDialog(null, "添加成功！", "成功！", JOptionPane.ERROR_MESSAGE);
			}catch(Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "日期输入错误！", "错误！", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else
		{
			Employee employee = employees.get(0);
			Date date = new Date();
			System.out.println(date.getYear());
			System.out.println(date.getMonth());
			System.out.println(date.getDate());
			int year = date.getYear() + 1900;
			int month = date.getMonth() + 1;
			int day = date.getDate();
			Tea tea = teas.get(0);
			double price = tea.getPrice();
			double salary = price*amount;
			this.labourController.addLabour(teaId, tea.getName(), employeeId, employee.getName(), amountStr, tea.getPrice() + "", salary + "", year + "", month + "", day + "");
			JOptionPane.showMessageDialog(null, "添加成功！", "成功！", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void returnButtonClicked(ActionEvent e)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getFrame().setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
