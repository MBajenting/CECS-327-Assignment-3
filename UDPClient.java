import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    

    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter target IP address:\n> "); 
		String targetAddress = in.nextLine(); // 192.168.1.8 my current local host
		
		System.out.print("Enter target port:\n> "); 
		String  targetPort = in.nextLine(); //6789
		
		System.out.print("Enter your message:\n> ");
		String message = in.nextLine();
		
		in.close();

    	try {
    		DatagramSocket socket = new DatagramSocket();
	    	InetAddress address = InetAddress.getByName(targetAddress);
	    	
	    	// sending message
	        byte[] buf = message.getBytes();
	        int port = Integer.valueOf(targetPort).intValue();
	        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
	        socket.send(packet);
	        
	        // receiving echo message
	        packet = new DatagramPacket(buf, buf.length);
	        socket.receive(packet);
	        
	        // printing echo message
	        String received = new String(packet.getData(), 0, packet.getLength());
	        System.out.println(received);
	        
	        socket.close();
    	}
		catch (UnknownHostException e) {System.out.println("IP Address Invalid: "+e.getMessage());}
		catch (EOFException e) {System.out.println("EOF: "+e.getMessage());}
		catch (IOException e) {System.out.println("IO: "+e.getMessage());}
    }
}
