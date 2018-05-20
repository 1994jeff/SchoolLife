package com.my.schoollife.bean;

import java.util.List;

public class RetParam<T> {

	private String retCode;
	private String retMsg;
	private Object obj;
	private List<T> retData;
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public List<T> getRetData() {
		return retData;
	}
	public void setRetData(List<T> retData) {
		this.retData = retData;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RetParam [retCode=");
		builder.append(retCode);
		builder.append(", retMsg=");
		builder.append(retMsg);
		builder.append(", retData=");
		builder.append(retData);
		builder.append(", obj=");
		builder.append(obj);
		builder.append("]");
		return builder.toString();
	}
}
