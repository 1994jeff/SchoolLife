package com.my.schoollife.service;

import java.util.List;

import com.my.schoollife.bean.City;

public interface CityService {

	void insertCity(City city) throws Exception;

	List<City> getAllCity();

	List<City> getCityLike(City c) throws Exception;

}
