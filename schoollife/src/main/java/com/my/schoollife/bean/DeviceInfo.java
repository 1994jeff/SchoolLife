package com.my.schoollife.bean;

import java.io.Serializable;

public class DeviceInfo extends Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String phone;
	private String headUrl;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceInfo [name=");
		builder.append(name);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", headUrl=");
		builder.append(headUrl);
		builder.append("]");
		return builder.toString();
	}
}
