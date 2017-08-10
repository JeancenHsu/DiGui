package com.digui.algorithm;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import com.digui.algorithm.DBConnection;
import com.digui.algorithm.temp;


public class realData {
	Connection conn;
	Statement stmt;
	PreparedStatement ps;
	ResultSet res;
	static int user_id;
	static ArrayList<Integer> list;
	static String dData[][]=new String[100][];
	static Date last_login_time=null,
			start_time=null,
			order_time=null,
			time=null;//�Ƚ�����ʱ��
	static String avgtip;//С��
	static int receive_num;//�ӵ���
	static int place_num;//�µ���
	static String num;
	static int complain,iscomplained;
	static String customer_churn;
	//��user_info���ҵ����е�user_id
	public ArrayList<Integer> findAllUserID(){
		conn=DBConnection.getConnection();
		list=new ArrayList<Integer>();
		String sql="select user_id from user_info where customer_churn != ? and customer_churn != ? or customer_churn is null";
//		String sql="select user_id from user_info where customer_churn=? or customer_churn is null";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "��");
			ps.setString(2, "��");
			res=ps.executeQuery();
			while(res.next()){
				list.add(res.getInt(1));
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//���һ�ε�¼ʱ��
	public void LastLoginTime(int user_id){
		conn=DBConnection.getConnection();
		String sql="select last_login_time,complain,iscomplained,customer_churn from user_info where user_id=? ";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				temp t=new temp();
				t.setLast_login_time(res.getDate(1));
				t.setComplain(res.getInt(2));
				t.setIscomplained(res.getInt(3));
				t.setCustomer_churn(res.getString(4));
				last_login_time=t.getLast_login_time();
				complain = t.getComplain();
				iscomplained = t.getIscomplained();
				customer_churn = t.getCustomer_churn();
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//���һ�νӵ�ʱ��
	public void StartTime(int user_id){
		conn=DBConnection.getConnection();
		String sql="select max(start_time) from order_info where deliver_user_id=?";//�ӵ��߽ӵ�
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				temp t=new temp();
				t.setStart_time(res.getDate(1));
				start_time=t.getStart_time();
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//���һ���µ�ʱ��
	public void OrderTime(int user_id){
		conn=DBConnection.getConnection();
		String sql="select max(start_time) from order_info where user_id=?";//�û��µ�
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				temp t=new temp();
				t.setOrder_time(res.getDate(1));
				order_time=t.getOrder_time();
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

		
	//ƽ��С��Ϊ����
	public void AvgTip(int user_id){
		conn=DBConnection.getConnection();
		String sql="select avg(tip_price) from pack_info where user_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				temp t=new temp();
				String avgTip=res.getString(1);
				if(avgTip==null){
					avgTip="0.0";
				}
				avgTip=avgTip.substring(0, 3);
				t.setAvgtip(avgTip);
				avgtip=t.getAvgtip();
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�ӵ���
	public void ReceiveNum(int user_id){
		conn=DBConnection.getConnection();
		String sql="select count(*) from order_info where deliver_user_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				temp t=new temp();
				t.setReceive_num(res.getInt(1));
				receive_num=t.getReceive_num();
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�µ���
	public void PlaceNum(int user_id){
		conn=DBConnection.getConnection();
		String sql="select count(*) from order_info where user_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				temp t=new temp();
				t.setPlace_num(res.getInt(1));
				place_num=t.getPlace_num();
			}
			ps.close();
			conn.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�������ڱȽϴ�Сstart_time,order_time
	 public static int compare_date(Date dt1, Date dt2) {
        try {
        	if(dt1!=null&&dt2!=null){
        		Long d1=dt1.getTime();
            	Long d2=dt2.getTime();
            	if(((0!=d1) &&d1>0)&&((0!=d2) &&d2>0)){
    	            if (d1 > d2) {
    	                return 1;
    	            } else if (d1 < d2) {
    	                return -1;
    	            } else {
    	                return 0;
    	            }
            	}
        	}
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
	 }
	 
	 //����ӵ��µ���receive_num�ӵ��� place_num�µ��� �ӵ���/�µ���
	public String compare_number(int receive_num,int place_num){
		if(receive_num==0){
			num=null;
		}
		if(place_num!=0){
			float n=(float)receive_num/place_num;
			DecimalFormat df = new DecimalFormat("0.00");//��ʽ��С�� 
			num=df.format(n);
			return num;
		}
		return "back";
	}
	public void method(int user_id){
		realData data=new realData();
		data.LastLoginTime(user_id);
		data.StartTime(user_id);
		data.OrderTime(user_id);
		if(start_time!=null||order_time!=null){
			int number=data.compare_date(start_time, order_time);
			if(number==1){
				time=start_time;
			}else if(number==0){
				time=order_time;
			}else{
				time=start_time;
			}
		}else{
			time=null;
		}
		data.AvgTip(user_id);
		data.ReceiveNum(user_id);
		data.PlaceNum(user_id);
		data.compare_number(receive_num, place_num);
	}
	
	public String[][] getOriginalData(){
		realData data = new realData();
		String d[] = new String[200];
		data.findAllUserID();
		if(list!=null&&list.size()>0){
			int[] arr=new int[list.size()];//����һ����list����һ��������
			for(int i=0;i<arr.length;i++){
				arr[i]=list.get(i);
			}
			for(int j=0;j<arr.length;j++){
				user_id=arr[j];//ȡ��user_id��ֵ
				data.method(user_id);
				d[j] = last_login_time+","+time+","+avgtip+","+complain+","+iscomplained+","+num+","+customer_churn;
			}
		}
		int j=0;
		for(int i=0; i<d.length; i++){
			if(d[i] == null){
				j++;
			}
		}
		String d1[] = new String[200-j];
		for(int i=0; i<d1.length; i++){
			d1[i] = d[i];
		}
		String deData[][] = new String[d1.length][8];
		if(list!=null&&list.size()>0){
			int[] arr=new int[list.size()];//����һ����list����һ��������
			for(int i=0;i<arr.length;i++){
				arr[i]=list.get(i);
			}
			for(int m=0;m<arr.length;m++){
				user_id=arr[m];//ȡ��user_id��ֵ
				data.method(user_id);
				deData[m][0] = last_login_time.toString();	
				if(time == null){
					deData[m][1] = null;
				}else{
					deData[m][1] = time.toString();
				}
				deData[m][2] = avgtip.toString();
				deData[m][3] = String.valueOf(complain);
				deData[m][4] = String.valueOf(iscomplained);
				deData[m][5] = String.valueOf(num);
				deData[m][6] = customer_churn;
				deData[m][7] = String.valueOf(user_id);
			}
//			for(int i=0; i<deData.length; i++){
//				System.out.println(i+":"+deData[i][0]+","+deData[i][1]+","+deData[i][2]+","+deData[i][3]+","+
//				deData[i][4]+","+deData[i][5]+","+deData[i][6]+","+deData[i][7]);
//			}
		}
		return deData;
	}
	
	public String[][] getData(){
		realData r = new realData();
		String[][] deData = r.getOriginalData();
		
		String attr[] = new String[] { "islogin", "isdeal", "tip","complain","iscomplained" ,"rate"};
		//����������Լ���
		LinkedHashSet<String> attributes = new LinkedHashSet<String>();
		for(int i=0; i<attr.length; i++){
			attributes.add(attr[i]);
		}
		// ���������ݼ��ж�Ӧ���ݵ��±�
		HashMap<String, Integer> attrIndexMap = new HashMap<String, Integer>();
		for(int i=0; i<attr.length; i++){
			attrIndexMap.put(attr[i], i);
		}
		
		//�����ݽ��з������
		//�����ݵ������飬�Ա���е�������ֵ�ķ���
		int index1 = attrIndexMap.get("islogin");           //��ȡ�����¼ʱ�������Լ��е��±�
		int index2 = attrIndexMap.get("isdeal");            //��ȡ����ӵ��µ�ʱ�������Լ��е��±�
		int index3 = attrIndexMap.get("tip");           //��ȡС�Ѹߵ������Լ��е��±�
		int index4 = attrIndexMap.get("complain");           //��ȡ��Ͷ�ߴ��������Լ��е��±�
		int index5 = attrIndexMap.get("iscomplained");           //��ȡ��Թ���������Լ��е��±�
		int index6 = attrIndexMap.get("rate");           //��ȡ�ӵ��µ��������Լ��е��±�
		
		String islogin[] = new String[deData.length];
		String isdeal[] = new String[deData.length];
		String tip[] = new String[deData.length];
		String complain[] = new String[deData.length];
		String iscomplained[] = new String[deData.length];
		String rate[] = new String[deData.length];
		
		for(int i=0; i<deData.length; i++){
			islogin[i] = deData[i][index1];                 //��¼�����¼ʱ���ֵ
			isdeal[i] = deData[i][index2];                  //��¼����ӵ��µ�ʱ���ֵ
			tip[i] = deData[i][index3];
			complain[i] = deData[i][index4];
			iscomplained[i] = deData[i][index5];
			rate[i] = deData[i][index6];
		}

		// �������һ�ε�¼ʱ��
		String date3 = "2015-7-1";
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date d3;
		Date[] date = new Date[islogin.length];
		try {
			d3 = dateformat.parse(date3);
			for(int i=0; i<islogin.length; i++){
				date[i] = dateformat.parse(islogin[i]);  //���ַ���ȫ��ת���������͵�����
			}
			for(int i=0; i<date.length; i++){
				if(date[i].before(d3)){
					islogin[i] = "�ܾ�δ��¼";
				}else{
					islogin[i] = "һ��δ��¼";
				}
			}
			// �������һ���µ��ӵ�ʱ��
			for(int i=0; i<isdeal.length; i++){
				if(isdeal[i] == null){
					date[i] = null;
				}else{
					date[i] = dateformat.parse(isdeal[i]);  //���ַ���ȫ��ת���������͵�����
				}
			}
			for(int i=0; i<date.length; i++){
				if((date[i] == null)){
					isdeal[i] = "�ܾ�δ�µ��ӵ�";
				}else{
					if(date[i].before(d3)){
						isdeal[i] = "�ܾ�δ�µ��ӵ�";
					}else{
						isdeal[i] = "һ��δ�µ��ӵ�";
					}
				}
			}
			//����С�Ѹߵ�
			for(int i=0; i<tip.length; i++){
				if(Double.parseDouble(tip[i]) >=3){
					tip[i] = "��";
				}else if(Double.parseDouble(tip[i])<3){
					tip[i] = "��";
				}
			}
			//����Ͷ�ߴ���
			for(int i=0; i<complain.length; i++){
				if(Integer.parseInt(complain[i])>=2){
					complain[i] = "�϶�";
				}else if(Integer.parseInt(complain[i])<2){
					complain[i] = "����";
				}
			}
			//����Թ����
			for(int i=0; i<iscomplained.length; i++){
				if(Integer.parseInt(iscomplained[i])>=2){
					iscomplained[i] = "�϶�";
				}else if(Integer.parseInt(iscomplained[i])<2){
					iscomplained[i] = "����";
				}
			}
			//����ӵ��µ���
			for(int i=0; i<rate.length; i++){
				if(rate[i].equals("null")){
					rate[i] = "��ƽ";
				}else{
					if(Double.parseDouble(rate[i])>=1.25){
						rate[i] = "�ӵ��϶�";
					}else if(Double.parseDouble(rate[i])<1.25 && Double.parseDouble(rate[i])>=0.8){
						rate[i] = "��ƽ";
					}else if(Double.parseDouble(rate[i])<0.8){
						rate[i] = "�µ��϶�";
					}
				}
			}
			for(int i=0; i<deData.length; i++){
				deData[i][0] = islogin[i];
				deData[i][1] = isdeal[i];
				deData[i][2] = tip[i];
				deData[i][3] = complain[i];
				deData[i][4] = iscomplained[i];
				deData[i][5] = rate[i];
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0; i<deData.length; i++){
		System.out.println(deData[i][0]+","+deData[i][1]+","+deData[i][2]+","+deData[i][3]+","+
		deData[i][4]+","+deData[i][5]+","+deData[i][6]+","+deData[i][7]);
	}
		return deData;
	}
//	public static void main(String[] args) {
//		realData r = new realData();
//		r.getData();
//	}
}

