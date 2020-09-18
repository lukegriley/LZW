import java.io.*;
import java.util.*;
public class LZWTester {
	//temporary tester
			public static void main(String[] args)
			{
				Encode encode = new Encode();
				try {
					encode.encoding("file1.txt");
				} catch (Exception e) {
					// TODO: handle exception
				}
//				int[] test = {115,100,102,107,108,104,97,97,100,115,106,109,102,110,103,104,106,115,107,268,120,122,98,99,100,104,115,114,116,106,274,100,109,110,122,100,103,115,271,97,258,288,290,115,102,270,272,107,288,300,100,106,281,303,110,100,98,300,271,115,110,109,118,120,278,110,106,318,317,32,118,310,106,110,115,109,32,100,110,102,328,315,300,310,317,110,98,109};
//				Decode thing = new Decode();
//				System.out.println(thing.getLZWDecodingHashMap(test));
			}
//			public static void main(String[] args)
//			{
//				test2 lol = new test2();	
//				lol.superyeet();
//			}
//			
		
//	public static void main(String[] args) throws IOException {
//		LZW compressor = new LZW();
//		compressor.encoding("file1.txt");
//	}
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
class test1{
	public void yeet()
	{
		System.out.println("yeet");
	}
	public void superyeet()
	{
		yeet();
	}
}
class test2 extends test1
{
	
	public void yeet()
	{
		System.out.println("not yeet");
	}
}
