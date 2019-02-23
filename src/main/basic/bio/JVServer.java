package main.basic.bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wangyoucai on 2019/2/23.
 */
public class JVServer {
	
	public static void main(String[] args) throws Exception {
		
		int port = 8080;
		
		ServerSocket server = null;
		
		try {
			// open listen port 8080
			server = new ServerSocket(port);
			System.out.println("server start ! port is :" + port);
			Socket socket = null;
			while (true) {
				// 收到连接请求
				socket = server.accept();
				// 开启处理线程处理客户端请求
				new Thread(new ServerHandler(socket)).start();
			}
		} finally {
			if (server != null) {
				System.out.println("server close ! ! !");
				server.close();
			}
			
		}
	}
}
