package com.my.schoollife.dao;

import java.util.List;

import com.my.schoollife.bean.Card;
import com.my.schoollife.bean.CardDto;

public interface CardDao {

	List<CardDto> getCard();

	void insert(Card c);

}
