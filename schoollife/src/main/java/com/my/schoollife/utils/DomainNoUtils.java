package com.my.schoollife.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DomainNoUtils {

	public static final String USER_NO = "UN";
	public static final String USERSPORT_NO = "USN";
	public static final String COMMENT_NO = "CN";
	public static final String COLLECT_NO = "CON";
	public static final String USERHEALTH_NO = "UH";
	public static final String COMM_SHARE_NO = "CSN";
	public static final String BC_NO = "BCN";

	public static final int bitlength = 5;
	
	private static String dayStr = "";
	private static Integer userNum=1;
	private static Integer signedNum=1;
	private static Integer userAuthNum=1;
	private static Integer authNum=1;
	private static Integer bcNum=1;
	private static Integer usNum=1;
	private static Integer csNum=1;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static String getNoByPreStr(String string) {
		String timeStr = timeFormat.format(new Date());
		String str = "";
		switch (string) {
		case USER_NO:
			str = getUserNo(timeStr);
			break;
		case USERSPORT_NO:
			str = getUserSportNo(timeStr);
			break;
		case COMMENT_NO:
			str = getSignedNo(timeStr);
			break;
		case COLLECT_NO:
			str = getUserAuthNo(timeStr);
			break;
		case USERHEALTH_NO:
			str = getAuthNo(timeStr);
			break;
		case COMM_SHARE_NO:
			str = getCSNo(timeStr);
		case BC_NO:
			str = getBCNo(timeStr);
			break;
		default:
			break;
		}
		return str;
	}

	private static String getCSNo(String timeStr) {
		String dateStr = getDateStr();
		if(dateStr.equals(dayStr)){
			csNum++;
		}else{
			csNum=1;
			dayStr=dateStr;
		}
		return COMM_SHARE_NO+timeStr+FormatNumBits(String.valueOf(csNum));
	}

	private static String getBCNo(String timeStr) {
		String dateStr = getDateStr();
		if(dateStr.equals(dayStr)){
			bcNum++;
		}else{
			bcNum=1;
			dayStr=dateStr;
		}
		return BC_NO+timeStr+FormatNumBits(String.valueOf(bcNum));
	}

	private static String getUserAuthNo(String timeStr) {
		String dateStr = getDateStr();
		if(dateStr.equals(dayStr)){
			userAuthNum++;
		}else{
			userAuthNum=1;
			dayStr=dateStr;
		}
		return COLLECT_NO+timeStr+FormatNumBits(String.valueOf(userAuthNum));
	}

	private static String getAuthNo(String timeStr) {
		String dateStr = getDateStr();
		if(dateStr.equals(dayStr)){
			authNum++;
		}else{
			authNum=1;
			dayStr=dateStr;
		}
		return USERHEALTH_NO+timeStr+FormatNumBits(String.valueOf(authNum));
	}

	private static String getSignedNo(String timeStr) {
		String dateStr = getDateStr();
		if(dateStr.equals(dayStr)){
			signedNum++;
		}else{
			signedNum=1;
			dayStr=dateStr;
		}
		return COMMENT_NO+timeStr+FormatNumBits(String.valueOf(signedNum));
	}

	private static String getUserNo(String timeStr) {
		String dateStr = getDateStr();
		if(dateStr.equals(dayStr)){
			userNum++;
		}else{
			userNum=1;
			dayStr=dateStr;
		}
		return USER_NO+timeStr+FormatNumBits(String.valueOf(userNum));
	}
	
	private static String getUserSportNo(String timeStr) {
		String dateStr = getDateStr();
		if(dateStr.equals(dayStr)){
			usNum++;
		}else{
			usNum=1;
			dayStr=dateStr;
		}
		return USERSPORT_NO+timeStr+FormatNumBits(String.valueOf(usNum));
	}

	private static String FormatNumBits(String valueOf) {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<(5-valueOf.length());i++)
		{
			builder.append("0");
		}
		return builder.toString()+valueOf;
	}

	private static String getDateStr() {
		String nowStr = dateFormat.format(new Date());
		return nowStr;
	}

}
