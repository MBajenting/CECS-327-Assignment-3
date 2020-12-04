import java.net.*;
import java.io.*;
import java.util.Scanner;

public class MulticastPeer{
	
	public static void main(String args[]){
		
	Scanner in = new Scanner(System.in);
	
	System.out.print("Enter target IP address:\n> "); //228.5.6.7
	String targetAddress = in.nextLine();
	
	System.out.print("Enter target port:\n> "); //6789
	String  portNumber = in.nextLine();
	
	System.out.print("Enter your message:\n> ");
	String message = in.nextLine();
	
	MulticastSocket s = null;
	
	try {
		InetAddress group = InetAddress.getByName(targetAddress); // This object represents our IP address
		s = new MulticastSocket(Integer.parseInt(portNumber)); // Creates a multicast socket and binds it specifically to the parameterized port
		s.joinGroup(group); // Joins the multicast group
		
		byte [] m = message.getBytes(); // Encodes the message string into a series of bytes
		DatagramPacket messageOut = new DatagramPacket(m, m.length, group, Integer.parseInt(portNumber)); // Constructs the outgoing message as a datagram packet
		s.send(messageOut); // Sends the message through the socket
		
		byte[] buffer = new byte[1000]; //Get messages from others in group
		
		for(int i=0; i< 3; i++) { // TODO: Why do we loop?
			DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length); // Constructs an empty datagram packet as a placeholder for incoming message
			s.receive(messageIn); // Receives a message though the socket
			System.out.println("Received:" + new String(messageIn.getData()));
		}
		
		s.leaveGroup(group); // Leaves the multicast group
	}
	catch (SocketException e){ System.out.println("Socket: " + e.getMessage());} // Prints error message for Socket Exception
	catch (IOException e){ System.out.println("IO: " + e.getMessage());} // Prints error message for IO Exception
	finally { if(s != null) s.close();} // Closes the socket
	in.close();
	}
}