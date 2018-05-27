package com.my.schoollife.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.schoollife.bean.Student;
import com.my.schoollife.dao.StudentDao;
import com.my.schoollife.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	StudentDao studentDao;
	
	@Override
	public void insert(Student c) {
		studentDao.insert(c);
	}

	@Override
	public List<Student> getStudent(Student c) {
		return studentDao.getStudent(c);
	}

}
