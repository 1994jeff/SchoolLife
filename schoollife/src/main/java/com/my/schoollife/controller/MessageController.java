package com.my.schoollife.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.schoollife.bean.Message;
import com.my.schoollife.bean.RetParam;
import com.my.schoollife.bean.User;
import com.my.schoollife.service.MessageService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

	@Resource
	MessageService messageService;
	
	@RequestMapping("/getAllMessage.do")
	@ResponseBody
	public String getMessage(User user) {
		RetParam<Message> param = new RetParam<>();
		try {
			List<Message> list = messageService.getAllMessage();
			param.setRetCode(SUCCESS_CODE);
			param.setRetData(list);
		} catch (Exception e) {
			param.setRetCode(FAILED_CODE);
			param.setRetMsg(e.getMessage());
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
}
