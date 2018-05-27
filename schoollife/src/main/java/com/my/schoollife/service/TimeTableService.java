package com.my.schoollife.service;

import java.util.List;

import com.my.schoollife.bean.Timetable;

public interface TimeTableService {

	List<Timetable> getTimeTable(Timetable c);

	void insert(Timetable c);

}
