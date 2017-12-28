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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import com.xiuya.bean.Employee;
import com.xiuya.bean.Tea;
import com.xiuya.controller.TeaController;
import com.xiuya.util.HibernateUtils;

import java.awt.Color;
import javax.swing.UIManager;

public class TeaPage {

	private static TeaPage instance;
	private JFrame frame;
	private JTable teas;
	private JPanel teaPanel;
	private JButton prePage;
	private JButton nextPage;
	private JButton pageButton;
	private JTextField pageField;
	private JTextField searchTextField;
	private JButton searchButton;
	private JButton addButton;
	private JButton deleteButton;
	private JButton returnButton;
	private JTextField textField;
	private InputTeaWindow inputTeaWindow;
	private TeaController teaController;
	private DefaultTableModel model;
	private int maxPage;
	private int curPage;
	private JLabel label_1;
	private JTextField totalPageField;
	private JLabel label_2;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public InputTeaWindow getInputTeaWindow() {
		return inputTeaWindow;
	}

	public void setInputTeaWindow(InputTeaWindow inputTeaWindow) {
		this.inputTeaWindow = inputTeaWindow;
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
	
	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
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
		this.teaController = TeaController.getInstance();
		this.inputTeaWindow = InputTeaWindow.getInstance();
		this.inputTeaWindow.setTeaController(teaController);
		System.out.println("teaController page:" + teaController);
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
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		String[] columnNames = {"blank",  "编号",  "名称",  "价格"};
		Object[][] data = {  
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""}
			};  
		
		teaPanel = new JPanel();
		teaPanel.setBounds(25, 91, 395, 154);
		panel.add(teaPanel);
		teaPanel.setLayout(null);
		
		model = new DefaultTableModel(columnNames, 7);
		model.setDataVector(data, columnNames);
		
		teas = new JTable(model);
		teas.setBackground(new Color(204, 204, 204));
		teas.setSurrendersFocusOnKeystroke(true);
		teas.setFillsViewportHeight(true);
		teas.setCellSelectionEnabled(true);
		teas.setColumnSelectionAllowed(true);
		javax.swing.table.TableColumn tc = teas.getColumn("blank"); 
		tc.setPreferredWidth(1);
		teas.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		teas.setBounds(17, 0, 378, 121);
		teaPanel.add(teas);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(0, 0, 15, 121);
		teas.add(scrollBar);
		
		nextPage = new JButton(">");
		nextPage.setBounds(346, 125, 43, 29);
		nextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchNextButtonClicked(e);
			}
		});
		teaPanel.add(nextPage);
		
		prePage = new JButton("<");
		prePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchPreButtonClicked(e);
			}
		});
		prePage.setBounds(0, 125, 43, 29);
		teaPanel.add(prePage);
		
		totalPageField = new JTextField();
		totalPageField.setColumns(10);
		totalPageField.setBounds(286, 125, 43, 28);
		teaPanel.add(totalPageField);
		
		pageButton = new JButton("转到");
		pageButton.setBounds(118, 125, 54, 29);
		pageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchSpecificButtonClicked(e);
			}
		});
		teaPanel.add(pageButton);
		
		JLabel lblNewLabel = new JLabel("第");
		lblNewLabel.setBounds(170, 130, 15, 16);
		teaPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("页");
		label.setBounds(225, 130, 15, 16);
		teaPanel.add(label);
		
		label_1 = new JLabel("页");
		label_1.setBounds(330, 131, 15, 16);
		teaPanel.add(label_1);
		label_2 = new JLabel("共");
		label_2.setBounds(272, 131, 15, 16);
		teaPanel.add(label_2);
		
		pageField = new JTextField();
		pageField.setBounds(184, 124, 43, 28);
		teaPanel.add(pageField);
		pageField.setColumns(10);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(197, 36, 134, 28);
		panel.add(searchTextField);
		searchTextField.setColumns(10);
		
		searchButton = new JButton("查找");
		searchButton.setBounds(346, 37, 75, 29);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchButtonClicked(e);
			}
		});
		panel.add(searchButton);
		
		addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addButton.setBounds(36, 37, 75, 29);
		panel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addButtonClicked(e);
			}
		});
		
		deleteButton = new JButton("删除");
		deleteButton.setBounds(110, 37, 75, 29);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteButtonClicked(e);
			}
		});
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
		textField.setText("                   编号                     名称                    价格");
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
	
	private void addButtonClicked(ActionEvent e)
	{
		inputTeaWindow = InputTeaWindow.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inputTeaWindow.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		searchButtonClicked(e);
	}
	
	private void deleteButtonClicked(ActionEvent e)
	{
		String number = JOptionPane.showInputDialog("输入要删除的茶叶编号:");
		Integer id = null;
		try {
			id = Integer.parseInt(number);
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "输入错误！", "错误！", JOptionPane.ERROR_MESSAGE);
			return;
		}
		teaController.deleteTea(number);
		searchButtonClicked(e);
	}
	
	private void searchButtonClicked(ActionEvent e)
	{
		String name = searchTextField.getText();
		int totalPage = getMaxPageCount();
		setMaxPage(totalPage);
		setCurrentPage(1);
		showTeaOnTable(name, "1");
		System.out.println("totalpage:" + totalPage);
		totalPageField.setText(totalPage + "");
	}
	
	private void searchPreButtonClicked(ActionEvent e)
	{
		String name = searchTextField.getText();
		Integer cur = getCurPage();
		if(cur <= 1)
		{
			return;
		}
		setCurrentPage(cur - 1);
		showTeaOnTable(name, (cur - 1) + "");
	}
	
	private void searchNextButtonClicked(ActionEvent e)
	{
		String name = searchTextField.getText();
		int totalPage = getMaxPage();
		Integer cur = getCurPage();
		if(cur >= totalPage)
		{
			return;
		}
		setCurrentPage(cur + 1);
		showTeaOnTable(name, (cur + 1) + "");
	}
	
	private void searchSpecificButtonClicked(ActionEvent e)
	{
		String name = searchTextField.getText();
		int totalPage = getMaxPage();
		int desPage = getCurrentPage();
		if(desPage <= 0)
		{
			setCurrentPage(1);
			showTeaOnTable(name, 1 + "");
			return;
		}
		if(desPage >= totalPage) {
			setCurrentPage(totalPage);
			showTeaOnTable(name, totalPage + "");
			return;
		}
		
		setCurrentPage(desPage);
		showTeaOnTable(name, desPage + "");
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
	
	private void showTeaOnTable(String name, String pageStr)
	{
		String[][] data = new String[7][4];
		try {
			int page = Integer.parseInt(pageStr);
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "输入错误！", "错误！", JOptionPane.ERROR_MESSAGE);
			return;
		}
		List<Tea> teas = teaController.getTea(name, pageStr);
		for(int i = 0; i < teas.size(); i++)
		{
			Tea tea = teas.get(i);
			data[i][0] = "";
			data[i][1] = tea.getId() + "";
			data[i][2] = tea.getName();
			data[i][3] = tea.getPrice() + "";
		}
		String[] columnNames = {"blank", "编号", "茶叶名", "单价"};
		getModel().setDataVector(data, columnNames);
	}
	
	private void setCurrentPage(Integer page)
	{
		this.setCurPage(page);
		this.pageField.setText(page + "");
	}
	
	private int getMaxPageCount()
	{
		String name = searchTextField.getText();
		int allSize = teaController.getTea(name, "").size();
		System.out.println("allSize:" + allSize);
		if(allSize%7 == 0)
			return allSize/7;
		else
			return allSize/7 + 1;
	}
}
