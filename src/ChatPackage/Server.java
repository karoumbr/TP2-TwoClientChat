package ChatPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
	
	Server(){
		try {
			ServerSocket server = new ServerSocket(22000);
			System.out.println("Server is waiting for connection requests...");
			Socket clientSocket = server.accept();
			ClientHandler clientHandler1 = new ClientHandler(clientSocket);
			clientHandler1.start();
			clientSocket = server.accept();
			ClientHandler clientHandler2 = new ClientHandler(clientSocket);
			clientHandler2.start();
			ArrayList<String> msgs;
			while(true) {
				msgs = clientHandler1.getMessages();
				if(!msgs.isEmpty()) {
					for(int i=0;i<msgs.size();i++) {
						clientHandler2.sendMessages(msgs.get(i));
					}
					msgs.clear();
				}
				msgs = clientHandler2.getMessages();
				if(!msgs.isEmpty()) {
					for(int i=0;i<msgs.size();i++) {
						clientHandler1.sendMessages(msgs.get(i));
					}
					msgs.clear();
				}
				
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
