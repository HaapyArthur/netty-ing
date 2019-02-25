package main.basic.nio;

/**
 * Created by wangyoucai on 2019/2/23.
 */
public class JVClient {
	
	public static void main(String[] args) {
		
		int port = 8080;
		
		new Thread(new ClientHandler("127.0.0.1",port)).start();
	}
}
