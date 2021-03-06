package com.my.schoollife.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.schoollife.bean.Book;
import com.my.schoollife.dao.DeviceDao;
import com.my.schoollife.service.DeviceService;
import com.my.schoollife.utils.DomainNoUtils;
import com.my.schoollife.utils.TextUtil;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService{

	@Resource
	DeviceDao deviceDao;

	@Override
	public void insertBook(Book book) throws Exception {
		try {
			if(book==null) {
				throw new Exception("请传入设备信息！");
			}
			if(TextUtil.isEmpty(book.getDeviceName())||TextUtil.isEmpty(book.getDeviceFlag()))
			{
				throw new Exception("请传入设备信息！");	
			}
			List<Book> books = deviceDao.getBookByCondition(book);
			if(books!=null && books.size()>0) {
				throw new Exception("设备【"+book.getDeviceName()+"】已存在");
			}
			String deviceNoPre = DomainNoUtils.getNoByPreStr(DomainNoUtils.BC_NO);
			book.setBookNo(deviceNoPre);
			book.setDeviceStatus("normal");
			deviceDao.insertBook(book);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void updateBookByCondition(Book book) throws Exception {
		try {
			if(book==null) {
				throw new Exception("请传入设备信息！");
			}
			if(TextUtil.isEmpty(book.getDeviceName()))
			{
				throw new Exception("请传入设备名！");	
			}
			if(TextUtil.isEmpty(book.getUserNo()))
			{
				throw new Exception("请传入设备预约人编号信息！");	
			}
			if(TextUtil.isEmpty(book.getDeviceStatus()))
			{
				throw new Exception("请传入设备预约状态deviceStatus,normal/book,normal未预约，book预约");	
			}
			Book b = new Book();
			b.setDeviceName(book.getDeviceName());
			List<Book> list = deviceDao.getBookByCondition(book);
			if(list!=null && list.size()>0)
			{
				deviceDao.updateBookByCondition(book);
			}
			else {
				throw new Exception("设备【"+book.getDeviceName()+"】不存在");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Book> getBookByCondition(Book book) throws Exception {
		List<Book> books = null;
		try {
			books = deviceDao.getBookByCondition(book);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return books;
	}

	@Override
	public void deleteBook(Book book) throws Exception {
		try {
			if(book==null || TextUtil.isEmpty(book.getDeviceName())) {
				throw new Exception("请传入设备编号信息！");
			}
			deviceDao.deleteBook(book);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
