package com.xiuya.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import com.xiuya.bean.Labour;
import com.xiuya.controller.LabourController;

import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;

public class LabourPage {

	private static LabourPage instance;
	private JFrame frame;
	private JTable labourTable;
	private JPanel labourPanel;
	private JButton prePage;
	private JButton pageButton;
	private JTextField curPageField;
	private JTextField nameField;
	private JButton searchButton;
	private JButton addButton;
	private JButton deleteButton;
	private JButton returnButton;
	private JTextField textField;
	private JTextField totalPageField;
	private JLabel label_3;
	private JTextField dateFrom;
	private JLabel label_4;
	private JTextField dateTo;
	private JTextField totalMoney;
	private JLabel label_5;
	private JLabel label_6;
	private JButton nextPage;
	
	private InputLabourWindow inputLabourWindow;
	private int maxPage;
	private int curPage;
	private LabourController labourController;
	private DefaultTableModel model;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LabourPage window = new LabourPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JTable getLabourTable() {
		return labourTable;
	}

	public void setLabourTable(JTable labours) {
		this.labourTable = labours;
	}

	/**
	 * Create the application.
	 */
	public LabourPage() {
		initialize();
	}
	
	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public InputLabourWindow getInputLabourWindow() {
		return inputLabourWindow;
	}

	public void setInputLabourWindow(InputLabourWindow inputLabourWindow) {
		this.inputLabourWindow = inputLabourWindow;
	}

	public static LabourPage getInstance()
	{
		if(null == instance) {
			return new LabourPage();
		}else
			return instance;
	}
	
	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		labourController = LabourController.getInstance();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 797, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		String[] columnNames = {"blank", "编号", "人员编号", "茶叶编号", "茶叶名称", "人员姓名", "数量", "茶叶单价", "总价", "日期"};
		Object[][] data = {  
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", "", ""}
			};  
		
		labourPanel = new JPanel();
		labourPanel.setBounds(67, 162, 670, 274);
		panel.add(labourPanel);
		labourPanel.setLayout(null);
		
		model = new DefaultTableModel(columnNames, 15);
		model.setDataVector(data, columnNames);
		
		
		labourTable = new JTable(model);
		labourTable.setBackground(new Color(204, 204, 204));
		labourTable.setSurrendersFocusOnKeystroke(true);
		labourTable.setFillsViewportHeight(true);
		labourTable.setCellSelectionEnabled(true);
		labourTable.setColumnSelectionAllowed(true);
		labourTable.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		labourTable.getColumn("blank").setMaxWidth(1);
		labourTable.getColumn("总价").setMinWidth(70);
		labourTable.getColumn("日期").setMinWidth(120);
//		javax.swing.table.TableColumn tc = labours.getColumn("blank"); 
//		tc.setWidth(1);
//		javax.swing.table.TableColumn tc2 = labours.getColumn("日期"); 
//		tc2.setWidth(200);
		labourTable.setBounds(17, 0, 647, 247);
		labourPanel.add(labourTable);
		
		nextPage = new JButton(">");
		nextPage.setBounds(621, 244, 43, 29);
		nextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextPageButtonClicked(e);
			}
		});
		labourPanel.add(nextPage);
		
		prePage = new JButton("<");
		prePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prePageButtonClicked(e);
			}
		});
		prePage.setBounds(17, 244, 43, 29);
		labourPanel.add(prePage);
		
		pageButton = new JButton("转到");
		pageButton.setBounds(232, 245, 54, 29);
		pageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specificPageButtonClicked(e);
			}
		});
		labourPanel.add(pageButton);
		
		JLabel lblNewLabel = new JLabel("第");
		lblNewLabel.setBounds(284, 250, 15, 16);
		labourPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("页");
		label.setBounds(339, 250, 15, 16);
		labourPanel.add(label);
		
		curPageField = new JTextField();
		curPageField.setBounds(298, 244, 43, 28);
		labourPanel.add(curPageField);
		curPageField.setColumns(10);
		
		JScrollBar scrollBar = new JScrollBar();
		labourPanel.add(scrollBar);
		scrollBar.setBounds(0, 0, 15, 236);
		
		JLabel label_1 = new JLabel("共");
		label_1.setBounds(463, 251, 15, 16);
		labourPanel.add(label_1);
		
		JLabel label_2 = new JLabel("页");
		label_2.setBounds(518, 251, 15, 16);
		labourPanel.add(label_2);
		
		totalPageField = new JTextField();
		totalPageField.setColumns(10);
		totalPageField.setBounds(477, 245, 43, 28);
		labourPanel.add(totalPageField);
		
		nameField = new JTextField();
		nameField.setBounds(343, 92, 134, 28);
		panel.add(nameField);
		nameField.setColumns(10);
		
		searchButton = new JButton("查找");
		searchButton.setBounds(492, 93, 75, 29);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchButtonClicked(e);
			}
		});
		panel.add(searchButton);
		
		addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addButtonClicked(e);
			}
		});
		addButton.setBounds(182, 93, 75, 29);
		panel.add(addButton);
		
		deleteButton = new JButton("删除");
		deleteButton.setBounds(256, 93, 75, 29);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteButtonClicked(e);
			}
		});
		panel.add(deleteButton);
		
		returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnButtonClicked(e);
			}
		});
		returnButton.setBounds(6, 6, 75, 29);
		panel.add(returnButton);
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				returnButtonClicked(e);
			}
		});
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		textField.setBackground(UIManager.getColor("Button.background"));
		textField.setText("     编号      人员号     茶叶号     茶叶名        姓名        数量          单价          总价                日期");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(87, 134, 640, 28);
		panel.add(textField);
		
		label_3 = new JLabel("从");
		label_3.setBounds(242, 65, 15, 16);
		panel.add(label_3);
		
		dateFrom = new JTextField();
		dateFrom.setColumns(10);
		dateFrom.setBounds(272, 62, 82, 22);
		panel.add(dateFrom);
		
		label_4 = new JLabel("到");
		label_4.setBounds(368, 64, 15, 16);
		panel.add(label_4);
		
		dateTo = new JTextField();
		dateTo.setColumns(10);
		dateTo.setBounds(389, 62, 88, 22);
		panel.add(dateTo);
		
		totalMoney = new JTextField();
		totalMoney.setColumns(10);
		totalMoney.setBounds(411, 460, 134, 28);
		panel.add(totalMoney);
		
		label_5 = new JLabel("总共");
		label_5.setBounds(378, 466, 26, 16);
		panel.add(label_5);
		
		label_6 = new JLabel("元");
		label_6.setBounds(546, 466, 15, 16);
		panel.add(label_6);
	}
	
	private void setMaxPageOnPanel(Integer maxPage)
	{
		this.setMaxPage(maxPage);
		this.totalPageField.setText(maxPage + "");
	}
	
	private void setCurrentPage(Integer curPage)
	{
		this.setCurPage(curPage);
		this.curPageField.setText(curPage + "");
	}
	
	private void setCountSalary(double count)
	{
		this.totalMoney.setText(count + "");
	}
	
	private void showLabourOnTable(String employeeName, String yearFrom, String monthFrom, String dayFrom, String yearTo, String monthTo, String dayTo, String pageStr)
	{
		String[][] data = new String[15][10];
		List<Labour> labours = labourController.getLabourByName(employeeName, yearFrom, monthFrom, dayFrom, yearTo, monthTo, dayTo, pageStr);
		for(int i = 0; i < labours.size() && i < 15; i++)
		{
			Labour labour = labours.get(i);
			data[i][0] = "";
			data[i][1] = labour.getId() + "";
			data[i][2] = labour.getEmployeeId() + "";
			data[i][3] = labour.getTeaId() + "";
			data[i][4] = labour.getTeaName() + "";
			data[i][5] = labour.getEmployeeName() + "";
			data[i][6] = labour.getAmount() + "";
			data[i][7] = labour.getPrice() + "";
			data[i][8] = labour.getSalary() + "";
			data[i][9] = labour.getDate().toLocaleString().split(" ")[0];
		}
		String[] columnNames = {"blank", "编号", "人员编号", "茶叶编号", "茶叶名称", "人员姓名", "数量", "茶叶单价", "总价", "日期"};
		getModel().setDataVector(data, columnNames);
		labourTable.getColumn("blank").setMaxWidth(1);
		labourTable.getColumn("总价").setMinWidth(70);
		labourTable.getColumn("日期").setMinWidth(120);
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
		this.labourController.deleteLabour(id + "");
		searchButtonClicked(e);
	}
	
	public void addButtonClicked(ActionEvent e)
	{
		inputLabourWindow = InputLabourWindow.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inputLabourWindow.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		searchButtonClicked(e);
	}
	
	private void prePageButtonClicked(ActionEvent e)
	{
		int cur = getCurPage();
		if(cur <= 1)
			return;
		
		setCurrentPage(cur - 1);
		String name = this.nameField.getText();
		int[] list = getDateList();
		if(list.length == 1)
			return;
		
		int fromYear = list[0];
		int fromMonth = list[1];
		int fromDay = list[2];
		int toYear = list[3];
		int toMonth = list[4];
		int toDay = list[5];
		
		showLabourOnTable(name, fromYear + "", fromMonth + "", fromDay + "" , toYear + "", toMonth + "", toDay + "", (cur - 1) + "");
	}
	
	private void nextPageButtonClicked(ActionEvent e)
	{
		int cur = getCurPage();
		int max = getMaxPage();
		if(cur >= max)
			return;
		setCurrentPage(cur + 1);
		String name = this.nameField.getText();
		int[] list = getDateList();
		if(list.length == 1)
			return;
		
		int fromYear = list[0];
		int fromMonth = list[1];
		int fromDay = list[2];
		int toYear = list[3];
		int toMonth = list[4];
		int toDay = list[5];
		
		showLabourOnTable(name, fromYear + "", fromMonth + "", fromDay + "" , toYear + "", toMonth + "", toDay + "", (cur + 1) + "");
	}
	
	private void specificPageButtonClicked(ActionEvent e)
	{
		String desStr = this.curPageField.getText();
		Integer des = null;
		String name = this.nameField.getText();
		int[] list = getDateList();
		if(list.length == 1)
			return;
		int fromYear = list[0];
		int fromMonth = list[1];
		int fromDay = list[2];
		int toYear = list[3];
		int toMonth = list[4];
		int toDay = list[5];
		
		try {
			des = Integer.parseInt(desStr);
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "页码输入错误！", "错误！", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int max = getMaxPage();
		if(des >= max)
		{
			setCurrentPage(max);
			showLabourOnTable(name, fromYear + "", fromMonth + "", fromDay + "" , toYear + "", toMonth + "", toDay + "", max + "");
		}else if(des <= 1)
		{
			setCurrentPage(1);
			showLabourOnTable(name, fromYear + "", fromMonth + "", fromDay + "" , toYear + "", toMonth + "", toDay + "", 1 + "");
		}else
		{
			setCurrentPage(des);
			showLabourOnTable(name, fromYear + "", fromMonth + "", fromDay + "" , toYear + "", toMonth + "", toDay + "", des + "");
		}
	}
	
	private void setCurrentPage(int page)
	{
		this.setCurPage(page);
		this.curPageField.setText(page + "");
	}
	
	private void searchButtonClicked(ActionEvent e)
	{
		String name = this.nameField.getText();
		int[] list = getDateList();
		if(list.length == 1)
			return;
		
		int fromYear = list[0];
		int fromMonth = list[1];
		int fromDay = list[2];
		int toYear = list[3];
		int toMonth = list[4];
		int toDay = list[5];
		
		int maxPage = this.labourController.getLabourByNameCount(name, fromYear + "", fromMonth + "", fromDay + "" , toYear + "", toMonth + "", toDay + "");
		double countSalary = this.labourController.getLabourByNameCountSalary(name, fromYear + "", fromMonth + "", fromDay + "" , toYear + "", toMonth + "", toDay + "");
		setMaxPageOnPanel(maxPage/15 + 1);
		setCurrentPage(1);
		showLabourOnTable(name, fromYear + "", fromMonth + "", fromDay + "" , toYear + "", toMonth + "", toDay + "", "1");
		setCountSalary(countSalary);
	}
	
	private int[] getDateList()
	{
		String dateFromStr = this.dateFrom.getText();
		String dateToStr = this.dateTo.getText();
		Integer fromYear = 1900;
		Integer fromMonth = 1;
		Integer fromDay = 1;
		Integer toYear = 2200;
		Integer toMonth = 1;
		Integer toDay = 1;
		
		if(!"".equals(dateFromStr))
		{
			String[] tokens = dateFromStr.split("-");
			if(tokens.length != 3)
			{
				JOptionPane.showMessageDialog(null, "日期输入错误！请输入=>   年-月-日", "错误！", JOptionPane.ERROR_MESSAGE);
				return new int[1];
			}
			try {
				fromYear = Integer.parseInt(tokens[0]);
				fromMonth = Integer.parseInt(tokens[1]);
				fromDay = Integer.parseInt(tokens[2]);
				if(fromYear <= 1900 || fromYear >= 2200 || fromMonth <= 0 || fromMonth >= 13 || fromDay <= 0 || fromDay >= 32)
				{
					JOptionPane.showMessageDialog(null, "开始日期超出范围！", "错误！", JOptionPane.ERROR_MESSAGE);
					return new int[1];
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "日期输入错误！", "错误！", JOptionPane.ERROR_MESSAGE);
				return new int[1];
			}
		}
		
		if(!"".equals(dateToStr))
		{
			String[] tokens = dateToStr.split("-");
			if(tokens.length != 3)
			{
				JOptionPane.showMessageDialog(null, "日期输入错误！请输入=>   年-月-日", "错误！", JOptionPane.ERROR_MESSAGE);
				return new int[1];
			}
			try {
				toYear = Integer.parseInt(tokens[0]);
				toMonth = Integer.parseInt(tokens[1]);
				toDay = Integer.parseInt(tokens[2]);
				if(toYear <= 1900 || toYear >= 2200 || toMonth <= 0 || toMonth >= 13 || toDay <= 0 || toDay >= 32)
				{
					JOptionPane.showMessageDialog(null, "结束日期超出范围！", "错误！", JOptionPane.ERROR_MESSAGE);
					return new int[1];
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "日期输入错误！", "错误！", JOptionPane.ERROR_MESSAGE);
				return new int[1];
			}
		}
		
		int[] result = new int[6];
		result[0] = fromYear;
		result[1] = fromMonth;
		result[2] = fromDay;
		result[3] = toYear;
		result[4] = toMonth;
		result[5] = toDay;
		
		return result;
	}
	
}
