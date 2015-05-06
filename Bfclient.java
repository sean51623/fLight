import java.util.*;
import java.io.*;
import java.net.*;

public class Bfclient {

	timeout;
	selfPort;
	HashMap<Host, Integer> table;
	
	public Bfclient() {}
	
	public static void main(String[] args) throws Exception{
		String readFile = args[0];
		parseFile(readFile);
		
		new Thread().start();
		new Thread().start();
		
		Bfclient bfc = new Bfclient();
		bfc.startConsole();
	}
	
	public void parseFile(String fileName) throws IOException{
		try {
			FileReader f1 = new FileReader(fileName);
			BufferedReader b1 = new BufferedReader(b1);
			String line = "";
			String[2] temp = new String[2];
			String[2] temp2 = new String[2];
			
			line = b1.readLine();
			temp = line.split(" ");
			while(b1.ready()) {
				line = b1.readLine();
				temp = line.split(" ");
				temp2 = temp[0].split(":");
				table.put(new Host(temp2[0], Integer.parseInt(temp2[1])), Float.parseFloat(temp[1]));
			}
		}
		catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public void startConsole(){
		System.out.println("Hello World!");
		
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
					processLinkDown(argument);
				}
				else if (head.equals("linkup")) {
					processLinkUp(argument);
				}
				else if (head.equals("changecost")) {
					processChangeCost(argument);
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

	
	void processLinkDown() {}
	
	void processLinkUp() {}
	
	void processChangeCost() {}
	
	void processShowRT(){
		System.out.println("Current time: "+);
	}
	
	void processClose(){
		System.exit(0);
	}
	
	void processTransfer(){}
}