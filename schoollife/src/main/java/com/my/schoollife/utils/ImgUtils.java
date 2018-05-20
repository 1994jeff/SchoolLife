package com.my.schoollife.utils;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class ImgUtils {

	public static void saveImg(String filePath,String name,MultipartFile file) throws Exception{
		OutputStream outputStream = null;
		try {
			if(file==null){
				throw new Exception("文件读取失败！");
			}
			outputStream = new FileOutputStream(filePath+name);
			BufferedOutputStream bo = new BufferedOutputStream(outputStream);
			bo.write(file.getBytes());
			outputStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FileNotFoundException("未找到文件！");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("IO错误！");
		}catch (Exception e) {
			e.printStackTrace();
			throw new FileNotFoundException("未知错误！");
		}finally{
			if(outputStream!=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
