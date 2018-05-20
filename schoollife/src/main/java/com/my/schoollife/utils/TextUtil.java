package com.my.schoollife.utils;

public class TextUtil {

	public static boolean isEmpty(String userNo) {
		if(null==userNo || "".equals(userNo))
		{
			return true;
		}
		else {
			return false;
		}
	}

}
