package com.xiuya.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.xiuya.controller.EmployeeController;
import com.xiuya.controller.TeaController;
import com.xiuya.util.HibernateUtils;

public class InputTeaWindow {

	public static InputTeaWindow instance;
	private TeaController teaController;
	private JFrame frame;
	private JTextField field1;
	private JTextField field2;
	private JLabel label1;
	private JLabel label2;
	
	public JFrame getFrame() {
		return frame;
	}
	
	public TeaController getTeaController() {
		return teaController;
	}

	public void setTeaController(TeaController teaController) {
		this.teaController = teaController;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputTeaWindow window = new InputTeaWindow();
					window.setTeaController((TeaController) HibernateUtils.context.getBean("teaController"));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static InputTeaWindow getInstance()
	{

		if(instance == null) {
			System.out.println("window created");
			instance = new InputTeaWindow();
		}
		
		return instance;
	}
	
	/**
	 * Create the application.
	 */
	public InputTeaWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("employeeController:" );
		
		frame = new JFrame();
		frame.setBounds(100, 100, 359, 246);
		frame.getContentPane().setLayout(null);
		
		label1 = new JLabel("茶叶名");
		label1.setBounds(36, 74, 39, 16);
		frame.getContentPane().add(label1);
		
		label2 = new JLabel("单价");
		label2.setBounds(183, 74, 39, 16);
		frame.getContentPane().add(label2);
		
		field1 = new JTextField();
		field1.setBounds(75, 68, 80, 28);
		frame.getContentPane().add(field1);
		field1.setColumns(10);
		
		field2 = new JTextField();
		field2.setColumns(10);
		field2.setBounds(213, 68, 91, 28);
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
					boolean flag = teaController.addTea(info.get(0), info.get(1));
					if(flag)
						JOptionPane.showMessageDialog(null, "添加成功！", "成功！", JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "单价必须是数字！", "错误！", JOptionPane.ERROR_MESSAGE);
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
