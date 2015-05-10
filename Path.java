import java.io.*;

public class Path implements Serializable{
	String nexthop;
	float totalDist;
	
	public Path(float totalDist) {
		nexthop = null;
		this.totalDist = totalDist;
	}
	
	public Path(String nexthop, float totalDist) {
		this.nexthop = nexthop;
		this.totalDist = totalDist;
	}
}