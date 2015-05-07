import java.util.*;
import java.net.*;
import java.io.*;

public class TestSender {

	public static void main(String[] args) throws IOException{
		try {
			int sendPort = 1106;
			int receivePort = 1121;
			InetAddress hostAddr = InetAddress.getLocalHost();
			String line = "";
			DatagramSocket ds = new DatagramSocket(sendPort);
		
			while(true) {
				Scanner sc = new Scanner(System.in);
				line = sc.nextLine();
				byte[] lineByte = line.getBytes();
				DatagramPacket dp = new DatagramPacket(lineByte, lineByte.length,hostAddr,receivePort);
				ds.send(dp);
			}
		}
		catch (Exception e) {
			System.err.println("Error");
		}
	}
	
}