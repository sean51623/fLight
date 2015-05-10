import java.io.*;

public class TestRead {
	public static void main(String[] args) throws IOException {
		FileReader f1 = new FileReader(args[0]);
		BufferedReader b1 = new BufferedReader(f1);
		String line = "";
		line = b1.readLine();
		System.out.println(line);
		System.out.println();
		while(b1.ready()) {
			line = b1.readLine();
			System.out.println(line);
		}
	}
}