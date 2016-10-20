package com.mhl.view.p3;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.mhl.model.HrManagerModel;
import com.mhl.tool.FontTool;
import com.mhl.view.HrMgrAddDlg;

/*************************************************************************
 * 人事管理界面                
 *************************************************************************/
public class HrManagerPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField userNameTF;	//查询文本框
	private JButton freshBtn;	//查询按钮
	private JTable hrTable;	//人事资料table
	private HrManagerModel mm = new HrManagerModel();
	private JLabel records;
	private JPanel sourthPanel;
	private JButton addBtn;		//添加按钮

	public HrManagerPanel() {
		this.setLayout(new BorderLayout());
		
		//初始化北部面板
		this.initNorth();
		
		//初始化中部面板
		this.initCenter();
		
		//初始化南部面板
		this.initSourth();
		
		//初始化尺寸
		this.initSize();
	}
	/** 初始化北部面板*/
	private void initNorth() {
		JLabel userName = new JLabel("请输入姓名(员工号或职位):");
		userNameTF = new JTextField(30);
		freshBtn = new JButton("刷新");
		freshBtn.addActionListener(this);
		
		//字体设置
		userName.setFont(FontTool.FONT1);
		freshBtn.setFont(FontTool.FONT1);
		
		//添加面板
		JPanel northPanel = new JPanel();
		northPanel.add(userName);
		northPanel.add(userNameTF);
		northPanel.add(freshBtn);
		
		this.add(northPanel, BorderLayout.NORTH);
	}
	/** 初始化中部面板*/
	private void initCenter() {
		//构建表格
		mm = new HrManagerModel();
		hrTable = new JTable(mm);
		
		//添加表格
		JScrollPane jsp = new JScrollPane(hrTable);
		//设置表格边框
		jsp.setBorder(BorderFactory.createLoweredBevelBorder());
		this.add(jsp, BorderLayout.CENTER);
	}
	/** 初始化南部面板*/
	private void initSourth() {
		//显示记录信息
		records = showRecords();
		
		//4个按钮
		JButton dtlBtn = new JButton("详细信息");
		addBtn = new JButton("添加");
		JButton modBtn = new JButton("修改");
		JButton delBtn = new JButton("删除");
		
		//字体设置
		dtlBtn.setFont(FontTool.FONT1);
		addBtn.setFont(FontTool.FONT1);
		modBtn.setFont(FontTool.FONT1);
		delBtn.setFont(FontTool.FONT1);
		
		//按钮面板
		JPanel btnPanel = new JPanel();
		btnPanel.add(dtlBtn);
		btnPanel.add(addBtn);
		btnPanel.add(modBtn);
		btnPanel.add(delBtn);
		
		//添加监听
		addBtn.addActionListener(this);
		
		//添加到各自的位置
		sourthPanel = new JPanel(new BorderLayout());
		sourthPanel.add(records, BorderLayout.CENTER);
		sourthPanel.add(btnPanel, BorderLayout.EAST);
		this.add(sourthPanel, BorderLayout.SOUTH);
	}
	/** 初始化尺寸*/
	private void initSize() {
		//自适应效果
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(width, height);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		if(freshBtn == target){
			String name = userNameTF.getText();
			mm = new HrManagerModel(name);
			hrTable.setModel(mm);
			sourthPanel.remove(records);
			records = showRecords();
			sourthPanel.add(records, BorderLayout.CENTER);
		}else if(addBtn == target){
			new HrMgrAddDlg();
		}
		
	}
	/** 显示记录条数*/
	private JLabel showRecords() {
		int count = mm.getRowCount();
		String tps = String.format("共有%s条记录", count);
		JLabel records = new JLabel(tps);
		records.setFont(FontTool.FONT1);
		return records;
	}
}
