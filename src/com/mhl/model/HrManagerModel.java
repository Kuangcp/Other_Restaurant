package com.mhl.model;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import com.mhl.db.SqlHelper;
import com.mhl.po.User;

/*************************************************************************
 * �������¹���ϵͳ��model                
 *************************************************************************/
public class HrManagerModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private Vector<String> columnNames;	//�������
	private Vector<Vector<String>> rowData;	//��������
	
	/** ��ѯ����(ȫ����ѯ)*/
	public HrManagerModel() {
		SqlHelper sh = new SqlHelper();
		rowData = sh.getAllRowData();
		columnNames = sh.getColumnNames();
	}
	public HrManagerModel(String name) {
		SqlHelper sh = new SqlHelper();
		rowData = sh.getRowDataByName(name);
		columnNames = sh.getColumnNames();
	}
	/** ��ѯ����*/
	public Vector<Vector<String>> queryByName(String name){
		SqlHelper sh = new SqlHelper();
		rowData = sh.getRowDataByName(name);
		columnNames = sh.getColumnNames();
		return rowData;
	}
	/** ��ѯȫ���Ĳ���*/
	public Vector<Vector<String>> queryAll(){
		SqlHelper sh = new SqlHelper();
		rowData = sh.getAllRowData();
		columnNames = sh.getColumnNames();
		return rowData;
	}
	/** ��������*/
	public boolean addUser(User user){
		SqlHelper sh = new SqlHelper();
		return sh.addUser(user);
	}
	/** �޸�����*/
	public boolean modUser(User user){
		SqlHelper sh = new SqlHelper();
		return sh.modUser(user);
	}
	/** ɾ������*/
	public boolean delUser(String Ա����){
		SqlHelper sh = new SqlHelper();
		return sh.delUser(Ա����);
	}
	@Override
	public int getRowCount() {
		return rowData.size();
	}
	@Override
	public int getColumnCount() {
		return columnNames.size();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return rowData.get(rowIndex).get(columnIndex);
	}
	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}
}
