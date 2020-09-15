import java.io.*;
import java.util.*;
public class LZWTester {
	public static void main(String[] args) throws IOException {
		LZW compressor = new LZW();
		compressor.encoding("file1.txt");
	}
}
