import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Decode{
	public static HashMap<String, Integer> getHashMap(String ciphertext)
	{
		
	}
	
	public void decodeCipherTextWithHashMap(ArrayList<Integer> encodedInts, String decodedFileName)
	{
		HashMap<Integer,String> dictionary= getLZWDecodingHashMap(ArrayList<Integer> encodedInts);
		
		File decodedFile = new File(decodedFileName);
		FileWriter decodeWriter = new FileWriter(decodedFile);
		
		for(int i=0;i<dictionary.size();i++)
		{
			decodeWriter.write(dictionary.get(encodedInts.get(i)));
		}
		decodeWriter.close();
		
	}
}