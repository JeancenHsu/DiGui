package com.digui.algorithm;

import java.util.HashMap;
import java.util.LinkedHashSet;

import com.digui.algorithm.DecisionTree;
import com.digui.algorithm.TreeNode;


public class DeTree {
	public static void main(String[] args) {		
		String deData[][] = new String[100][];	
//		String deData1[][] = new String[100][];	
//		dataPretreatment da = new dataPretreatment();
//		deData = da.dealData();
//		datatemp da = new datatemp();   //database
//		deData = da.getData();
		tempData t = new tempData();    //java
		deData = t.getData();  
//		text t = new text();            //txt
//		deData = t.getData();

//		for(int i=0; i<deData.length; i++){
//			for(int j=0; j<deData[0].length; j++){
//				deData[i][j]  = deData[i][j].toString();
//			}
//		System.out.println(i+"11111"+deData[i][0]+","+deData[i][1]+","+deData[i][2]+","+deData[i][3]+","+
//				deData[i][4]+","+deData[i][5]+","+deData[i][6]);
//		System.out.println(i+"2222"+deData1[i][0]+","+deData1[i][1]+","+deData1[i][2]+","+deData1[i][3]+","+
//				deData[i][4]+","+deData[i][5]+","+deData[i][6]);
//		}		
		String info = "һ��δ��¼,�ܾ�δ�ӵ��µ�,��,�϶�,����,�µ��϶�"; 
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
//		for(int m=0; m<resultTree.length ;m++){
//			System.out.println(resultTree[m][0]+","+resultTree[m][1]+","+resultTree[m][2]);
//		}
//		System.out.println("----------------");
		printResult p = new printResult();
//		String[][] Result = p.changeTree(resultTree, root, deData, flags, attributes, attrIndexMap);

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
//		//���֦Ҷ�Ƿ񱻱���
		boolean flag[] = new boolean[Result.length];
		for(int i=0; i<flag.length; i++){
			flag[i] = true;
		}
		String root_value = p.getRootValue(info, attrIndexMap, Result);
		p.printtResult(Result, root_value, info, flag, attributes, attrIndexMap);
	}
}