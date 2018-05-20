package com.my.schoollife.service;

import java.util.List;

import com.my.schoollife.bean.Message;

public interface MessageService {

	void insertMessage(Message msg) throws Exception;

	List<Message> getAllMessage() throws Exception;

}
