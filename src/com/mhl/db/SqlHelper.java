package com.mhl.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import com.mhl.po.User;

/*************************************************************************
 * �������ݿ�İ�����                
 *************************************************************************/
public class SqlHelper{
	private Connection cnn;	//���ݿ�����
	private PreparedStatement ps;	//ִ�����
	private Vector<String> columnNames = new Vector<String>();		//Ҫ��ѯ������
	
	public SqlHelper() {
		columnNames.add("Ա����");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("ְλ");
	}
	/** �������ݿ�����*/
	public Connection getConn(){
		Connection cnn = null;
		try {
			//1.��������
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//2.������ݿ������(��ȫ�����ӷ�ʽ,ѡ��windows��½�ķ�ʽ����д�û���,����)
			cnn = DriverManager.getConnection("jdbc:odbc:mhl_jdbc_odbc", "sa", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnn;
	}
	/** �ر����ݿ���Դ*/
	public void close(){
		try {
			if (ps != null) {
				ps.close();
			}
			if (cnn != null) {
				cnn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/** ��ȡ�����Ĳ�ѯ���*/
	public Vector<Vector<String>> getRowData(String sql, String[] paras){
		Vector<Vector<String>> rowData = new Vector<Vector<String>>();
		Connection cnn = getConn();
		try {
			PreparedStatement ps = cnn.prepareStatement(sql);
			//ע������
			for(int i=0; i<paras.length; i++){
				ps.setString(i+1, paras[i]);
			}
			ResultSet resultSet = ps.executeQuery();
			ResultSetMetaData metaData = null;
			while(resultSet.next()){
				Vector<String> singleRow = new Vector<String>();
				metaData = resultSet.getMetaData();	//�����������Ԫ��Ϣ
				for(int i=0; i<metaData.getColumnCount(); i++){
					String columName = metaData.getColumnName(i+1);
					String columValue = resultSet.getString(columName);
					singleRow.add(columValue);
				}
				rowData.add(singleRow);
			}
			//��ʼ������
			if(metaData != null){
				//���Ĭ�ϵ�����
				columnNames.clear();
				for(int i=0; i<metaData.getColumnCount(); i++){
					String columName = metaData.getColumnName(i+1);
					columnNames.add(columName);
				}
			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowData;
	}
	/** ��ѯ���еĽ��*/
	public Vector<Vector<String>> getAllRowData(){
		String sql = "SELECT Ա����, ����, �Ա�, ְλ FROM �������� WHERE 1=?";
		String[] paras = {"1"};
		Vector<Vector<String>> result = this.getRowData(sql, paras);
		return result;
	}
	/** ����������ѯ���*/
	public Vector<Vector<String>> getRowDataByName(String name){
		String sql = "SELECT Ա����, ����, �Ա�, ְλ FROM �������� WHERE ����=?";
		if(name==null || name.trim().equals("")){
			return this.getAllRowData();
		}
		String[] paras = {name};
		Vector<Vector<String>> result = this.getRowData(sql, paras);
		return result;
	}
	/** ���²���*/
	public boolean updateRowData(String sql, String[] paras){
		Connection cnn = getConn();
		try {
			PreparedStatement ps = cnn.prepareStatement(sql);
			//ע������
			for(int i=0; i<paras.length; i++){
				ps.setString(i+1, paras[i]);
			}
			int effect = ps.executeUpdate();
			if(effect < 1){
				return false;
			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	/** ��������*/
	public boolean addUser(User user){
		Map<String, String> props = new HashMap<String, String>();
		String Ա���� = null;
		if(user.get����() != null)	props.put("����", user.get����());
		if(user.get��Ƭ() != null)	props.put("��Ƭ", user.get��Ƭ());
		if(user.get�Ա�() != null)	props.put("�Ա�", user.get�Ա�());
		if(user.get��ַ() != null)	props.put("��ַ", user.get��ַ());
		if(user.get��������() != null)	props.put("��������", user.get��������());
		if(user.get���֤��() != null)	props.put("���֤��", user.get���֤��());
		if(user.getѧ��() != null)	props.put("ѧ��", user.getѧ��());
		if(user.getְλ() != null)	props.put("ְλ", user.getְλ());
		if(user.get���() != null)	props.put("���", user.get���());
		if(user.getԱ����() != null)	props.put("Ա����", user.getԱ����());
		
		StringBuilder sb = new StringBuilder("INSERT INTO �������� VALUES( ");
		List<String> parasStr = new ArrayList<String>();
		String[] keySet = (String[])props.keySet().toArray();
		for(int i=0; i<keySet.length; i++){
			sb.append(String.format("?, ", keySet[i]));
			parasStr.add(props.get(keySet[i]));
		}
		sb.append(")");
		String sqlStr = sb.toString();
		String sql = sqlStr.replace(", )", ")");
		String[] paras = (String[])parasStr.toArray();
		return this.updateRowData(sql, paras);
	}
	/** ɾ������*/
	public boolean delUser(String Ա����){
		String sql = "DELETE �������� WHERE Ա����=?";
		String[] paras = {Ա����};
		return this.updateRowData(sql, paras);
	}
	/** �޸�����*/
	public boolean modUser(User user){
		Map<String, String> props = new HashMap<String, String>();
		String Ա���� = null;
		if(user.get����() != null)	props.put("����", user.get����());
		if(user.get��Ƭ() != null)	props.put("��Ƭ", user.get��Ƭ());
		if(user.get�Ա�() != null)	props.put("�Ա�", user.get�Ա�());
		if(user.get��ַ() != null)	props.put("��ַ", user.get��ַ());
		if(user.get��������() != null)	props.put("��������", user.get��������());
		if(user.get���֤��() != null)	props.put("���֤��", user.get���֤��());
		if(user.getѧ��() != null)	props.put("ѧ��", user.getѧ��());
		if(user.getְλ() != null)	props.put("ְλ", user.getְλ());
		if(user.get���() != null)	props.put("���", user.get���());
		if(user.getԱ����() != null)		Ա���� = user.getԱ����();
		
		StringBuilder sb = new StringBuilder("UPDATE �������� SET ");
		List<String> parasStr = new ArrayList<String>();
		String[] keySet = (String[])props.keySet().toArray();
		for(int i=0; i<keySet.length; i++){
			sb.append(String.format("%s=?, ", keySet[i]));
			parasStr.add(props.get(keySet[i]));
		}
		sb.append(String.format("WHERE Ա����=%s", Ա����));
		String sqlStr = sb.toString();
		String sql = sqlStr.replace(", WHERE", " WHERE");
		String[] paras = (String[])parasStr.toArray();
		return this.updateRowData(sql, paras);
	}
	public Vector<String> getColumnNames() {
		return columnNames;
	}
}
