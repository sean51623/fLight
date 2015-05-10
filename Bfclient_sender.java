import java.net.*;
import java.io.*;
import java.util.*;

public class Bfclient_sender implements Runnable {
	//Timer counter;
	int timeout;
	Bfclient_host host;
	
	public Bfclient_sender(Bfclient_host host) {
		this.host = host;
	}
	
	public void run() {
		Timer timer = new Timer();
		timer.schedule(new TimeoutTask(this.host), 1000, this.host.timeout*1000);
	}
}