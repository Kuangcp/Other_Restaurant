package com.mhl.view;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.mhl.tool.FontTool;

/*************************************************************************
 * ����¥������ϵϵͳ                
 *************************************************************************/
public class FoodBvrgRlt extends JFrame{
	private static final long serialVersionUID = 1L;
	private int width = 0;
	private int height = 0;

	public static void main(String[] args) {
		new FoodBvrgRlt();
	}
	public FoodBvrgRlt() {
		//1.��ʼ��������
		initTitle();
		
		//2.��ʼ���˵�
		initMenu();
		
		//����Ļ�������
		initDefault();
	}
	/** ��ʼ��������*/
	private void initTitle() {
		ImageIcon img = new ImageIcon("img/cup.gif");
		this.setIconImage(img.getImage());
		this.setTitle("����¥������ϵϵͳ");
	}
	/** ��ʼ���˵�*/
	private void initMenu() {
		JMenuBar jmb = new JMenuBar();
		JMenu sysMgr = new JMenu("ϵͳ����");
		JMenuItem swtUser = new JMenuItem("�л��û�");
		JMenuItem swtCash = new JMenuItem("�л����տ����");
		JMenuItem lgnMgr = new JMenuItem("��½����");
		JMenuItem calendar = new JMenuItem("������");
		JMenuItem esc = new JMenuItem("�˳�");
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
}
