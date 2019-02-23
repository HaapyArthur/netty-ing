package main.basic.bio.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wangyoucai on 2019/2/23.
 */
public class JVClient {
	
	public static void main(String[] args) {
		
		int port = 8080;
		
		Socket socket = null;
		
		BufferedReader in = null;
		
		PrintWriter out = null;
		
		try {
			socket = new Socket("127.0.0.1", port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("hello server");
			String resp = in.readLine();
			System.out.println("resp is :" + resp);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
