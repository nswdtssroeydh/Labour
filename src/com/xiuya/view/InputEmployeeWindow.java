package com.xiuya.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.xiuya.controller.EmployeeController;

import javax.swing.JButton;

public class InputEmployeeWindow {

	private static InputEmployeeWindow instance;
	private JFrame frame;
	private JTextField field1;
	private JTextField field2;
	private JLabel label1;
	private JLabel label2;
	private EmployeeController employeeController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputEmployeeWindow window = new InputEmployeeWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static InputEmployeeWindow getInstance()
	{

		if(instance == null) {
			System.out.println("window created");
			instance = new InputEmployeeWindow();
		}
		
		return instance;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public EmployeeController getEmployeeController() {
		return employeeController;
	}

	public void setEmployeeController(EmployeeController employeeController) {
		this.employeeController = employeeController;
	}

	/**
	 * Create the application.
	 */
	public InputEmployeeWindow() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("employeeController:" + employeeController);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 359, 246);
		frame.getContentPane().setLayout(null);
		
		label1 = new JLabel("姓名");
		label1.setBounds(36, 74, 39, 16);
		frame.getContentPane().add(label1);
		
		label2 = new JLabel("电话号码");
		label2.setBounds(167, 74, 63, 16);
		frame.getContentPane().add(label2);
		
		field1 = new JTextField();
		field1.setBounds(75, 68, 80, 28);
		frame.getContentPane().add(field1);
		field1.setColumns(10);
		
		field2 = new JTextField();
		field2.setColumns(10);
		field2.setBounds(231, 68, 91, 28);
		frame.getContentPane().add(field2);
		
		JButton confirmButton = new JButton("确定");
		confirmButton.setBounds(113, 149, 117, 29);
		frame.getContentPane().add(confirmButton);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> info = getInfo();
				System.out.println(info.size());
				if(info.size() == 2)
				{
					employeeController.addEmployee(info.get(0), info.get(1));
					JOptionPane.showMessageDialog(null, "添加成功！", "成功！", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "输入错误！", "错误！", JOptionPane.ERROR_MESSAGE);
				
				close();
			}
		});
	}
	
	public ArrayList<String> getInfo()
	{
		ArrayList<String> result = new ArrayList<>();
		String label1 = field1.getText();
		String label2 = field2.getText();
		field1.setText("");
		field2.setText("");
		if(!"".equals(label1) && !"".equals(label2))
		{
			result.add(label1);
			result.add(label2);
			return result;
		}
		return result;
	}
	
	public void close()
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
