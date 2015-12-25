package com.naming.peoplehelp.entity;

import java.io.Serializable;

public class Order implements Serializable{

	private static final long serialVersionUID = 436336385583508952L;
	
	private String id;
	
	private String user_name;
	
	private Integer order_type;//��������
	
	private String order_address;//������ַ
	
	private String order_detail;//��������
	
	private String order_time;//����ʱ��
	
	private Integer order_state;//����״̬ 0:��δ��ʼ ��1�����ڽ��У�2���Ѿ����

	public Order(String id, String user_name, Integer order_type,
			String order_address, String order_detail, String order_time,
			Integer order_state) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.order_type = order_type;
		this.order_address = order_address;
		this.order_detail = order_detail;
		this.order_time = order_time;
		this.order_state = order_state;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getOrder_type() {
		return order_type;
	}

	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}

	public String getOrder_address() {
		return order_address;
	}

	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}

	public String getOrder_detail() {
		return order_detail;
	}

	public void setOrder_detail(String order_detail) {
		this.order_detail = order_detail;
	}

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}

	public Integer getOrder_state() {
		return order_state;
	}

	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}
	
}
