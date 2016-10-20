package com.mhl.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import com.mhl.tool.FontTool;
import com.mhl.view.p3.HrManagerPanel;

/*************************************************************************
 * 酒店管理系统                
 *************************************************************************/
public class HotelMgrView extends JFrame implements ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private int width = 0;
	private int height = 0;
	private JLabel timeLabel;
	/** 左边显示的刚进入时的页面*/
	private JPanel p1;	
	/** 向左向右缩进的图标面板*/
	private JPanel p2;	
	/** 详细的内容面板*/
	private JPanel p3;	
	/** 包含P2,P3面板的面板*/
	private JPanel p4;	
	private JLabel hrLabel;		//人事登记
	private JLabel loginLabel;	//登录管理
	private JLabel priceLabel;	//菜谱价格
	private JLabel tabSttLabel;	//报表统计
	private JLabel costLabel;	//成本及库房
	private JLabel setLabel;	//系统设置
	private JLabel helpLabel;	//动画帮助
	private CardLayout cardLayout2;	//面板2的布局管理器
	private CardLayout cardLayout3;	//面板3的布局管理器
	private JSplitPane jsp;	//中间最大的分割面板
	private JLabel leftLabel;		//P2面板中的向左的图标
	private JLabel rightLabel;		//P2面板中的向右的图标
	
	public static void main(String[] args) {
		new HotelMgrView();
	}
	public HotelMgrView() {
		//设置光标(TODO设置整体的光标图像)
		Image cursorImg = new ImageIcon("img/arrow.png").getImage();
		Cursor newCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(10,10), "光标");
		this.setCursor(newCursor);
		
		//初始化标题栏
		this.initTitle();
		
		//初始化菜单
		this.initMenu();
		
		//初始化工具栏
		this.initTools();
		
		//初始化中间的面板
		this.initCenter();
		
		//初始化时间
		this.initTime();
		
		//窗体的基本设置
		this.initDefault();
	}
	/** 初始化标题栏*/
	private void initTitle() {
		ImageIcon img = new ImageIcon("img/cup.gif");
		this.setIconImage(img.getImage());
		String user = "郝海亮";
		String pos = "经理";
		String titile = String.format("酒店管理系统   当前用户: %s(%s) ", user, pos);
		this.setTitle(titile);
	}
	/** 初始化菜单*/
	private void initMenu() {
		JMenuBar jmb = new JMenuBar();
		
		ImageIcon swtUserIcon = new ImageIcon("img/login_b.jpg");
		ImageIcon swtCashIcon = new ImageIcon("img/hr_b.jpg");
		ImageIcon lgnMgrIcon = new ImageIcon("img/pc_b.jpg");
		ImageIcon calendarIcon = new ImageIcon("img/wnl.jpg");
		ImageIcon escIcon = new ImageIcon("img/info_b.jpg");
		
		JMenu sysMgr = new JMenu("系统管理");
		JMenuItem swtUser = new JMenuItem("切换用户", swtUserIcon);
		JMenuItem swtCash = new JMenuItem("切换到收款界面", swtCashIcon);
		JMenuItem lgnMgr = new JMenuItem("登陆管理", lgnMgrIcon);
		JMenuItem calendar = new JMenuItem("万年历", calendarIcon);
		JMenuItem esc = new JMenuItem("退出", escIcon);
		swtUser.setFont(FontTool.FONT0);
		swtCash.setFont(FontTool.FONT0);
		lgnMgr.setFont(FontTool.FONT0);
		calendar.setFont(FontTool.FONT0);
		esc.setFont(FontTool.FONT0);
		sysMgr.add(swtUser);
		sysMgr.add(swtCash);
		sysMgr.add(lgnMgr);
		sysMgr.add(calendar);
		sysMgr.add(esc);
		
		JMenu hrMgr = new JMenu("人事管理");
		JMenu mnSvc = new JMenu("菜单服务");
		JMenu rptStt = new JMenu("报表统计");
		JMenu cstStoHos = new JMenu("成本及库房");
		JMenu hlp = new JMenu("帮助");
		
		//菜单字体设置
		sysMgr.setFont(FontTool.FONT1);
		hrMgr.setFont(FontTool.FONT1);
		mnSvc.setFont(FontTool.FONT1);
		rptStt.setFont(FontTool.FONT1);
		cstStoHos.setFont(FontTool.FONT1);
		hlp.setFont(FontTool.FONT1);
		
		jmb.add(sysMgr);
		jmb.add(hrMgr);
		jmb.add(mnSvc);
		jmb.add(rptStt);
		jmb.add(cstStoHos);
		jmb.add(hlp);
		this.setJMenuBar(jmb);
	}
	/** 窗体的默认设置*/
	private void initDefault() {
		//最大化处理
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height - 30;
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	/** 初始化工具栏*/
	private void initTools() {
		JToolBar toolbar = new JToolBar();
		//按钮图片
		ImageIcon hrImg = new ImageIcon("img/hr.png");
		ImageIcon loginImg = new ImageIcon("img/login.jpg");
		ImageIcon pcImg = new ImageIcon("img/pc.jpg");
		ImageIcon earthImg = new ImageIcon("img/earth.jpg");
		ImageIcon uDiskImg = new ImageIcon("img/uDisk.jpg");
		ImageIcon fishImg = new ImageIcon("img/fish.jpg");
		ImageIcon cukeImg = new ImageIcon("img/cuke.jpg");
		ImageIcon butterflyImg = new ImageIcon("img/butterfly.jpg");
		ImageIcon robotImg = new ImageIcon("img/robot.jpg");
		ImageIcon infoImg = new ImageIcon("img/info.jpg");
		
		//图片添加到按钮
		JButton hrBtn = new JButton(hrImg);
		JButton loginBtn = new JButton(loginImg);
		JButton pcBtn = new JButton(pcImg);
		JButton earthBtn = new JButton(earthImg);
		JButton uDiskBtn = new JButton(uDiskImg);
		JButton fishBtn = new JButton(fishImg);
		JButton cukeBtn = new JButton(cukeImg);
		JButton butterflyBtn = new JButton(butterflyImg);
		JButton robotBtn = new JButton(robotImg);
		JButton infoBtn = new JButton(infoImg);
		
		//按钮添加到工具栏
		toolbar.add(hrBtn);
		toolbar.add(loginBtn);
		toolbar.add(pcBtn);
		toolbar.add(earthBtn);
		toolbar.add(uDiskBtn);
		toolbar.add(fishBtn);
		toolbar.add(cukeBtn);
		toolbar.add(butterflyBtn);
		toolbar.add(robotBtn);
		toolbar.add(infoBtn);
		
		//添加到JFrame
		toolbar.setFloatable(false);
		this.add(toolbar, BorderLayout.NORTH);
	}
	/** 初始化时间*/
	private void initTime() {
		String curTime = this.getCurTimeStr();
		timeLabel = new JLabel(curTime);
		//创建一个背景图片
		Image img = new ImageIcon("img/time_bg.jpg").getImage();
		ImagePanel imgPanel = new ImagePanel(img);
		imgPanel.setLayout(new BorderLayout());
		imgPanel.add(timeLabel, BorderLayout.EAST);
		this.add(imgPanel, BorderLayout.SOUTH);
		new Timer(1000, this).start();
	}
	/** 获得当前时间的字符串*/
	private String getCurTimeStr(){
		Date curTime = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("当前时间:  yyyy年MM月dd日    HH点mm分ss秒                              ");
		String timeStr = format.format(curTime);
		return timeStr;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timeLabel.setText(this.getCurTimeStr());
	}
	/** 初始化中间的面板*/
	private void initCenter() {
		//左边显示的刚进入时的页面
		p1 = new JPanel(new BorderLayout());
		//向左向右缩进的图标面板
		cardLayout2 = new CardLayout();
		p2 = new JPanel(cardLayout2);
		//详细的内容面板
		cardLayout3 = new CardLayout();
		p3 = new JPanel(cardLayout3);
		//包含P2,P3面板的面板
		p4 = new JPanel(new BorderLayout());
		
		//构建P1面板的内容
		this.initP1();
		
		//构建P2面板的内容
		this.initP2();
		
		//构建P3面板的内容
		this.initP3();
		
		//构建P4面板的内容
		this.initP4();
		
		//总的分割面板
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, p1, p4);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		jsp.setDividerLocation(160);
		
		jsp.setOneTouchExpandable(false);	//不显示分割三角按钮
		jsp.setEnabled(false);	//布局大小不可拖动
		jsp.setDividerSize(0);	//不显示分割线
		this.add(jsp, BorderLayout.CENTER);
	}
	/** 构建P1面板的内容*/
	private void initP1() {
		Image bcImg = new ImageIcon("img/jp1_bg.jpg").getImage();
		ImagePanel imgPanel = new ImagePanel(bcImg);
		imgPanel.setLayout(new GridLayout(8, 1));
		
		//添加具体的内容
		ImageIcon logo = new ImageIcon("img/logo/black.png");
		ImageIcon hr = new ImageIcon("img/p1_rsgl.jpg");
		ImageIcon login = new ImageIcon("img/p1_dlgl.jpg");
		ImageIcon price = new ImageIcon("img/p1_cpjg.jpg");
		ImageIcon tabStt = new ImageIcon("img/p1_bbtj.jpg");
		ImageIcon cost = new ImageIcon("img/p1_cb.jpg");
		ImageIcon setting = new ImageIcon("img/p1_xtsz.jpg");
		ImageIcon help = new ImageIcon("img/p1_dhbz.jpg");
		
		JLabel logoLabel = new JLabel(logo);
		
		hrLabel = new JLabel("人事登记", hr, SwingConstants.CENTER);
		loginLabel = new JLabel("登录管理", login, SwingConstants.CENTER);
		priceLabel = new JLabel("菜谱价格", price, SwingConstants.CENTER);
		tabSttLabel = new JLabel("报表统计", tabStt, SwingConstants.CENTER);
		costLabel = new JLabel("成本及库房", cost, SwingConstants.CENTER);
		setLabel = new JLabel("系统设置", setting, SwingConstants.CENTER);
		helpLabel = new JLabel("动画帮助", help, SwingConstants.CENTER);
		
		//没有获得焦点的变为 灰色图标
		hrLabel.setEnabled(false);
		loginLabel.setEnabled(false);
		priceLabel.setEnabled(false);
		tabSttLabel.setEnabled(false);
		costLabel.setEnabled(false);
		setLabel.setEnabled(false);
		helpLabel.setEnabled(false);
		
		//各个label添加监听
		hrLabel.addMouseListener(this);
		loginLabel.addMouseListener(this);
		priceLabel.addMouseListener(this);
		tabSttLabel.addMouseListener(this);
		costLabel.addMouseListener(this);
		setLabel.addMouseListener(this);
		helpLabel.addMouseListener(this);
		
		hrLabel.setFont(FontTool.FONT1);
		loginLabel.setFont(FontTool.FONT1);
		priceLabel.setFont(FontTool.FONT1);
		tabSttLabel.setFont(FontTool.FONT1);
		costLabel.setFont(FontTool.FONT1);
		setLabel.setFont(FontTool.FONT1);
		helpLabel.setFont(FontTool.FONT1);
		
		imgPanel.add(logoLabel);
		imgPanel.add(hrLabel);
		imgPanel.add(loginLabel);
		imgPanel.add(priceLabel);
		imgPanel.add(tabSttLabel);
		imgPanel.add(costLabel);
		imgPanel.add(setLabel);
		imgPanel.add(helpLabel);
		
		//夹在中间时会出现自适应效果
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height - 30;
		p1.setSize(width, height);
		p1.add(imgPanel);
	}
	/** 构建P2面板的内容*/
	private void initP2() {
		ImageIcon leftImg = new ImageIcon("img/p2_left.jpg");
		ImageIcon rightImg = new ImageIcon("img/p2_right.jpg");
		
		leftLabel = new JLabel(leftImg);
		rightLabel = new JLabel(rightImg);
		
		//添加监听
		leftLabel.addMouseListener(this);
		rightLabel.addMouseListener(this);
		
		p2.add(leftLabel, "left");
		p2.add(rightLabel, "right");
	}
	/** 构建P3面板的内容*/
	private void initP3() {
		//7个背景图片,代替7个面板
//		Image jp3_1Img = new ImageIcon("img/jp3_1.jpg").getImage();
		Image jp3_2Img = new ImageIcon("img/jp3_2.jpg").getImage();
		Image jp3_3Img = new ImageIcon("img/jp3_3.jpg").getImage();
		Image jp3_4Img = new ImageIcon("img/jp3_4.jpg").getImage();
		Image jp3_5Img = new ImageIcon("img/jp3_5.jpg").getImage();
		Image jp3_6Img = new ImageIcon("img/jp3_6.jpg").getImage();
		Image jp3_7Img = new ImageIcon("img/jp3_7.jpg").getImage();
		
		//构造背景面板,准备添加
//		ImagePanel p31bg = new ImagePanel(jp3_1Img);
		HrManagerPanel hmp = new HrManagerPanel();
		ImagePanel p32bg = new ImagePanel(jp3_2Img);
		ImagePanel p33bg = new ImagePanel(jp3_3Img);
		ImagePanel p34bg = new ImagePanel(jp3_4Img);
		ImagePanel p35bg = new ImagePanel(jp3_5Img);
		ImagePanel p36bg = new ImagePanel(jp3_6Img);
		ImagePanel p37bg = new ImagePanel(jp3_7Img);
		
		p3.add(hmp, "jp31");
		p3.add(p32bg, "jp32");
		p3.add(p33bg, "jp33");
		p3.add(p34bg, "jp34");
		p3.add(p35bg, "jp35");
		p3.add(p36bg, "jp36");
		p3.add(p37bg, "jp37");
	}
	/** 构建P4面板的内容*/
	private void initP4() {
		p4.setBackground(Color.RED);
		p4.setSize(500, 768);
		p4.setOpaque(true);
		p4.add(p2, BorderLayout.WEST);
		p4.add(p3, BorderLayout.CENTER);
	}
	/** 为面板中JLabel添加的鼠标事件*/
	@Override
	public void mouseClicked(MouseEvent e) {
		Object target = e.getSource();
		if(hrLabel == target){
			cardLayout3.show(p3, "jp31");
			jsp.setDividerLocation(160);
		}else if(loginLabel == target){
			cardLayout3.show(p3, "jp32");
			jsp.setDividerLocation(160);
		}else if(priceLabel == target){
			cardLayout3.show(p3, "jp33");
			jsp.setDividerLocation(160);
		}else if(tabSttLabel == target){
			cardLayout3.show(p3, "jp34");
			jsp.setDividerLocation(160);
		}else if(costLabel == target){
			cardLayout3.show(p3, "jp35");
			jsp.setDividerLocation(160);
		}else if(setLabel == target){
			cardLayout3.show(p3, "jp36");
			jsp.setDividerLocation(160);
		}else if(helpLabel == target){
			cardLayout3.show(p3, "jp37");
			jsp.setDividerLocation(160);
		}else if(leftLabel == target){
			cardLayout2.show(p2, "right");
			jsp.setDividerLocation(0);
		}else if(rightLabel == target){
			cardLayout2.show(p2, "left");
			jsp.setDividerLocation(160);
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
		Object target = e.getSource();
		if(hrLabel == target){
			hrLabel.setEnabled(true);
		}else if(loginLabel == target){
			loginLabel.setEnabled(true);
		}else if(priceLabel == target){
			priceLabel.setEnabled(true);
		}else if(tabSttLabel == target){
			tabSttLabel.setEnabled(true);
		}else if(costLabel == target){
			costLabel.setEnabled(true);
		}else if(setLabel == target){
			setLabel.setEnabled(true);
		}else if(helpLabel == target){
			helpLabel.setEnabled(true);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		Object target = e.getSource();
		if(hrLabel == target){
			hrLabel.setEnabled(false);
		}else if(loginLabel == target){
			loginLabel.setEnabled(false);
		}else if(priceLabel == target){
			priceLabel.setEnabled(false);
		}else if(tabSttLabel == target){
			tabSttLabel.setEnabled(false);
		}else if(costLabel == target){
			costLabel.setEnabled(false);
		}else if(setLabel == target){
			setLabel.setEnabled(false);
		}else if(helpLabel == target){
			helpLabel.setEnabled(false);
		}
	}
}
/** 时间panel类(初始化状态栏的背景图)*/
class ImagePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Image img;
	
	public ImagePanel(Image img) {
		this.img = img;
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(width, height);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//清屏
		super.paintComponents(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
}
