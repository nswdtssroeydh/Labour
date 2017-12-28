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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

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
	private JLabel label_1;
	private JTextField totalPageField;
	private JLabel label_2;
	
	private InputEmployeeWindow inputEmployeeWindow;
	private EmployeeController employeeController;
	private DefaultTableModel model;
	private int maxPage;
	private int curPage;
	
	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public InputEmployeeWindow getInputWindow() {
		return inputEmployeeWindow;
	}

	public void setInputWindow(InputEmployeeWindow inputWindow) {
		this.inputEmployeeWindow = inputWindow;
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
		this.employeeController = EmployeeController.getInsance();
		this.inputEmployeeWindow = InputEmployeeWindow.getInstance();
		this.inputEmployeeWindow.setEmployeeController(employeeController);
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
		
		String[] columnNames = {"blank",  "编号",  "姓名",  "电话"};
		
		String[][] data = {  
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""}
			};
		
		model = new DefaultTableModel(columnNames, 7);
		model.setDataVector(data, columnNames);
		
		employeePanel = new JPanel();
		employeePanel.setBounds(47, 148, 484, 154);
		panel.add(employeePanel);
		employeePanel.setLayout(null);
		
		employees = new JTable(model);
		employees.setBackground(new Color(204, 204, 204));
		employees.setSurrendersFocusOnKeystroke(true);
		employees.setFillsViewportHeight(true);
		employees.setCellSelectionEnabled(true);
		employees.setColumnSelectionAllowed(true);
		javax.swing.table.TableColumn tc =employees.getColumn("blank"); 
		tc.setPreferredWidth(1);
		employees.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		employees.setBounds(0, 0, 478, 121);
		employeePanel.add(employees);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(0, 0, 15, 121);
		employees.add(scrollBar);
		
		JButton nextPage = new JButton(">");
		nextPage.setBounds(435, 125, 43, 29);
		nextPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchNextButtonClicked(e);
			}
		});
		employeePanel.add(nextPage);
		
		prePage = new JButton("<");
		prePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchPreButtonClicked(e);
			}
		});
		prePage.setBounds(0, 125, 43, 29);
		employeePanel.add(prePage);
		
		pageButton = new JButton("转到");
		pageButton.setBounds(133, 126, 54, 29);
		pageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchSpecificButtonClicked(e);
			}
		});
		employeePanel.add(pageButton);
		
		JLabel lblNewLabel = new JLabel("第");
		lblNewLabel.setBounds(185, 131, 15, 16);
		employeePanel.add(lblNewLabel);
		
		JLabel label = new JLabel("页");
		label.setBounds(240, 131, 15, 16);
		employeePanel.add(label);
		
		pageField = new JTextField();
		pageField.setBounds(199, 125, 43, 28);
		setCurrentPage(1);
		employeePanel.add(pageField);
		pageField.setColumns(10);
		
		label_1 = new JLabel("页");
		label_1.setBounds(387, 131, 15, 16);
		employeePanel.add(label_1);
		
		totalPageField = new JTextField();
		totalPageField.setColumns(10);
		totalPageField.setBounds(346, 125, 43, 28);
		employeePanel.add(totalPageField);
		
		label_2 = new JLabel("共");
		label_2.setBounds(332, 131, 15, 16);
		employeePanel.add(label_2);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(255, 61, 134, 28);
		panel.add(searchTextField);
		searchTextField.setColumns(10);
		
		searchButton = new JButton("查找");
		searchButton.setBounds(404, 62, 75, 29);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchButtonClicked(e);
			}
		});
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
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteButtonClicked(e);
			}
		});
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
		textField.setText("                                编号                         姓名                          电话");
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
		inputEmployeeWindow = InputEmployeeWindow.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inputEmployeeWindow.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private int getMaxPageCount()
	{
		String name = searchTextField.getText();
		int allSize = employeeController.selectEmployee("", name).size();
		System.out.println("allSize:" + allSize);
		if(allSize%7 == 0)
			return allSize/7;
		else
			return allSize/7 + 1;
	}
	
	public void searchButtonClicked(ActionEvent e)
	{
		String name = searchTextField.getText();
		int totalPage = getMaxPageCount();
		setMaxPage(totalPage);
		totalPageField.setText(totalPage + "");
		showEmployeeOnTable(name, "");
		setCurrentPage(1);
	}
	
	public void searchNextButtonClicked(ActionEvent e)
	{
		String name = searchTextField.getText();
		int curPage = getCurPage();
		if(curPage >= getMaxPage())
			return;
		String pageStr = curPage + 1 + "";
		showEmployeeOnTable(name, pageStr);
		setCurrentPage(curPage + 1);
	}
	
	public void searchPreButtonClicked(ActionEvent e)
	{
		String name = searchTextField.getText();
		int curPage = getCurPage();
		if(curPage <= 1)
			return;
		String pageStr = curPage - 1 + "";
		showEmployeeOnTable(name, pageStr);
		setCurrentPage(curPage - 1);
	}
	
	public void searchSpecificButtonClicked(ActionEvent e)
	{
		String name = searchTextField.getText();
		int desPage = getCurrentPage();
		int maxPage = getMaxPage();
		if(desPage <= 0)
			return;
		if(desPage > maxPage)
		{
			setCurrentPage(maxPage);
			showEmployeeOnTable(name, maxPage + "");
			return;
		}
		String pageStr = desPage + "";
		showEmployeeOnTable(name, pageStr);
		setCurrentPage(desPage);
	}
	
	private void setCurrentPage(int page)
	{
		setCurPage(page);
		this.pageField.setText(page + "");
	}
	
	private int getCurrentPage()
	{
		String curPageStr = this.pageField.getText();
		Integer curPage = null;
		try {
			curPage = Integer.parseInt(curPageStr);
			return curPage;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "输入错误！", "错误！", JOptionPane.ERROR_MESSAGE);
		}
		
		return -1;
	}
	
	private void showEmployeeOnTable(String name, String pageStr)
	{
		String[][] data = new String[7][4];
		List<Employee> employees = employeeController.selectEmployee("", name, pageStr);
		for(int i = 0; i < employees.size(); i++)
		{
			Employee employee = employees.get(i);
			data[i][0] = "";
			data[i][1] = employee.getId() + "";
			data[i][2] = employee.getName();
			data[i][3] = employee.getPhone();
		}
		String[] columnNames = {"blank", "编号", "姓名", "电话"};
		getModel().setDataVector(data, columnNames);
	}
	
	private void deleteButtonClicked(ActionEvent e)
	{
		String number = JOptionPane.showInputDialog("输入要删除的员工号:");
		Integer id = null;
		try {
			id = Integer.parseInt(number);
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "输入错误！", "错误！", JOptionPane.ERROR_MESSAGE);
			return;
		}
		employeeController.deleteEmployee(number);
		searchButtonClicked(e);
	}
}
