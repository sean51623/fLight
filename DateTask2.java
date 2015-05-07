import java.util.*;
import java.net.*;
import java.io.*;

public class DateTask2 extends TimerTask {
	int sendPort;
	Host host;
	String line2="";
	public DateTask2(Host host, int sendPort) {
		this.host = host;
		this.sendPort = sendPort;
	}
	public void run() {
		try{
			line2 = "hb sent at " + (long)System.currentTimeMillis()/1000;
			byte[] lineByte2 = line2.getBytes();
			DatagramPacket dp2 = new DatagramPacket(lineByte2, lineByte2.length,host.ip,sendPort);
			host.socket.send(dp2);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}