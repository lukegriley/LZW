import java.util.*;
import java.io.*;

public class Decode{
	public static HashMapString, Integer> getHashMap(String ciphertext)
	{
		
	}
	
	public ArrayList<Integer> convertFileToString(String filename)
	{
		//read the encoded file as an input
		BufferedReader encodedFileReader = new BufferedReader(new FileReader(filename));
		//create an arraylist that will take in the encoded file in the form of ints from chars
		ArrayList<Integer> encodedFileInts = new ArrayList<Integer>();
		int current; 
		
		//iterate through the encoded file and convert it into a string
		while((current = encodedFileReader.read())!=-1)
		{
			encodedFileInts.add(current);
		}
		
		return encodedFileInts;
	}
}