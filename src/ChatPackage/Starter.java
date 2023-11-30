package ChatPackage;


import java.net.SocketException;
import java.util.Scanner;

public class Starter {

	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		if(scan.next().equals("server")){
			Server s = new Server();
			
		}else {
			//Client Code
			Client c = new Client();			
		}
	}

}
