import java.util.*;
import java.net.*;

public class Bfclient_host {

	HashMap<Host, Float> table = new HashMap<>();
	public Bfclient_host(String fileName) {
		parsefile(fileName);
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
			selfPort = Integer.parseInt(temp[0]);
			timeout = Integer.parseInt(temp[1]);
			
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
}