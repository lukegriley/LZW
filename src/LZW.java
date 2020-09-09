import java.util.*;
import java.io.*;
public class LZW {
	String inputFile;
	BufferedReader br;
	public LZW(String input) throws FileNotFoundException {
		inputFile = input;
		br = new BufferedReader(new FileReader(inputFile));
	}
}
