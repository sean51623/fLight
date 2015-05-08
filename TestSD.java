import java.util.*;
import java.net.*;
import java.io.*;

public class TestSD implements Runnable {
	int timeout = 5;
	Host host;
	int sendPort;
	public TestSD(Host host, int port2) {
		this.host = host;
		sendPort = port2;
	}
	
	public void run(){
		Timer timer = new Timer();
		timer.schedule(new DateTask2(host, sendPort), 1000, timeout*1000);
		String line="";
		
		try{
			while(true) {
				Scanner sc = new Scanner(System.in);
				line = sc.nextLine();
				byte[] lineByte = line.getBytes();
				DatagramPacket dp = new DatagramPacket(lineByte, lineByte.length,host.ip,sendPort);
				host.socket.send(dp);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
