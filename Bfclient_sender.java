import java.net.*;
import java.io.*;
import java.util.*;

public class Bfclient_sender implements Runnable {
	Timer counter;
	int timeout;
	Bfclient_host host;
	
	public Bfclient_sender(Bfclient host) {
		this.host = host;
	}
	
	public void run() {
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