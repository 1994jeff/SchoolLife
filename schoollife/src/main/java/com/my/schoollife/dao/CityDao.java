package com.my.schoollife.dao;

import java.util.List;

import com.my.schoollife.bean.City;

public interface CityDao {

	void insertCity(City city);
	
	List<City> getCityByCondition(City city);

	List<City> getCityLike(City c);
}
