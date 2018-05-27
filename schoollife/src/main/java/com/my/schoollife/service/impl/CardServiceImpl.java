package com.my.schoollife.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.schoollife.bean.Card;
import com.my.schoollife.bean.CardDto;
import com.my.schoollife.dao.CardDao;
import com.my.schoollife.service.CardService;

@Service("cardService")
public class CardServiceImpl implements CardService {

	@Resource
	CardDao cardDao;
	
	@Override
	public void insert(Card c) {
		cardDao.insert(c);
	}

	@Override
	public List<CardDto> getCard(Card c) {
		return cardDao.getCard();
	}

	@Override
	public void updateCard(Card c) {
		cardDao.updateCard(c);
	}

}
