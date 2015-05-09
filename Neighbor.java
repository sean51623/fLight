import java.net.*;
import java.io.*;
import java.util.*;

public class Neighbor {
	String ip;
	int port;
	float distance;
	long lastUpdateTime;
	HashMap<String, Path> distanceVector;
	
	public Neighbor(String ip, int port, float distance) {
		this.ip = ip;
		this.port = port;
		this.distance = distance;
		this.lastUpdateTime = -1;
	}
}