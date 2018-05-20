package com.my.schoollife.service;

import java.util.List;

import com.my.schoollife.bean.RetParam;
import com.my.schoollife.bean.User;

public interface UserService {

	List<User> getUserByCondition(User user) throws Exception;

	void updateUserByCondition(User user) throws Exception;

	void insertUser(User auser) throws Exception;

	void deleteUserByUserNo(String userNo)throws Exception;

}
