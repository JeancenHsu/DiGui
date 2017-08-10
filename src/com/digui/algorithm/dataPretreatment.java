package com.digui.algorithm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class dataPretreatment {
/*	���ݼ��Ľ���˵����
	��1���������ݾ�Ϊ�Ѿ�ȷ������ʧ�ͻ������ݣ�����ֹ2016��6��1��������6����δ��¼
	��2��������ʵ���о��ҿ��Ƕ෽�����أ�����֣��ѡȡ�����һ�ε�¼ʱ�䣬���һ�νӵ��µ�ʱ�䣬С�Ѹߵͣ���Ͷ�ߴ�������Թ�������ӵ��µ���
		�����������������ʽ���ӵ�������Ծ�ȣ�ΪԤ����ʧ�ͻ�������ֵ
	��3����������ֵ�Ĵ���˵����
		1.���һ�ε�¼ʱ��
		���ǵ���Щ�����Ѿ��ǳ���6����δ��¼��ȷ����ʧ���ݣ����Ǿ����Ի�ȡ��ʱ�������±��
			2015-7-1���¼����	 		һ��δ��¼
			2015-7-1ǰ��¼����			 �ܾ�δ��¼
		2.���һ�νӵ��µ�ʱ��
			2015-7-1��ӵ��µ�����   		һ��δ�ӵ��µ�
			2015-7-1ǰ�ӵ��µ�����		�ܾ�δ�ӵ��µ�
		3.С�Ѹߵ�
			С��ƽ��ֵ��0~3:	 			��
			С��ƽ��ֵ��3~������:			��
		4.��Ͷ�ߴ���
			0~2	��						����
			2~�����					�϶�
		5.��Թ����
			0~2	��						����
			2~�����					�϶�
		6.�ӵ��µ���
			0~0.8��						�µ��϶�  	    
			0.8~1.25��					��ƽ
			1.25~�����					�ӵ��϶�
		7.Ԥ����
			Ԥ�⽫Ҫ��Ϊ��ʧ�ͻ��� 			��
			Ԥ�ⲻ���Ϊ��ʧ�ͻ��� 			��*/
	public String[][] dealData(){
		Data d = new Data();
		String deData[][] = d.getData();
		// ����������Լ�
		//���������������
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
		int index3 = attrIndexMap.get("tip");          		 //��ȡС�Ѹߵ������Լ��е��±�
		int index4 = attrIndexMap.get("complain");           //��ȡ��Ͷ�ߴ��������Լ��е��±�
		int index5 = attrIndexMap.get("iscomplained");       //��ȡ��Թ���������Լ��е��±�
		int index6 = attrIndexMap.get("rate");           	//��ȡ�ӵ��µ��������Լ��е��±�
		
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
//			d1 = dateformat.parse(date1);
//			d2 = dateformat.parse(date2);
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
		return deData;
	}
}
