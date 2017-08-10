package com.digui.pojo;

public class Rec_info {
	private int user_id;
	private int rec_info_id;
	private String name;//收件人姓名
	private String sex;//性别
	private String school_name;
	private int is_defaut;
	
	public int getIs_defaut() {
		return is_defaut;
	}
	public void setIs_defaut(int is_defaut) {
		this.is_defaut = is_defaut;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRec_info_id() {
		return rec_info_id;
	}
	public void setRec_info_id(int rec_info_id) {
		this.rec_info_id = rec_info_id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	private String stu_num;//学号
	private String address;//地址
	private String phone_num;//手机号码
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStu_num() {
		return stu_num;
	}
	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	
	
}
