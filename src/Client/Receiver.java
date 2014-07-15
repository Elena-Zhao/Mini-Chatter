import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

//Receive messages from server by TCP Socket 
public class Receiver {
	private Socket tcpSocket;
	BufferedReader reader;
	public Receiver(Socket tcpSocket) throws IOException{
		this.tcpSocket = tcpSocket;
		reader = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
	}
	
	public String receive() throws IOException{
		String message;
		if(!tcpSocket.isClosed()){
			message = reader.readLine();
			//System.out.println(message);
			return message;
		}
		else
			return "Socket closed.";
	}

}
