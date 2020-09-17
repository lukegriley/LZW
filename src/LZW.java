import java.util.*;
import java.io.*;
public class LZW {
	
	public LZW() {
		
	}
	
	
	
	public void encoding(String input) throws IOException {
		
		String inputFile = input;
		BufferedReader br = new BufferedReader(new FileReader(inputFile));//reads input file
		String encodeFileName = inputFile.substring(0,inputFile.length()-4)+" encoded.txt";
		BufferedWriter encodeWriter = new BufferedWriter(new FileWriter(encodeFileName));//writes to new encoded file
		
		HashMap<String,Integer> dictionary = new HashMap<String,Integer>();
		/*our dictionary (table of codes) only contains the custom codes we give to groups of characters. single characters with an 
		 * index under 255 are already known by java and do not need to be stored in the dictionary in our program
		 */
		Decode.initializeEncodingDictionary(dictionary, 256);
		String p = null; 
		char c = (char)br.read(); 
		String sum = "";
		int counter = 255;//keeps track of what index new Strings should be added to the custom dictionary as
		boolean first = true;//keeps track of if this is our first time going through the while loop. the necessity for this is explained further down
		String temp = "";
		char lastC = 'q';
		while(br.ready()) {
			String tempP = p;
			sum = p+c;
			if(dictionary.containsKey(sum)) {//if P+C is already in the dictionary, then P=P+C. 
				p = sum;
			}
			else {	
				//if p is a single char with an index of below 256, then it is already in the "default dictionary" and does not need to be added to the custom dicitonary; we can simply output its default char index to the codestream
				if(p!=null&&p.length()==1&&p.charAt(0)<256) {
					String output = (int)p.charAt(0)+",";
					encodeWriter.write(output);
				}
				//if p is not in the default dictionary, we know it should already be in the custom dictionary somewhere. here we output its code to the codestream
				else if(p!=null) {
					encodeWriter.write(dictionary.get(p)+",");
				}
				//this if statement makes sure we are not putting the very first sum, which would just be a single character long (e.g. null+"a"), into the custom dictionary; the "first" boolean keeps track if this is the first time going through the while loop
				if(!first) {
					if (counter<50000) {
						counter++;
						dictionary.put(sum,counter);
					}
					//System.out.println("P:"+p+", c:"+c);
				}
				p = c+"";
			 }
			lastC = c;
			 c = (char) br.read();
			 first = false;
			 temp = p+c;//temp saves the most recent sum for the last time the while loop runs, for the next line outside the loop to output its code
		}
		if(!dictionary.containsKey(temp)&&counter<50000) {
			counter++;
			dictionary.put(temp,counter);
		}
		if(p.length() > 1)
		{
			encodeWriter.write(dictionary.get(p)+","+(int)c+",");
		}
		else {
			encodeWriter.write((int)p.charAt(0)+","+(int)c+",");
		}
		System.out.println("temp:"+temp);
		//close writers
		br.close();
		System.out.println(dictionary.toString());
		System.out.println("last p="+p+", last c="+c);
		encodeWriter.close();
		
	}
	
	public void ifInDictionary() {
		
	}
	
	public void decoding() {
		
	}
}
