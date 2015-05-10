import java.util.*;
import java.io.*;
import java.net.*;

public class Bfclient {
	Bfclient_host bh;
	public Bfclient(Bfclient_host host) {
		this.bh = host;
	}
	
	public static void main(String[] args) throws Exception{
		String readFile = args[0];
		String wer = InetAddress.getLocalHost().getHostAddress();
		InetAddress hostAddr = InetAddress.getByName(wer);
		//System.out.println(hostAddr);
		Bfclient_host bh = new Bfclient_host(readFile, hostAddr);
		
		new Thread(new Bfclient_listener(bh)).start();
		new Thread(new Bfclient_sender(bh)).start();
		
		Bfclient bfc = new Bfclient(bh);
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
					//processTransfer();
				}
				else {
					System.out.println("Invalid command.");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void processLinkDown(Bfclient_host bh, String[] splitLine) {
		bh.pldown(splitLine);
	}
	
	void processLinkUp(Bfclient_host bh, String[] splitLine) {
		bh.plup(splitLine);
	}
	
	void processChangeCost(Bfclient_host bh, String[] splitLine) {
		bh.pcc(splitLine);
	}
	
	void processShowRT(){
		System.out.println("Current time: "+(long)(System.currentTimeMillis()/1000));
		bh.showRouteTable();
	}
	
	void processClose(){
		System.exit(0);
	}
	
	//void processTransfer(){}

}