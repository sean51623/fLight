import java.net.*;
import java.io.*;
import java.util.*;

public class Bfclient_host {

	InetAddress ip;
	int timeout;
	int port;
	ArrayList<Neighbor> neighbor;
	HashMap<String, Path> routeTable;
	DatagramSocket socket;
	
	
	public Bfclient_host(String fileName, InetAddress hostAddr) {
		parsefile(fileName);
		this.ip = hostAddr;
		socket = new DatagramSocket(port);
	}
	
	public void parseFile(String fileName) throws IOException{
		try {
			FileReader f1 = new FileReader(fileName);
			BufferedReader b1 = new BufferedReader(b1);
			String line = "";
			
			line = b1.readLine();
			temp = line.split(" ");
			port = Integer.parseInt(temp[0]);
			timeout = Integer.parseInt(temp[1]);
			
			while(b1.ready()) {
				line = b1.readLine();
				String temp = line.split(" ");
				String temp2 = temp[0].split(":");
				neighbor.add(new Neighbor(temp2[0], Integer.parseInt(temp2[1]), Float.parseFloat(temp[1])));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showRouteTable() {
		foreach(Neighbor nb: neighbor) {
			
		}
	}
	
	public void processPacket() {
	
	}
}