import java.net.*;
import java.io.*;
import java.util.*;

public class Neighbor {
	InetAddress ip;
	int port;
	float distance;
	long lastUpdateTime;
	
	public Neighbor(String ip, int port, float distance) {
		try{
			this.ip = InetAddress.getByName(ip);
			this.port = port;
			this.distance = distance;
			this.lastUpdateTime = -1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}