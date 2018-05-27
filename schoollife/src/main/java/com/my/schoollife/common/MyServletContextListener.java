package com.my.schoollife.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.my.schoollife.utils.TcpServer;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("------------------------------------------");
		System.out.println("web应用初始化");
		new Thread(new TcpThread()).start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("web应用结束");
		System.out.println("------------------------------------------");
	}

	class TcpThread implements Runnable{
		@Override
		public void run() {
			new TcpServer();
		}
		
	}
}
