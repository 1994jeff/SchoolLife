package com.my.schoollife.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.my.schoollife.bean.User;
import com.my.schoollife.utils.JsonDateValueProcessor;

import net.sf.json.JsonConfig;
/**
 */
@Controller
public class BaseController {
	
	final String SUCCESS_CODE = "success";
	final String FAILED_CODE = "failed";
	
	@InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }
	
	private JsonConfig jsonConfig = new JsonConfig();  

	public User getSessionUser(HttpSession session){
		return (User) session.getAttribute("user");
	}
	
	public JsonConfig getJsonConfig(){
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		return jsonConfig;
	}
	
	
}
