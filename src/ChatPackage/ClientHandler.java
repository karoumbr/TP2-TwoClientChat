package ChatPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
	Socket client;
	DataInputStream input;
	DataOutputStream output;
	final ArrayList<String> receivedMessages = new ArrayList<>();
	
	ClientHandler(Socket clientSocket){
		client =clientSocket;
		try {
			input = new DataInputStream(client.getInputStream());
		output = new DataOutputStream(client.getOutputStream());	
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			String str;
			while(true) {
				try {
				str= input.readUTF();
				synchronized(receivedMessages){
					receivedMessages.add(str);
				}
					
				}catch (EOFException e) {
					System.out.println("Connection closed by client.");
					break;
				}
				
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			if(input!=null)
				input.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(output!=null)
				output.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(client!=null) 
				client.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendMessages(String str) {
		try {
			output.writeUTF(str);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getMessages(){
		return receivedMessages;
	}
}
