import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Connection {
	private Socket tcpClient;                     //tcp for receiving IM from server
	private String screenName;
	private int udp_Port;
	private InetAddress inetAddress;              //get the server's InetAddress for routing Datagrampacket
    private DatagramSocket udpClient;             //udp for sending IM to server
	private Sender sender;                        //Sender for sending udp packet to server
	private Receiver receiver;                    //Receiver for receiving tcp message from server
	BufferedReader reader;
	final JTextField textInput;
	final JTextArea showInfo;
	final JButton quit;
	
	//Use TCP socket to connect to Server and get Jabber UDP socket¡¯s port to which the client send the following IM
	public Connection(String screenName, String ip, int tcp_port, final JTextField textInput,
			final JTextArea showInfo, final JButton quit) throws UnknownHostException, IOException{
		this.screenName = screenName;
		this.showInfo = showInfo;
		this.textInput = textInput;
		this.quit = quit;
		tcpClient = new Socket(ip, tcp_port);       
		udpClient = new DatagramSocket();          
		inetAddress = InetAddress.getByName(ip);   		
		//Once connected, first receive the server's udp port
		reader = new BufferedReader(new InputStreamReader(tcpClient.getInputStream()));
		String greetingMsg = reader.readLine();
		udp_Port = Integer.parseInt(greetingMsg.substring(8));
		sender = new Sender(udpClient,inetAddress, udp_Port);      
		receiver = new Receiver(tcpClient);                        
		
		showInfo.append(greetingMsg +"\n");
		String greeting = "HELO " + screenName;
		sender.send(greeting.getBytes());
	}
	
	//Receive messages and print to the showInfo TextArea
	public void receive() throws IOException{
		String message = new String("") ;
		while(true){
			try{
				message = receiver.receive();
			    if(!message.equals("null")){
			    	showInfo.append(message+"\n");       //show the message in showInfo TextArea
			    }
			}
			catch(Exception e){
			  
			}
		}
	}
	
	//Use the event handling mechanism for sending input messages
	public void send() throws IOException{
		textInput.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String message = screenName + ": "+textInput.getText();
				System.out.println(message);  
				try{
					sender.send(message.getBytes());
					textInput.setText("");
				}catch(Exception e){
					System.out.println("socket has been closed");
				}
			}
			
		});
		
	}
	
	public void quit() throws IOException{
		quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String message ="QUIT: " + screenName;
				System.out.println(message);  
				try{
					sender.send(message.getBytes());
					textInput.setText("");
					reader.close();
					tcpClient.close();
					System.exit(0);
				}catch(Exception e){
					System.out.println("socket has been closed");
				}
			}
			
		});
		
	}
	
	public boolean isActive(){
		return !tcpClient.isClosed();
	}

}
