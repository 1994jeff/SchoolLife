package com.my.schoollife.service;

import java.util.List;

import com.my.schoollife.bean.Card;
import com.my.schoollife.bean.CardDto;

public interface CardService {

	void insert(Card c);

	List<CardDto> getCard(Card c);

}
