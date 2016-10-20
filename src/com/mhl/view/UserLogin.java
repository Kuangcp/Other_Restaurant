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
 * �û���½����        
 * �ղ���,Ҫд��ϸ�Ķ�λ����С��Ϣ        
 *************************************************************************/
public class UserLogin extends JDialog{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new UserLogin();
	}

	public UserLogin() {
		//��ʹ�����¿�
		this.setUndecorated(true);//��ô�б߿�
		this.setLayout(null);
		
		//1.�û���
		JLabel userLb = new JLabel("�������û���:");
		userLb.setFont(FontTool.FONT2);
		userLb.setBounds(50, 190, 150, 30);
		this.add(userLb);
		
		//2.�û����ı���
		JTextField userTf = new JTextField(20);
		userTf.setBounds(170, 190, 170, 30);
		userTf.setBorder(BorderFactory.createLoweredBevelBorder());	//ʹ�ı����а���,�������
		this.add(userTf);
		
		//3.����
		JLabel pwdLb = new JLabel("����:");
		pwdLb.setFont(FontTool.FONT2);
		pwdLb.setBounds(120, 230, 150, 30);
		this.add(pwdLb);
		
		//4.�����ı���
		JPasswordField pwdTf = new JPasswordField(20);
		pwdTf.setBounds(170, 230, 170, 30);
		pwdTf.setFocusable(true);	//Ĭ��Ϊtrue,��ý���,false��꽫ѡ���и��ı���
		this.add(pwdTf);
		
		//5.ȷ����ť
		JButton cfmBtn = new JButton("��¼");
		cfmBtn.setBounds(80, 300, 80, 30);
		this.add(cfmBtn);
		
		//6.ȡ����ť
		JButton cclBtn = new JButton("ȡ��");
		cclBtn.setBounds(200, 300, 80, 30);
		this.add(cclBtn);
		
		//��ӱ���ͼƬ
		ImageIcon img = new ImageIcon("img/userLogin.gif");
		JLabel imgLabel = new JLabel(img);
		imgLabel.setBounds(0, 0, 360, 360);
		this.add(imgLabel);
		
		
		//ʹͼ��λ����Ļ������
		int x = Toolkit.getDefaultToolkit().getScreenSize().width/2 - 200;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height/2 - 150;
		this.setBounds(x, y, 360, 360);
		this.setVisible(true);
	}
}
