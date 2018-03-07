package client;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
	Socket socket;
	static BufferedReader read;
	static PrintWriter output;

	public static void main(String args[]) throws Exception {
		Socket clsock = null;	
		//Scanner tastiera = new Scanner(System.in);

		final InetAddress URL = InetAddress.getLocalHost();
		final  int PORT = 3333;
		clsock = new Socket(URL,PORT);
		System.out.println("Client Connesso" + URL + PORT);
		login(clsock);

		clsock.close();



	}

	public static int login(Socket clsock) throws Exception {
		Scanner tastiera = new Scanner(System.in);
		int selec = 3;
		String contr = "Combinazione Utente/Password incorretta...";

		//creo lo scrittore per inviare i dati al server
		output = new PrintWriter(new OutputStreamWriter(clsock.getOutputStream()));
		System.out.println("Immetti username: ");
		String username = tastiera.nextLine();


		//mando il nome al server
		output.println(username);

		System.out.println("Immetti password: ");
		String password = tastiera.nextLine();

		//mando la password al server
		output.println(password);
		output.flush();

		//creo un oggetto per leggere la risposta del server 

		InputStream inp = clsock.getInputStream();
		ObjectInputStream ooin = new ObjectInputStream(inp);
		String Risposta = (String) ooin.readObject();

		System.out.println(Risposta);    //fine login

		if(Risposta.equals(contr)) {
			selec = 2;
			do {
				System.out.println("Digita:\n 1.Ritenta il login\n 2.Registrati come nuovo utente\n 3.Esci");
				selec = tastiera.nextInt();
				switch(selec) {
				case 1: selec=login(clsock);
				break;
				case 2: selec = Registrati(clsock);
						break;

				case 3: clsock.close();;

				}

			}while(selec!=3);
		}
		return selec;
	}
	public static int Registrati(Socket clsock) throws Exception {
		PrintWriter output = new PrintWriter(clsock.getOutputStream(), true);
		Scanner tastiera = new Scanner(System.in);
		int i = 2;
		output.println(i);
		System.out.println("Registrazione");
		System.out.print("immetti username: ");
		String temp = tastiera.nextLine();
		output.println(temp);
		System.out.print("immetti password: ");
		String tamp = tastiera.nextLine();
		//System.out.println(tamp);
		output.println(tamp);
		output.flush();
		/*InputStream inp = clsock.getInputStream();
			ObjectInputStream ooin = new ObjectInputStream(inp);
			String Risposta = (String) ooin.readObject();
		  System.out.println(Risposta);*/

		System.out.println("Utente creato\n Premi:\n 1: Per effettuare il login\n 2: Per uscire");
		i=tastiera.nextInt();
		output.println(i);
		if(i==1)
			login(clsock);
		tastiera.close();
		return 3;
	}




}
