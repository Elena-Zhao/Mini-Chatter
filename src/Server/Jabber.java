import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Jabber extends Thread{
	static ArrayList<Socket> clients;
	Sender tcpSender;
	Receiver udpReceiver;
	DatagramSocket udpServer;
    
	//Create the Jabber which receive UDP packet from clients broadcast the massage via TCP connection
	public Jabber(ArrayList<Socket> clients, DatagramSocket udpServer) throws IOException{
    	this.clients = clients;
    	this.udpServer = udpServer;
    	tcpSender = new Sender(clients);
		udpReceiver = new Receiver(udpServer);
    	start();
    	}
    
	//Message relayer
    public void run(){
    	try {
    		while(true){
    			byte [] message = udpReceiver.receive();  
    			//Case "QUIT": swap
    			String ifQuit = new String(message);
    			if(ifQuit.substring(0,4).equals("QUIT") ){
    				message = (ifQuit.substring(6)+ ":QUIT").getBytes();
    				
    			}
    			tcpSender.send(message);
    		}
    		
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
}
