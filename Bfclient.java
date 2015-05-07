import java.util.*;
import java.io.*;
import java.net.*;

public class Bfclient {

	int timeout;
	int selfPort;
	
	public Bfclient() {}
	
	public static void main(String[] args) throws Exception{
		String readFile = args[0];
		Bfclient_host bh = new Bfclient_host(readFile);
		
		new Thread(new Bfclient_listener(selfPort)).start();
		new Thread(new Bfclient_sender(timeout)).start();
		
		Bfclient bfc = new Bfclient();
		bfc.startConsole();
	}
	
	public void startConsole(){
		System.out.println("Welcome to Bfclient!");
		
		try {
			Scanner sc = new Scanner(System.in);
			String head = "";
			String rest = "";
			ArrayList<String> argument = new ArrayList<>();
			while(true){
				String cmd = sc.nextLine();
				StringTokenizer st = new StringTokenizer(cmd);
				head = st.nextToken();
				head = head.toLowerCase();
				while(st.hasMoreTokens()) {
					rest = st.nextToken();
					argument.add(rest);
				}
				
				if (head.equals("linkdown")) {
					processLinkDown(bh, argument);
				}
				else if (head.equals("linkup")) {
					processLinkUp(bh, argument);
				}
				else if (head.equals("changecost")) {
					processChangeCost(bh, argument);
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
			System.err.println("Error: "+e.getMessage());
		}
	}

	
	void processLinkDown(Bfclient_host bh, ArrayList<String> argument) {
		
	}
	
	void processLinkUp(Bfclient_host bh, ArrayList<String> argument) {}
	
	void processChangeCost(Bfclient_host bh, ArrayList<String> argument) {}
	
	void processShowRT(){
		System.out.println("Current time: "+(long)(System.currentTimeMillis()/1000));
		bh.showRouteTable();
	}
	
	void processClose(){
		System.exit(0);
	}
	
	void processTransfer(){}
}