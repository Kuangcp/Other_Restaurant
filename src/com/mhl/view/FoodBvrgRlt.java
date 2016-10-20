package com.mhl.view;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.mhl.tool.FontTool;

/*************************************************************************
 * 满汉楼餐饮关系系统                
 *************************************************************************/
public class FoodBvrgRlt extends JFrame{
	private static final long serialVersionUID = 1L;
	private int width = 0;
	private int height = 0;

	public static void main(String[] args) {
		new FoodBvrgRlt();
	}
	public FoodBvrgRlt() {
		//1.初始化标题栏
		initTitle();
		
		//2.初始化菜单
		initMenu();
		
		//窗体的基本设置
		initDefault();
	}
	/** 初始化标题栏*/
	private void initTitle() {
		ImageIcon img = new ImageIcon("img/cup.gif");
		this.setIconImage(img.getImage());
		this.setTitle("满汉楼餐饮关系系统");
	}
	/** 初始化菜单*/
	private void initMenu() {
		JMenuBar jmb = new JMenuBar();
		JMenu sysMgr = new JMenu("系统管理");
		JMenuItem swtUser = new JMenuItem("切换用户");
		JMenuItem swtCash = new JMenuItem("切换到收款界面");
		JMenuItem lgnMgr = new JMenuItem("登陆管理");
		JMenuItem calendar = new JMenuItem("万年历");
		JMenuItem esc = new JMenuItem("退出");
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
}
