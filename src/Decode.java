import java.util.HashMap;

public class Decode{
	/**
	 * Returns a HashMap<Integer, String> object that represents the dictionary that an LZW algorithm would create to encode a plaintext string.
	 * Takes in a ciphertext string formatted as a sequence of integers delimited by commas (e.g. "12,18,19,258,"). There will be an excess comma at the end.
	 * For example, if the input is 97,98,99,259, where the plaintext is abcabc, then the HashMap would have as key-value pairs (256, ab), (257, bc), (258, ca), (259, abc)
	 *  
	 * @param commaDelimitedCiphertext the ciphertext that will correspond to the HashMap we return. 
	 * @return the HashMap that can be used to decode the ciphertext.
	 */
	public static HashMap<Integer, String> getLZWDecodingHashMap(String commaDelimitedCiphertext)
	{
		//Since it is easier to work with an array of integers than a comma-delimited string, we will first convert the commaDelimtiedCipherText into an array of integers.
		int[] integerArrayCiphertext = convertCommaDelimitedStringToIntegerArray(commaDelimitedCiphertext);
		//This HashMap will keep track of what ciphertext integers correspond to which plaintext strings. We will return this at the end of the function.
		HashMap<Integer, String> decodingDictionary = new HashMap<Integer, String>();
		//We will essentially perform LZW encoding again to find the decoding dictionary. The following variables will all be needed for this algorithm.
		
		//This HashMap will do the opposite of decodingDictioanry, keeping track of which plaintext strings correspond to which plaintext integers. We will need this to compute which ciphertext strings correspond to which plaintext integers.
		HashMap<String, Integer> encodingDictionary = new HashMap<String, Integer>();
		
		//We will decode all of the ciphertext that we can, perform LZW encoding on it to get a portion of the decoding dictionary, use that portion of the decoding dictionary to decode more of the ciphertext, and iterate this until the ciphertext is completely decoded. 
		for(int index = 0; index < integerArrayCiphertext.length; index++)
		{
			
		}
	}
	

	/**
	 * Returns an array of integers where each entry corresponds to the integers in a comma delimited string of integers.
	 * For example, if the input was "2,3,1043", then the array outputted would be [2, 3, 1043].
	 * @param commaDelimitedString a string of integers delimited by commas.
	 * @return an array of integers corresponding to the integers in the commaDelimitedString.
	 */
	private static int[] convertCommaDelimitedStringToIntegerArray(String commaDelimitedString)
	{
		
	}
}