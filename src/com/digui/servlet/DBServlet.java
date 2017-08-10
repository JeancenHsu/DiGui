package com.digui.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class DBServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Connection conn = null;
	protected Statement stmt = null;
	protected ServletConfig config;
	
	protected boolean open = false;
	
	public String driver;
	public String url;
	public String user;
	public String password;
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public boolean conn(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			
			open = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return open;
	}
	
	
	
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		driver = config.getInitParameter("driver");
		url = config.getInitParameter("url")+"?useUnicode=true&characterEncoding=utf8";
		user = config.getInitParameter("user");
		password = config.getInitParameter("password");
		conn();
		super.init(config);
		
		System.out.println("ok");
	}
	
	public void init() throws ServletException {
		super.init();
	}
	
//	public void service(ServletRequest req, ServletResponse res)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.service(req, res);
//	}
	
	public void destroy() {
		try {
			if(stmt !=null && !stmt.isClosed()){
				stmt.close();
			}
			if(conn !=null && !conn.isClosed()){
				conn.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.destroy();
	}
}
