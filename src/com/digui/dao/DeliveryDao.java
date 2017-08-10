package com.digui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.digui.dao.DBConnection;
import com.digui.pojo.Order_info;
import com.digui.pojo.Pack_info;
import com.digui.pojo.Rec_info;
import com.digui.pojo.User_info;


public class DeliveryDao {
	Connection conn;
	PreparedStatement ps;
	ResultSet res;
	public ArrayList<Order_info> getAllOrders(int user_id){
		conn=DBConnection.getConnection();
		ArrayList<Order_info> list = new ArrayList<Order_info>();
		String sql="select order_id,user_id,user_order_id,rec_info_id,pack_info_id,deliver_user_id,sum_price,pay_way,start_time,finish_time,status from order_info where status='已下单' and user_id<>?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				Order_info o=new Order_info();
				o.setOrder_id(res.getInt(1));
				o.setUser_id(res.getInt(2));
				o.setUser_order_id(res.getInt(3));
				o.setRec_info_id(res.getInt(4));
				o.setPack_info_id(res.getInt(5));
				o.setDeliver_user_id(res.getInt(6));
				o.setSum_price(res.getInt(7));
				o.setPay_way(res.getString(8));
				o.setStart_time(res.getTimestamp(9));
				o.setFinish_time(res.getTimestamp(10));
				o.setStatus(res.getString(11));
				
				list.add(o);
				
				
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public Order_info getOrderByOrderId(int order_id){
		conn=DBConnection.getConnection();
		Order_info o = null;
		String sql="select order_id,user_id,user_order_id,rec_info_id,pack_info_id,deliver_user_id,sum_price,pay_way,start_time,finish_time,status from order_info where order_id=?";
		try {
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, order_id);
			res=ps.executeQuery();
			if(res.next()){
				o=new Order_info();
				o.setOrder_id(res.getInt(1));
				o.setUser_id(res.getInt(2));
				o.setUser_order_id(res.getInt(3));
				o.setRec_info_id(res.getInt(4));
				o.setPack_info_id(res.getInt(5));
				o.setDeliver_user_id(res.getInt(6));
				o.setSum_price(res.getInt(7));
				o.setPay_way(res.getString(8));
				o.setStart_time(res.getTimestamp(9));
				o.setFinish_time(res.getTimestamp(10));
				o.setStatus(res.getString(11));
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	
	public ArrayList<Pack_info> getPac(int pack_info_id,int user_id){
		conn=DBConnection.getConnection();
		//定义一个快递信息集合
		ArrayList<Pack_info> list = new ArrayList<Pack_info>();
		//String sql = "select * from pack_info where pack_info_id in(select pack_info_id from order_info)";
		String sql2="select user_id,pack_info_id,company,pack_num,get_num,size,require_time,basic_price,tip_price,sum_price from pack_info where pack_info_id=? and user_id=?";
		try {
			ps=conn.prepareStatement(sql2);
			ps.setInt(1, pack_info_id);
			ps.setInt(2, user_id);
			res=ps.executeQuery();
			while(res.next()){
				Pack_info p=new Pack_info();
				p.setUser_id(res.getInt(1));
				p.setPack_info_id(res.getInt(2));
				p.setCompany(res.getString(3));
				p.setPack_num(res.getString(4));
				p.setGet_num(res.getInt(5));
				p.setSize(res.getString(6));
				p.setRequire_time(res.getString(7));
				p.setBasic_price(res.getInt(8));
				p.setTip(res.getInt(9));
				p.setSum_price(res.getInt(10));
				list.add(p);
				
				
			}
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Rec_info> getRec(int user_id_2,int rec_info_id){
		conn=DBConnection.getConnection();
		ArrayList<Rec_info> list = new ArrayList<Rec_info>();
		String sql = "select user_id,rec_info_id,name,sex,school_name,stu_num,address,phone_num from rec_info where user_id=? and rec_info_id=?" ;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id_2);
			ps.setInt(2, rec_info_id);
			res=ps.executeQuery();
			while(res.next()){
				Rec_info r=new Rec_info();
				r.setUser_id(res.getInt(1));
				r.setRec_info_id(res.getInt(2));
				r.setName(res.getString(3));
				r.setSex(res.getString(4));
				r.setSchool_name(res.getString(5));
				r.setStu_num(res.getString(6));
				r.setAddress(res.getString(7));
				r.setPhone_num(res.getString(8));
				list.add(r);
				//System.out.print(list.size());
				
				
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Rec_info> getRecByUserId(int user_id){
		conn=DBConnection.getConnection();
		ArrayList<Rec_info> list = new ArrayList<Rec_info>();
		String sql = "select user_id,rec_info_id,name,sex,school_name,stu_num,address,phone_num,is_default from rec_info where user_id=?" ;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				Rec_info r=new Rec_info();
				r.setUser_id(res.getInt(1));
				r.setRec_info_id(res.getInt(2));
				r.setName(res.getString(3));
				r.setSex(res.getString(4));
				r.setSchool_name(res.getString(5));
				r.setStu_num(res.getString(6));
				r.setAddress(res.getString(7));
				r.setPhone_num(res.getString(8));
				r.setIs_defaut(res.getInt(9));
				list.add(r);
				//System.out.print(list.size());
				
				
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<User_info> getUser(int user_id){
		conn=DBConnection.getConnection();
		ArrayList<User_info> list = new ArrayList<User_info>();
		String sql = "select user_id,phone_num,password,name,sex,id_num,school_name,stu_num,address,head_ico_link,last_login_time,register_time from user_info where user_id=?" ;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				User_info u = new User_info();
				u.setUser_id(res.getInt(1));
				u.setPhone_num(res.getString(2));
				u.setPassword(res.getString(3));
				u.setName(res.getString(4));
				u.setSex(res.getString(5));
				u.setId_num(res.getString(6));
				u.setSchool_name(res.getString(7));
				u.setStu_num(res.getString(8));
				u.setAddress(res.getString(9));
				u.setHead_ico_link(res.getString(10));
				u.setLast_login_time(res.getTimestamp(11));
				u.setRegister_time(res.getTimestamp(12));
				list.add(u);
				//System.out.print(list.size());
				
				
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Order_info> getAllOrdersByUserId(int user_id){
		conn=DBConnection.getConnection();
		ArrayList<Order_info> list = new ArrayList<Order_info>();
		String sql="select order_id,user_id,user_order_id,rec_info_id,pack_info_id,deliver_user_id,sum_price,pay_way,start_time,finish_time,status from order_info where user_id=? order by order_id DESC";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				Order_info o=new Order_info();
				o.setOrder_id(res.getInt(1));
				o.setUser_id(res.getInt(2));
				o.setUser_order_id(res.getInt(3));
				o.setRec_info_id(res.getInt(4));
				o.setPack_info_id(res.getInt(5));
				o.setDeliver_user_id(res.getInt(6));
				o.setSum_price(res.getInt(7));
				o.setPay_way(res.getString(8));
				o.setStart_time(res.getTimestamp(9));
				o.setFinish_time(res.getTimestamp(10));
				o.setStatus(res.getString(11));
				
				list.add(o);
				
				
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Order_info> getAllOrdersByDeliverUserId(int user_id){
		conn=DBConnection.getConnection();
		ArrayList<Order_info> list = new ArrayList<Order_info>();
		String sql="select order_id,user_id,user_order_id,rec_info_id,pack_info_id,deliver_user_id,sum_price,pay_way,start_time,finish_time,status from order_info where deliver_user_id=? order by order_id DESC";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				Order_info o=new Order_info();
				o.setOrder_id(res.getInt(1));
				o.setUser_id(res.getInt(2));
				o.setUser_order_id(res.getInt(3));
				o.setRec_info_id(res.getInt(4));
				o.setPack_info_id(res.getInt(5));
				o.setDeliver_user_id(res.getInt(6));
				o.setSum_price(res.getInt(7));
				o.setPay_way(res.getString(8));
				o.setStart_time(res.getTimestamp(9));
				o.setFinish_time(res.getTimestamp(10));
				o.setStatus(res.getString(11));
				
				list.add(o);
				
				
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
//		new DeliveryDao().getOrder();
//		new DeliveryDao().getRec(4,2);
	}
}

