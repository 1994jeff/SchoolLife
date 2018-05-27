package com.my.schoollife.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.schoollife.bean.ClassInfo;
import com.my.schoollife.dao.ClassDao;
import com.my.schoollife.service.ClassService;
@Service("classService")
public class ClassServiceImpl implements ClassService {

	@Resource
	ClassDao classDao;
	
	@Override
	public void insert(ClassInfo c) {
		classDao.insert(c);
	}

	@Override
	public List<ClassInfo> getClass(ClassInfo c) {
		return classDao.getClasss();
	}

}
