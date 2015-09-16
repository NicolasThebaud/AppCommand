package com.appcommand.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private static ArrayList<Client> clients = new ArrayList<Client>();
	private Socket socket;
	private ServerSocket server;
	private Client client;

	public static void main(String[] args) {
		int port = (args.length == 0) ? 8080 : Integer.parseInt(args[0]);
		Server serv = new Server(port);
		serv.run();
	}

	public Server(int port) {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) { e.printStackTrace(); }
		
		System.out.println("[info]SERVER: Server started on port " + port);
	}

	public void run() {
		while(true) {
			try {
				socket = server.accept();
				System.out.println("[info]SERVER: Client connected");
				client = new Client(socket);
				clients.add(client);
				client.start();
			} catch (IOException e) { 
				e.printStackTrace(); 
			}
		}
	}

	public ArrayList<Client> getClients() {
		return clients;
	}
}
