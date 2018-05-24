package com.my.schoollife.service.impl;

import java.util.List;

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
	public void insertCity(City city) throws Exception {
		try {
			if(city==null||"".equals(city.getCity())) {
				throw new Exception("城市名为空");
			}
			cityDao.insertCity(city);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<City> getAllCity() {
		return cityDao.getCityByCondition(new City());
	}

	@Override
	public List<City> getCityLike(City c) throws Exception {
		List<City> list = null;
		try {
			list = cityDao.getCityLike(c);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return list;
	}

}
