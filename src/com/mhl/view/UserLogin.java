package com.mhl.view;

import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mhl.tool.FontTool;

/*************************************************************************
 * 用户登陆界面        
 * 空布局,要写详细的定位及大小信息        
 *************************************************************************/
public class UserLogin extends JDialog{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new UserLogin();
	}

	public UserLogin() {
		//不使用上下框
		this.setUndecorated(true);//有么有边框
		this.setLayout(null);
		
		//1.用户名
		JLabel userLb = new JLabel("请输入用户名:");
		userLb.setFont(FontTool.FONT2);
		userLb.setBounds(50, 190, 150, 30);
		this.add(userLb);
		
		//2.用户名文本框
		JTextField userTf = new JTextField(20);
		userTf.setBounds(170, 190, 170, 30);
		userTf.setBorder(BorderFactory.createLoweredBevelBorder());	//使文本框有凹槽,有立体感
		this.add(userTf);
		
		//3.密码
		JLabel pwdLb = new JLabel("密码:");
		pwdLb.setFont(FontTool.FONT2);
		pwdLb.setBounds(120, 230, 150, 30);
		this.add(pwdLb);
		
		//4.密码文本框
		JPasswordField pwdTf = new JPasswordField(20);
		pwdTf.setBounds(170, 230, 170, 30);
		pwdTf.setFocusable(true);	//默认为true,获得焦点,false鼠标将选不中该文本框
		this.add(pwdTf);
		
		//5.确定按钮
		JButton cfmBtn = new JButton("登录");
		cfmBtn.setBounds(80, 300, 80, 30);
		this.add(cfmBtn);
		
		//6.取消按钮
		JButton cclBtn = new JButton("取消");
		cclBtn.setBounds(200, 300, 80, 30);
		this.add(cclBtn);
		
		//添加背景图片
		ImageIcon img = new ImageIcon("img/userLogin.gif");
		JLabel imgLabel = new JLabel(img);
		imgLabel.setBounds(0, 0, 360, 360);
		this.add(imgLabel);
		
		
		//使图像位于屏幕正中央
		int x = Toolkit.getDefaultToolkit().getScreenSize().width/2 - 200;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height/2 - 150;
		this.setBounds(x, y, 360, 360);
		this.setVisible(true);
	}
}
