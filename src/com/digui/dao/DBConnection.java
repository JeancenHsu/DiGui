package com.digui.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection conn;
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/digui?useUnicode=true&characterEncoding=gbk";
			String user = "u1";
			String password = "abc";
			conn = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("error!");
		}
		return conn;
	}
}