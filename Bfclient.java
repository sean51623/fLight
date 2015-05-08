import java.util.*;
import java.io.*;
import java.net.*;

public class Bfclient {

	public Bfclient() {}
	
	public static void main(String[] args) throws Exception{
		String readFile = args[0];
		InetAddress hostAddr = InetAddress.getLocalHost();
		Bfclient_host bh = new Bfclient_host(readFile, hostAddr);
		
		new Thread(new Bfclient_listener(bh)).start();
		new Thread(new Bfclient_sender(bh)).start();
		
		Bfclient bfc = new Bfclient();
		bfc.startConsole();
	}
	
	public void startConsole(){
		System.out.println("Welcome to Bfclient!");
		
		try {
			Scanner sc = new Scanner(System.in);
			String head = "";
			while(true){
				String line = sc.nextLine();
				String[] splitLine = line.split(" ");
				head = splitLine[0].toLowerCase();
				
				if (head.equals("linkdown")) {
					processLinkDown(bh, splitLine);
				}
				else if (head.equals("linkup")) {
					processLinkUp(bh, splitLine);
				}
				else if (head.equals("changecost")) {
					processChangeCost(bh, splitLine);
				}
				else if (head.equals("showrt")) {
					processShowRT();
				}
				else if (head.equals("close")) {
					processClose();
				}
				else if (head.equals("transfer")) {
					processTransfer();
				}
				else {
					System.out.println("Invalid command.")
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	void processLinkDown(Bfclient_host bh, String[] splitLine) {
		if (splitLine.length!=3) {
			System.out.println("Invalid command!");
		}
		else {
			foreach(Neighbor nb: bh.neighbor) {
				if (nb.ip.equals(splitLine[1]) && nb.port.equals(splitLine[2])) {
					nb.distance = Float.MAX_VALUE;
				}
			}
		}
	}
	
	void processLinkUp(Bfclient_host bh, String[] splitLine) {}
	
	void processChangeCost(Bfclient_host bh, String[] splitLine) {
		if (splitLine.length!=4) {
			System.out.println("Invalid command!");
		}
		else {
			foreach(Neighbor nb: bh.neighbor) {
				if (nb.ip.equals(splitLine[1]) && nb.port.equals(splitLine[2])) {
					nb.distance = Float.parseFloat(splitLine[3]);
				}
			}
		}
	}
	
	void processShowRT(){
		System.out.println("Current time: "+(long)(System.currentTimeMillis()/1000));
		bh.showRouteTable();
	}
	
	void processClose(){
		System.exit(0);
	}
	
	void processTransfer(){}
}