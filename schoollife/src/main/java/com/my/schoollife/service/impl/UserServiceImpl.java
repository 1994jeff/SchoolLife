package com.my.schoollife.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.schoollife.bean.User;
import com.my.schoollife.dao.UserDao;
import com.my.schoollife.service.UserService;
import com.my.schoollife.utils.DomainNoUtils;
import com.my.schoollife.utils.TextUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	UserDao userDao;
	
	
	@Override
	public List<User> getUserByCondition(User user) throws Exception {
		try {
			return userDao.getUserByCondition(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void updateUserByCondition(User user) throws Exception {
		try {
			if(user==null || TextUtil.isEmpty(user.getUserNo())) {
				throw new Exception("用户编号不能为空！");
			}
			userDao.updateUserByCondition(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void insertUser(User auser) throws Exception {
		try {
			User user = new User();
			user.setUserName(auser.getUserName());
			if(userDao.getUserByCondition(user).size()>0){
				throw new Exception("添加失败! 账户【"+auser.getUserName()+"】已存在！");
			}
			String userNo = DomainNoUtils.getNoByPreStr(DomainNoUtils.USER_NO);
			auser.setUserNo(userNo);
			auser.setHeadUrl("");
			Date d = new Date();
			auser.setCreateTime(d);
			userDao.insertUser(auser);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteUserByUserNo(String userNo) throws Exception {
		try {
			userDao.deleteUserByUserNo(userNo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
