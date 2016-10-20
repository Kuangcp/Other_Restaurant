package com.mhl.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.mhl.tool.FontTool;

/*************************************************************************
 * 添加新员工                
 *************************************************************************/
public class HrMgrAddDlg extends JDialog{
	private static final long serialVersionUID = 1L;
	private int width = 500;
	private int height = 400;

	public HrMgrAddDlg() {
		//初始化组件
		this.initComponets();
		
		//初始化默认设置
		this.initDefault();
	}
	/** 初始化组件*/
	private void initComponets() {
		this.setLayout(null);
		//姓名
		JLabel nameLbl = new JLabel("姓名:");
		nameLbl.setFont(FontTool.FONT1);
		nameLbl.setBounds(50, 30, 100, 14);
		this.add(nameLbl);
		//姓名文本框
		JTextField nameTxt = new JTextField(30);
		nameTxt.setBounds(108, 28, 240, 25);
		this.add(nameTxt);
		
		//性别
		JLabel genderLbl = new JLabel("性别:");
		genderLbl.setFont(FontTool.FONT1);
		genderLbl.setBounds(50, 60, 100, 14);
		this.add(genderLbl);
		//性别radio
		ButtonGroup sexGroup = new ButtonGroup();
		JRadioButton man = new JRadioButton("男");
		JRadioButton lady = new JRadioButton("女");
		man.setFont(FontTool.FONT1);
		lady.setFont(FontTool.FONT1);
		man.setBounds(108, 58, 50, 14);
		lady.setBounds(168, 58, 50, 14);
		sexGroup.add(man);
		sexGroup.add(lady);
		this.add(man);
		this.add(lady);
		
		//出生日期
		JLabel btdLbl = new JLabel("出生日期:");
		btdLbl.setFont(FontTool.FONT1);
		btdLbl.setBounds(22, 90, 100, 14);
		this.add(btdLbl);
		//生日选择框(年)
		String[] years = new String[30];
		for(int i=0, y=2013; i<years.length; i++){
			years[i] = y-- + "";
		}
		JComboBox yearBox = new JComboBox(years);
		yearBox.setBounds(108, 85, 60, 25);
		this.add(yearBox);
		//年
		JLabel yearLbl = new JLabel("年");
		yearLbl.setFont(FontTool.FONT1);
		yearLbl.setBounds(175, 90, 30, 14);
		this.add(yearLbl);
		//选择框(月))
		String[] months = new String[12];
		for(int i=0, y=12; i<months.length; i++){
			if(y>9){
				months[i] = y-- + "";
			}else{
				months[i] = " " + y-- + "";
			}
		}
		JComboBox monthBox = new JComboBox(months);
		monthBox.setBounds(200, 85, 50, 25);
		this.add(monthBox);
		//月
		JLabel monthLbl = new JLabel("月");
		monthLbl.setFont(FontTool.FONT1);
		monthLbl.setBounds(253, 90, 30, 14);
		this.add(monthLbl);
		//选择框(日)
		String[] days = new String[31];
		for(int i=0, y=1; i<days.length; i++){
			if(y>9){
				days[i] = y++ + "";
			}else{
				days[i] = " " + y++ + "";
			}
		}
		JComboBox dayBox = new JComboBox(days);
		dayBox.setBounds(284, 85, 50, 25);
		this.add(dayBox);
		//日
		JLabel dayLbl = new JLabel("日");
		dayLbl.setFont(FontTool.FONT1);
		dayLbl.setBounds(336, 90, 30, 14);
		this.add(dayLbl);
		
		//身份证号
		JLabel idLbl = new JLabel("身份证号:");
		idLbl.setFont(FontTool.FONT1);
		idLbl.setBounds(22, 120, 100, 14);
		this.add(idLbl);
		//身份证号文本框
		JTextField idTxt = new JTextField(30);
		idTxt.setBounds(108, 118, 240, 25);
		this.add(idTxt);
		
		//学历
		JLabel eduLbl = new JLabel("学历:");
		eduLbl.setFont(FontTool.FONT1);
		eduLbl.setBounds(50, 150, 100, 14);
		this.add(eduLbl);
		//学历值
		String[] edus = {"大本", "大专", "无", "小学", "职高", "中学", "中专"};
		JComboBox eduBox = new JComboBox(edus);
		eduBox.setBounds(108, 148, 60, 25);
		this.add(eduBox);
		
		//婚否
		JLabel marryLbl = new JLabel("婚否:");
		marryLbl.setFont(FontTool.FONT1);
		marryLbl.setBounds(50, 180, 100, 14);
		this.add(marryLbl);
		//婚否radio
		ButtonGroup marryGp = new ButtonGroup();
		JRadioButton md = new JRadioButton("已婚");
		JRadioButton um = new JRadioButton("未婚");
		md.setFont(FontTool.FONT1);
		um.setFont(FontTool.FONT1);
		md.setBounds(108, 178, 60, 14);
		um.setBounds(178, 178, 60, 14);
		marryGp.add(md);
		marryGp.add(um);
		this.add(md);
		this.add(um);
		
		//照片
		JPanel picPanel = new JPanel();
		picPanel.setLayout(new BorderLayout());
		JButton addPicBtn = new JButton("添加照片");
		picPanel.add(addPicBtn, BorderLayout.SOUTH);
		picPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		picPanel.setBounds(360, 30, 130, 164);
		this.add(picPanel);
		
		//地址
		JLabel addLbl = new JLabel("地址:");
		addLbl.setFont(FontTool.FONT1);
		addLbl.setBounds(50, 210, 100, 14);
		this.add(addLbl);
		//地址文本框
		JTextField addTxt = new JTextField(30);
		addTxt.setBounds(108, 208, 380, 25);
		this.add(addTxt);
		
		//职位
		JLabel posLbl = new JLabel("职位:");
		posLbl.setFont(FontTool.FONT1);
		posLbl.setBounds(50, 240, 100, 14);
		this.add(posLbl);
		//职位值
		String[] posts = {"采购员", "厨师", "服务员", "经理", "领班", "领位", "收银员"};
		JComboBox posBox = new JComboBox(posts);
		posBox.setBounds(108, 238, 80, 25);
		this.add(posBox);
		
		//添加
		JButton addBtn = new JButton("添加");
		JButton escBtn = new JButton("退出");
		addBtn.setFont(FontTool.FONT1);
		escBtn.setFont(FontTool.FONT1);
		addBtn.setBounds(105, 300, 80, 30);
		escBtn.setBounds(315, 300, 80, 30);
		this.add(addBtn);
		this.add(escBtn);
	}
	/** 初始化默认设置*/
	private void initDefault() {
		this.setTitle("新员工登记");
		Image logo = new ImageIcon("img/cup.gif").getImage();
		this.setIconImage(logo);
		//Dialog居中显示
		int x = Toolkit.getDefaultToolkit().getScreenSize().width/2 - width/2;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height/2 - height/2;
		this.setBounds(x, y, width, height);
		this.setVisible(true);
	}
}
