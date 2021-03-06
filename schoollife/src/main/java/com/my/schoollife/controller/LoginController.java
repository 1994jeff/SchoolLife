package com.my.schoollife.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.schoollife.bean.RetParam;
import com.my.schoollife.bean.User;
import com.my.schoollife.service.UserService;
import com.my.schoollife.utils.TextUtil;

import net.sf.json.JSONObject;

/**
 *登录控制器
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	UserService userService;
	
	@RequestMapping("/toIndex.do")
	public String toIndex(HttpSession session,Model model){
		User user = getSessionUser(session);
		log.debug("------------------------login jsp page------------------------");
		if(null==user)
		{
			return "redirect:/login/toLogin.do";
		}
		try {
//			if(user.getAuth().equals("3")){
//				model.addAttribute("errorMsg","请通知管理员先激活您的商家/客户身份后方可登录！");
//				return "error/404";
//			}
			model.addAttribute("user",user);
			
		} catch (Exception e) {
			model.addAttribute("errorMsg",e.getMessage());
			return "error/404";
		}
		return "index";
	}
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		return "../../index";
	}
	@RequestMapping("/toExit.do")
	public String toExit(HttpSession session){
		session.removeAttribute("user");
		User user = (User) session.getAttribute("user");
		if(user!=null){
			session.setAttribute("user", null);
		}
		return "../../index";
	}
	
	@RequestMapping(value="/toCheckLogin.do")
	@ResponseBody
	public String toCheckLogin(User user,HttpSession session){
		RetParam<User> param = new RetParam<User>();
		try {
			if(null==user){
				throw new Exception("user param are not exists!!!");
			}
			if(TextUtil.isEmpty(user.getUserName())||TextUtil.isEmpty(user.getUserPsd())) {
				throw new Exception("登录名和密码不能为空!!!");
			}
			List<User> uList = userService.getUserByCondition(user);
			if(null!=uList&&uList.size()==1){
				param.setRetMsg("登录成功");
				param.setRetData(uList);
				User u = new User();
				u.setUserName(user.getUserName());
				u.setUserPsd(user.getUserPsd());
				u.setLastLoginTime(new Date());
				userService.updateUserByCondition(u);
				param.setRetCode("success");
			}else{
				param.setRetMsg("登录名或密码错误！");
				param.setRetCode("failed");
			}
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode("failed");
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
}
