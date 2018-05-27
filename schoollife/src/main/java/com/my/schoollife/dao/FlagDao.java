package com.my.schoollife.dao;

import java.util.List;

import com.my.schoollife.bean.Flag;

public interface FlagDao {

	void insert(String deviceFlag);

	List<Flag> getFlag();

}
