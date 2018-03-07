package server;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.MalformedInputException;
import java.util.Scanner;

import com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor;

import database.datalog;

public class handler extends Thread{
	protected Socket socket;
	public handler(Socket clientSocket) {
		this.socket=clientSocket;
		System.out.println();
	}

	public static void main(String args[]){
		Scanner tastiera = new Scanner(System.in);
		InputStreamReader in = null;
		DataOutputStream out = null;
		String usr = "peppe";
		String  pw = "ruggio";
		datalog d = new datalog();
		int a=1;

		System.out.println("Welcome!!");
		while(a!=3) {

			System.out.println("Immetti dati per il login");

			System.out.println("Username: ");
			usr = tastiera.nextLine();
			System.out.println("Immetti Password: ");
			pw = tastiera.nextLine();
			if(d.check(usr,pw)) {
				System.out.println("Utente trovato!!");
				a = 3;
			}
			else {
				System.out.println("Combinazione Utente/Password Errata");
				System.out.print("Digita:\n 1. Ritenta\n 2.Registrati\n 3.Esci ");

				a = tastiera.nextInt();
				switch(a) {

				case 1:tastiera.nextLine();
				System.out.println(""); 
				continue;
				case 2: 
					tastiera.nextLine();
					System.out.println(""); 
					System.out.println("Registrazione");
					System.out.println("Immetti il tuo Username: ");
					usr = tastiera.nextLine();
					System.out.println("Immetti la tua Password: ");
					pw = tastiera.nextLine();

					d.Signup(usr,pw);
					break;

				case 3: break;

				default: System.out.println("immetti una delle opzioni proposte!!!");

				}
			} 
		}



	}

}
