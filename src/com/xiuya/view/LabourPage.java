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
import java.awt.Font;

public class LabourPage {

	private static LabourPage instance;
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
					LabourPage window = new LabourPage();
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
	public LabourPage() {
		initialize();
	}
	
	public static LabourPage getInstance()
	{
		if(null == instance) {
			return new LabourPage();
		}else
			return instance;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 797, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		String[] columnNames = {"编号", "人员编号", "茶叶编号", "茶叶名称", "人员姓名", "数量", "茶叶单价", "总价", "日期"};
		Object[][] data = {  
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""},
			    {"", "", "", "", "", "", "", "", ""}
			};  
		
		employeePanel = new JPanel();
		employeePanel.setBounds(67, 162, 670, 274);
		panel.add(employeePanel);
		employeePanel.setLayout(null);
		
		employees = new JTable(data, columnNames);
		employees.setBackground(new Color(204, 204, 204));
		employees.setSurrendersFocusOnKeystroke(true);
		employees.setFillsViewportHeight(true);
		employees.setCellSelectionEnabled(true);
		employees.setColumnSelectionAllowed(true);
		employees.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		employees.setBounds(17, 0, 647, 247);
		employeePanel.add(employees);
		
		JButton nextPage = new JButton(">");
		nextPage.setBounds(621, 244, 43, 29);
		employeePanel.add(nextPage);
		
		prePage = new JButton("<");
		prePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		prePage.setBounds(17, 244, 43, 29);
		employeePanel.add(prePage);
		
		pageButton = new JButton("转到");
		pageButton.setBounds(276, 245, 54, 29);
		employeePanel.add(pageButton);
		
		JLabel lblNewLabel = new JLabel("第");
		lblNewLabel.setBounds(328, 250, 15, 16);
		employeePanel.add(lblNewLabel);
		
		JLabel label = new JLabel("页");
		label.setBounds(383, 250, 15, 16);
		employeePanel.add(label);
		
		pageField = new JTextField();
		pageField.setBounds(342, 244, 43, 28);
		employeePanel.add(pageField);
		pageField.setColumns(10);
		
		JScrollBar scrollBar = new JScrollBar();
		employeePanel.add(scrollBar);
		scrollBar.setBounds(0, 0, 15, 236);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(343, 92, 134, 28);
		panel.add(searchTextField);
		searchTextField.setColumns(10);
		
		searchButton = new JButton("查找");
		searchButton.setBounds(492, 93, 75, 29);
		panel.add(searchButton);
		
		addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addButton.setBounds(182, 93, 75, 29);
		panel.add(addButton);
		
		deleteButton = new JButton("删除");
		deleteButton.setBounds(256, 93, 75, 29);
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
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		textField.setBackground(UIManager.getColor("Button.background"));
		textField.setText("     编号     人员编号      茶叶编号    茶叶名称       姓名          数量            单价            总价       日期");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(87, 134, 640, 28);
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
