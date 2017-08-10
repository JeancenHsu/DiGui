package com.digui.algorithm;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class printResult {
	TreeNode root; // 决策树的树根结点
	boolean flag2 = true;
	int set = 0;
	public String getRootValue(String info,	HashMap<String, Integer> attrIndexMap,String[][] resultTree){
		String[] sp = info.split(",");
		String s = resultTree[0][1];                		//记录为root的属性值
		int root_index = attrIndexMap.get(s);       		//为root的属性是第几个属性
		String root_value = sp[root_index];             	//测试数据在根属性的值
		return root_value;
	}

	// 输出决策树
	public String printtResult(String[][] resultTree,String value,String info,
			boolean flag[],LinkedHashSet<String> attributes,
			HashMap<String, Integer> attrIndexMap) {
		String result = null;
		String[] sp = info.split(",");
		String element = null;
		int index = 0;
		result = "否";
		for(int i=0; i<resultTree.length; i++){
			if(flag2){
				if(flag[i]){
					//如果遇到叶子结点且循环值相同
					if((resultTree[i][0].equals("leaf")&&(i == set)) || (resultTree[i][0].equals("yezi") &&(i == set))){
				//		System.out.println(resultTree[i][1]+","+resultTree[i][2]+","+i);
						result = resultTree[i][1];
						flag2 = false;
						return result;
					}else{
						if(resultTree[i][2] == null){
							continue;
						}
						if(resultTree[i][2].equals(value)){
							flag[0] = false;
							element = resultTree[i][1];
							//如果在此处就遇到叶子结点，则输出
							if(element.equals("否") || element.equals("是")){
					//			System.out.println(1+element);
								result = element;
								System.out.println(result);
								flag2 = false;
								return result;
							}
							//获取当前的元素属性
							index = attrIndexMap.get(element);
							//获取当前的元素的属性值
							String newvalue = sp[index];
							boolean newflag[] = new boolean[flag.length];	
							for(int k=0; k<flag.length; k++){
								newflag[k] = flag[k];
							}
							//已被处理过的枝叶需被标记
							flag[i] = false;
							//标记此处的循环值
							set = i;
							//递归调用函数
							printtResult(resultTree, newvalue, info, newflag, attributes, attrIndexMap);
						}
					}
				}
			}
		}
		return result;
	}
}
