package com.digui.algorithm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class dataPretreatment {
/*	数据集的解释说明：
	（1）以下数据均为已经确定的流失客户的数据，即截止2016年6月1日起，已有6个月未登录
	（2）经反复实验研究且考虑多方面因素，我们郑重选取：最近一次登录时间，最近一次接单下单时间，小费高低，被投诉次数，抱怨次数，接单下单比
		（待加入特征：付款方式，接单数，活跃度）为预测流失客户的特征值
	（3）关于特征值的处理说明：
		1.最近一次登录时间
		考虑到这些数据已经是超过6个月未登录的确认流失数据，我们决定对获取的时间做如下标记
			2015-7-1后登录过：	 		一般未登录
			2015-7-1前登录过：			 很久未登录
		2.最近一次接单下单时间
			2015-7-1后接单下单过：   		一般未接单下单
			2015-7-1前接单下单过：		很久未接单下单
		3.小费高低
			小费平均值在0~3:	 			低
			小费平均值在3~正无穷:			高
		4.被投诉次数
			0~2	：						较少
			2~正无穷：					较多
		5.抱怨次数
			0~2	：						较少
			2~正无穷：					较多
		6.接单下单比
			0~0.8：						下单较多  	    
			0.8~1.25：					持平
			1.25~正无穷：					接单较多
		7.预测结果
			预测将要成为流失客户： 			是
			预测不会成为流失客户： 			否*/
	public String[][] dealData(){
		Data d = new Data();
		String deData[][] = d.getData();
		// 待分类的属性集
		//待分类的属性数组
		String attr[] = new String[] { "islogin", "isdeal", "tip","complain","iscomplained" ,"rate"};
		//待分类的属性集合
		LinkedHashSet<String> attributes = new LinkedHashSet<String>();
		for(int i=0; i<attr.length; i++){
			attributes.add(attr[i]);
		}
		// 属性与数据集中对应数据的下标
		HashMap<String, Integer> attrIndexMap = new HashMap<String, Integer>();
		for(int i=0; i<attr.length; i++){
			attrIndexMap.put(attr[i], i);
		}
		
		//将数据进行分箱操作
		//将数据单个分组，以便进行单个特征值的分析
		int index1 = attrIndexMap.get("islogin");           //获取最近登录时间在属性集中的下标
		int index2 = attrIndexMap.get("isdeal");            //获取最近接单下单时间在属性集中的下标
		int index3 = attrIndexMap.get("tip");          		 //获取小费高低在属性集中的下标
		int index4 = attrIndexMap.get("complain");           //获取被投诉次数在属性集中的下标
		int index5 = attrIndexMap.get("iscomplained");       //获取抱怨次数在属性集中的下标
		int index6 = attrIndexMap.get("rate");           	//获取接单下单比在属性集中的下标
		
		String islogin[] = new String[deData.length];
		String isdeal[] = new String[deData.length];
		String tip[] = new String[deData.length];
		String complain[] = new String[deData.length];
		String iscomplained[] = new String[deData.length];
		String rate[] = new String[deData.length];
		
		for(int i=0; i<deData.length; i++){
			islogin[i] = deData[i][index1];                 //记录最近登录时间的值
			isdeal[i] = deData[i][index2];                  //记录最近接单下单时间的值
			tip[i] = deData[i][index3];
			complain[i] = deData[i][index4];
			iscomplained[i] = deData[i][index5];
			rate[i] = deData[i][index6];
		}

		// 处理最近一次登录时间
		String date3 = "2015-7-1";
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date d3;
		Date[] date = new Date[islogin.length];
		try {
//			d1 = dateformat.parse(date1);
//			d2 = dateformat.parse(date2);
			d3 = dateformat.parse(date3);
			for(int i=0; i<islogin.length; i++){
				date[i] = dateformat.parse(islogin[i]);  //将字符型全部转化成日期型的数组
			}
			for(int i=0; i<date.length; i++){
				if(date[i].before(d3)){
					islogin[i] = "很久未登录";
				}else{
					islogin[i] = "一般未登录";
				}
			}
			// 处理最近一次下单接单时间
			for(int i=0; i<isdeal.length; i++){
				if(isdeal[i] == null){
					date[i] = null;
				}else{
					date[i] = dateformat.parse(isdeal[i]);  //将字符型全部转化成日期型的数组
				}
			}
			for(int i=0; i<date.length; i++){
				if((date[i] == null)){
					isdeal[i] = "很久未下单接单";
				}else{
					if(date[i].before(d3)){
						isdeal[i] = "很久未下单接单";
					}else{
						isdeal[i] = "一般未下单接单";
					}
				}
			}				
			//处理小费高低
			for(int i=0; i<tip.length; i++){
				if(Double.parseDouble(tip[i]) >=3){
					tip[i] = "高";
				}else if(Double.parseDouble(tip[i])<3){
					tip[i] = "低";
				}
			}
			//处理被投诉次数
			for(int i=0; i<complain.length; i++){
				if(Integer.parseInt(complain[i])>=2){
					complain[i] = "较多";
				}else if(Integer.parseInt(complain[i])<2){
					complain[i] = "较少";
				}
			}
			//处理抱怨次数
			for(int i=0; i<iscomplained.length; i++){
				if(Integer.parseInt(iscomplained[i])>=2){
					iscomplained[i] = "较多";
				}else if(Integer.parseInt(iscomplained[i])<2){
					iscomplained[i] = "较少";
				}
			}
			//处理接单下单比
			for(int i=0; i<rate.length; i++){
				if(rate[i].equals("null")){
					rate[i] = "持平";
				}else{
					if(Double.parseDouble(rate[i])>=1.25){
						rate[i] = "接单较多";
					}else if(Double.parseDouble(rate[i])<1.25 && Double.parseDouble(rate[i])>=0.8){
						rate[i] = "持平";
					}else if(Double.parseDouble(rate[i])<0.8){
						rate[i] = "下单较多";
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
		return deData;
	}
}
