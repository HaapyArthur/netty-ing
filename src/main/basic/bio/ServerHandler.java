package main.basic.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wangyoucai on 2019/2/23.
 */
public class ServerHandler implements Runnable {
	
	Socket socket = null;
	
	public ServerHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(), true);
			while (true) {
				// req is message from client
				String req = in.readLine();
				if (req == null) {
					break;
				}

				String resp = "Im message from server,your req is" + req ;
				// send response message
				out.println(resp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
