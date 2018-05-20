package com.my.schoollife.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.schoollife.bean.Book;
import com.my.schoollife.bean.RetParam;
import com.my.schoollife.service.DeviceService;
import com.my.schoollife.utils.TextUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController {

	@Resource
	DeviceService deviceService;
	
	@RequestMapping("/addDevice.do")
	@ResponseBody
	public String addDevice(HttpSession session,Book book) {
		RetParam<Book> param = new RetParam<>();
		try {
			deviceService.insertBook(book);
			param.setRetMsg("添加成功");
			param.setRetCode(SUCCESS_CODE);
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
	@RequestMapping("/updateDevice.do")
	@ResponseBody
	public String updateDevice(HttpSession session,Book book) {
		RetParam<Book> param = new RetParam<>();
		try {
			deviceService.updateBookByCondition(book);
			param.setRetMsg("更新成功");
			param.setRetCode(SUCCESS_CODE);
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
	@RequestMapping("/getDevice.do")
	@ResponseBody
	public String getDevice(HttpSession session,Book book) {
		RetParam<Book> param = new RetParam<>();
		try {
			List<Book> list = deviceService.getBookByCondition(book);
			param.setRetData(list);
			param.setRetCode(SUCCESS_CODE);
			param.setRetMsg("查询成功");
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
	@RequestMapping("/deleteDevice.do")
	@ResponseBody
	public String deleteDevice(HttpSession session,Book book) {
		RetParam<Book> param = new RetParam<>();
		try {
			deviceService.deleteBook(book);
			param.setRetCode(SUCCESS_CODE);
			param.setRetMsg("删除成功");
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
}
