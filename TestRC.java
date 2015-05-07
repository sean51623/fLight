import java.util.*;
import java.net.*;
import java.io.*;

public class TestRC implements Runnable {
	int timeout;
	int port;
	public TestRC(int timeout, int port) {
		this.timeout = timeout;
		this.port = port;
	}
	public void run() throws IOException{		
		try {
			//int sendPort = 1106;
			//int receivePort = 1121;
			DatagramSocket socket = new DatagramSocket(port);
		
			while(true) {
				System.out.println("hi");
				byte[] receiveData = new byte[60*1024];
				DatagramPacket dp = new DatagramPacket(receiveData, receiveData.length);
				socket.receive(dp);
			
				String strInfo = new String(dp.getData(),0,dp.getLength())+" from "+dp.getAddress().getHostAddress()+":"+dp.getPort();
				System.out.println(strInfo);
			}
		}
		catch(Exception e) {
			System.err.println("Error");
		}
	}
}