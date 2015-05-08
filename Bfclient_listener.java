import java.net.*;
import java.io.*;
import java.util.*;

public class Bfclient_listener implements Runnable {

	Bfclient_host host;
	public Bfclient_listener(Bfclient_host host){
		this.host = host;
	}
	
	public void run() {
		try {
			while(true) {
				System.out.println("hi");
				byte[] receiveData = new byte[60*1024];
				DatagramPacket dp = new DatagramPacket(receiveData, receiveData.length);
				host.socket.receive(dp);
				host.processPacket(dp);
				//String strInfo = new String(dp.getData(),0,dp.getLength())+" from "+dp.getAddress().getHostAddress()+":"+dp.getPort();
				//System.out.println(strInfo);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}