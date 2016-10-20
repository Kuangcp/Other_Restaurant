package com.mhl.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import com.mhl.tool.FontTool;

/*************************************************************************
 * 开机进入的闪屏类
 * 文字内容:
 * 系统正在加载,请稍后****
 * 满汉楼
 * 融满汉精华
 * 做天下美味
 * 招八方食客
 * 接四海宾朋 
 * -满汉楼工作室
 * paint和paintComponents的区别:
 * paint绘制的是整个画面
 * paintComponents是绘制单一的组件,效率较高
 *************************************************************************/
public class FlashScreen extends JWindow implements Runnable{
	private static final long serialVersionUID = 1L;
	private String[] ads = {"满","汉","楼","融","满","汉","精","华","做","天","下","美","味","招","八","方","食","客","接","四","海","宾","朋","|","满","汉","楼","工","作","室"};
	private int i = 0;
	
	public static void main(String[] args) {
		FlashScreen t = new FlashScreen(); 
		new Thread(t).start();
	}
	public FlashScreen() {
		this.setLayout(null);
		//添加 图片
		ImageIcon img = new ImageIcon("img/flashscreen.jpg");
		JLabel jlabel = new JLabel(img);
		jlabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		this.add(jlabel);
		
		//使图像位于屏幕正中央
		int x = Toolkit.getDefaultToolkit().getScreenSize().width/2 - 200;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height/2 - 150;
		this.setBounds(x, y, 400, 300);
		this.setVisible(true);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int j=0; j<=i; j++){
			//题词
			g.setColor(Color.RED);
			g.setFont(FontTool.FONT2);
			if(j<3){
				g.drawString(ads[j], 250, 70+25*j);
			}else if(j<8){
				g.drawString(ads[j], 210, 70+25*(j-3));
			}else if(j<13){
				g.drawString(ads[j], 170, 70+25*(j-8));
			}else if(j<18){
				g.drawString(ads[j], 130, 70+25*(j-13));
			}else if(j<23){
				g.drawString(ads[j], 90, 70+25*(j-18));
			}else if(j<30){
				g.setColor(Color.BLUE);
				g.drawString(ads[j], 50, 70+25*(j-23));
			}
		}
		
		//进度条
		g.setFont(FontTool.FONT0);
		Random r = new Random();
		int red = r.nextInt(255);
		int green = r.nextInt(255);
		int blue = r.nextInt(255);
		Color borderColor = new Color(red, green, blue);
		g.setColor(borderColor);
		//外边的边框
		g.drawRect(5, 249, 390, 48);
		//里边的进度矩形条
		g.setColor(Color.YELLOW);
		g.fillRect(10, 254, 20+12*i, 38);
		//边界的小光标
		g.setColor(borderColor);
		g.fillRect(20+12*i, 250, 10, 45);
		//提示文字
		g.setColor(Color.BLACK);
		if(i%4 == 3){
			g.drawString("系  统  正  在  加  载 , 请  稍  后  *", 80, 280);
		}else if(i%4 == 0){
			g.drawString("系  统  正  在  加  载 , 请  稍  后  * *", 80, 280);
		}else if(i%4 == 1){
			g.drawString("系  统  正  在  加  载 , 请  稍  后  * * *", 80, 280);
		}else{
			g.drawString("系  统  正  在  加  载 , 请  稍  后  * * * *", 80, 280);
		}
	}
	@Override
	public void run() {
		while(i<ads.length){
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
			i++;
		}
		//暂停一下
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.dispose();
	}
}
