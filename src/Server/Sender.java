import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

// Send the message to all clients in the contact list via the client TCP socket;
public class Sender {
	//HashMap<String, Socket> clients;
	private ArrayList<Socket> clients;
	public Sender(ArrayList<Socket> clients){
		this.clients = clients;
	}
	
	public void send(byte [] message) throws IOException{
		Iterator<Socket> it = (Iterator<Socket>) clients.iterator();
		Socket client = null;
		while(it.hasNext()){
			try{
				client = (Socket)it.next();
			    PrintWriter writer = new PrintWriter(client.getOutputStream());
			    writer.println(new String(message,0,message.length));
			    writer.flush();
			}
			catch(IOException e){
				 clients.remove(client); 
			}
			
		}
	}

}
