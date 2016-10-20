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
 * 连接数据库的帮助类                
 *************************************************************************/
public class SqlHelper{
	private Connection cnn;	//数据库连接
	private PreparedStatement ps;	//执行语句
	private Vector<String> columnNames = new Vector<String>();		//要查询的列名
	
	public SqlHelper() {
		columnNames.add("员工号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("职位");
	}
	/** 创建数据库连接*/
	public Connection getConn(){
		Connection cnn = null;
		try {
			//1.加载驱动
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//2.获得数据库的连接(完全的连接方式,选用windows登陆的方式不用写用户名,密码)
			cnn = DriverManager.getConnection("jdbc:odbc:mhl_jdbc_odbc", "sa", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnn;
	}
	/** 关闭数据库资源*/
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
	/** 读取操作的查询语句*/
	public Vector<Vector<String>> getRowData(String sql, String[] paras){
		Vector<Vector<String>> rowData = new Vector<Vector<String>>();
		Connection cnn = getConn();
		try {
			PreparedStatement ps = cnn.prepareStatement(sql);
			//注入数据
			for(int i=0; i<paras.length; i++){
				ps.setString(i+1, paras[i]);
			}
			ResultSet resultSet = ps.executeQuery();
			ResultSetMetaData metaData = null;
			while(resultSet.next()){
				Vector<String> singleRow = new Vector<String>();
				metaData = resultSet.getMetaData();	//包含结果集的元信息
				for(int i=0; i<metaData.getColumnCount(); i++){
					String columName = metaData.getColumnName(i+1);
					String columValue = resultSet.getString(columName);
					singleRow.add(columValue);
				}
				rowData.add(singleRow);
			}
			//初始化列名
			if(metaData != null){
				//清空默认的属性
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
	/** 查询所有的结果*/
	public Vector<Vector<String>> getAllRowData(){
		String sql = "SELECT 员工号, 姓名, 性别, 职位 FROM 人事资料 WHERE 1=?";
		String[] paras = {"1"};
		Vector<Vector<String>> result = this.getRowData(sql, paras);
		return result;
	}
	/** 根据姓名查询结果*/
	public Vector<Vector<String>> getRowDataByName(String name){
		String sql = "SELECT 员工号, 姓名, 性别, 职位 FROM 人事资料 WHERE 姓名=?";
		if(name==null || name.trim().equals("")){
			return this.getAllRowData();
		}
		String[] paras = {name};
		Vector<Vector<String>> result = this.getRowData(sql, paras);
		return result;
	}
	/** 更新操作*/
	public boolean updateRowData(String sql, String[] paras){
		Connection cnn = getConn();
		try {
			PreparedStatement ps = cnn.prepareStatement(sql);
			//注入数据
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
	/** 增加数据*/
	public boolean addUser(User user){
		Map<String, String> props = new HashMap<String, String>();
		String 员工号 = null;
		if(user.get姓名() != null)	props.put("姓名", user.get姓名());
		if(user.get照片() != null)	props.put("照片", user.get照片());
		if(user.get性别() != null)	props.put("性别", user.get性别());
		if(user.get地址() != null)	props.put("地址", user.get地址());
		if(user.get出生日期() != null)	props.put("出生日期", user.get出生日期());
		if(user.get身份证号() != null)	props.put("身份证号", user.get身份证号());
		if(user.get学历() != null)	props.put("学历", user.get学历());
		if(user.get职位() != null)	props.put("职位", user.get职位());
		if(user.get婚否() != null)	props.put("婚否", user.get婚否());
		if(user.get员工号() != null)	props.put("员工号", user.get员工号());
		
		StringBuilder sb = new StringBuilder("INSERT INTO 人事资料 VALUES( ");
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
	/** 删除数据*/
	public boolean delUser(String 员工号){
		String sql = "DELETE 人事资料 WHERE 员工号=?";
		String[] paras = {员工号};
		return this.updateRowData(sql, paras);
	}
	/** 修改数据*/
	public boolean modUser(User user){
		Map<String, String> props = new HashMap<String, String>();
		String 员工号 = null;
		if(user.get姓名() != null)	props.put("姓名", user.get姓名());
		if(user.get照片() != null)	props.put("照片", user.get照片());
		if(user.get性别() != null)	props.put("性别", user.get性别());
		if(user.get地址() != null)	props.put("地址", user.get地址());
		if(user.get出生日期() != null)	props.put("出生日期", user.get出生日期());
		if(user.get身份证号() != null)	props.put("身份证号", user.get身份证号());
		if(user.get学历() != null)	props.put("学历", user.get学历());
		if(user.get职位() != null)	props.put("职位", user.get职位());
		if(user.get婚否() != null)	props.put("婚否", user.get婚否());
		if(user.get员工号() != null)		员工号 = user.get员工号();
		
		StringBuilder sb = new StringBuilder("UPDATE 人事资料 SET ");
		List<String> parasStr = new ArrayList<String>();
		String[] keySet = (String[])props.keySet().toArray();
		for(int i=0; i<keySet.length; i++){
			sb.append(String.format("%s=?, ", keySet[i]));
			parasStr.add(props.get(keySet[i]));
		}
		sb.append(String.format("WHERE 员工号=%s", 员工号));
		String sqlStr = sb.toString();
		String sql = sqlStr.replace(", WHERE", " WHERE");
		String[] paras = (String[])parasStr.toArray();
		return this.updateRowData(sql, paras);
	}
	public Vector<String> getColumnNames() {
		return columnNames;
	}
}
