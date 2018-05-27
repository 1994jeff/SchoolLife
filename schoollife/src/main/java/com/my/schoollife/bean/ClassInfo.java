package com.my.schoollife.bean;

import java.io.Serializable;

public class ClassInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String classNo;
	private String profession;
	private String className;
	private String classPeople;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassPeople() {
		return classPeople;
	}
	public void setClassPeople(String classPeople) {
		this.classPeople = classPeople;
	}

}
