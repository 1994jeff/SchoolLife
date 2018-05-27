package com.my.schoollife.service;

import java.util.List;

import com.my.schoollife.bean.Student;

public interface StudentService {

	void insert(Student c);

	List<Student> getStudent(Student c);

}
