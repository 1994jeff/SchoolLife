package com.my.schoollife.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.my.schoollife.bean.User;
import com.my.schoollife.service.UserService;

/**
 * 定时任务
 */
@Component("schuduleTask")
public class SchuduleTask {

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM");

	private static String oldDate = "";
	
	@Resource
	UserService userService;
	
	@Scheduled(cron = "0 0 0/7 * * ? ")
	public void runFunction(){
		String date = fm.format(new Date());
		String time = format.format(new Date());
		if(!oldDate.equals(date)){
			oldDate = date;
		}
		String log = "";
		try {
			User user = new User();
			userService.getUserByCondition(user);
			log = time + " \n-----------------------------------> TaskOk \t\n ";
			System.out.println(log);
		} catch (Exception e) {
			log = time + " \n-----------------------------------> TaskError\n";
		}
		System.out.println(log);
	}
	
	//	按顺序依次为
	//	秒（0~59）
	//	分钟（0~59）
	//	小时（0~23）
	//	天（月）（0~31，但是你需要考虑你月的天数）
	//	月（0~11）
	//	天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
	//	年份（1970－2099）
	//	其中每个元素可以是一个值(如6),一个连续区间(9-12),
	//	一个间隔时间(8-18/4)(/表示每隔4小时),一个列表(1,3,5),通配符。
	//	由于"月份中的日期"和"星期中的日期"这两个元素互斥的,必须要对其中一个设置?
	
	//	CRON表达式    	含义
	//	字段   			允许值   					允许的特殊字符
	//	秒      			0-59    				, - * /
	//	分       			0-59    				, - * /
	//	小时    		0-23    				, - * /
	//	日期    		1-31    				, - * ? / L W C
	//	月份    		1-12 或者 JAN-DEC    		, - * /
	//	星期    		1-7 或者 SUN-SAT    		, - * ? / L C #
	//	年（可选）    	留空, 1970-2099   		, - * / 
	//	- 			区间  
	//	* 			通配符  
	//	? 			你不想设置那个字段
	//  eg:秒 分 时  
	//	"0 0 12 * * ?"    每天中午十二点触发 
	//	"0 15 10 ? * *"    每天早上10：15触发 
	//	"0 15 10 * * ?"    每天早上10：15触发 
	//	"0 15 10 * * ? *"    每天早上10：15触发 
	//	"0 15 10 * * ? 2005"    2005年的每天早上10：15触发 
	//	"0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发 
	//	"0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发 
	//	"0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 
	//	"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发 
	//	"0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发 
	//	"0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发 
}
