
public class Bfclient_sender implements Runnable {
	Timer counter;
	int timeout;
	
	public Bfclient_sender(int timeout) {
		this.timeout = timeout;
	}
	
	public void run() {
		Timer timer = new Timer();
		timer.schedule();
	}
}