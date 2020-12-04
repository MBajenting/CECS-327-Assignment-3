import java.io.*;
import java.net.*;

public class UDPServer extends Thread {
    public static void main(String[] args) {
    	DatagramSocket socket;
    	DatagramPacket packet;
		try {
			socket = new DatagramSocket(6789); // Creates a datagram socket on port 6789
	    	byte[] buf = new byte[256];
	    	
	        while (true) {
	            packet = new DatagramPacket(buf, buf.length); // Creates a datagram packet with a buffer
	            socket.receive(packet); // Receives a datagram packet and puts it into buffer packet
	            
	            // Getting network metadata from the packet
	            InetAddress address = packet.getAddress(); 
	            int port = packet.getPort();
	            String received = new String(packet.getData(), 0, packet.getLength());
		        System.out.println("Message Received: " + received);
	            
	            // Uses network metadata from above to echo the message back
	            packet = new DatagramPacket(buf, buf.length, address, port);
	            socket.send(packet);
	            String echo = new String(packet.getData(), 0, packet.getLength());
		        System.out.println("Message Echoed: " + echo);
	        }
		}
	    catch (SocketException e) { e.printStackTrace();}   
		catch(IOException e) { System.out.println("Listen :"+e.getMessage()); }
    }
}