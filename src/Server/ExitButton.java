import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class ExitButton extends JFrame{
	JButton exit;
	Jabber jabber;
	Welcome wel;
	public ExitButton(final Jabber jabber, final Welcome wel){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this); 
		} catch (Exception ex) {
		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.jabber = jabber;
		this.wel = wel;
		setLayout(null);                                          
		setTitle("Server Exit");
		this.setSize(50,80);
		setResizable(false);
		
		exit = new JButton("Exit");
		exit.setLocation(0, 0);
		exit.setSize(50, 50);
		
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String message = "QUIT SERVER";
				System.out.println(message);  
				try{
					jabber.tcpSender.send(message.getBytes());
					wel.serverSocket.close();
					Iterator<Socket> it = (Iterator<Socket>) jabber.clients.iterator();
					Socket client = null;
					while(it.hasNext()){
						client = (Socket)it.next();
						client.close();
					}
						
				}catch(Exception e){
					
				}
				System.exit(0);
			}
			
		});
		add(exit);
	}
	

}
