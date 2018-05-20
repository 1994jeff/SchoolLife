package com.my.schoollife.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String userNo;
	private String userName;
	private String userPsd;
	private String headUrl;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastLoginTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;
	private String remark;
	
	/**************/
	private String name;
	private String sex;
	private String age;
	private String email;
	private String phone;
	private String address;
	/**************/
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPsd() {
		return userPsd;
	}
	public void setUserPsd(String userPsd) {
		this.userPsd = userPsd;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return String.format(
				"User [id=%s, userNo=%s, userName=%s, userPsd=%s, headUrl=%s, lastLoginTime=%s, createTime=%s, updateTime=%s, remark=%s, name=%s, sex=%s, age=%s, email=%s, phone=%s, address=%s]",
				id, userNo, userName, userPsd, headUrl, lastLoginTime, createTime, updateTime, remark, name, sex, age,
				email, phone, address);
	}
}
