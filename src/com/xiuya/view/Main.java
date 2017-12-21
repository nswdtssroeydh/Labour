package com.xiuya.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class Main {

	private JFrame frame;
	private EmployeePage employeePage;
	private TeaPage teaPage;
	private LabourPage labourPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 717, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(211, 211, 211), 4));
		panel.setForeground(new Color(255, 51, 51));
		frame.getContentPane().add(panel);
		
		JButton employeeButton = new JButton("人员");
		employeeButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		employeeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				employeeButtonClicked(e);
			}
		});
		
		JButton labourButton = new JButton("劳动");
		labourButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		labourButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labourButtonClicked(e);
			}
		});
		
		JButton teaButton = new JButton("茶叶");
		teaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				teaButtonClicked(e);
			}
		});
		teaButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		
		JLabel label = new JLabel("永荣茶厂员工劳务平台");
		label.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 25));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(90)
							.addComponent(employeeButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
							.addGap(70)
							.addComponent(labourButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(82)
							.addComponent(teaButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(219)
							.addComponent(label)))
					.addContainerGap(103, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(47)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(113)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(employeeButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
						.addComponent(teaButton, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(labourButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(173, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	
	public void employeeButtonClicked(ActionEvent e)
	{
		employeePage = EmployeePage.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeePage.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void teaButtonClicked(ActionEvent e)
	{
		teaPage = TeaPage.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teaPage.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void labourButtonClicked(ActionEvent e)
	{
		labourPage = LabourPage.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					labourPage.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
