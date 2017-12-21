package com.xiuya.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.xiuya.bean.Employee;
import com.xiuya.controller.EmployeeController;
import com.xiuya.util.HibernateUtils;

import java.awt.Color;
import java.awt.Dialog;

import javax.swing.UIManager;

public class EmployeePage {

	private static EmployeePage instance;
	private JFrame frame;
	private JTable employees;
	private JPanel employeePanel;
	private JButton prePage;
	private JButton pageButton;
	private JTextField pageField;
	private JTextField searchTextField;
	private JButton searchButton;
	private JButton addButton;
	private JButton deleteButton;
	private JButton returnButton;
	private JTextField textField;
	private InputEmployeeWindow inputWindow;
	private EmployeeController employeeController;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public InputEmployeeWindow getInputWindow() {
		return inputWindow;
	}

	public void setInputWindow(InputEmployeeWindow inputWindow) {
		this.inputWindow = inputWindow;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeePage window = new EmployeePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private EmployeePage() {
		this.employeeController = (EmployeeController) HibernateUtils.context.getBean("employeeController");
		this.inputWindow = InputEmployeeWindow.getInstance();
		this.inputWindow.setEmployeeController(employeeController);
		System.out.println("employeeController page:" + employeeController);
		initialize();
	}
	
	public static EmployeePage getInstance()
	{
		if(instance == null) {
			instance = new EmployeePage();
			System.out.println("page created");
		}
		return instance;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 615, 420);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		String[] columnNames = {"编号",  "姓名",  "电话"};
		Object[][] data = {  
			    {"", "", ""},
			    {"", "", ""},
			    {"", "", ""},
			    {"", "", ""},
			    {"", "", ""},
			    {"", "", ""},
			    {"", "", ""}
			};  
		
		employeePanel = new JPanel();
		employeePanel.setBounds(47, 148, 484, 154);
		panel.add(employeePanel);
		employeePanel.setLayout(null);
		
		employees = new JTable(data, columnNames);
		employees.setBackground(new Color(204, 204, 204));
		employees.setSurrendersFocusOnKeystroke(true);
		employees.setFillsViewportHeight(true);
		employees.setCellSelectionEnabled(true);
		employees.setColumnSelectionAllowed(true);
		employees.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		employees.setBounds(0, 0, 478, 121);
		employeePanel.add(employees);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(0, 0, 15, 121);
		employees.add(scrollBar);
		
		JButton nextPage = new JButton(">");
		nextPage.setBounds(435, 125, 43, 29);
		employeePanel.add(nextPage);
		
		prePage = new JButton("<");
		prePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		prePage.setBounds(0, 125, 43, 29);
		employeePanel.add(prePage);
		
		pageButton = new JButton("转到");
		pageButton.setBounds(177, 126, 54, 29);
		employeePanel.add(pageButton);
		
		JLabel lblNewLabel = new JLabel("第");
		lblNewLabel.setBounds(229, 131, 15, 16);
		employeePanel.add(lblNewLabel);
		
		JLabel label = new JLabel("页");
		label.setBounds(284, 131, 15, 16);
		employeePanel.add(label);
		
		pageField = new JTextField();
		pageField.setBounds(243, 125, 43, 28);
		employeePanel.add(pageField);
		pageField.setColumns(10);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(255, 61, 134, 28);
		panel.add(searchTextField);
		searchTextField.setColumns(10);
		
		searchButton = new JButton("查找");
		searchButton.setBounds(404, 62, 75, 29);
		panel.add(searchButton);
		
		addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addButtonClick(e);
			}
		});
		addButton.setBounds(94, 62, 75, 29);
		panel.add(addButton);
		
		deleteButton = new JButton("删除");
		deleteButton.setBounds(168, 62, 75, 29);
		panel.add(deleteButton);
		
		returnButton = new JButton("返回");
		returnButton.setBounds(6, 6, 75, 29);
		panel.add(returnButton);
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				returnButtonClicked(e);
			}
		});
		
		textField = new JTextField();
		textField.setBackground(UIManager.getColor("Button.background"));
		textField.setText("                编号                                  姓名                               电话");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(47, 119, 484, 28);
		panel.add(textField);
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
	
	public void addButtonClick(ActionEvent e)
	{
		System.out.println("add button");
		inputWindow = InputEmployeeWindow.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inputWindow.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
//	public void addEmployee(String name, String phone)
//	{
//		inputWindow = new InputWindow();
//		this.employeeController.addEmployee(name, phone);
//	}
//	
//	public void deleteEmployee(String idStr)
//	{
//		this.employeeController.deleteEmployee(idStr);
//	}
//	
//	public void updateEmployee(String idStr, String name, String phone)
//	{
//		this.employeeController.updateEmployee(idStr, name, phone);
//	}
//	
//	public List<Employee> search(String name)
//	{
//		return this.employeeController.selectEmployee("", name);
//	}
}
