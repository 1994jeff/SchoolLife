package com.my.schoollife.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.schoollife.bean.Card;
import com.my.schoollife.bean.CardDto;
import com.my.schoollife.bean.ClassInfo;
import com.my.schoollife.bean.RetParam;
import com.my.schoollife.service.CardService;
import com.my.schoollife.service.ClassService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/class")
public class ClassController extends BaseController {

	@Resource
	ClassService classService;
	
	@RequestMapping("/addClass.do")
	@ResponseBody
	public String addCard(HttpSession session,ClassInfo c) {
		RetParam<ClassInfo> param = new RetParam<>();
		try {
			classService.insert(c);
			param.setRetMsg("添加成功");
			param.setRetCode(SUCCESS_CODE);
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
	@RequestMapping("/getClass.do")
	@ResponseBody
	public String getClass(HttpSession session,ClassInfo c) {
		RetParam<ClassInfo> param = new RetParam<>();
		try {
			List<ClassInfo> card = classService.getClass(c);
			param.setRetMsg("添加成功");
			param.setRetData(card);
			param.setRetCode(SUCCESS_CODE);
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
}
