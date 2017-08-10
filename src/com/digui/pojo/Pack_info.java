package com.digui.pojo;


public class Pack_info {
	private int user_id;
	private int pack_info_id;
	public int getPack_info_id() {
		return pack_info_id;
	}
	public void setPack_info_id(int pack_info_id) {
		this.pack_info_id = pack_info_id;
	}
	private String company;//快递公司
	private String pack_num;//快递单号
	private int get_num;//取货号
	private String size;//货物类型
	private String require_time;//送达时间
	private int basic_price;//基准费用
	private int tip;//小费
	private int sum_price;//总价
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPack_num() {
		return pack_num;
	}
	public void setPack_num(String pack_num) {
		this.pack_num = pack_num;
	}
	public int getGet_num() {
		return get_num;
	}
	public void setGet_num(int get_num) {
		this.get_num = get_num;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getRequire_time() {
		return require_time;
	}
	public void setRequire_time(String require_time) {
		this.require_time = require_time;
	}
	public int getBasic_price() {
		return basic_price;
	}
	public void setBasic_price(int basic_price) {
		this.basic_price = basic_price;
	}
	public int getTip() {
		return tip;
	}
	public void setTip(int tip) {
		this.tip = tip;
	}
	public int getSum_price() {
		return sum_price;
	}
	public void setSum_price(int sum_price) {
		this.sum_price = sum_price;
	}
	
	
}