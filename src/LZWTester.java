import java.io.*;
import java.util.*;
public class LZWTester {
	public static void main(String[] args) throws IOException
	{
//		Encode testEncoder = new Encode();
//		testEncoder.encoding("file1.txt");
		Decode testDecoder = new Decode();
		testDecoder.decode("file1.txt.lzw");
	}
}
