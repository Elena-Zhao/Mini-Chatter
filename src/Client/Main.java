import java.io.IOException;
import java.net.UnknownHostException;


public class Main {
	public static void main(String[] args) throws IllegalArgumentException, UnknownHostException, IOException{
		GUI model = new GUI();
		model.setVisible(true);
		
		//initialize the connection between server and client;
		Connection connect = new Connection(args[0],args[1],Integer.parseInt(args[2]), 
				model.textInput, model.showInfo, model.quit);
		
		connect.send();
		connect.quit();
		connect.receive();
		
	}
}
