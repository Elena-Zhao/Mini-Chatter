import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Welcome {
	ServerSocket serverSocket;
	static ArrayList<Socket> clients = null;
	DatagramSocket udpServer;
	Jabber jabber;
	int tcp_port;
	int udp_port;
	public Welcome(int tcp_port,int udp_port) throws IOException{
		this.tcp_port = tcp_port;
		this.udp_port = udp_port;
		try {
			serverSocket = new ServerSocket(tcp_port);         
			System.out.print("服务器已启动.\n");
			udpServer = new DatagramSocket(udp_port);          
			clients = new ArrayList<Socket>();
			jabber = new Jabber(clients, udpServer);          //Jabber for message relaying
			
			ExitButton exit = new ExitButton(this.jabber, this);
			exit.setVisible(true);
			
			this.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			serverSocket.close();
		}
	}
	
	//Wait for TCP connections and send back abber UDP socket’s port
	void open(){
		try {
			
			while(!serverSocket.isClosed()){
				
				Socket client = serverSocket.accept();   //wait for connections with a TCP welcome socket
				clients.add(client);                          
				greeting(client);                        //send greeting with the Jabber UDP socket’s port 
			}
			
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
	}
	
	//send greeting to the newly connected client with the Jabber UDP socket’s port
	void greeting(Socket client){
		PrintWriter writer;
		try {
			String greeting = "WELCOME " + udp_port;
			writer = new PrintWriter(client.getOutputStream());
			writer.println(greeting);
		    writer.flush();
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
}
