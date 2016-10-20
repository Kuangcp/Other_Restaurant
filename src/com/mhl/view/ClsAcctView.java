package com.mhl.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.Timer;
import com.mhl.tool.FontTool;

/*************************************************************************
 * 满汉楼收款界面,就是有桌子椅子的界面               
 * TODO 时间的动态变化,时间的颜色
 *************************************************************************/
public class ClsAcctView extends JFrame implements ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private int width = 0;
	private int height = 0;
	private JLabel timeLabel;
	private String timeStr;
	private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, jl10, jl11, jl12, jl13, jl14, jl15, jl16, jl17, jl18, jl19, jl20, jl21, jl22, jl23, jl24, jl25;
	private Vector<JLabel> chairVec = new Vector<JLabel>();
	private JLabel target;		//被选中的椅子
	private JButton preOrder;	//预订按钮
	private JButton retOrder;	//退订按钮
	private JButton order;	//点菜按钮

	public static void main(String[] args) {
		new ClsAcctView();
	}
	public ClsAcctView(){
		this.setTitle("融满汉精髓, 做天下美味-----满汉楼");
		try {
			Image image = ImageIO.read(new File("img/cup.gif"));
			this.setIconImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setLayout(null);
		
		//初始化菜单
		this.initMenu();
		
		//初始化椅子
		this.initChairs();
		
		//初始化LOGO
		this.initLogo();
		
		//初始化按钮
		this.initBtns();
		
		//初始化时间
		this.initTime();
		
		//为时间添加一个监听类
		new Timer(1000, this).start();
		
		//初始化时间背景
		this.initTimeBg();
		
		//初始化工具栏
		this.initToolBar();
		
		//初始化背景
		this.initBg();
		
		//窗口启动时默认全屏
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//设定窗口的默认宽高为显示器的宽高
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height - 30;
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	/** 初始化菜单*/
	private void initMenu() {
		JMenuBar jmb = new JMenuBar();
		JMenu sysMenu = new JMenu("系统");
		JMenu svcMenu = new JMenu("服务");
		JMenu hlpMenu = new JMenu("帮助");
		
		//设置字体类型
		sysMenu.setFont(FontTool.FONT1);
		svcMenu.setFont(FontTool.FONT1);
		hlpMenu.setFont(FontTool.FONT1);
		jmb.add(sysMenu);
		jmb.add(svcMenu);
		jmb.add(hlpMenu);
		this.setJMenuBar(jmb);
	}
	/** 初始化工具栏*/
	private void initToolBar() {
		JToolBar toolbar = new JToolBar();
		//按钮图片
		ImageIcon hrImg = new ImageIcon("img/hr.png");
		ImageIcon loginImg = new ImageIcon("img/login.jpg");
		ImageIcon loveImg = new ImageIcon("img/love.png");
		ImageIcon uDiskImg = new ImageIcon("img/uDisk.jpg");
		ImageIcon infoImg = new ImageIcon("img/info.jpg");
		
		//图片添加到按钮
		JButton hrBtn = new JButton(hrImg);
		JButton loginBtn = new JButton(loginImg);
		JButton loveBtn = new JButton(loveImg);
		JButton uDiskBtn = new JButton(uDiskImg);
		JButton infoBtn = new JButton(infoImg);
		
		//按钮添加到工具栏
		toolbar.add(hrBtn);
		toolbar.add(loginBtn);
		toolbar.add(loveBtn);
		toolbar.add(uDiskBtn);
		toolbar.add(infoBtn);
		
		//添加到JFrame
		toolbar.setFloatable(false);
		toolbar.setBounds(0, 0, 170, 38);
		this.add(toolbar);
	}
	/** 初始化背景*/
	private void initBg(){
		ImageIcon img = new ImageIcon("img/orderBg.png");
		JLabel imgLabel = new JLabel(img);
		imgLabel.setBounds(0, -30, img.getIconWidth(), img.getIconHeight());
		this.add(imgLabel);
	}
	/** 初始化椅子*/
	private void initChairs(){
		
		//初始化1-5的椅子
		init1to5();
		
		//初始化11-15的椅子
		init11to15();
		
		//初始化21-25的椅子
		init21to25();
		
		//初始化16-20的椅子
		init16to20();
		
		//初始化6-10的椅子
		init6to10();
		
		//把椅子放入到集合中
		initChairVec();
		
		//初始化提示信息
		initTips();
	}
	/** 初始化1到5的椅子*/
	private void init1to5(){
		ImageIcon img1 = new ImageIcon("img/1/blue/1桌面组合.png");
		ImageIcon img2 = new ImageIcon("img/1/blue/2桌面组合.png");
		ImageIcon img3 = new ImageIcon("img/1/blue/3桌面组合.png");
		ImageIcon img4 = new ImageIcon("img/1/blue/4桌面组合.png");
		ImageIcon img5 = new ImageIcon("img/1/blue/5桌面组合.png");
		
		jl1 = new JLabel(img1);
		jl2 = new JLabel(img2);
		jl3 = new JLabel(img3);
		jl4 = new JLabel(img4);
		jl5 = new JLabel(img5);
		
		//添加监听
		jl1.addMouseListener(this);
		jl2.addMouseListener(this);
		jl3.addMouseListener(this);
		jl4.addMouseListener(this);
		jl5.addMouseListener(this);
		
		jl1.setBounds(220, 258, 100, 68);
		jl2.setBounds(360, 258, 100, 68);
		jl3.setBounds(500, 258, 100, 68);
		jl4.setBounds(640, 258, 100, 68);
		jl5.setBounds(780, 258, 100, 68);
		
		this.add(jl1);
		this.add(jl2);
		this.add(jl3);
		this.add(jl4);
		this.add(jl5);
	}
	/** 初始化11到15的椅子*/
	private void init11to15() {
		ImageIcon img1 = new ImageIcon("img/2/blue/11桌面组合.png");
		ImageIcon img2 = new ImageIcon("img/2/blue/12桌面组合.png");
		ImageIcon img3 = new ImageIcon("img/2/blue/13桌面组合.png");
		ImageIcon img4 = new ImageIcon("img/2/blue/14桌面组合.png");
		ImageIcon img5 = new ImageIcon("img/2/blue/15桌面组合.png");
		
		jl11 = new JLabel(img1);
		jl12 = new JLabel(img2);
		jl13 = new JLabel(img3);
		jl14 = new JLabel(img4);
		jl15 = new JLabel(img5);
		
		//添加监听
		jl11.addMouseListener(this);
		jl12.addMouseListener(this);
		jl13.addMouseListener(this);
		jl14.addMouseListener(this);
		jl15.addMouseListener(this);
		
		jl11.setBounds(220, 338, 100, 68);
		jl12.setBounds(360, 338, 100, 68);
		jl13.setBounds(500, 338, 100, 68);
		jl14.setBounds(640, 338, 100, 68);
		jl15.setBounds(780, 338, 100, 68);
		
		this.add(jl11);
		this.add(jl12);
		this.add(jl13);
		this.add(jl14);
		this.add(jl15);
	}
	/** 初始化21到25的椅子*/
	private void init21to25() {
		ImageIcon img1 = new ImageIcon("img/3/blue/21桌面组合.png");
		ImageIcon img2 = new ImageIcon("img/3/blue/22桌面组合.png");
		ImageIcon img3 = new ImageIcon("img/3/blue/23桌面组合.png");
		ImageIcon img4 = new ImageIcon("img/3/blue/24桌面组合.png");
		ImageIcon img5 = new ImageIcon("img/3/blue/25桌面组合.png");
		
		jl21 = new JLabel(img1);
		jl22 = new JLabel(img2);
		jl23 = new JLabel(img3);
		jl24 = new JLabel(img4);
		jl25 = new JLabel(img5);
		
		//添加监听
		jl21.addMouseListener(this);
		jl22.addMouseListener(this);
		jl23.addMouseListener(this);
		jl24.addMouseListener(this);
		jl25.addMouseListener(this);
		
		jl21.setBounds(220, 418, 100, 68);
		jl22.setBounds(360, 418, 100, 68);
		jl23.setBounds(500, 418, 100, 68);
		jl24.setBounds(640, 418, 100, 68);
		jl25.setBounds(780, 418, 100, 68);
		
		this.add(jl21);
		this.add(jl22);
		this.add(jl23);
		this.add(jl24);
		this.add(jl25);
	}
	/** 初始化16到20的椅子*/
	private void init16to20() {
		ImageIcon img1 = new ImageIcon("img/2/blue/16桌面组合.png");
		ImageIcon img2 = new ImageIcon("img/2/blue/17桌面组合.png");
//		ImageIcon img2 = new ImageIcon("img/2/red/17桌面组合.jpg");
		ImageIcon img3 = new ImageIcon("img/2/blue/18桌面组合.png");
		ImageIcon img4 = new ImageIcon("img/2/blue/19桌面组合.png");
		ImageIcon img5 = new ImageIcon("img/2/blue/20桌面组合.png");
		
		jl16 = new JLabel(img1);
		jl17 = new JLabel(img2);
		jl18 = new JLabel(img3);
		jl19 = new JLabel(img4);
		jl20 = new JLabel(img5);
		
		//添加监听
		jl16.addMouseListener(this);
		jl17.addMouseListener(this);
		jl18.addMouseListener(this);
		jl19.addMouseListener(this);
		jl20.addMouseListener(this);
		
		jl16.setBounds(220, 498, 100, 68);
		jl17.setBounds(360, 498, 100, 68);
		jl18.setBounds(500, 498, 100, 68);
		jl19.setBounds(640, 498, 100, 68);
		jl20.setBounds(780, 498, 100, 68);
		
		
		this.add(jl16);
		this.add(jl17);
		this.add(jl18);
		this.add(jl19);
		this.add(jl20);
	}
	/** 初始化6到10的椅子*/
	private void init6to10() {
		ImageIcon img1 = new ImageIcon("img/1/blue/6桌面组合.png");
		ImageIcon img2 = new ImageIcon("img/1/blue/7桌面组合.png");
		ImageIcon img3 = new ImageIcon("img/1/blue/8桌面组合.png");
		ImageIcon img4 = new ImageIcon("img/1/blue/9桌面组合.png");
		ImageIcon img5 = new ImageIcon("img/1/blue/10桌面组合.png");
		
		jl6 = new JLabel(img1);
		jl7 = new JLabel(img2);
		jl8 = new JLabel(img3);
		jl9 = new JLabel(img4);
		jl10 = new JLabel(img5);
		
		//添加监听
		jl6.addMouseListener(this);
		jl7.addMouseListener(this);
		jl8.addMouseListener(this);
		jl9.addMouseListener(this);
		jl10.addMouseListener(this);
		
		jl6.setBounds(220, 578, 100, 68);
		jl7.setBounds(360, 578, 100, 68);
		jl8.setBounds(500, 578, 100, 68);
		jl9.setBounds(640, 578, 100, 68);
		jl10.setBounds(780, 578, 100, 68);
		
		this.add(jl6);
		this.add(jl7);
		this.add(jl8);
		this.add(jl9);
		this.add(jl10);
	}
	/** 初始化椅子集合*/
	private void initChairVec() {
		chairVec.add(jl1);
		chairVec.add(jl2);
		chairVec.add(jl3);
		chairVec.add(jl4);
		chairVec.add(jl5);
		chairVec.add(jl6);
		chairVec.add(jl7);
		chairVec.add(jl8);
		chairVec.add(jl9);
		chairVec.add(jl10);
		chairVec.add(jl11);
		chairVec.add(jl12);
		chairVec.add(jl13);
		chairVec.add(jl14);
		chairVec.add(jl15);
		chairVec.add(jl16);
		chairVec.add(jl17);
		chairVec.add(jl18);
		chairVec.add(jl19);
		chairVec.add(jl20);
		chairVec.add(jl21);
		chairVec.add(jl22);
		chairVec.add(jl23);
		chairVec.add(jl24);
		chairVec.add(jl25);
	}
	/** 初始化右侧按钮*/
	private void initBtns() {
		preOrder = new JButton("预订");
		retOrder = new JButton("退订");
		order = new JButton("点菜");
		JButton clsAcct = new JButton("结账");
		
		//设置禁用
//		retOrder.setEnabled(false);
//		order.setEnabled(false);
		clsAcct.setEnabled(false);
		
		preOrder.setFont(FontTool.FONT1);
		retOrder.setFont(FontTool.FONT1);
		order.setFont(FontTool.FONT1);
		clsAcct.setFont(FontTool.FONT1);
		
		//添加监听
		preOrder.addActionListener(this);
		retOrder.addActionListener(this);
		order.addActionListener(this);
		
		preOrder.setBounds(1025, 530, 80, 30);
		retOrder.setBounds(1145, 530, 80, 30);
		order.setBounds(1025, 580, 80, 30);
		clsAcct.setBounds(1145, 580, 80, 30);
		
		this.add(preOrder);
		this.add(retOrder);
		this.add(order);
		this.add(clsAcct);
	}
	/** 初始化LOGO*/
	private void initLogo() {
		ImageIcon logo = new ImageIcon("img/logo/blue.png");
		JLabel logoLabel = new JLabel(logo);
		//计算LOGO的位置
		int w = 1366-458+(458-224)/2;
		int h = 0 + (210-140)/2;
		logoLabel.setBounds(w, h, logo.getIconWidth(), logo.getIconHeight());
		this.add(logoLabel);
	}
	/** 初始化提示信息*/
	private void initTips() {
 		JLabel userLabel = new JLabel("当前用户:");
 		JLabel user = new JLabel("谢彦");
 		JLabel posLabel = new JLabel("职位:");
 		JLabel pos = new JLabel("经理");
 		
 		userLabel.setFont(FontTool.FONT1);
 		user.setFont(FontTool.FONT1);
 		posLabel.setFont(FontTool.FONT1);
 		pos.setFont(FontTool.FONT1);
 		
 		userLabel.setBounds(931, 160, 80, 20);
 		user.setBounds(1031, 160, 80, 20);
 		posLabel.setBounds(955, 200, 80, 20);
 		pos.setBounds(1031, 200, 80, 20);
 		
 		this.add(userLabel);
 		this.add(user);
 		this.add(posLabel);
 		this.add(pos);
	}
	/** 初始化时间背景*/
	private void initTimeBg() {
		ImageIcon timeImg = new ImageIcon("img/time_bg.jpg");
		JLabel timeLabel = new JLabel(timeImg);
		timeLabel.setBounds(0, 660, 1366, 30);
		this.add(timeLabel);
	}
	/** 初始化时间*/
	private void initTime() {
		Date curTime = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("当前时间:  yyyy年MM月dd日    HH点mm分ss秒");
		timeStr = sdf.format(curTime);
		timeLabel = new JLabel(timeStr);
		timeLabel.setFont(FontTool.FONT1);
		timeLabel.setBounds(900, 660, 350, 30);
		this.add(timeLabel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Date curTime = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("当前时间:  yyyy年MM月dd日    HH点mm分ss秒");
		timeStr = sdf.format(curTime);
		timeLabel.setText(timeStr);
		
		Object btn = e.getSource();
		//预定功能
		if(btn==preOrder && target==null){
			JOptionPane.showMessageDialog(this, "请选择要预订的座位!");
		}else if(btn==preOrder && target!=null){
			for(int i=0; i<chairVec.size(); i++){
				int ds = -1;		//大的文件夹的编号
				if(i<10)	ds = 1;
				else if(i<20)	ds = 2;
				else ds = 3;
				
				if(target == chairVec.get(i)){
					String impPath = String.format("img/%s/red/%s桌面组合.png", ds, i+1);
					ImageIcon icon = new ImageIcon(impPath);
					target.setIcon(icon);
				}
			}
		}
		
		//退定功能
		if(btn==retOrder && target==null){
			JOptionPane.showMessageDialog(this, "请选择要退订的座位!");
		}else if(btn==retOrder && target!=null){
			for(int i=0; i<chairVec.size(); i++){
				int ds = -1;		//大的文件夹的编号
				if(i<10)	ds = 1;
				else if(i<20)	ds = 2;
				else ds = 3;
				
				if(target == chairVec.get(i)){
					String impPath = String.format("img/%s/blue/%s桌面组合.png", ds, i+1);
					ImageIcon icon = new ImageIcon(impPath);
					target.setIcon(icon);
				}
			}
		}
		
		//点菜功能
		if(btn==order && target==null){
			JOptionPane.showMessageDialog(this, "请选择要点菜的座位!");
		}else if(btn==order && target!=null){
			for(int i=0; i<chairVec.size(); i++){
				int ds = -1;		//大的文件夹的编号
				if(i<10)	ds = 1;
				else if(i<20)	ds = 2;
				else ds = 3;
				
				if(target == chairVec.get(i)){
					String impPath = String.format("img/%s/yellow/%s桌面组合.png", ds, i+1);
					ImageIcon icon = new ImageIcon(impPath);
					target.setIcon(icon);
				}
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		target = (JLabel)e.getSource();
		if(chairVec.contains(target)){
			//创建凹槽效果
			target.setBorder(BorderFactory.createLoweredBevelBorder());
			//其他的按钮弹起
			for(int i=0; i<chairVec.size(); i++){
				if(target == chairVec.get(i))	continue;
				chairVec.get(i).setBorder(BorderFactory.createEmptyBorder());
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
