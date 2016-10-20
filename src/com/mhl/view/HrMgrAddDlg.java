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
 * �����Ա��                
 *************************************************************************/
public class HrMgrAddDlg extends JDialog{
	private static final long serialVersionUID = 1L;
	private int width = 500;
	private int height = 400;

	public HrMgrAddDlg() {
		//��ʼ�����
		this.initComponets();
		
		//��ʼ��Ĭ������
		this.initDefault();
	}
	/** ��ʼ�����*/
	private void initComponets() {
		this.setLayout(null);
		//����
		JLabel nameLbl = new JLabel("����:");
		nameLbl.setFont(FontTool.FONT1);
		nameLbl.setBounds(50, 30, 100, 14);
		this.add(nameLbl);
		//�����ı���
		JTextField nameTxt = new JTextField(30);
		nameTxt.setBounds(108, 28, 240, 25);
		this.add(nameTxt);
		
		//�Ա�
		JLabel genderLbl = new JLabel("�Ա�:");
		genderLbl.setFont(FontTool.FONT1);
		genderLbl.setBounds(50, 60, 100, 14);
		this.add(genderLbl);
		//�Ա�radio
		ButtonGroup sexGroup = new ButtonGroup();
		JRadioButton man = new JRadioButton("��");
		JRadioButton lady = new JRadioButton("Ů");
		man.setFont(FontTool.FONT1);
		lady.setFont(FontTool.FONT1);
		man.setBounds(108, 58, 50, 14);
		lady.setBounds(168, 58, 50, 14);
		sexGroup.add(man);
		sexGroup.add(lady);
		this.add(man);
		this.add(lady);
		
		//��������
		JLabel btdLbl = new JLabel("��������:");
		btdLbl.setFont(FontTool.FONT1);
		btdLbl.setBounds(22, 90, 100, 14);
		this.add(btdLbl);
		//����ѡ���(��)
		String[] years = new String[30];
		for(int i=0, y=2013; i<years.length; i++){
			years[i] = y-- + "";
		}
		JComboBox yearBox = new JComboBox(years);
		yearBox.setBounds(108, 85, 60, 25);
		this.add(yearBox);
		//��
		JLabel yearLbl = new JLabel("��");
		yearLbl.setFont(FontTool.FONT1);
		yearLbl.setBounds(175, 90, 30, 14);
		this.add(yearLbl);
		//ѡ���(��))
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
		//��
		JLabel monthLbl = new JLabel("��");
		monthLbl.setFont(FontTool.FONT1);
		monthLbl.setBounds(253, 90, 30, 14);
		this.add(monthLbl);
		//ѡ���(��)
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
		//��
		JLabel dayLbl = new JLabel("��");
		dayLbl.setFont(FontTool.FONT1);
		dayLbl.setBounds(336, 90, 30, 14);
		this.add(dayLbl);
		
		//���֤��
		JLabel idLbl = new JLabel("���֤��:");
		idLbl.setFont(FontTool.FONT1);
		idLbl.setBounds(22, 120, 100, 14);
		this.add(idLbl);
		//���֤���ı���
		JTextField idTxt = new JTextField(30);
		idTxt.setBounds(108, 118, 240, 25);
		this.add(idTxt);
		
		//ѧ��
		JLabel eduLbl = new JLabel("ѧ��:");
		eduLbl.setFont(FontTool.FONT1);
		eduLbl.setBounds(50, 150, 100, 14);
		this.add(eduLbl);
		//ѧ��ֵ
		String[] edus = {"��", "��ר", "��", "Сѧ", "ְ��", "��ѧ", "��ר"};
		JComboBox eduBox = new JComboBox(edus);
		eduBox.setBounds(108, 148, 60, 25);
		this.add(eduBox);
		
		//���
		JLabel marryLbl = new JLabel("���:");
		marryLbl.setFont(FontTool.FONT1);
		marryLbl.setBounds(50, 180, 100, 14);
		this.add(marryLbl);
		//���radio
		ButtonGroup marryGp = new ButtonGroup();
		JRadioButton md = new JRadioButton("�ѻ�");
		JRadioButton um = new JRadioButton("δ��");
		md.setFont(FontTool.FONT1);
		um.setFont(FontTool.FONT1);
		md.setBounds(108, 178, 60, 14);
		um.setBounds(178, 178, 60, 14);
		marryGp.add(md);
		marryGp.add(um);
		this.add(md);
		this.add(um);
		
		//��Ƭ
		JPanel picPanel = new JPanel();
		picPanel.setLayout(new BorderLayout());
		JButton addPicBtn = new JButton("�����Ƭ");
		picPanel.add(addPicBtn, BorderLayout.SOUTH);
		picPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		picPanel.setBounds(360, 30, 130, 164);
		this.add(picPanel);
		
		//��ַ
		JLabel addLbl = new JLabel("��ַ:");
		addLbl.setFont(FontTool.FONT1);
		addLbl.setBounds(50, 210, 100, 14);
		this.add(addLbl);
		//��ַ�ı���
		JTextField addTxt = new JTextField(30);
		addTxt.setBounds(108, 208, 380, 25);
		this.add(addTxt);
		
		//ְλ
		JLabel posLbl = new JLabel("ְλ:");
		posLbl.setFont(FontTool.FONT1);
		posLbl.setBounds(50, 240, 100, 14);
		this.add(posLbl);
		//ְλֵ
		String[] posts = {"�ɹ�Ա", "��ʦ", "����Ա", "����", "���", "��λ", "����Ա"};
		JComboBox posBox = new JComboBox(posts);
		posBox.setBounds(108, 238, 80, 25);
		this.add(posBox);
		
		//���
		JButton addBtn = new JButton("���");
		JButton escBtn = new JButton("�˳�");
		addBtn.setFont(FontTool.FONT1);
		escBtn.setFont(FontTool.FONT1);
		addBtn.setBounds(105, 300, 80, 30);
		escBtn.setBounds(315, 300, 80, 30);
		this.add(addBtn);
		this.add(escBtn);
	}
	/** ��ʼ��Ĭ������*/
	private void initDefault() {
		this.setTitle("��Ա���Ǽ�");
		Image logo = new ImageIcon("img/cup.gif").getImage();
		this.setIconImage(logo);
		//Dialog������ʾ
		int x = Toolkit.getDefaultToolkit().getScreenSize().width/2 - width/2;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height/2 - height/2;
		this.setBounds(x, y, width, height);
		this.setVisible(true);
	}
}
