package com.my.schoollife.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.schoollife.bean.RetParam;
import com.my.schoollife.bean.Timetable;
import com.my.schoollife.service.TimeTableService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/timeTable")
public class TimeTableController extends BaseController {

	@Resource
	TimeTableService timeTableService;
	
	@RequestMapping("/addTimetable.do")
	@ResponseBody
	public String addTimetable(HttpSession session,Timetable c) {
		RetParam<Timetable> param = new RetParam<>();
		try {
			timeTableService.insert(c);
			param.setRetMsg("添加成功");
			param.setRetCode(SUCCESS_CODE);
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
	@RequestMapping("/getTimetable.do")
	@ResponseBody
	public String getTimetable(HttpSession session,Timetable c) {
		RetParam<Timetable> param = new RetParam<>();
		try {
			List<Timetable> card = timeTableService.getTimeTable(c);
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
