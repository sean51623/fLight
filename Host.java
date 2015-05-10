import java.net.*;
import java.io.*;
import java.util.*;

public class Host {
	InetAddress ip;
	DatagramSocket socket;
	int port;
	public Host() {}
	public Host(InetAddress ip, int port, DatagramSocket socket) {
		this.ip = ip;
		System.out.println(ip);
		this.port = port;
		this.socket = socket;
	}
}