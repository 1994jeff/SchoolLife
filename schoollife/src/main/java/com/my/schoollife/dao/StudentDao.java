package com.my.schoollife.dao;

import java.util.List;

import com.my.schoollife.bean.Student;

public interface StudentDao {

	void insert(Student c);

	List<Student> getStudent(Student c);

}
