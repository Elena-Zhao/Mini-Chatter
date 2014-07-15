import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;


public class Receiver {
	private DatagramSocket udpServer;
	public Receiver(DatagramSocket udpServer){
		this.udpServer = udpServer;
	}
	public byte [] receive() throws IOException{
		byte [] message = new byte[100];
		//New a Datagrampacket to deliver message;
		DatagramPacket packet = new DatagramPacket(message, message.length);
		try{
			udpServer.receive(packet);
		}catch(Exception e){
			System.out.println("socket has been closed");
		}
		
		//Return the message in bytes
		return new String(message, 0, packet.getLength()).getBytes();
	}
}
