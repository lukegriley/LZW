import java.util.*;
import java.io.*;
public class LZW {
	
	public LZW() {
		
	}
	
	
	
	public void encoding(String input) throws IOException {
		HashMap<String,Integer> dictionary = new HashMap<String,Integer>();
		String inputFile = input;
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String dictFileName = inputFile.substring(0,inputFile.length()-4)+" dict.txt";
		BufferedWriter dictionaryWriter = new BufferedWriter(new FileWriter(dictFileName));
		String encodeFileName = inputFile.substring(0,inputFile.length()-4)+" encoded.txt";
		BufferedWriter encodeWriter = new BufferedWriter(new FileWriter(encodeFileName));
		
		String p = null;
		char c = (char)br.read();
		String sum = "";
		int counter = 255;
		boolean first = true;
		String temp = "";
		while(br.ready()) {
			sum = p+c;
			if(dictionary.containsKey(sum)) {
				p = sum;
			}
			else {
				//if p index is under 255
				if(p!=null&&p.length()==1) {
					String output = (int)p.charAt(0)+",";
					encodeWriter.write(output);
				}
				//if p is not in dictionary and needs to be added
				else if(p!=null&&p.length()>1) {
					encodeWriter.write(dictionary.get(p)+",");
				}
				if(!first) {
					counter++;
					dictionary.put(sum,counter);
					dictionaryWriter.write(sum+":"+counter+"\n");
					System.out.println("P:"+p+", c:"+c);
				}
				p = c+"";
			 }
			
			 c = (char) br.read();
			 first = false;
			 temp = p+c;
		}
		encodeWriter.write(dictionary.get(temp)+",");
		//close things
		br.close();
		dictionaryWriter.close();
		System.out.println(dictionary.toString());
		System.out.println("last p="+p+", last c="+c);
		encodeWriter.close();
		
	}
	
	public void ifInDictionary() {
		
	}
	
	public void decoding() {
		
	}
}
