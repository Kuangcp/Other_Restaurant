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
 * �Ƶ����ϵͳ                
 *************************************************************************/
public class HotelMgrView extends JFrame implements ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private int width = 0;
	private int height = 0;
	private JLabel timeLabel;
	/** �����ʾ�ĸս���ʱ��ҳ��*/
	private JPanel p1;	
	/** ��������������ͼ�����*/
	private JPanel p2;	
	/** ��ϸ���������*/
	private JPanel p3;	
	/** ����P2,P3�������*/
	private JPanel p4;	
	private JLabel hrLabel;		//���µǼ�
	private JLabel loginLabel;	//��¼����
	private JLabel priceLabel;	//���׼۸�
	private JLabel tabSttLabel;	//����ͳ��
	private JLabel costLabel;	//�ɱ����ⷿ
	private JLabel setLabel;	//ϵͳ����
	private JLabel helpLabel;	//��������
	private CardLayout cardLayout2;	//���2�Ĳ��ֹ�����
	private CardLayout cardLayout3;	//���3�Ĳ��ֹ�����
	private JSplitPane jsp;	//�м����ķָ����
	private JLabel leftLabel;		//P2����е������ͼ��
	private JLabel rightLabel;		//P2����е����ҵ�ͼ��
	
	public static void main(String[] args) {
		new HotelMgrView();
	}
	public HotelMgrView() {
		//���ù��(TODO��������Ĺ��ͼ��)
		Image cursorImg = new ImageIcon("img/arrow.png").getImage();
		Cursor newCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(10,10), "���");
		this.setCursor(newCursor);
		
		//��ʼ��������
		this.initTitle();
		
		//��ʼ���˵�
		this.initMenu();
		
		//��ʼ��������
		this.initTools();
		
		//��ʼ���м�����
		this.initCenter();
		
		//��ʼ��ʱ��
		this.initTime();
		
		//����Ļ�������
		this.initDefault();
	}
	/** ��ʼ��������*/
	private void initTitle() {
		ImageIcon img = new ImageIcon("img/cup.gif");
		this.setIconImage(img.getImage());
		String user = "�º���";
		String pos = "����";
		String titile = String.format("�Ƶ����ϵͳ   ��ǰ�û�: %s(%s) ", user, pos);
		this.setTitle(titile);
	}
	/** ��ʼ���˵�*/
	private void initMenu() {
		JMenuBar jmb = new JMenuBar();
		
		ImageIcon swtUserIcon = new ImageIcon("img/login_b.jpg");
		ImageIcon swtCashIcon = new ImageIcon("img/hr_b.jpg");
		ImageIcon lgnMgrIcon = new ImageIcon("img/pc_b.jpg");
		ImageIcon calendarIcon = new ImageIcon("img/wnl.jpg");
		ImageIcon escIcon = new ImageIcon("img/info_b.jpg");
		
		JMenu sysMgr = new JMenu("ϵͳ����");
		JMenuItem swtUser = new JMenuItem("�л��û�", swtUserIcon);
		JMenuItem swtCash = new JMenuItem("�л����տ����", swtCashIcon);
		JMenuItem lgnMgr = new JMenuItem("��½����", lgnMgrIcon);
		JMenuItem calendar = new JMenuItem("������", calendarIcon);
		JMenuItem esc = new JMenuItem("�˳�", escIcon);
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
		
		JMenu hrMgr = new JMenu("���¹���");
		JMenu mnSvc = new JMenu("�˵�����");
		JMenu rptStt = new JMenu("����ͳ��");
		JMenu cstStoHos = new JMenu("�ɱ����ⷿ");
		JMenu hlp = new JMenu("����");
		
		//�˵���������
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
	/** �����Ĭ������*/
	private void initDefault() {
		//��󻯴���
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height - 30;
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	/** ��ʼ��������*/
	private void initTools() {
		JToolBar toolbar = new JToolBar();
		//��ťͼƬ
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
		
		//ͼƬ��ӵ���ť
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
		
		//��ť��ӵ�������
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
		
		//��ӵ�JFrame
		toolbar.setFloatable(false);
		this.add(toolbar, BorderLayout.NORTH);
	}
	/** ��ʼ��ʱ��*/
	private void initTime() {
		String curTime = this.getCurTimeStr();
		timeLabel = new JLabel(curTime);
		//����һ������ͼƬ
		Image img = new ImageIcon("img/time_bg.jpg").getImage();
		ImagePanel imgPanel = new ImagePanel(img);
		imgPanel.setLayout(new BorderLayout());
		imgPanel.add(timeLabel, BorderLayout.EAST);
		this.add(imgPanel, BorderLayout.SOUTH);
		new Timer(1000, this).start();
	}
	/** ��õ�ǰʱ����ַ���*/
	private String getCurTimeStr(){
		Date curTime = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("��ǰʱ��:  yyyy��MM��dd��    HH��mm��ss��                              ");
		String timeStr = format.format(curTime);
		return timeStr;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timeLabel.setText(this.getCurTimeStr());
	}
	/** ��ʼ���м�����*/
	private void initCenter() {
		//�����ʾ�ĸս���ʱ��ҳ��
		p1 = new JPanel(new BorderLayout());
		//��������������ͼ�����
		cardLayout2 = new CardLayout();
		p2 = new JPanel(cardLayout2);
		//��ϸ���������
		cardLayout3 = new CardLayout();
		p3 = new JPanel(cardLayout3);
		//����P2,P3�������
		p4 = new JPanel(new BorderLayout());
		
		//����P1��������
		this.initP1();
		
		//����P2��������
		this.initP2();
		
		//����P3��������
		this.initP3();
		
		//����P4��������
		this.initP4();
		
		//�ܵķָ����
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, p1, p4);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		jsp.setDividerLocation(160);
		
		jsp.setOneTouchExpandable(false);	//����ʾ�ָ����ǰ�ť
		jsp.setEnabled(false);	//���ִ�С�����϶�
		jsp.setDividerSize(0);	//����ʾ�ָ���
		this.add(jsp, BorderLayout.CENTER);
	}
	/** ����P1��������*/
	private void initP1() {
		Image bcImg = new ImageIcon("img/jp1_bg.jpg").getImage();
		ImagePanel imgPanel = new ImagePanel(bcImg);
		imgPanel.setLayout(new GridLayout(8, 1));
		
		//��Ӿ��������
		ImageIcon logo = new ImageIcon("img/logo/black.png");
		ImageIcon hr = new ImageIcon("img/p1_rsgl.jpg");
		ImageIcon login = new ImageIcon("img/p1_dlgl.jpg");
		ImageIcon price = new ImageIcon("img/p1_cpjg.jpg");
		ImageIcon tabStt = new ImageIcon("img/p1_bbtj.jpg");
		ImageIcon cost = new ImageIcon("img/p1_cb.jpg");
		ImageIcon setting = new ImageIcon("img/p1_xtsz.jpg");
		ImageIcon help = new ImageIcon("img/p1_dhbz.jpg");
		
		JLabel logoLabel = new JLabel(logo);
		
		hrLabel = new JLabel("���µǼ�", hr, SwingConstants.CENTER);
		loginLabel = new JLabel("��¼����", login, SwingConstants.CENTER);
		priceLabel = new JLabel("���׼۸�", price, SwingConstants.CENTER);
		tabSttLabel = new JLabel("����ͳ��", tabStt, SwingConstants.CENTER);
		costLabel = new JLabel("�ɱ����ⷿ", cost, SwingConstants.CENTER);
		setLabel = new JLabel("ϵͳ����", setting, SwingConstants.CENTER);
		helpLabel = new JLabel("��������", help, SwingConstants.CENTER);
		
		//û�л�ý���ı�Ϊ ��ɫͼ��
		hrLabel.setEnabled(false);
		loginLabel.setEnabled(false);
		priceLabel.setEnabled(false);
		tabSttLabel.setEnabled(false);
		costLabel.setEnabled(false);
		setLabel.setEnabled(false);
		helpLabel.setEnabled(false);
		
		//����label��Ӽ���
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
		
		//�����м�ʱ���������ӦЧ��
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height - 30;
		p1.setSize(width, height);
		p1.add(imgPanel);
	}
	/** ����P2��������*/
	private void initP2() {
		ImageIcon leftImg = new ImageIcon("img/p2_left.jpg");
		ImageIcon rightImg = new ImageIcon("img/p2_right.jpg");
		
		leftLabel = new JLabel(leftImg);
		rightLabel = new JLabel(rightImg);
		
		//��Ӽ���
		leftLabel.addMouseListener(this);
		rightLabel.addMouseListener(this);
		
		p2.add(leftLabel, "left");
		p2.add(rightLabel, "right");
	}
	/** ����P3��������*/
	private void initP3() {
		//7������ͼƬ,����7�����
//		Image jp3_1Img = new ImageIcon("img/jp3_1.jpg").getImage();
		Image jp3_2Img = new ImageIcon("img/jp3_2.jpg").getImage();
		Image jp3_3Img = new ImageIcon("img/jp3_3.jpg").getImage();
		Image jp3_4Img = new ImageIcon("img/jp3_4.jpg").getImage();
		Image jp3_5Img = new ImageIcon("img/jp3_5.jpg").getImage();
		Image jp3_6Img = new ImageIcon("img/jp3_6.jpg").getImage();
		Image jp3_7Img = new ImageIcon("img/jp3_7.jpg").getImage();
		
		//���챳�����,׼�����
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
	/** ����P4��������*/
	private void initP4() {
		p4.setBackground(Color.RED);
		p4.setSize(500, 768);
		p4.setOpaque(true);
		p4.add(p2, BorderLayout.WEST);
		p4.add(p3, BorderLayout.CENTER);
	}
	/** Ϊ�����JLabel��ӵ�����¼�*/
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
/** ʱ��panel��(��ʼ��״̬���ı���ͼ)*/
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
		//����
		super.paintComponents(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
}
