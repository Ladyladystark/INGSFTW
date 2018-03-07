package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static final int PORT = 3306;
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(PORT);		
		} catch(Exception e) {
			System.err.println(e);
		}
		while(true) {
			try {
				socket = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			new handler(socket).start();

		}
	}

}
