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
 * ����¥�տ����,�������������ӵĽ���               
 * TODO ʱ��Ķ�̬�仯,ʱ�����ɫ
 *************************************************************************/
public class ClsAcctView extends JFrame implements ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private int width = 0;
	private int height = 0;
	private JLabel timeLabel;
	private String timeStr;
	private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, jl10, jl11, jl12, jl13, jl14, jl15, jl16, jl17, jl18, jl19, jl20, jl21, jl22, jl23, jl24, jl25;
	private Vector<JLabel> chairVec = new Vector<JLabel>();
	private JLabel target;		//��ѡ�е�����
	private JButton preOrder;	//Ԥ����ť
	private JButton retOrder;	//�˶���ť
	private JButton order;	//��˰�ť

	public static void main(String[] args) {
		new ClsAcctView();
	}
	public ClsAcctView(){
		this.setTitle("����������, ��������ζ-----����¥");
		try {
			Image image = ImageIO.read(new File("img/cup.gif"));
			this.setIconImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setLayout(null);
		
		//��ʼ���˵�
		this.initMenu();
		
		//��ʼ������
		this.initChairs();
		
		//��ʼ��LOGO
		this.initLogo();
		
		//��ʼ����ť
		this.initBtns();
		
		//��ʼ��ʱ��
		this.initTime();
		
		//Ϊʱ�����һ��������
		new Timer(1000, this).start();
		
		//��ʼ��ʱ�䱳��
		this.initTimeBg();
		
		//��ʼ��������
		this.initToolBar();
		
		//��ʼ������
		this.initBg();
		
		//��������ʱĬ��ȫ��
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//�趨���ڵ�Ĭ�Ͽ��Ϊ��ʾ���Ŀ��
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height - 30;
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	/** ��ʼ���˵�*/
	private void initMenu() {
		JMenuBar jmb = new JMenuBar();
		JMenu sysMenu = new JMenu("ϵͳ");
		JMenu svcMenu = new JMenu("����");
		JMenu hlpMenu = new JMenu("����");
		
		//������������
		sysMenu.setFont(FontTool.FONT1);
		svcMenu.setFont(FontTool.FONT1);
		hlpMenu.setFont(FontTool.FONT1);
		jmb.add(sysMenu);
		jmb.add(svcMenu);
		jmb.add(hlpMenu);
		this.setJMenuBar(jmb);
	}
	/** ��ʼ��������*/
	private void initToolBar() {
		JToolBar toolbar = new JToolBar();
		//��ťͼƬ
		ImageIcon hrImg = new ImageIcon("img/hr.png");
		ImageIcon loginImg = new ImageIcon("img/login.jpg");
		ImageIcon loveImg = new ImageIcon("img/love.png");
		ImageIcon uDiskImg = new ImageIcon("img/uDisk.jpg");
		ImageIcon infoImg = new ImageIcon("img/info.jpg");
		
		//ͼƬ��ӵ���ť
		JButton hrBtn = new JButton(hrImg);
		JButton loginBtn = new JButton(loginImg);
		JButton loveBtn = new JButton(loveImg);
		JButton uDiskBtn = new JButton(uDiskImg);
		JButton infoBtn = new JButton(infoImg);
		
		//��ť��ӵ�������
		toolbar.add(hrBtn);
		toolbar.add(loginBtn);
		toolbar.add(loveBtn);
		toolbar.add(uDiskBtn);
		toolbar.add(infoBtn);
		
		//��ӵ�JFrame
		toolbar.setFloatable(false);
		toolbar.setBounds(0, 0, 170, 38);
		this.add(toolbar);
	}
	/** ��ʼ������*/
	private void initBg(){
		ImageIcon img = new ImageIcon("img/orderBg.png");
		JLabel imgLabel = new JLabel(img);
		imgLabel.setBounds(0, -30, img.getIconWidth(), img.getIconHeight());
		this.add(imgLabel);
	}
	/** ��ʼ������*/
	private void initChairs(){
		
		//��ʼ��1-5������
		init1to5();
		
		//��ʼ��11-15������
		init11to15();
		
		//��ʼ��21-25������
		init21to25();
		
		//��ʼ��16-20������
		init16to20();
		
		//��ʼ��6-10������
		init6to10();
		
		//�����ӷ��뵽������
		initChairVec();
		
		//��ʼ����ʾ��Ϣ
		initTips();
	}
	/** ��ʼ��1��5������*/
	private void init1to5(){
		ImageIcon img1 = new ImageIcon("img/1/blue/1�������.png");
		ImageIcon img2 = new ImageIcon("img/1/blue/2�������.png");
		ImageIcon img3 = new ImageIcon("img/1/blue/3�������.png");
		ImageIcon img4 = new ImageIcon("img/1/blue/4�������.png");
		ImageIcon img5 = new ImageIcon("img/1/blue/5�������.png");
		
		jl1 = new JLabel(img1);
		jl2 = new JLabel(img2);
		jl3 = new JLabel(img3);
		jl4 = new JLabel(img4);
		jl5 = new JLabel(img5);
		
		//��Ӽ���
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
	/** ��ʼ��11��15������*/
	private void init11to15() {
		ImageIcon img1 = new ImageIcon("img/2/blue/11�������.png");
		ImageIcon img2 = new ImageIcon("img/2/blue/12�������.png");
		ImageIcon img3 = new ImageIcon("img/2/blue/13�������.png");
		ImageIcon img4 = new ImageIcon("img/2/blue/14�������.png");
		ImageIcon img5 = new ImageIcon("img/2/blue/15�������.png");
		
		jl11 = new JLabel(img1);
		jl12 = new JLabel(img2);
		jl13 = new JLabel(img3);
		jl14 = new JLabel(img4);
		jl15 = new JLabel(img5);
		
		//��Ӽ���
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
	/** ��ʼ��21��25������*/
	private void init21to25() {
		ImageIcon img1 = new ImageIcon("img/3/blue/21�������.png");
		ImageIcon img2 = new ImageIcon("img/3/blue/22�������.png");
		ImageIcon img3 = new ImageIcon("img/3/blue/23�������.png");
		ImageIcon img4 = new ImageIcon("img/3/blue/24�������.png");
		ImageIcon img5 = new ImageIcon("img/3/blue/25�������.png");
		
		jl21 = new JLabel(img1);
		jl22 = new JLabel(img2);
		jl23 = new JLabel(img3);
		jl24 = new JLabel(img4);
		jl25 = new JLabel(img5);
		
		//��Ӽ���
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
	/** ��ʼ��16��20������*/
	private void init16to20() {
		ImageIcon img1 = new ImageIcon("img/2/blue/16�������.png");
		ImageIcon img2 = new ImageIcon("img/2/blue/17�������.png");
//		ImageIcon img2 = new ImageIcon("img/2/red/17�������.jpg");
		ImageIcon img3 = new ImageIcon("img/2/blue/18�������.png");
		ImageIcon img4 = new ImageIcon("img/2/blue/19�������.png");
		ImageIcon img5 = new ImageIcon("img/2/blue/20�������.png");
		
		jl16 = new JLabel(img1);
		jl17 = new JLabel(img2);
		jl18 = new JLabel(img3);
		jl19 = new JLabel(img4);
		jl20 = new JLabel(img5);
		
		//��Ӽ���
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
	/** ��ʼ��6��10������*/
	private void init6to10() {
		ImageIcon img1 = new ImageIcon("img/1/blue/6�������.png");
		ImageIcon img2 = new ImageIcon("img/1/blue/7�������.png");
		ImageIcon img3 = new ImageIcon("img/1/blue/8�������.png");
		ImageIcon img4 = new ImageIcon("img/1/blue/9�������.png");
		ImageIcon img5 = new ImageIcon("img/1/blue/10�������.png");
		
		jl6 = new JLabel(img1);
		jl7 = new JLabel(img2);
		jl8 = new JLabel(img3);
		jl9 = new JLabel(img4);
		jl10 = new JLabel(img5);
		
		//��Ӽ���
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
	/** ��ʼ�����Ӽ���*/
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
	/** ��ʼ���Ҳఴť*/
	private void initBtns() {
		preOrder = new JButton("Ԥ��");
		retOrder = new JButton("�˶�");
		order = new JButton("���");
		JButton clsAcct = new JButton("����");
		
		//���ý���
//		retOrder.setEnabled(false);
//		order.setEnabled(false);
		clsAcct.setEnabled(false);
		
		preOrder.setFont(FontTool.FONT1);
		retOrder.setFont(FontTool.FONT1);
		order.setFont(FontTool.FONT1);
		clsAcct.setFont(FontTool.FONT1);
		
		//��Ӽ���
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
	/** ��ʼ��LOGO*/
	private void initLogo() {
		ImageIcon logo = new ImageIcon("img/logo/blue.png");
		JLabel logoLabel = new JLabel(logo);
		//����LOGO��λ��
		int w = 1366-458+(458-224)/2;
		int h = 0 + (210-140)/2;
		logoLabel.setBounds(w, h, logo.getIconWidth(), logo.getIconHeight());
		this.add(logoLabel);
	}
	/** ��ʼ����ʾ��Ϣ*/
	private void initTips() {
 		JLabel userLabel = new JLabel("��ǰ�û�:");
 		JLabel user = new JLabel("л��");
 		JLabel posLabel = new JLabel("ְλ:");
 		JLabel pos = new JLabel("����");
 		
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
	/** ��ʼ��ʱ�䱳��*/
	private void initTimeBg() {
		ImageIcon timeImg = new ImageIcon("img/time_bg.jpg");
		JLabel timeLabel = new JLabel(timeImg);
		timeLabel.setBounds(0, 660, 1366, 30);
		this.add(timeLabel);
	}
	/** ��ʼ��ʱ��*/
	private void initTime() {
		Date curTime = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("��ǰʱ��:  yyyy��MM��dd��    HH��mm��ss��");
		timeStr = sdf.format(curTime);
		timeLabel = new JLabel(timeStr);
		timeLabel.setFont(FontTool.FONT1);
		timeLabel.setBounds(900, 660, 350, 30);
		this.add(timeLabel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Date curTime = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("��ǰʱ��:  yyyy��MM��dd��    HH��mm��ss��");
		timeStr = sdf.format(curTime);
		timeLabel.setText(timeStr);
		
		Object btn = e.getSource();
		//Ԥ������
		if(btn==preOrder && target==null){
			JOptionPane.showMessageDialog(this, "��ѡ��ҪԤ������λ!");
		}else if(btn==preOrder && target!=null){
			for(int i=0; i<chairVec.size(); i++){
				int ds = -1;		//����ļ��еı��
				if(i<10)	ds = 1;
				else if(i<20)	ds = 2;
				else ds = 3;
				
				if(target == chairVec.get(i)){
					String impPath = String.format("img/%s/red/%s�������.png", ds, i+1);
					ImageIcon icon = new ImageIcon(impPath);
					target.setIcon(icon);
				}
			}
		}
		
		//�˶�����
		if(btn==retOrder && target==null){
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�˶�����λ!");
		}else if(btn==retOrder && target!=null){
			for(int i=0; i<chairVec.size(); i++){
				int ds = -1;		//����ļ��еı��
				if(i<10)	ds = 1;
				else if(i<20)	ds = 2;
				else ds = 3;
				
				if(target == chairVec.get(i)){
					String impPath = String.format("img/%s/blue/%s�������.png", ds, i+1);
					ImageIcon icon = new ImageIcon(impPath);
					target.setIcon(icon);
				}
			}
		}
		
		//��˹���
		if(btn==order && target==null){
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ��˵���λ!");
		}else if(btn==order && target!=null){
			for(int i=0; i<chairVec.size(); i++){
				int ds = -1;		//����ļ��еı��
				if(i<10)	ds = 1;
				else if(i<20)	ds = 2;
				else ds = 3;
				
				if(target == chairVec.get(i)){
					String impPath = String.format("img/%s/yellow/%s�������.png", ds, i+1);
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
			//��������Ч��
			target.setBorder(BorderFactory.createLoweredBevelBorder());
			//�����İ�ť����
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
