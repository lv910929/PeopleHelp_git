package com.naming.peoplehelp.api.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class SpecialItem implements Serializable {

	private static final long serialVersionUID = -5982286909589038691L;
	
	private String catname;
	private ArrayList<ArticleItem> data;

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public ArrayList<ArticleItem> getData() {
		return data;
	}

	public void setData(ArrayList<ArticleItem> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "SpecialItme [catname=" + catname + ", data=" + data + "]";
	}

	public SpecialItem(String catname, ArrayList<ArticleItem> data) {
		super();
		this.catname = catname;
		this.data = data;
	}

	public SpecialItem() {
		super();
	}

}
