package com.my.schoollife.dao;

import java.util.List;

import com.my.schoollife.bean.Card;
import com.my.schoollife.bean.CardDto;

public interface CardDao {

	void insert(Card c);

	void updateCard(Card c);

	List<CardDto> getCard(Card c);

}
