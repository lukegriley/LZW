import java.io.*;

public class Decode{
	public static HashMapString, Integer> getHashMap(String ciphertext)
	{
		
	}
	
	public String convertFileToString(String filename)
	{
		//read encoded file as an input
		BufferedReader encodedFileReader = new BufferedReader(new FileReader(filename));
		//create the string that will be the decoded file in that format
		String encodedString = "";
		int current;
		
		//iterate through the encoded file and convert it into a string
		while((current = encodedFileReader.read())!=-1)
		{
			encodedString = encodedString+(char)current;
		}
		
		return encodedString;
	}
}