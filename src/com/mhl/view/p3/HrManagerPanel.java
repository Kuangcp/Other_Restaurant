package com.mhl.view.p3;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.mhl.model.HrManagerModel;
import com.mhl.tool.FontTool;
import com.mhl.view.HrMgrAddDlg;

/*************************************************************************
 * ���¹������                
 *************************************************************************/
public class HrManagerPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField userNameTF;	//��ѯ�ı���
	private JButton freshBtn;	//��ѯ��ť
	private JTable hrTable;	//��������table
	private HrManagerModel mm = new HrManagerModel();
	private JLabel records;
	private JPanel sourthPanel;
	private JButton addBtn;		//��Ӱ�ť

	public HrManagerPanel() {
		this.setLayout(new BorderLayout());
		
		//��ʼ���������
		this.initNorth();
		
		//��ʼ���в����
		this.initCenter();
		
		//��ʼ���ϲ����
		this.initSourth();
		
		//��ʼ���ߴ�
		this.initSize();
	}
	/** ��ʼ���������*/
	private void initNorth() {
		JLabel userName = new JLabel("����������(Ա���Ż�ְλ):");
		userNameTF = new JTextField(30);
		freshBtn = new JButton("ˢ��");
		freshBtn.addActionListener(this);
		
		//��������
		userName.setFont(FontTool.FONT1);
		freshBtn.setFont(FontTool.FONT1);
		
		//������
		JPanel northPanel = new JPanel();
		northPanel.add(userName);
		northPanel.add(userNameTF);
		northPanel.add(freshBtn);
		
		this.add(northPanel, BorderLayout.NORTH);
	}
	/** ��ʼ���в����*/
	private void initCenter() {
		//�������
		mm = new HrManagerModel();
		hrTable = new JTable(mm);
		
		//��ӱ��
		JScrollPane jsp = new JScrollPane(hrTable);
		//���ñ��߿�
		jsp.setBorder(BorderFactory.createLoweredBevelBorder());
		this.add(jsp, BorderLayout.CENTER);
	}
	/** ��ʼ���ϲ����*/
	private void initSourth() {
		//��ʾ��¼��Ϣ
		records = showRecords();
		
		//4����ť
		JButton dtlBtn = new JButton("��ϸ��Ϣ");
		addBtn = new JButton("���");
		JButton modBtn = new JButton("�޸�");
		JButton delBtn = new JButton("ɾ��");
		
		//��������
		dtlBtn.setFont(FontTool.FONT1);
		addBtn.setFont(FontTool.FONT1);
		modBtn.setFont(FontTool.FONT1);
		delBtn.setFont(FontTool.FONT1);
		
		//��ť���
		JPanel btnPanel = new JPanel();
		btnPanel.add(dtlBtn);
		btnPanel.add(addBtn);
		btnPanel.add(modBtn);
		btnPanel.add(delBtn);
		
		//��Ӽ���
		addBtn.addActionListener(this);
		
		//��ӵ����Ե�λ��
		sourthPanel = new JPanel(new BorderLayout());
		sourthPanel.add(records, BorderLayout.CENTER);
		sourthPanel.add(btnPanel, BorderLayout.EAST);
		this.add(sourthPanel, BorderLayout.SOUTH);
	}
	/** ��ʼ���ߴ�*/
	private void initSize() {
		//����ӦЧ��
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(width, height);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		if(freshBtn == target){
			String name = userNameTF.getText();
			mm = new HrManagerModel(name);
			hrTable.setModel(mm);
			sourthPanel.remove(records);
			records = showRecords();
			sourthPanel.add(records, BorderLayout.CENTER);
		}else if(addBtn == target){
			new HrMgrAddDlg();
		}
		
	}
	/** ��ʾ��¼����*/
	private JLabel showRecords() {
		int count = mm.getRowCount();
		String tps = String.format("����%s����¼", count);
		JLabel records = new JLabel(tps);
		records.setFont(FontTool.FONT1);
		return records;
	}
}
