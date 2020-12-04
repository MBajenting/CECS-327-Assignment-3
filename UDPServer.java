import java.net.*;
import java.io.*;

public class UDPServer {
	private static ServerSocket listenSocket;

	public static void main (String args[]) {
		try{
			
			listenSocket = new ServerSocket(6789);
			
			while(true) {
				Socket clientSocket = listenSocket.accept(); // Enables the socket to listen for connections
				DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
				
				String messageIn = (String)dataIn.readUTF(); // Reads in the encoded message a converts it to string.
				System.out.println("Message Recieved: " + messageIn);
				
				// Sending the message back
				DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
				dataOut.writeUTF(messageIn);
				System.out.println("Message Echoed: " + messageIn);
			}
		}
		catch(IOException e) {System.out.println("Listen :"+e.getMessage());}
	}
}