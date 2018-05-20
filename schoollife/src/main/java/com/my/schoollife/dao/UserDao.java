package com.my.schoollife.dao;

import java.util.List;

import com.my.schoollife.bean.RetParam;
import com.my.schoollife.bean.User;

public interface UserDao {

	List<User> getUserByCondition(User user);

	void updateUserByCondition(User user);

	void insertUser(User auser);

	void deleteUserByUserNo(String userNo);

}
