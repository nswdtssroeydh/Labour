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
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class TeaPage {

	private static TeaPage instance;
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
					TeaPage window = new TeaPage();
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
	public TeaPage() {
		initialize();
	}
	
	public static TeaPage getInstance()
	{
		if(null == instance) {
			return new TeaPage();
		}else
			return instance;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		String[] columnNames = {"编号",  "名称",  "价格"};
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
		employeePanel.setBounds(25, 91, 395, 154);
		panel.add(employeePanel);
		employeePanel.setLayout(null);
		
		employees = new JTable(data, columnNames);
		employees.setBackground(new Color(204, 204, 204));
		employees.setSurrendersFocusOnKeystroke(true);
		employees.setFillsViewportHeight(true);
		employees.setCellSelectionEnabled(true);
		employees.setColumnSelectionAllowed(true);
		employees.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		employees.setBounds(17, 0, 378, 121);
		employeePanel.add(employees);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(0, 0, 15, 121);
		employees.add(scrollBar);
		
		JButton nextPage = new JButton(">");
		nextPage.setBounds(346, 125, 43, 29);
		employeePanel.add(nextPage);
		
		prePage = new JButton("<");
		prePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		prePage.setBounds(0, 125, 43, 29);
		employeePanel.add(prePage);
		
		pageButton = new JButton("转到");
		pageButton.setBounds(118, 125, 54, 29);
		employeePanel.add(pageButton);
		
		JLabel lblNewLabel = new JLabel("第");
		lblNewLabel.setBounds(170, 130, 15, 16);
		employeePanel.add(lblNewLabel);
		
		JLabel label = new JLabel("页");
		label.setBounds(225, 130, 15, 16);
		employeePanel.add(label);
		
		pageField = new JTextField();
		pageField.setBounds(184, 124, 43, 28);
		employeePanel.add(pageField);
		pageField.setColumns(10);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(197, 36, 134, 28);
		panel.add(searchTextField);
		searchTextField.setColumns(10);
		
		searchButton = new JButton("查找");
		searchButton.setBounds(346, 37, 75, 29);
		panel.add(searchButton);
		
		addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addButton.setBounds(36, 37, 75, 29);
		panel.add(addButton);
		
		deleteButton = new JButton("删除");
		deleteButton.setBounds(110, 37, 75, 29);
		panel.add(deleteButton);
		
		returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		textField.setBackground(UIManager.getColor("Button.background"));
		textField.setText("           编号                        名称                        价格");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(47, 64, 374, 28);
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
}
