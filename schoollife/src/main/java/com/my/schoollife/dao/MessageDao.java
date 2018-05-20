package com.my.schoollife.dao;

import java.util.List;

import com.my.schoollife.bean.Message;

public interface MessageDao {

	void insertMessage(Message msg);

	List<Message> getAllMessage();

}
