package ChatPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	boolean isOn;
	Client(){
		try {
			InetAddress ip = InetAddress.getLocalHost();
			Socket other = new Socket(ip,22000);
			Scanner scan = new Scanner(System.in);
			DataInputStream otherReadSource = new DataInputStream(other.getInputStream());
			DataOutputStream otherWriteSource = new DataOutputStream(other.getOutputStream());
			isOn = true;
			Thread t = new Thread() {
				@Override
				public void run() {
					String str ="";
					while(isOn) {
						try {
							str = otherReadSource.readUTF();
							System.out.println("Other client said: " + str);
						}catch(IOException e) {
							//e.printStackTrace();
							System.out.println("Connection closed by client.");
						}
					}
				}
			};
			t.start();
			String str ="";
			while(true) {
				str = scan.nextLine();
				otherWriteSource.writeUTF(str);
				if(str.equals("exit")) {
					otherReadSource.close();
					otherWriteSource.close();
					other.close();
					isOn=false;
					break;
				}
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
