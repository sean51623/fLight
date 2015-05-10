import java.util.*;
import java.net.*;
import java.io.*;

public class TestHost {
	static DatagramSocket socket;
	static InetAddress hostAddr;
	static int port;
	public static void main(String[] args) throws IOException{
		
		port = Integer.parseInt(args[0]);
		socket = new DatagramSocket(port);
		hostAddr = InetAddress.getLocalHost();
		Host h1 = new Host(hostAddr, port, socket);
		String qwe = hostAddr.getHostAddress();
		System.out.println(qwe);
		int port2 = Integer.parseInt(args[1]);
		
		new Thread(new TestRC(h1)).start();
		new Thread(new TestSD(h1, port2)).start();

	}
	
}