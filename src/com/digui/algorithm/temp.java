package com.digui.algorithm;

import java.sql.Date;

public class temp {
	private int user_id;
	private Date last_login_time;//���һ�ε�¼ʱ��
	private Date start_time;//���һ�νӵ�ʱ��
	private Date order_time;//���һ���µ�ʱ��
	private String avgtip;//С��
	private int complain;//��Ͷ�ߴ���
	private int iscomplained;//��Թ����
	private int receive_num;//�ӵ���
	private int place_num;//�µ���
	private String customer_churn;//�Ƿ���ʧ
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public String getAvgtip() {
		return avgtip;
	}
	public void setAvgtip(String avgtip) {
		this.avgtip = avgtip;
	}
	public int getReceive_num() {
		return receive_num;
	}
	public void setReceive_num(int receive_num) {
		this.receive_num = receive_num;
	}
	public int getPlace_num() {
		return place_num;
	}
	public void setPlace_num(int place_num) {
		this.place_num = place_num;
	}
	public int getComplain() {
		return complain;
	}
	public void setComplain(int complain) {
		this.complain = complain;
	}
	public int getIscomplained() {
		return iscomplained;
	}
	public void setIscomplained(int iscomplained) {
		this.iscomplained = iscomplained;
	}
	public String getCustomer_churn() {
		return customer_churn;
	}
	public void setCustomer_churn(String customer_churn) {
		this.customer_churn = customer_churn;
	}
	
}
