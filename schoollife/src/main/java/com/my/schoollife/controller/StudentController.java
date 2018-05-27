package com.my.schoollife.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.schoollife.bean.Card;
import com.my.schoollife.bean.CardDto;
import com.my.schoollife.bean.RetParam;
import com.my.schoollife.bean.Student;
import com.my.schoollife.service.CardService;
import com.my.schoollife.service.StudentService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

	@Resource
	StudentService studentService;
	
	@RequestMapping("/addStudent.do")
	@ResponseBody
	public String addCard(HttpSession session,Student c) {
		RetParam<Card> param = new RetParam<>();
		try {
			studentService.insert(c);
			param.setRetMsg("添加成功");
			param.setRetCode(SUCCESS_CODE);
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
	@RequestMapping("/getStudent.do")
	@ResponseBody
	public String getStudent(HttpSession session,Student c) {
		RetParam<Student> param = new RetParam<>();
		try {
			List<Student> card = studentService.getStudent(c);
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
