package com.my.schoollife.dao;

import java.util.List;

import com.my.schoollife.bean.ClassInfo;

public interface ClassDao {

	public void insert(ClassInfo c);

	public List<ClassInfo> getClasss();

}
