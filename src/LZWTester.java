import java.io.*;
import java.util.*;
public class LZWTester {
	//temporary tester
//			public static void main(String[] args)
//			{
//				int[] test = {97, 98, 99, 256, 99};
//				System.out.println(Decode.getLZWDecodingHashMap(test));
//			}
	public static void main(String[] args) throws IOException {
		LZW compressor = new LZW();
		compressor.encoding("file1.txt");
	}
//		StringBuilder meow = new StringBuilder();
//		for(int i = 0; i < 10; i++)
//		{
//			test(meow);
//			System.out.println(meow);
//		}
//		test2(meow);
//		System.out.println(meow);
//	}
//	public static void test(StringBuilder blep)
//	{
//		blep.append("yeet");
//	}
//	public static void test2(StringBuilder blep)
//	{
//		blep.setLength(0);
//		blep.append("heck");
//	}
}
