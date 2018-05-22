package com.my.schoollife.utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.my.schoollife.bean.Message;
import com.my.schoollife.service.MessageService;

import net.sf.json.JSONObject;

/**
 * tcp服务器
 * 管理客户端发起的连接
 */
@Component("tcpServer")
public class TcpServer {

	private static final int PORT = 8888;  
    private volatile static List<Socket> mClientList = new ArrayList<Socket>();  
    private volatile ServerSocket server = null;  
    private ExecutorService mExecutors = null; // 线程池对象  
    
    @Resource
    MessageService messageService;
    
    public static void main(String[] args) {
//    	new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while(true) {
//					try {
//						Thread.sleep(2000);
//						System.out.println(mClientList.size());
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//
//				}
//			}
//		}).start();
    	new TcpServer();
    }
    
    /** 
     * 构造方法：任务是启动服务器，等待客户端连接 
     */  
    public TcpServer() {  
    	try {  
            server = new ServerSocket(PORT);  
            mExecutors = Executors.newCachedThreadPool(); // 创建线程池  
            System.out.println("服务器已启动，等待客户端连接...");  
            Socket client = null;  
            /* 
             * 用死循环等待多个客户端的连接，连接一个就启动一个线程进行管理 
             */  
            while (true) {  
                client = server.accept();  
                // 把客户端放入集合中  
                mClientList.add(client);  
                mExecutors.execute(new Service(client)); // 启动一个线程，用以守候从客户端发来的消息  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
	}  
  
    public void init() {
    }
    
    class Service implements Runnable {  
        private Socket socket;  
        private BufferedReader in = null;  
        private String message = "";  
  
        public Service(Socket socket) {  
            this.socket = socket;  
            try {  
                in = new BufferedReader(new InputStreamReader(  
                        socket.getInputStream()));// 获得输入流对象  
                // 客户端只要一连到服务器，便发送连接成功的信息  
                message = "当前在线人数:" + mClientList.size()+"人";
                Message msg = initSystemMsg(message);
                this.sendMessage(JSON.toJSONString(msg));  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
  
        }

		private Message initSystemMsg(String message) {
			Message msg = new Message();
			msg.setContent(message);
			msg.setUserNo("system");
			msg.setName("系统消息");
			return msg;
		}  
  
        @Override  
        public void run() {  
            try {  
                while (true) {  
                    if ((message = in.readLine()) != null) {  
                        // 当客户端发送的信息为：exit时，关闭连接  
                        if (message.equals("exit")) {  
                            closeSocket();  
                            break;  
                        } else {  
                            // 接收客户端发过来的信息message，然后转发给客户端。  
                            if(saveIntoDataBase(message))
                            {
//                                Message msg = initUserMsg(message);
                            	this.sendMessage(message);  
                            }else {
                                Message msg = initSystemMsg(message);
                            	this.sendMessage(JSONObject.fromObject(msg).toString(),socket);
                            }
                        }  
                    }
                }  
            } catch (Exception e) {  
            }  
        }

		private void sendMessage(String string, Socket socket2) {
			int count = mClientList.size();  
            // 遍历客户端集合  
            for (int i = 0; i < count; i++) {  
                Socket mSocket = mClientList.get(i);  
                if(mSocket==socket2) {
                	PrintWriter out = null;  
                    try {  
                        out = new PrintWriter(new BufferedWriter(  
                                new OutputStreamWriter(mSocket.getOutputStream())),  
                                true);// 创建输出流对象  
                        out.println(string);// 转发  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }
            }  
		}

		private boolean saveIntoDataBase(String message) {
			try {
				Gson gson = new Gson();
				Message msg = gson.fromJson(message, Message.class);
				if(msg==null) {
					throw new Exception("");
				}
//				messageService.insertMessage(msg);
			} catch (Exception e) {
				return false;
			}
			return true;
		}  
  
		/** 
         * 关闭客户端 
         *  
         * @throws IOException 
         */  
        public void closeSocket() throws IOException {  
            mClientList.remove(socket);  
            in.close();  
            message = "主机:" + socket.getInetAddress() + "关闭连接\n目前在线:"  
                    + mClientList.size();  
            socket.close();  
            this.sendMessage(message);  
        }  
  
        /** 
         * 将接收的消息转发给每一个客户端 
         *  
         * @param msg 
         */  
  
        public void sendMessage(String msg) {  
            int count = mClientList.size();  
            // 遍历客户端集合  
            for (int i = 0; i < count; i++) {  
                Socket mSocket = mClientList.get(i);  
                PrintWriter out = null;  
                try {  
                    out = new PrintWriter(new BufferedWriter(  
                            new OutputStreamWriter(mSocket.getOutputStream())),  
                            true);// 创建输出流对象  
                    out.println(msg);// 转发  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }    
}
