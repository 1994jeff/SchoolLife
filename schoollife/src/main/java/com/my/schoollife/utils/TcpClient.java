package com.my.schoollife.utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.my.schoollife.bean.Message;

public class TcpClient implements Runnable{

//	private static final String HOST = "139.199.182.22";//服务器地址  
	private static final String HOST = "127.0.0.1";//服务器地址  
    private static final int PORT = 8888;//连接端口号  
	private static volatile Socket socket = null;  
    private BufferedReader in = null;  
    private static volatile PrintWriter out = null;  
    public volatile static boolean inited = false;
    private static boolean isClose = false;
    
	public static void main(String[] args) {
		TcpClient client = new TcpClient();
		new Thread(client).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				int n = 0;
				while(n<=4) {
					try {
						if(socket!=null && socket.isConnected() && socket.isBound() && inited)
						{
							n++;
							Thread.sleep(20);
							sendMsg("大家好，我是客户端3的第"+(n)+"条消息哦");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				closeConnect();
				sendMsg("大家好，我是客户端4的第"+(n)+"条消息哦");
			}

			private void closeConnect() {
				System.out.println("1");
				out.println("exit");
				try {
					socket.shutdownInput();
					socket.shutdownOutput();
					socket.close();
					isClose = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private static void sendMsg(String msg) {
		String str="{\"createTime\":null,\"receiveNo\":\"\",\"userNo\":\"1001\",\"name\":\"jeff\",\"remark\":\"\",\"id\":\"\",\"receiveType\":\"\",\"content\":\""+msg+"\",\"recordNo\":\"\"}";
		Message m = new Message();
		m.setContent(msg);
		m.setUserNo("111");
		m.setName("jeff");
		out.println(str);
	}

	@Override
	public void run() {
		connect();
		
		while(true) {
			while (true && !isClose) {//死循环守护，监控服务器发来的消息  
                if (!socket.isClosed()) {//如果服务器没有关闭  
                    if (socket.isConnected()) {//连接正常  
                        if (!socket.isInputShutdown()) {//如果输入流没有断开  
                            String getLine;  
                            try {
								if ((getLine = in.readLine()) != null) {//读取接收的信息  
								    getLine += "\n";  
								    Message msg = null;
									try {
										Gson gson = new Gson();
										msg = gson.fromJson(getLine, Message.class);
									    System.out.println(msg.getName()+"说："+msg.getContent());
									} catch (JsonSyntaxException e) {
										System.out.println(getLine);
									}
								} else {  
  
								}
							} catch (IOException e) {
								e.printStackTrace();
							}  
                        }  
                    }  
                }  
            }  
		}
	}

	private void connect() {
		try {  
            socket = new Socket(HOST, PORT);//连接服务器  
            in = new BufferedReader(new InputStreamReader(socket  
                    .getInputStream()));//接收消息的流对象  
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(  
                    socket.getOutputStream())), true);//发送消息的流对象  
            inited = true;
        } catch (IOException ex) {  
            ex.printStackTrace();  
            System.out.println("连接服务器失败：" + ex.getMessage());  
        }  		
	}
	
	
	
}
