package com.digui.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

// ��������
class DecisionTree {
	TreeNode root; // ���������������
	int index = 0;
	String[][] Result = new String[100][3];
	HashMap<TreeNode, Integer> denode = new HashMap<TreeNode, Integer>();
	int set = 0;
	boolean flag2 = true;
	public DecisionTree() {
		root = new TreeNode();
	}

	public DecisionTree(TreeNode root) {
		this.root = root;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	// node���ڵ�ǰ��㹹�������
	// deData�����ݼ�
	// flags��ָʾ�ڵ�ǰ��㹹�������ʱ��Щ��������Ҫ��
	// attributes��δ��������Լ�
	// attrIndexMap���������Ӧ�����±�
	public String selectAtrribute(TreeNode node,String[][] deData, boolean flags[],
			LinkedHashSet<String> attributes,HashMap<String, Integer> attrIndexMap){
		// Gain�����ŵ�ǰ���δ�������Ե�Gainֵ
		double Gain[] = new double[attributes.size()];
		// ÿ�������й�����±꣬Ϊÿ�����ݵ����һ��ֵ
		int class_index = deData[0].length - 1;
		// ���������ý���ڸ������Ͻ��з���
		String return_atrribute = null;
		// ����ÿ��δ�������Ե� Gainֵ
		int count = 0; // ���㵽�ڼ�������
		for (String atrribute : attributes) {
			// �������ж��ٸ�ֵ���������ж��ٸ�����
			int values_count, class_count;
			// ����ֵ��Ӧ���±�
			int index = attrIndexMap.get(atrribute);
			// ������Եĸ���ֵ�ͷ���ֵ
			LinkedHashSet<String> values = new LinkedHashSet<String>();
			LinkedHashSet<String> classes = new LinkedHashSet<String>();
			for (int i = 0; i < deData.length; i++) {
				if (flags[i] == true) {
					values.add(deData[i][index]);
					classes.add(deData[i][class_index]);
				}
			}
			
			values_count = values.size();
			class_count = classes.size();
			int values_vector[] = new int[values_count * class_count];
			int class_vector[] = new int[class_count];
			for (int i = 0; i < deData.length; i++) {
				if (flags[i] == true) {
					int j = 0;
					for (String v: values) {
						if (deData[i][index].equals(v)) {
							break;
						} else {
							j++;
						}
					}
					int k = 0;
					for (String c : classes) {
						if (deData[i][class_index].equals(c)) {
							break;
						} else {
							k++;
						}
					}
					values_vector[j * class_count + k]++;
					class_vector[k]++;
				}
			}
			// ����InforD
			double InfoD = 0.0;
			double class_total = 0.0;
			for (int i = 0; i < class_vector.length; i++) {
				class_total += class_vector[i];
			}

			for (int i = 0; i < class_vector.length; i++) {
				if (class_vector[i] == 0) {
					continue;
				} else {
					double d = Math.log(class_vector[i] / class_total)
							/ Math.log(2.0) * class_vector[i] / class_total;
					InfoD = InfoD - d;
				}
			}

			// ����InfoA
			double InfoA = 0.0;
			for (int i = 0; i < values_count; i++) {
				double middle = 0.0;
				double attr_count = 0.0;
				for (int j = 0; j < class_count; j++) {
					attr_count += values_vector[i * class_count + j];
				}

				for (int j = 0; j < class_count; j++) {
					if (values_vector[i * class_count + j] != 0) {
						double k = values_vector[i * class_count + j];
						middle = middle - Math.log(k / attr_count)
								/ Math.log(2.0) * k / attr_count;
					}
				}
				InfoA += middle * attr_count / class_total;
			}
			Gain[count] = InfoD - InfoA;
//			for(double d:Gain){
//				System.out.println(atrribute+":"+d);
//			}
			count++;
		}
		double max = 0.0;
		int i = 0;
		for (String atrribute : attributes) {
			if (Gain[i] > max) {
				max = Gain[i];
				return_atrribute = atrribute;
			}
			i++;
		}
		return return_atrribute;
	}
	int num=0;
	// node���ڵ�ǰ��㹹�������
	// deData�����ݼ�
	// flags��ָʾ�ڵ�ǰ��㹹�������ʱ��Щ��������Ҫ��
	// attributes��δ��������Լ�
	// attrIndexMap���������Ӧ�����±�
	public String[][] buildDecisionTree(TreeNode node, String[][] deData,
			boolean flags[],
			LinkedHashSet<String> attributes,
			HashMap<String, Integer> attrIndexMap) {
		// ��������������ѿ�
		if (attributes.isEmpty() == true) {
			// �����ݼ���ѡ������࣬����������������������
			HashMap<String, Integer> classMap = new HashMap<String, Integer>();
			int classIndex = deData[0].length - 1;
			for (int i = 0; i < deData.length; i++) {
				if (flags[i] == true) {
					if (classMap.containsKey(deData[i][classIndex])) {
						int count = classMap.get(deData[i][classIndex]);
						classMap.put(deData[i][classIndex], count + 1);
					} else {
						classMap.put(deData[i][classIndex], 1);
					}
				}
			}

			// ѡ�������
			String mostClass = null;
			int mostCount = 0;
			Iterator<String> it = classMap.keySet().iterator();
			while (it.hasNext()) {
				String strClass = (String) it.next();
				if (classMap.get(strClass) > mostCount) {
					mostClass = strClass;
					mostCount = classMap.get(strClass);
				}
			}

			// �Խ����и�ֵ���ý��ΪҶ���
			node.setElement(mostClass);
			node.setChilds(null);
			System.out.println("leaf:" + node.getElement() + ":"+ node.getValue());
			Result[index++] = new String[]{"leaf" ,node.getElement() , node.getValue()};
			return Result;
		}

		// �������������ȫ������һ����
		int class_index = deData[0].length - 1;
		String class_name = null;
		HashSet<String> classSet = new HashSet<String>();
		for (int i = 0; i < deData.length; i++) {
			if (flags[i] == true) {
				class_name = deData[i][class_index];
				classSet.add(class_name);
			}
		}
		// ��ý��ΪҶ��㣬�����й�ֵ��Ȼ�󷵻�
		if (classSet.size() == 1) {
			node.setElement(class_name);
			node.setChilds(null);
			System.out.println("leaf:" + node.getElement() + ":"+ node.getValue());
			Result[index++] = new String[]{"leaf",node.getElement(),node.getValue()};
			return Result;
		}

		// ѡ��һ����������
		String attribute = selectAtrribute(node, deData, flags, attributes,attrIndexMap);
		// ���÷��ѽ���ֵ
		node.setElement(attribute);	
		if (node == root) {
			num++;
			if(num == 1){
				System.out.println("root:" + node.getElement() + ":"+ node.getValue());
				Result[index++] = new String[]{"root",node.getElement(),node.getValue()};
			}else{
				System.exit(0);
			}
		} else {
			System.out.println("branch:" + node.getElement() + ":"+ node.getValue());
			Result[index++] = new String[]{"branch",node.getElement(),node.getValue()};
		}
		// ���ɺ����ø����ӽ��
		int attrIndex = attrIndexMap.get(attribute);
		LinkedHashSet<String> attrValues = new LinkedHashSet<String>();
		for (int i = 0; i < deData.length; i++) {
			if (flags[i] == true) {
				attrValues.add(deData[i][attrIndex]);
			}
		}
		LinkedHashSet<TreeNode> childs = new LinkedHashSet<TreeNode>();
		for (String attrValue : attrValues) {
			TreeNode tn = new TreeNode(attrValue);
			childs.add(tn);
		}
		node.setChilds(childs);
		// �ں�ѡ����������ɾ����ǰ����
		attributes.remove(attribute);
		// �ڸ����ӽ���ϵݹ���ñ�����
		if (childs.isEmpty() != true) {
			for (TreeNode child : childs) {
				// �����ӽ�����������ݼ�
				boolean newFlags[] = new boolean[deData.length];
				for (int i = 0; i < deData.length; i++) {
					newFlags[i] = flags[i];
					if (deData[i][attrIndex] != child.getValue()) {
						newFlags[i] = false;
					}
				}
				// �����ӽ�����������Լ�
				LinkedHashSet<String> newAttributes = new LinkedHashSet<String>();
				for (String attr : attributes) {
					newAttributes.add(attr);
				}
				// ���ӽ���ϵݹ����ɾ�����
				buildDecisionTree(child, deData, newFlags, newAttributes,attrIndexMap);
			}
		}
		return Result;
	}
}