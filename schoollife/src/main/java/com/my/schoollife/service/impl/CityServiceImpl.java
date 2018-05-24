package com.my.schoollife.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.schoollife.bean.City;
import com.my.schoollife.dao.CityDao;
import com.my.schoollife.service.CityService;

@Service("cityService")
public class CityServiceImpl implements CityService {
	
	@Resource
	CityDao cityDao;

	@Override
	public void insertCity(City city) {
		cityDao.insertCity(city);
	}

}
