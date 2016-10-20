package com.mhl.db;

import java.io.FileOutputStream;
import java.io.IOException;
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
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import com.mhl.po.User;

/*************************************************************************
 * 用XML文件存放数据       
 *************************************************************************/
public class XmlHelper{
	private Connection cnn;	//数据库连接
	private PreparedStatement ps;	//执行语句
	private Vector<String> columnNames = new Vector<String>();		//要查询的列名
	
	public XmlHelper() {
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
//		String sql = "SELECT 员工号, 姓名, 性别, 职位 FROM 人事资料 WHERE 1=?";
		String sql = "SELECT 员工号, 姓名, 照片, 性别, 地址, 出生日期, 身份证号, 学历, 职位, 婚否  FROM 人事资料 WHERE 1=?";
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
	
	public static void main(String[] args) {
		XmlHelper helper = new XmlHelper();
		
		//1.把数据库中的数据迁移到XML中
		helper.copyDb2Xml("xml/人事资料.xml");
	}
	/** 把数据库中的数据迁移到XML中*/
	private void copyDb2Xml(String xmlPath) {
		//把数据库中的内容迁移过来
		XmlHelper helper = new XmlHelper();
		Vector<Vector<String>> rowData = helper.getAllRowData();
		Document doc = helper.loadXML(xmlPath);
		Element rootElement = doc.getRootElement();
		for(int i=0; i<rowData.size(); i++){
			Vector<String> row = rowData.get(i);
			Element emp = DocumentHelper.createElement("员工"); 
			Element empId = DocumentHelper.createElement("员工号"); 
			Element name = DocumentHelper.createElement("姓名"); 
			Element pic = DocumentHelper.createElement("照片"); 
			Element gender = DocumentHelper.createElement("性别"); 
			Element addr = DocumentHelper.createElement("地址"); 
			Element birth = DocumentHelper.createElement("出生日期"); 
			Element cardId = DocumentHelper.createElement("身份证号"); 
			Element edu = DocumentHelper.createElement("学历"); 
			Element job = DocumentHelper.createElement("职位"); 
			Element isMarried = DocumentHelper.createElement("婚否");
			
			empId.setText(row.get(0));
			name.setText(row.get(1));
			pic.setText(row.get(2));
			gender.setText(row.get(3));
			addr.setText(row.get(4));
			birth.setText(row.get(5));
			cardId.setText(row.get(6));
			edu.setText(row.get(7));
			job.setText(row.get(8));
			isMarried.setText(row.get(9));
			emp.add(empId);
			emp.add(name);
			emp.add(pic);
			emp.add(gender);
			emp.add(addr);
			emp.add(birth);
			emp.add(cardId);
			emp.add(edu);
			emp.add(job);
			emp.add(isMarried);
			rootElement.add(emp);
		}
		helper.saveToXml(xmlPath, rootElement);
	}
	/** 加载XML文档*/
	private Document loadXML(String xmlPath){
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(xmlPath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}
	/** 保存到XML文件中*/
	private void saveToXml(String xmlPath, Node rootNode) {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(xmlPath), format);
			xmlWriter.write(rootNode);
			xmlWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
