package main.basic.bio.v2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangyoucai on 2019/2/23.
 */
public class JVServer {
	
	public static void main(String[] args) throws Exception {
		
		int port = 8080;
		
		ServerSocket server = null;
		// 开启线程池
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		try {
			// open listen port 8080
			server = new ServerSocket(port);
			System.out.println("server start ! port is :" + port);
			Socket socket = null;
			while (true) {
				// 收到连接请求
				socket = server.accept();
				// 开启处理线程处理客户端请求
				executorService.execute(new ServerHandler(socket));
			}
		} finally {
			if (server != null) {
				System.out.println("server close ! ! !");
				server.close();
			}
			
		}
	}
}
