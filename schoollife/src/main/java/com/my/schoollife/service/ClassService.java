package com.my.schoollife.service;

import java.util.List;

import com.my.schoollife.bean.ClassInfo;

public interface ClassService{

	void insert(ClassInfo c);

	List<ClassInfo> getClass(ClassInfo c);

}
