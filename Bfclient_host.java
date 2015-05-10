import java.net.*;
import java.io.*;
import java.nio.*;
import java.util.*;

public class Bfclient_host {

	InetAddress ip;
	int timeout;
	String self;
	int port;
	ArrayList<String> liveNode;
	ArrayList<Neighbor> neighbor;
	HashMap<String, HashMap<String, Path>> routeTable;
	HashMap<String, Path> selfMap;
	DatagramSocket socket;
	DatagramPacket packet;
	
	public Bfclient_host(String fileName, InetAddress hostAddr) {
		try{
			neighbor = new ArrayList<>();
			routeTable = new HashMap<String, HashMap<String, Path>>();
			parseFile(fileName);
			this.ip = hostAddr;
			String asd = ip.toString()+'\t'+Integer.toString(port);
			System.out.println(asd);
			socket = new DatagramSocket(port);
			liveNode = new ArrayList<>();
			liveNode.add(self);
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void parseFile(String fileName) throws IOException{
		try {
			FileReader f1 = new FileReader(fileName);
			BufferedReader b1 = new BufferedReader(f1);
			String line = "";
			selfMap = new HashMap<>();
			
			line = b1.readLine();
			String[] temp = line.split(" ");
			port = Integer.parseInt(temp[0]);
			timeout = Integer.parseInt(temp[1]);
			
			while(b1.ready()) {
				line = b1.readLine();
				temp = line.split(" ");
				String[] temp2 = temp[0].split(":");
				//System.out.println(temp2[0]+temp2[1]+temp[1]);
				neighbor.add(new Neighbor(temp2[0], Integer.parseInt(temp2[1]), Float.parseFloat(temp[1])));
				String qwe = temp2[0]+'\t'+temp2[1];
				this.self = qwe;
				System.out.println(qwe);
				selfMap.put(qwe, new Path(qwe,Float.parseFloat(temp[1])));
			}
			
			routeTable.put(self,selfMap);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void dvupdate(String src, HashMap<String, Path> distanceVector) {
		routeTable.put(src, distanceVector);
		System.out.println(src);
		selfMap = routeTable.get(self);
		//System.out.println("WWW");
		for (Map.Entry<String, Path> et: distanceVector.entrySet()) {
			//System.out.println("ZZZ");
			String n = et.getKey();
			Path p = et.getValue();
			if (!selfMap.containsKey(n)) {
				liveNode.add(n);
				selfMap.put(n, new Path(src, p.totalDist+selfMap.get(src).totalDist));
			}
			else {
				if (n==null) {
					selfMap.get(n).nexthop = null;
				}
				else if (selfMap.get(src).totalDist + p.totalDist < selfMap.get(n).totalDist) {
					selfMap.put(n, new Path(src, p.totalDist+selfMap.get(src).totalDist));
				}
			}
		}
		routeTable.put(self,selfMap);
	}
	
	public void pldown(String[] linkDownHost) {
		String ldh = linkDownHost[0]+'\t'+linkDownHost[1];
		selfMap = routeTable.get(self);
		selfMap.get(ldh).nexthop = null;
		
		routeTable.put(self,selfMap);
		byte[] toSend = pack(ip,port,selfMap);
		for (Neighbor nb: this.neighbor) {
			try {
				packet = new DatagramPacket(toSend,toSend.length,nb.ip,nb.port);
				this.socket.send(packet);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void plup(String[] linkUpHost) {
		String ldu = linkUpHost[0]+'\t'+linkUpHost[1];;
		selfMap = routeTable.get(self);
		selfMap.get(ldu).nexthop = ldu;
		
		routeTable.put(self,selfMap);
		byte[] toSend = pack(ip,port,selfMap);
		for (Neighbor nb: this.neighbor) {
			try{
				packet = new DatagramPacket(toSend,toSend.length,nb.ip,nb.port);
				this.socket.send(packet);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void pcc(String[] changeCost) {
		String cc = changeCost[0]+'\t'+changeCost[1];
		selfMap = routeTable.get(self);
		selfMap.get(cc).totalDist = Float.parseFloat(changeCost[2]);
		
		routeTable.put(self,selfMap);
		byte[] toSend = pack(ip,port,selfMap);
		for (Neighbor nb: this.neighbor) {
			try{
				packet = new DatagramPacket(toSend,toSend.length,nb.ip,nb.port);
				this.socket.send(packet);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//public void generateDV()
	
	public void showRouteTable() {
		selfMap = routeTable.get(self);
		for (Map.Entry<String, Path> et: selfMap.entrySet()) {
			String dest = et.getKey();
			Path through = et.getValue();
			if (through.nexthop==null || through.nexthop.isEmpty()) {
				System.out.println("Destination: " + dest + " Cost: INF  NextHop: --");
			}
			else {
				System.out.println("Destination: " + dest + " Cost: " + Float.toString(through.totalDist) + " Link: " + through.nexthop);
			}
		}
	}
	
	public void processPacket(byte[] receiveData) {

		try {	
			byte[] srcRawAddr  = new byte[4];
			byte[] srcRawPort  = new byte[4];
			byte[] dv = new byte[60*1024];
            
			System.arraycopy (receiveData,   0, srcRawAddr,  0, 4);
			System.arraycopy (receiveData,   4, srcRawPort,  0, 4);
			System.arraycopy (receiveData,  8, dv, 0, receiveData.length-16);
			//System.out.println("www");
			
			InetAddress srcAddr = InetAddress.getByAddress (srcRawAddr);
			int srcPort = ByteBuffer.wrap (srcRawPort).getInt ();
		
			String comeFrom = srcAddr.toString() + '\t' + Integer.toString(srcPort);
			//System.out.println(comeFrom);
			HashMap<String, Path> passedDV = new HashMap<>();;
		
			ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(dv));
			passedDV = (HashMap)inputStream.readObject();
		
			//for (Map.Entry<String, Path> et : passedDV.entrySet()){
			//	System.out.println(et.getKey());
			//}
			dvupdate(comeFrom, passedDV);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public byte[] pack(InetAddress srcip, int srcPort, HashMap<String, Path> selfdv) {
		byte[] srcRawAddr  = srcip.getAddress ();
		byte[] srcRawPort  = ByteBuffer.allocate(4).putInt(srcPort).array ();
		byte[] pck = new byte[60*1024];;
		try{

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream outputStream = new ObjectOutputStream(out);
			outputStream.writeObject(selfdv);
			outputStream.close();
			byte[] listData = out.toByteArray();
			
			System.arraycopy (srcRawAddr,  0, pck,  0, srcRawAddr.length);
			System.arraycopy (srcRawPort,  0, pck,  4, srcRawPort.length);
			System.arraycopy (listData, 0, pck, 8, listData.length);
	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return pck;
	}
}