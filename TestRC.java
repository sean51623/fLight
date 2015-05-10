import java.util.*;
import java.net.*;
import java.io.*;

public class TestRC implements Runnable {
	Host host;
	public TestRC(Host host) {
		this.host = host;
	}
	
	public void run(){		
		try {
			HashMap<String, Path> read = new HashMap<>();
			while(true) {
				System.out.println("hi");
				/*
				byte[] receiveData = new byte[60*1024];
				DatagramPacket dp = new DatagramPacket(receiveData, receiveData.length);
				host.socket.receive(dp);
			
				String strInfo = new String(dp.getData(),0,dp.getLength())+" from "+dp.getAddress().getHostAddress()+":"+dp.getPort();
				System.out.println(strInfo);
				*/
				byte[] receiveData = new byte[60*1024];
				DatagramPacket dp = new DatagramPacket(receiveData, receiveData.length);
				host.socket.receive(dp);
				
				ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(receiveData));
				read = (HashMap)inputStream.readObject();
				System.out.println(read.get("sean").totalDist);
				System.out.println(read.get("alfred").totalDist);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}