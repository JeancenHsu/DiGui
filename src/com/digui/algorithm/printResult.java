package com.digui.algorithm;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class printResult {
	TreeNode root; // ���������������
	boolean flag2 = true;
	int set = 0;
	public String getRootValue(String info,	HashMap<String, Integer> attrIndexMap,String[][] resultTree){
		String[] sp = info.split(",");
		String s = resultTree[0][1];                		//��¼Ϊroot������ֵ
		int root_index = attrIndexMap.get(s);       		//Ϊroot�������ǵڼ�������
		String root_value = sp[root_index];             	//���������ڸ����Ե�ֵ
		return root_value;
	}

	// ���������
	public String printtResult(String[][] resultTree,String value,String info,
			boolean flag[],LinkedHashSet<String> attributes,
			HashMap<String, Integer> attrIndexMap) {
		String result = null;
		String[] sp = info.split(",");
		String element = null;
		int index = 0;
		result = "��";
		for(int i=0; i<resultTree.length; i++){
			if(flag2){
				if(flag[i]){
					//�������Ҷ�ӽ����ѭ��ֵ��ͬ
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
							//����ڴ˴�������Ҷ�ӽ�㣬�����
							if(element.equals("��") || element.equals("��")){
					//			System.out.println(1+element);
								result = element;
								System.out.println(result);
								flag2 = false;
								return result;
							}
							//��ȡ��ǰ��Ԫ������
							index = attrIndexMap.get(element);
							//��ȡ��ǰ��Ԫ�ص�����ֵ
							String newvalue = sp[index];
							boolean newflag[] = new boolean[flag.length];	
							for(int k=0; k<flag.length; k++){
								newflag[k] = flag[k];
							}
							//�ѱ��������֦Ҷ�豻���
							flag[i] = false;
							//��Ǵ˴���ѭ��ֵ
							set = i;
							//�ݹ���ú���
							printtResult(resultTree, newvalue, info, newflag, attributes, attrIndexMap);
						}
					}
				}
			}
		}
		return result;
	}
}
