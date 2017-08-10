package com.digui.algorithm;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class getCustomerChurn {
	public String getResult(String info){
		tempData t = new tempData();    
		String deData[][] = t.getData();  
		// ����������Լ�
		//���������������
		String attr[] = new String[] { "islogin","isdeal","tip","complain","iscomplained","rate"};
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
		// ��Ҫ��������ݣ���ʼΪ�������ݼ�
		boolean flags[] = new boolean[deData.length];
		for(int i=0; i<flags.length; i++){
			flags[i] = true;
		}
		// ���������
		TreeNode root = new TreeNode();
		DecisionTree decisionTree = new DecisionTree(root);
		String[][] resultTree = new String[100][3];
		resultTree = decisionTree.buildDecisionTree(root, deData, flags, attributes, attrIndexMap);
		System.out.println("-------------");
		printResult p = new printResult();
		int j = 0;
		for(int i=0; i<resultTree.length; i++){
			if(resultTree[i][0] == null){
				j++;
			}
		}
		String[][] Result = new String[100-j][3];
		for(int i=0; i<Result.length; i++){
			for(int m=0; m<Result[0].length; m++){
				Result[i][m] = resultTree[i][m];
			}
		}
	//	//���֦Ҷ�Ƿ񱻱���
		boolean flag[] = new boolean[Result.length];
		for(int i=0; i<flag.length; i++){
			flag[i] = true;
		}
		String root_value = p.getRootValue(info, attrIndexMap, Result);
		String result = p.printtResult(Result, root_value, info, flag, attributes, attrIndexMap);
		return result;
	}
	public static void main(String[] args) {
		getCustomerChurn g = new getCustomerChurn();
		realData r = new realData();
		String[][] realData = r.getData();
		String[] info = new String[realData.length];
		System.out.println("-----------------------------");
		for(int i=0; i<realData.length; i++){
			info[i] = realData[i][0]+","+realData[i][1]+","+realData[i][2]+","+realData[i][3]+","+realData[i][4]+","+realData[i][5];
	//		g.getResult(info[i]);
		}
		g.getResult(info[0]);
	//	String info2 = "�ܾ�δ��¼,�ܾ�δ�µ��ӵ�,��,����,�϶�,�ӵ��϶�";
	//	String info1 = "һ��δ��¼,һ��δ�µ��ӵ�,��,����,�϶�,�ӵ��϶�";
	}
}
