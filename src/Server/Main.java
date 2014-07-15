import java.io.IOException;



public class Main {
	public static void main(String[] args) throws IOException{
		final int tcp_port = Integer.parseInt(args[0]);
		final int udp_port = Integer.parseInt(args[1]);
		//final int port = Integer.parseInt(args[0]);
		Welcome welcome = new Welcome(tcp_port, udp_port);
		
	}

}
