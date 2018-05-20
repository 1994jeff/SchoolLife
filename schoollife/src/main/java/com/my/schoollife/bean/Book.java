package com.my.schoollife.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备预约信息 
 */
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	
	private String id;
	private String bookNo;
	private String deviceName;
	private String deviceStatus;
	private String deviceFlag;
	private String userNo;
	private Date createTime;
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public String getDeviceFlag() {
		return deviceFlag;
	}
	public void setDeviceFlag(String deviceFlag) {
		this.deviceFlag = deviceFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return String.format(
				"Book [id=%s, bookNo=%s, deviceName=%s, deviceStatus=%s, deviceFlag=%s, userNo=%s, createTime=%s, remark=%s]",
				id, bookNo, deviceName, deviceStatus, deviceFlag, userNo, createTime, remark);
	}
}
