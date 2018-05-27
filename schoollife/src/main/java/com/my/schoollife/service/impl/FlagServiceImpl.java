package com.my.schoollife.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.schoollife.bean.Flag;
import com.my.schoollife.dao.FlagDao;
import com.my.schoollife.service.FlagService;

@Service("flagService")
public class FlagServiceImpl implements FlagService {
	
	@Resource
	FlagDao flagDao;

	@Override
	public void insert(String deviceFlag) {
		flagDao.insert(deviceFlag);
	}

	@Override
	public List<Flag> getFlag() {
		return flagDao.getFlag();		
	}
	
}
