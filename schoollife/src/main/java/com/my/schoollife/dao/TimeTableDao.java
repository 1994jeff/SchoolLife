package com.my.schoollife.dao;

import java.util.List;

import com.my.schoollife.bean.Timetable;

public interface TimeTableDao {

	void insert(Timetable c);

	List<Timetable> getTimeTable(Timetable c);

}
