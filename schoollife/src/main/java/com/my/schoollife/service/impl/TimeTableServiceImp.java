package com.my.schoollife.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.schoollife.bean.Timetable;
import com.my.schoollife.dao.TimeTableDao;
import com.my.schoollife.service.TimeTableService;
@Service("timeTableService")
public class TimeTableServiceImp implements TimeTableService {

	@Resource
	TimeTableDao timeTableDao;
	
	@Override
	public List<Timetable> getTimeTable(Timetable c) {
		return timeTableDao.getTimeTable(c);
	}

	@Override
	public void insert(Timetable c) {
		timeTableDao.insert(c);
	}

}
