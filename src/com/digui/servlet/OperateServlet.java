package com.digui.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OperateServlet extends DBServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		request=req;
		response=resp;
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8"); //GET方式无效
		System.out.println("ok");
		if(request.getParameter("method").toString().equals("doLogin")){
			doLogin();
		}
		if(request.getParameter("method").toString().equals("doRegister")){
			doRegister();
		}
		if(request.getParameter("method").toString().equals("doAddinfo")){
			doAddinfo();
		}
		if(request.getParameter("method").toString().equals("createOrder")){
			createOrder();
		}
		if(request.getParameter("method").toString().equals("confirmOrder")){
			confirmOrder();
		}
		if(request.getParameter("method").toString().equals("choosePayWay")){
			choosePayWay();
		}
		if(request.getParameter("method").toString().equals("confirmDeliver")){
			confirmDeliver();
		}
		if(request.getParameter("method").toString().equals("confirmReceivePack")){
			confirmReceivePack();
		}
		if(request.getParameter("method").toString().equals("assess")){
			assess();
		}
		if(request.getParameter("method").toString().equals("doAdminLogin")){
			doAdminLogin();
		}
		if(request.getParameter("method").toString().equals("changeDefaultRec")){
			changeDefaultRec();
		}
		
		
		
	}
	
	public void doLogin(){
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		String phone_num = request.getParameter("userphone");
		String password = request.getParameter("password");
		HttpSession session=request.getSession();
		//session.setAttribute("role", role);
		String sql = "select name,school_name,user_id from user_info where phone_num=? and password=?";
		String sql2 = "select current_timestamp()";
		String sql3 = "update user_info set last_login_time=? where phone_num=? ";

		PreparedStatement pst,pst2;
		Statement stmt;
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, phone_num);
				pst.setString(2, password);
				ResultSet rst = pst.executeQuery();
				if(rst.next()){
//					resp.getWriter().print("sucess");
					String name = rst.getString(1);
					String school_name = rst.getString(2);
					int user_id=rst.getInt(3);
					session.setAttribute("name", name);
					session.setAttribute("school_name", school_name);
					session.setAttribute("user_id", user_id);
					
					stmt = conn.createStatement();
					ResultSet rst2 = stmt.executeQuery(sql2);
					java.sql.Timestamp time;
					
					if(rst2.next()){
						time=rst2.getTimestamp(1);
						pst2=conn.prepareStatement(sql3);
						pst2.setTimestamp(1, time);
						pst2.setString(2, phone_num);
						pst2.executeUpdate();
					}
					
					
					response.sendRedirect("home.jsp");
					pst.close();
				}else{
					response.sendRedirect("login_failture.html");
					pst.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void doRegister(){
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpSession session=request.getSession();
		
		String phone_num = request.getParameter("userphone");
		String password = request.getParameter("password");
		
		
		String sql = "select phone_num from user_info where phone_num=?";
		String sql1 = "insert into user_info(phone_num,password) values(?,?)";
		String sql1_1 = "insert into rec_info(user_id,rec_info_id,phone_num) values(?,1,?)";
		String sql2 = "select user_id from user_info where phone_num=?";
		String sql3 = "select current_timestamp()";
		String sql4 = "update user_info set register_time=? where user_id=? ";
		
		Statement stmt;
		PreparedStatement pst,pst1,pst1_1,pst2,pst3;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, phone_num);
			ResultSet rst = pst.executeQuery();
			if(rst.next()){
				session.setAttribute("erroMsg", "该手机号已注册！");
				response.sendRedirect("register_fail.jsp");
				pst.close();
			}else{
				//写user_info表
				pst1 = conn.prepareStatement(sql1);
				pst1.setString(1, phone_num);
				pst1.setString(2,password);
				int i=pst1.executeUpdate();
				if(i>0){
					//查user_id
					pst2 = conn.prepareStatement(sql2);
					pst2.setString(1, phone_num);
					ResultSet rst2 = pst2.executeQuery();
					if(rst2.next()){
						int user_id = rst2.getInt(1);
						session.setAttribute("user_id", user_id);
						
						//写rec_info表
						pst1_1 = conn.prepareStatement(sql1_1);
						pst1_1.setInt(1, user_id);
						pst1_1.setString(2, phone_num);
						pst1_1.executeUpdate();
						
						//写注册时间戳
						stmt = conn.createStatement();
						ResultSet rst3 = stmt.executeQuery(sql3);
						java.sql.Timestamp time;
						
						if(rst3.next()){
							time=rst3.getTimestamp(1);
							pst3=conn.prepareStatement(sql4);
							pst3.setTimestamp(1, time);
							pst3.setInt(2, user_id);
							pst3.executeUpdate();
						}
						
					}
					response.sendRedirect("register_info.html");
					pst2.close();
				}else{
					session.setAttribute("erroMsg", "数据库写入失败！");
					response.sendRedirect("register_fail.jsp");
				}
				pst1.close();
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void doAddinfo(){
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpSession session=request.getSession();
		
		int user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String id_num = request.getParameter("id_num");
		String school_name = request.getParameter("school_name");
		String stu_num = request.getParameter("stu_num");
		String address = request.getParameter("address");
	
		
		String sql = "update user_info set name=?,sex=?,id_num=?,school_name=?,stu_num=?,address=? where user_id=?";
		String sql2 = "update rec_info set name=?,sex=?,school_name=?,stu_num=?,address=? where user_id=?";
		PreparedStatement pst,pst2;
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setString(2, sex);
				pst.setString(3, id_num);
				pst.setString(4, school_name);
				pst.setString(5, stu_num);
				pst.setString(6, address);
				pst.setInt(7, user_id);
				
				pst2 = conn.prepareStatement(sql2);
				pst2.setString(1, name);
				pst2.setString(2, sex);
				pst2.setString(3, school_name);
				pst2.setString(4, stu_num);
				pst2.setString(5, address);
				pst2.setInt(6, user_id);
				
				int i = pst.executeUpdate();
				int j = pst2.executeUpdate();
				
				if(i>0 && j>0){
					response.sendRedirect("register_success.html");
					pst.close();
				}else{
					session.setAttribute("erroMsg", "数据库写入失败！");
					response.sendRedirect("register_fail.jsp");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void createOrder(){
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		String company = request.getParameter("company");
		String pack_num = request.getParameter("pack_num");
		int get_num = Integer.parseInt(request.getParameter("get_num"));
		String size = request.getParameter("size");
		int tip_price = Integer.parseInt(request.getParameter("tip_price"));
		int hour = Integer.parseInt(request.getParameter("hour"));
		int minute = Integer.parseInt(request.getParameter("minute"));
		int basic_price = 0;
		if(size.equals("小")){
			basic_price=3;
		}else if(size.equals("中")){
			basic_price=4;
		}else if(size.equals("大")){
			basic_price=5;
		}
		
		int sum_price=basic_price+tip_price;
		
		String require_time=hour+":"+minute+":"+"00";
		
		int user_id=0;
		int pack_info_id=0;
		
		if(session.getAttribute("user_id")!=null){
			user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		}
		
		
		PreparedStatement pst1,pst2,pst3;
		String sql1="select max(pack_info_id) from pack_info where user_id=?";
		String sql2="insert into pack_info(user_id,pack_info_id,company,pack_num,get_num,size,basic_price,tip_price,sum_price,require_time) values(?,?,?,?,?,?,?,?,?,?)";
		String sql3="select name,sex,school_name,stu_num,address,phone_num,rec_info_id from rec_info where user_id=? and rec_info_id=1";
		try {
			pst1 = conn.prepareStatement(sql1);
			pst1.setInt(1, user_id);
			ResultSet rs = pst1.executeQuery();
			if(rs.next()){
				pack_info_id=rs.getInt(1)+1;
			}else{
				pack_info_id=1;
			}
			
			pst2 = conn.prepareStatement(sql2);
			pst2.setInt(1, user_id);
			pst2.setInt(2, pack_info_id);
			pst2.setString(3, company);
			pst2.setString(4, pack_num);
			pst2.setInt(5, get_num);
			pst2.setString(6, size);
			pst2.setInt(7, basic_price);
			pst2.setInt(8, tip_price);
			pst2.setInt(9, sum_price);
			pst2.setString(10, require_time);
			int i = pst2.executeUpdate();
			if(i>0){
				System.out.println("success");
				
				session.setAttribute("user_id", user_id);
				session.setAttribute("pack_info_id", pack_info_id);
				session.setAttribute("company", company);
				session.setAttribute("pack_num", pack_num);
				session.setAttribute("get_num",get_num);
				session.setAttribute("size", size);
				session.setAttribute("basic_price", basic_price);
				session.setAttribute("tip_price", tip_price);
				session.setAttribute("sum_price", sum_price);
				session.setAttribute("require_time", require_time);
				
				
				pst3 =conn.prepareStatement(sql3);
				pst3.setInt(1, user_id);
				ResultSet rs2 = pst3.executeQuery();
				if(rs2.next()){
					String name = rs2.getString(1);
					String sex = rs2.getString(2);
					String school_name = rs2.getString(3);
					String stu_num = rs2.getString(4);
					String address = rs2.getString(5);
					String phone_num = rs2.getString(6);
					String rec_info_id = rs2.getString(7);
					
					session.setAttribute("rec_info_id", rec_info_id);
					session.setAttribute("rec_name", name);
					session.setAttribute("sex", sex);
					session.setAttribute("school_name", school_name);
					session.setAttribute("stu_num", stu_num);
					session.setAttribute("address", address);
					session.setAttribute("phone_num", phone_num);
					
					response.sendRedirect("info.jsp");
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
 
	public void confirmOrder(){
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		int user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		int rec_info_id = Integer.parseInt(session.getAttribute("rec_info_id").toString());
		int pack_info_id = Integer.parseInt(session.getAttribute("pack_info_id").toString());
		int sum_price = Integer.parseInt(session.getAttribute("sum_price").toString());
		int user_order_id=0;
		java.sql.Timestamp start_time = null;
		
		PreparedStatement pst1,pst2;
		Statement stmt;
		String sql1="select max(user_order_id) from order_info where user_id=?";
		String sql2 = "select current_timestamp()";
		String sql3 = "insert into order_info(user_id,user_order_id,rec_info_id,pack_info_id,sum_price,start_time,status) values(?,?,?,?,?,?,?)";
		try {
			pst1=conn.prepareStatement(sql1);
			pst1.setInt(1, user_id);
			ResultSet rs = pst1.executeQuery();
			if(rs.next()){
				user_order_id=rs.getInt(1)+1;
			}else{
				user_order_id=1;
			}
			
			stmt = conn.createStatement();
			ResultSet rst = stmt.executeQuery(sql2);			
			if(rst.next()){
				start_time=rst.getTimestamp(1);
			}
			
			pst2 = conn.prepareStatement(sql3);
			pst2.setInt(1, user_id);
			pst2.setInt(2, user_order_id);
			pst2.setInt(3, rec_info_id);
			pst2.setInt(4, pack_info_id);
			pst2.setInt(5, sum_price);
			pst2.setTimestamp(6, start_time);
			pst2.setString(7, "已下单");
			
			int i = pst2.executeUpdate();
			if(i>0){
				System.out.println("下单成功");
				response.sendRedirect("pay.jsp");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void choosePayWay(){
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		int user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		int pack_info_id = Integer.parseInt(session.getAttribute("pack_info_id").toString());
		
		String pay_way = request.getParameter("pay_way");
		
		PreparedStatement pst;
		String sql = "update order_info set pay_way=? where user_id=? and pack_info_id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, pay_way);
			pst.setInt(2, user_id);
			pst.setInt(3, pack_info_id);
			int i = pst.executeUpdate();
			if(i>0){
				System.out.println("支付成功！");
				response.sendRedirect("pay_success.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void confirmDeliver(){
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		int deliver_user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		String ss[] = request.getParameterValues("hidden");
		int order_ids[]= new int[ss.length];
		int sum=0;
		for(int i=0;i<ss.length;i++){
			order_ids[i]=Integer.parseInt(ss[i]);
			System.out.println(order_ids[i]);
			PreparedStatement pst1;
			String sql1="update order_info set deliver_user_id=?,status=? where order_id=?";
			
			try {
					pst1=conn.prepareStatement(sql1);
					pst1.setInt(1, deliver_user_id);
					pst1.setString(2, "已接单");
					pst1.setInt(3, order_ids[i]);
					int m = pst1.executeUpdate();
					sum=sum+m;
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			if(sum>=ss.length){
				try {
					response.sendRedirect("delivery_success.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}

	public void confirmReceivePack(){
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		int order_id = Integer.parseInt(request.getParameter("order_id"));
		int deliver_user_id = Integer.parseInt(request.getParameter("deliver_user_id"));
		PreparedStatement pst1;
		String sql1="update order_info set status=?,finish_time=current_timestamp() where order_id=?";
		try {
				pst1=conn.prepareStatement(sql1);
				pst1.setString(1, "已完成");
				pst1.setInt(2, order_id);
				int m = pst1.executeUpdate();
				
				session.setAttribute("order_id", order_id);
				session.setAttribute("deliver_user_id", deliver_user_id);
				if(m>0){
					response.sendRedirect("confirm_receive_success.jsp");
				}
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void assess(){
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//HttpSession session = request.getSession();
		//int order_id = Integer.parseInt(request.getParameter("order_id"));
		int quality_point,speed_point;
		if(request.getParameter("quality_point")!=null){
			quality_point = Integer.parseInt(request.getParameter("quality_point"));
		}else{
			quality_point=0;
		}
		
		if(request.getParameter("speed_point")!=null){
			speed_point = Integer.parseInt(request.getParameter("speed_point"));
		}else{
			speed_point=0;
		}
		
		int order_id = Integer.parseInt(request.getParameter("order_id"));
		int deliver_user_id = Integer.parseInt(request.getParameter("deliver_user_id"));
		
		System.out.println(quality_point);
		System.out.println(speed_point);
		System.out.println(order_id);
		
		PreparedStatement pst1,pst2,pst3;
		String sql1="update order_info set quality_point=?,speed_point=?,status='已评价' where order_id=?";
		String sql2="update user_info set get_pack_nums=get_pack_nums+1 where user_id=?";
		String sql3="update user_info set quality_point=(quality_point+?)/get_pack_nums,speed_point=(speed_point+?)/get_pack_nums where user_id=?";
		
		try {
				pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, quality_point);
				pst1.setInt(2, speed_point);
				pst1.setInt(3, order_id);
				int m = pst1.executeUpdate();
				
				pst2=conn.prepareStatement(sql2);
				pst2.setInt(1, deliver_user_id);
				int n=pst2.executeUpdate();
				
				pst3=conn.prepareStatement(sql3);
				pst3.setInt(1, quality_point);
				pst3.setInt(2, speed_point);
				pst3.setInt(3, deliver_user_id);
				int a = pst3.executeUpdate();
				
				
				if(m>0 && n>0 && a>0){
					response.sendRedirect("assess_success.jsp");
				}
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void doAdminLogin(){
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		HttpSession session=request.getSession();
		//session.setAttribute("role", role);
		String sql = "select admin_id,admin_user_name from admin_info where admin_user_name=? and admin_password=?";
		String sql2 = "select current_timestamp()";
		String sql3 = "update admin_info set last_login_time=? where admin_user_name=? ";

		PreparedStatement pst,pst2;
		Statement stmt;
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, user_name);
				pst.setString(2, password);
				ResultSet rst = pst.executeQuery();
				if(rst.next()){
//					resp.getWriter().print("sucess");
					int user_id = rst.getInt(1);
					String name = rst.getString(2);
					session.setAttribute("name", name);
					session.setAttribute("user_id", user_id);
					
					stmt = conn.createStatement();
					ResultSet rst2 = stmt.executeQuery(sql2);
					java.sql.Timestamp time;
					
					if(rst2.next()){
						time=rst2.getTimestamp(1);
						pst2=conn.prepareStatement(sql3);
						pst2.setTimestamp(1, time);
						pst2.setString(2, user_name);
						pst2.executeUpdate();
					}
					
					
					response.sendRedirect("admin/show_user_churn.jsp");
					pst.close();
				}else{
					response.sendRedirect("login_failture.html");
					pst.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void changeDefaultRec(){
		System.out.println("start");
		//int rec_info_id = Integer.parseInt(request.getParameter("rec_info_id"));
		//System.out.println(rec_info_id);
	}
}
