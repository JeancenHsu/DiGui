package com.digui.pojo;

import java.sql.Timestamp;

public class Order_info {
	protected int order_id;
	protected int user_id;
	protected int user_order_id;
	protected int rec_info_id;
	protected int pack_info_id;
	protected int deliver_user_id;
	protected int sum_price;
	protected String pay_way;
	protected Timestamp start_time;
	protected Timestamp finish_time;
	protected String status;
	public int getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUser_order_id() {
		return user_order_id;
	}
	public void setUser_order_id(int user_order_id) {
		this.user_order_id = user_order_id;
	}
	public int getRec_info_id() {
		return rec_info_id;
	}
	public void setRec_info_id(int rec_info_id) {
		this.rec_info_id = rec_info_id;
	}
	public int getPack_info_id() {
		return pack_info_id;
	}
	public void setPack_info_id(int pack_info_id) {
		this.pack_info_id = pack_info_id;
	}
	public int getDeliver_user_id() {
		return deliver_user_id;
	}
	public void setDeliver_user_id(int deliver_user_id) {
		this.deliver_user_id = deliver_user_id;
	}
	public int getSum_price() {
		return sum_price;
	}
	public void setSum_price(int sum_price) {
		this.sum_price = sum_price;
	}
	public String getPay_way() {
		return pay_way;
	}
	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getFinish_time() {
		return finish_time;
	}
	public void setFinish_time(Timestamp finish_time) {
		this.finish_time = finish_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
