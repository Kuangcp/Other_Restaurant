package com.mhl.model;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import com.mhl.db.SqlHelper;
import com.mhl.po.User;

/*************************************************************************
 * 更新人事管理系统的model                
 *************************************************************************/
public class HrManagerModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private Vector<String> columnNames;	//表格列名
	private Vector<Vector<String>> rowData;	//列数据名
	
	/** 查询操作(全部查询)*/
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
	/** 查询操作*/
	public Vector<Vector<String>> queryByName(String name){
		SqlHelper sh = new SqlHelper();
		rowData = sh.getRowDataByName(name);
		columnNames = sh.getColumnNames();
		return rowData;
	}
	/** 查询全部的操作*/
	public Vector<Vector<String>> queryAll(){
		SqlHelper sh = new SqlHelper();
		rowData = sh.getAllRowData();
		columnNames = sh.getColumnNames();
		return rowData;
	}
	/** 增加数据*/
	public boolean addUser(User user){
		SqlHelper sh = new SqlHelper();
		return sh.addUser(user);
	}
	/** 修改数据*/
	public boolean modUser(User user){
		SqlHelper sh = new SqlHelper();
		return sh.modUser(user);
	}
	/** 删除数据*/
	public boolean delUser(String 员工号){
		SqlHelper sh = new SqlHelper();
		return sh.delUser(员工号);
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
