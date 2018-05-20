package com.my.schoollife.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware{

	public static ApplicationContext ct;  
    //spring中获得bean  
    public static Object getObject(String beanId){  
        return ct.getBean(beanId);  
    }  
    @Override  
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {  
        SpringUtil.ct = arg0;  
    }
    
}
