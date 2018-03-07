package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static final int PORT = 3333;
	public static void main(String[] args) throws Exception {
		Socket client = null;
		//try {
			ServerSocket host = new ServerSocket(PORT);
			System.out.println("Server online");
		/*} catch(Exception e) {
			System.err.println(e);
		}*/
		while(true) {
			try {
			 client = host.accept();
				System.out.println("client connesso al server");
			} catch (IOException e) {
				e.printStackTrace();
				host.close();
			}
			new handler(client).start();

		}
		
		
	}
	
	

}
