package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import CaptureTheFlagGame.GameManager;

public class ServerCommunication extends Thread{

	private GameManager gManager;
	private boolean running = true;
	private int port = 8008;
	private ArrayList<ServerCommClient> clients;
	private ServerGame serverGame;
	private ServerSocket serverSocket;
	public ServerCommunication(GameManager gManager) {
		this.gManager = gManager;
		clients = new ArrayList<ServerCommClient>();
		setName("SERVER");
		serverGame = new ServerGame(gManager);
		start();
	}

	@Override
	public void run() {
		while(running) {
			try {
				 serverSocket = new ServerSocket(port);
				Socket socket = serverSocket.accept();
				clients.add(new ServerCommClient(gManager,socket));

				Thread.sleep(1);
			} catch (Exception e) {
				
			}
		}
	}
	public void end() {
		running  = false;
		for (int i = 0; i < clients.size(); i++) {
			clients.get(i).end();
		}
		serverGame.end();
		try {
			serverSocket.close();
		} catch (Exception e) {
			
		}
	}

}
