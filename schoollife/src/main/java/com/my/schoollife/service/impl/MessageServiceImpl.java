package com.my.schoollife.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.schoollife.bean.Message;
import com.my.schoollife.dao.MessageDao;
import com.my.schoollife.service.MessageService;
import com.my.schoollife.utils.DomainNoUtils;
import com.my.schoollife.utils.TextUtil;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Resource
	MessageDao messageDao;
	
	@Override
	public void insertMessage(Message msg) throws Exception {
		try {
			if(msg==null || TextUtil.isEmpty(msg.getUserNo())||TextUtil.isEmpty(msg.getContent())) {
				throw new Exception("请传入用户编号userNo,内容content,名字name");
			}
			String msgNo = DomainNoUtils.getNoByPreStr(DomainNoUtils.COLLECT_NO);
			msg.setReceiveNo("");
			msg.setReceiveType("all");
			msg.setRecordNo(msgNo);
			messageDao.insertMessage(msg);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Message> getAllMessage() throws Exception {
		List<Message> list = null;
		try {
			list = messageDao.getAllMessage();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return list;
	}

}
