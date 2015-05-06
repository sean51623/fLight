import java.util.*;
import java.net.*;

public class Bfclient_listener implements Runnable {
	public Bfclient_listener(){}
	public void run() {
		DatagramSocket socket = new DatagramSocket();
		
		while(true) {
		
			socket.receive();
		}
	}
}