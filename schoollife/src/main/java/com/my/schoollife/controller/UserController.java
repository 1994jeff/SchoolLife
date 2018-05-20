package com.my.schoollife.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.my.schoollife.bean.RetParam;
import com.my.schoollife.bean.User;
import com.my.schoollife.service.UserService;
import com.my.schoollife.utils.ImgUtils;
import com.my.schoollife.utils.TextUtil;

import net.sf.json.JSONObject;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	UserService userService;
	
	@RequestMapping("/modifyPsd.do")
	@ResponseBody
	public String modifyPsd(HttpSession session,String userNo, String userPsd, String newUserPsd) {
		RetParam<Object> obj = new RetParam<>();
		 try {
		 if(TextUtil.isEmpty(userNo)) {
			throw new Exception("请传入要修改的用户编号"); 
		 }
		 if(TextUtil.isEmpty(userPsd)) {
			throw new Exception("请传入旧密码"); 
		 }
		 if(TextUtil.isEmpty(newUserPsd)) {
			throw new Exception("请传入新密码"); 
		 }
		 User user = new User();
		 user.setUserNo(userNo);
		 user.setUserPsd(userPsd);
		List<User> list = userService.getUserByCondition(user );
		if(list!=null && list.size()>0) {
			userService.updateUserByCondition(user);	
			obj.setRetCode(SUCCESS_CODE);
			obj.setRetMsg("修改成功");
		}else {
			throw new Exception("用户不存在，无法修改");
		}
		 } catch (Exception e) {
		 obj.setRetMsg(e.getMessage());
		 obj.setRetCode(FAILED_CODE);
		 }
		return JSONObject.fromObject(obj, getJsonConfig()).toString();
	}

	@RequestMapping("/queryUser.do")
	@ResponseBody
	public String queryUser(HttpSession session, User quser) {
		RetParam<User> obj = new RetParam<>();
		try {
			List<User> uList = userService.getUserByCondition(quser);
			obj.setRetCode("success");
			obj.setRetData(uList);
		} catch (Exception e) {
			obj.setRetMsg(e.getMessage());
			obj.setRetCode("failed");
		}
		return JSONObject.fromObject(obj, getJsonConfig()).toString();
	}

	@RequestMapping("/insertUser.do")
	@ResponseBody
	public String insertUser(HttpSession session, Model model, User auser) {
		RetParam<Object> retParam = new RetParam<>();
		try {
			if (auser == null) {
				throw new Exception("用户参数为空！");
			}
			userService.insertUser(auser);
			retParam.setRetCode("success");
			retParam.setRetMsg("注册用户成功！");
		} catch (Exception e) {
			retParam.setRetCode("failed");
			retParam.setRetMsg(e.getMessage());
		}
		return JSONObject.fromObject(retParam).toString();
	}

	@RequestMapping("/deleteUser.do")
	@ResponseBody
	public String deleteUser(HttpSession session, Model model, String userNo) {
		RetParam<Object> retParam = new RetParam<>();
		User user = getSessionUser(session);
		try {
			if (userNo.equals("")) {
				throw new Exception("Sorry, please pass in the parameters！");
			}
			userService.deleteUserByUserNo(userNo);
			retParam.setRetCode("success");
			retParam.setRetMsg("successfully deleted！");
		} catch (Exception e) {
			retParam.setRetCode("failed");
			retParam.setRetMsg(e.getMessage());
		}
		return JSONObject.fromObject(retParam).toString();
	}

	@RequestMapping("/updateUser.do")
	@ResponseBody
	public String updateUser(HttpSession session, Model model, User auser) {
		RetParam<Object> retParam = new RetParam<>();
		try {
			userService.updateUserByCondition(auser);
			retParam.setRetCode(SUCCESS_CODE);
			retParam.setRetMsg("update success！");
		} catch (Exception e) {
			retParam.setRetCode(FAILED_CODE);
			retParam.setRetMsg(e.getMessage());
		}
		return JSONObject.fromObject(retParam).toString();
	}

	@RequestMapping(value="/uploadImg.do")
	@ResponseBody
	public String uploadImg(HttpServletRequest req,HttpSession session,MultipartFile file,String userNo){
		RetParam<Object> param = new RetParam<Object>(); 
		try {
			if(file==null){
				throw new Exception("文件读取失败！");
			}
			int pointValue = file.getOriginalFilename().lastIndexOf(".");
			String imgType = file.getOriginalFilename().substring(pointValue+1,file.getOriginalFilename().length());				//
			//判断图片类型
			if(imgType.equals("png")||imgType.equals("jpg")||imgType.equals("gif")){
				String name = new Date().getTime()+"."+imgType;
				String headPath = "fileupload/headimg/"+name;
				
				//保存图片到服务器
				URL url = req.getServletContext().getResource("fileupload/headimg");
				String path = url.getPath();
				ImgUtils.saveImg(path,name,file);
				
				//保存用户头像路径到数据库
				User userParam = new User();
				userParam.setUserNo(userNo);
				userParam.setHeadUrl(headPath);
				userService.updateUserByCondition(userParam);
				param.setRetMsg(headPath);
				param.setRetCode(SUCCESS_CODE);
			}else{
				throw new Exception("图片格式错误！");
			}
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
}
