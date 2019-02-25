package main.basic.nio;

/**
 * Created by wangyoucai on 2019/2/23.
 */
public class JVServer {
	
	public static void main(String[] args) throws Exception {
		
		int port = 8080;
		
		ServerHandler handler = new ServerHandler(port);

		new Thread(handler).start();
	}
}
