import java.net.*;
import java.io.*;
import java.util.*;

public class TimeoutTask extends TimerTask{
	Bfclient_host host;
	public TimeoutTask(Bfclient_host host) {
		this.host = host;
	}
	public void run() {
		try {
		/*
		broadcastPkt = new DatagramPacket(this.WrapPacket(), this.WrapPacket().length, InetAddress.getByName(neighbor.routerIP), neighbor.port);
		//System.out.println("BroadcastThread: Sending data toward " + neighbor.routerIP + ":" + neighbor.port);
		broadcastSkt.send(broadcastPkt);
		*/
			HashMap<String, Path> selfMap = this.host.routeTable.get(this.host.self);
			byte[] toSend = this.host.pack(this.host.ip, this.host.port,selfMap);
			for (Neighbor nb: this.host.neighbor) {
				this.host.packet = new DatagramPacket(toSend,toSend.length,nb.ip,nb.port);
				this.host.socket.send(this.host.packet);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}