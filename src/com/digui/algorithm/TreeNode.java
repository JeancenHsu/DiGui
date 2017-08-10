package com.digui.algorithm;

import java.util.LinkedHashSet;

import com.digui.algorithm.TreeNode;


public class TreeNode {
	String element; // ��ֵΪ���ݵ���������
	String value; // ��һ�����������ڴ˽���ֵ
	LinkedHashSet<TreeNode> childs; // �����ӽ�㣬����˳�����ʽ��ϣ���洢

	public TreeNode() {
		this.element = null;
		this.value = null;
		this.childs = null;
	}

	public TreeNode(String value) {
		this.element = null;
		this.value = value;
		this.childs = null;
	}

	public String getElement() {
		return this.element;
	}

	public void setElement(String e) {
		this.element = e;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String v) {
		this.value = v;
	}

	public LinkedHashSet<TreeNode> getChilds() {
		return this.childs;
	}

	public void setChilds(LinkedHashSet<TreeNode> childs) {
		this.childs = childs;
	}
	
	@Override
	public boolean equals(Object obj) {
		TreeNode t=(TreeNode)obj;
		if(!element.equals(t.element)){
			return false;
		}
		if(!value.equals(t.value)){
			return false;
		}
		if(!childs.equals(t.childs)){
			return false;
		}
		return true;
	}
}
