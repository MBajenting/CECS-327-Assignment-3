import java.net.*;
import java.util.Scanner;
import java.io.*;

public class UDPClient {
	public static void main (String args[]) {
	
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter target IP address:\n> "); 
		String targetAddress = in.nextLine(); // 192.168.1.8 my current local host
		
		System.out.print("Enter target port:\n> "); 
		String  targetPort = in.nextLine(); //6789
		
		System.out.print("Enter your message:\n> ");
		String message = in.nextLine();
		
		Socket s = null;
		
		try{	
			s = new Socket(targetAddress, Integer.parseInt(targetPort)); // Creates a socket connection to the parameterized ip address and port
			
			DataInputStream dataIn = new DataInputStream(s.getInputStream()); // Creates a data strea
			DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
			
			dataOut.writeUTF(message); // Encodes the message to send out
			
			String data = dataIn.readUTF();
			System.out.println("Received: "+ data);

		}
		catch (UnknownHostException e) {System.out.println("IP Address Invalid: "+e.getMessage());}
		catch (EOFException e) {System.out.println("EOF: "+e.getMessage());}
		catch (IOException e) {System.out.println("IO: "+e.getMessage());}
		
		finally { 
			if(s!=null) { 
				try { 
					s.close();
					in.close();
				}
				catch (IOException	e){System.out.println("close:"+e.getMessage());}
			}
		}
	}
}
