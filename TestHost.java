import java.util.*;
import java.net.*;
import java.io.*;

public class TestHost {

	public static void main(String[] args) throws IOException{
		
		new Thread(new TestRC()).start();
		new Thread(new TestSD()).start();

	}
	
}