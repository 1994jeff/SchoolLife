package com.my.schoollife.service;

import java.util.List;

import com.my.schoollife.bean.Flag;

public interface FlagService {

	void insert(String deviceFlag);

	List<Flag> getFlag();

	
}
