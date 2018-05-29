package com.my.schoollife.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.schoollife.bean.Card;
import com.my.schoollife.bean.CardDto;
import com.my.schoollife.bean.RetParam;
import com.my.schoollife.service.CardService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/card")
public class CardController extends BaseController {

	Logger log = LoggerFactory.getLogger(CardController.class); 
	
	@Resource
	CardService cardService;
	
	@RequestMapping("/addCard.do")
	@ResponseBody
	public String addCard(HttpSession session,Card c) {
		RetParam<Card> param = new RetParam<>();
		try {
			cardService.insert(c);
			param.setRetMsg("添加成功");
			param.setRetCode(SUCCESS_CODE);
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
	@RequestMapping("/getCard.do")
	@ResponseBody
	public String getCard(HttpSession session,Card c) {
		RetParam<CardDto> param = new RetParam<>();
		try {
			List<CardDto> card = cardService.getCard(c);
			param.setRetMsg("添加成功");
			param.setRetData(card);
			param.setRetCode(SUCCESS_CODE);
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
	@RequestMapping("/updateCard.do")
	@ResponseBody
	public String updateCard(HttpSession session,Card c) {
		RetParam<CardDto> param = new RetParam<>();
		try {
			cardService.updateCard(c);
			param.setRetMsg("添加成功");
			param.setRetCode(SUCCESS_CODE);
		} catch (Exception e) {
			param.setRetMsg(e.getMessage());
			param.setRetCode(FAILED_CODE);
		}
		return JSONObject.fromObject(param,getJsonConfig()).toString();
	}
	
}
