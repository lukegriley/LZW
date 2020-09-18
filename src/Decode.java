
import java.util.*;
import java.io.*;
import java.util.HashMap;
public class Decode{


public class Decode extends LZWHelper{

	/*
	 * TODO: Merge initializeEncodingDictionary and initializeDecodingDictionary into a single function that calls addNewSymbolToDictionary CHARSET_SIZE times
	 */

	public Decode()
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
		encodedFileReader.close();

		return encodedFileInts;
	}

	/**
	 * Returns a HashMap<Integer, String> object that represents the dictionary that an LZW algorithm would create to encode a plaintext string.
	 * Takes in ciphertext formatted as an array of integers.
	 * For example, if the input is [97,98,99,256,99] where the plaintext is abcabc, then the HashMap would have as key-value pairs (256, ab), (257, bc), (258, ca), (259, abc)
	 *
	 * @param ciphertextAsArray the ciphertext that will correspond to the HashMap we return.
	 * @return the HashMap that can be used to decode the ciphertext.
	 */
	public HashMap<Integer, String> getLZWDecodingHashMap(int[] ciphertextAsArray)
	{

		//The following two methods will initialize our encoding and decoding dictionaries, respectively.
		initializeEncodingDictionary(encodingDictionary, CHARSET_SIZE);
		initializeDecodingDictionary(decodingDictionary, CHARSET_SIZE);
		//We will decode all of the ciphertext that we can, perform LZW encoding on it to get a portion of the decoding dictionary, use that portion of the decoding dictionary to decode more of the ciphertext, and iterate this until the ciphertext is completely decoded.
		for(int i = 0; i < ciphertextAsArray.length; i++)
		{
			decode(ciphertextAsArray[i], currentLongestSubstringInDictionary, encodingDictionary, decodingDictionary);
		}
		return decodingDictionary;
	}
	/**
	 * Performs one step of the decoding algorithm. Takes in the ciphertext, uses the decodingDictionary to decode it, then for each character in the decoded chunk, it applies the LZW encoding algorithm.
	 * Namely, for each character in the decoded chunk, it appends it to currentLongestSubstringInDictionary, then checks whether currentLongestSubstringInDictionary is in encodingDictionary.
	 * If it is, then it moves on to the next character, or if there is no next character, returns.
	 * If it is not, then it adds the key-value pair (currentLongestSubstringInDictionary, encodingDictionary.size()) to encodingDictionary and the key-value pair (decodingDictionary.size(), currentLongestSubstringInDictionary) to decodingDictionary.
	 * It then sets currentLongestSubstringInDictionary to the character currently being observed.
	 * @param ciphertext the integer representing one block of ciphertext to be decoded into plaintext
	 * @param currentLongestSubstringInDictionary the longest consecutive substring in the plaintext which is already in our dictionary
	 * @param encodingDictionary the dictionary we will use to keep track of the encoding key-value pairs
	 * @param decodingDictionary the dictionary we will use to keep track of the decoding key-value pairs
	 */
	private void decode(int ciphertext, StringBuilder currentLongestSubstringInDictionary, HashMap<String, Integer> encodingDictionary, HashMap<Integer, String> decodingDictionary)
	{
		//plaintextChunk will contain the plaintext that ciphertext corresponds to.
		String plaintextChunk = new String();
		plaintextChunk = decodingDictionary.get(ciphertext);
		if(plaintextChunk == null)
		{
			handleCiphertextNotInDictionaryError(currentLongestSubstringInDictionary, encodingDictionary, decodingDictionary);
			plaintextChunk = decodingDictionary.get(ciphertext);
		}
		//We will now iterate through the LZW encoding-esque algorithm for each character in plaintextChunk
		for(int i = 0; i < plaintextChunk.length(); i++)
		{
			currentLongestSubstringInDictionary.append(plaintextChunk.charAt(i) + "");
			if(encodingDictionary.get(currentLongestSubstringInDictionary.toString()) == null)
			{
				handleSubstringNotInDictionary(plaintextChunk.charAt(i), currentLongestSubstringInDictionary, encodingDictionary, decodingDictionary);
				System.out.println(encodingDictionary);
			}
		}
	}
	/**
	 * Adds the key-value pair (currentLongestSubstringInDictionary, encodingDictionary.size()) to encodingDictionary and the key-value pair (decodingDictionary.size(), currentLongestSubstringInDictionary) to decodingDictionary.
	 * @param currentLongestSubstringInDictionary the symbol to be added to the dictionaries
	 * @param encodingDictionary the encoding dictionary
	 * @param decodingDictionary the decoding dictionary
	 */
	public void addNewSymbolToDictionary(StringBuilder currentLongestSubstringInDictionary, HashMap<String, Integer> encodingDictionary, HashMap<Integer, String> decodingDictionary)
	{
		String symbol = currentLongestSubstringInDictionary.toString();
		encodingDictionary.put(symbol, encodingDictionary.size());
		decodingDictionary.put(decodingDictionary.size(), symbol);
	}
	/**
	 * This function will run through a loop CHARSET_SIZE times in which it adds the key value pair (c, i) where i is the index of the loop and c is the character corresponding to that index, as a string.
	 * @param encodingDictionary the dictionary to be initialized
	 * @param CHARSET_SIZE the size of the charset
	 */
	public void initializeEncodingDictionary(HashMap<String, Integer> encodingDictionary, int CHARSET_SIZE)
	{
		for(int i = 0; i < CHARSET_SIZE; i++)
		{
			encodingDictionary.put((char)(i) + "", i);
		}
	}
	/**
	 * This function will run through a loop CHARSET_SIZE times in which it adds the key value pair (i, c) where i is the index of the loop and c is the character corresponding to that index, as a string.
	 * @param decodingDictionary the dictionary to be initialized
	 * @param CHARSET_SIZE the size of the charset
	 */
	private void initializeDecodingDictionary(HashMap<Integer, String> decodingDictionary, int CHARSET_SIZE)
	{
		for(int i = 0; i < CHARSET_SIZE; i++)
		{
			decodingDictionary.put(i, (char)(i) + "");
		}
	}
	/**
	 * We will have to handle a strange edge case in which, during LZW encoding, the information required to add a symbol into the dictionary is contained within the first encoded instance of that symbol.
	 * In other words, the information required to add, say, "bb=256" cannot be accessed, because to add "bb" you need to first decode "256", but to decode "256" you must first add "bb."
	 * By way of example, consider encoding the string "bbbb". Each of the steps are enumerated below:
	 * P = null, C = b, no output, no change in dictionary
	 * P = b, C = b, output 98 (b), add bb (256) to dictionary
	 * P = b, C = b, no output, no change in dictionary
	 * P = bb, C = b, output bb (256) and b (98) [both are outputted because we have reached the end and triggered the edge case], no change in dictionary
	 * The final output will be 98,256,98. But when we perform our algorithm of decoding everything that we can, adding every key that we can create from the decoded part to the dictionary, and iterating, we find a problem.
	 * Initially, we can only decode 98 (b). But from here, without the edge case handling which is about to occur, we will not be able to assign a symbol to 256.
	 * The only way in which this error can occur is if a symbol s is put into the dictionary, then immediately afterwards, a symbol which consists of s plus another character is added into the dictionary.
	 * The only way in which that scenario can occur is if a symbol starts and ends with the same character. (A proof of this is trivial.)
	 * Therefore, if and only if we encounter a symbol not in our dictionary, we may safely handle it by taking the last observed symbol s and adding s+s.charAt(0) to our dictionary.
	 * @param currentLongestSubstringInDictionary the symbol we will use to handle this edge case
	 * @param encodingDictionary the encoding dictionary
	 * @param decodingDictionary the decoding dictionary
	 */
	private void handleCiphertextNotInDictionaryError(StringBuilder currentLongestSubstringInDictionary, HashMap<String, Integer> encodingDictionary, HashMap<Integer, String> decodingDictionary)
	{
		addNewSymbolToDictionary(new StringBuilder(currentLongestSubstringInDictionary.toString()+currentLongestSubstringInDictionary.toString().charAt(0)), encodingDictionary, decodingDictionary);
	}
	
}
