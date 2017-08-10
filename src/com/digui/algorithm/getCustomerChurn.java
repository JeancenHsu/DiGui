package com.digui.algorithm;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class getCustomerChurn {
	public String getResult(String info){
		tempData t = new tempData();    
		String deData[][] = t.getData();  
		// 待分类的属性集
		//待分类的属性数组
		String attr[] = new String[] { "islogin","isdeal","tip","complain","iscomplained","rate"};
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
		// 需要分类的数据，初始为整个数据集
		boolean flags[] = new boolean[deData.length];
		for(int i=0; i<flags.length; i++){
			flags[i] = true;
		}
		// 构造决策树
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
	//	//标记枝叶是否被遍历
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
	//	String info2 = "很久未登录,很久未下单接单,低,较少,较多,接单较多";
	//	String info1 = "一般未登录,一般未下单接单,低,较少,较多,接单较多";
	}
}
