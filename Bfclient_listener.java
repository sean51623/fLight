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
				host.processPacket(receiveData);
				
				/*
				incomingPkt = new DatagramPacket(incomingData_byte, incomingData_byte.length);
				listenSkt.receive(incomingPkt);

				RUDPPacket rudpIn = new RUDPPacket(incomingData_byte);
				ois = new ObjectInputStream(new ByteArrayInputStream(rudpIn.GetReceiveData()));
				LinkInfo incomingLinks = (LinkInfo) ois.readObject();
				DoRouteUpdate(incomingLinks);
				*/
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}