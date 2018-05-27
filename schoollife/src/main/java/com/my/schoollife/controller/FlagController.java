package com.my.schoollife.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.schoollife.bean.Flag;
import com.my.schoollife.bean.RetParam;
import com.my.schoollife.service.FlagService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/flag")
public class FlagController extends BaseController {

	@Resource
	FlagService flagService;
	
	@RequestMapping("/addFlag.do")
	@ResponseBody
	public String addFlag(HttpSession session,String deviceFlag) {
		RetParam<Flag> param = new RetParam<>();
		try {
			List<Flag> list = flagService.getFlag();
			if(list!=null && list.size()>0) {
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getDeviceFlag().equals(deviceFlag)) {
						throw new Exception("分类已存在！");
					};
				}
				flagService.insert(deviceFlag);
				param.setRetMsg("添加成功");
				param.setRetCode(SUCCESS_CODE);	
			}
			
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
	@RequestMapping("/getFlag.do")
	@ResponseBody
	public String getFlag(HttpSession session) {
		RetParam<Flag> param = new RetParam<>();
		try {
			List<Flag> l = flagService.getFlag();
			param.setRetMsg("成功");
			param.setRetData(l);
			param.setRetCode(SUCCESS_CODE);
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
}
