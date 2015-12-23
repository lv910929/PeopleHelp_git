package com.naming.peoplehelp.entity;

import java.io.Serializable;

public class Address implements Serializable{

	private static final long serialVersionUID = 2537450045024088120L;
	
	private Integer id;
	
	private String  userName;
	
	private String  phoneNumber;
	
	private String  addressName;
	
	private String  addressDetail;

	public Address(Integer id, String userName, String phoneNumber,
			String addressName, String addressDetail) {
		super();
		this.id = id;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.addressName = addressName;
		this.addressDetail = addressDetail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

}
