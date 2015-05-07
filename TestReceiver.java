import java.util.*;
import java.net.*;
import java.io.*;

public class TestReceiver {

	public static void main(String[] args) throws IOException{
		try {
			int sendPort = 1106;
			int receivePort = 1121;
			DatagramSocket ds = new DatagramSocket(receivePort);
		
			while(true) {
				System.out.println("hi");
				byte[] receiveData = new byte[60*1024];
				DatagramPacket dp = new DatagramPacket(receiveData, receiveData.length);
				ds.receive(dp);
			
				String strInfo = new String(dp.getData(),0,dp.getLength())+" from "+dp.getAddress().getHostAddress()+":"+dp.getPort();
				System.out.println(strInfo);
			}
		}
		catch(Exception e) {
			System.err.println("Error");
		}
	}
	
}