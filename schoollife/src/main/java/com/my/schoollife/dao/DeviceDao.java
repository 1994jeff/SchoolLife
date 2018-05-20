package com.my.schoollife.dao;

import java.util.List;

import com.my.schoollife.bean.Book;

public interface DeviceDao {

	void insertBook(Book book);

	List<Book> getBookByCondition(Book book);

	void updateBookByCondition(Book book);

	void deleteBook(Book book);

}
