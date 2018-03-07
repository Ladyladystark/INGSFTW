package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.MalformedInputException;
import java.util.Scanner;

import com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor;

import database.datalog;

public class handler extends Thread{
	BufferedReader input;
	PrintWriter output;
	Socket client;
	public handler(Socket clientSocket) {
		this.client=clientSocket;
		System.out.println();
	}

	public  void run(){

		login();
		System.out.println("boh");
		int scelta = 0;
		try {
			Scanner input = new Scanner(client.getInputStream());

			scelta= input.nextInt();
			System.out.println(scelta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(scelta==2)
			try {
				registrati();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // fine run




	public void login() {
		String usr="";
		String pw= "";

		// per leggere dal client
		try {
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		try {
			usr = input.readLine();
			System.out.println(usr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pw = input.readLine();
			System.out.println(pw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		datalog d = new datalog();


		//per scrivere al client
		try {
			output = new PrintWriter(new OutputStreamWriter(this.client.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 




		if(d.check(usr,pw)) {
			OutputStream out;
			try {
				// preparo l'oggetto per inviare la risposta al client
				out = client.getOutputStream();
				ObjectOutputStream oout = new ObjectOutputStream(out);
				oout.writeObject("Benvenuto "+ usr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}else {
			OutputStream out;

			try {
				// preparo l'oggetto per inviare la risposta al client
				out = client.getOutputStream();
				ObjectOutputStream oout = new ObjectOutputStream(out);
				oout.writeObject("Combinazione Utente/Password incorretta...");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // fine catch

		}//fine else 



	}// fine login

	public void registrati() throws IOException {
		String utente;
		String password;
		System.out.println("reg");
		datalog d = new datalog();
		try {
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		utente=input.readLine();
		System.out.println(utente);
		password = input.readLine();
		System.out.println(password);
		d.Signup(utente,password);






	}// fine registrati

}// fine classe
