package com.my.schoollife.service;

import java.util.List;

import com.my.schoollife.bean.Book;

public interface DeviceService {

	void insertBook(Book book) throws Exception;

	void updateBookByCondition(Book book) throws Exception;
	
	List<Book> getBookByCondition(Book book) throws Exception;
	
	void deleteBook(Book book) throws Exception;
}
