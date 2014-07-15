import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

//Build user interface
public class GUI extends JFrame{
	JLabel headline;
	JTextArea showInfo;
	JButton quit;
	JTextField textInput;
	
	public GUI(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this); 
		} catch (Exception ex) {
		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(null);                                          
		setTitle("Chat Client");
		setSize(500,300);
		setResizable(false);  
		
		headline = new JLabel("Chat Client Programe");
		headline.setSize(500, 30);
		headline.setLocation(0,0);
		headline.setBackground(Color.CYAN);
		headline.setFont(new Font("Arial Rounded MT Bold",Font.LAYOUT_LEFT_TO_RIGHT,15));
		add(headline);
		
		showInfo = new JTextArea();
		showInfo.setEditable(false);
		JScrollPane scroll = new JScrollPane(showInfo); 
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setLocation(0, 30);
		scroll.setSize(500,160);
		add(scroll);
		
		textInput = new JTextField();
		JScrollPane iscroll = new JScrollPane(textInput); 
		iscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		iscroll.setLocation(0, 210);
		iscroll.setSize(420,50);
		add(iscroll);
		
		quit = new JButton("QUIT");
		quit.setSize(65,30);
		quit.setLocation(425, 226);
		quit.setBorder(BorderFactory.createEtchedBorder());
		add(quit);
		
	}

}
