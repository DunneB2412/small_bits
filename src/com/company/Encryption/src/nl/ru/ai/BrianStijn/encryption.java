package nl.ru.ai.BrianStijn;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.io.FileInputStream;


public class encryption {
	/**
	 * takes in a file name and makes sure that it has a file type
	 * @param name; the name of the file with/without .(type) at the end
	 * @param fileType; the defined file type to asign to a file name
	 * @return file name with a specified type
	 */
	private static String fileType(String name, String fileType)
	{
		for (int i=1;i<=3;i++)
		{
			if (name.charAt(name.length()-(i+1))!=fileType.charAt(fileType.length()-(i+1)))
			{
				return name+fileType;
			}
		}
		return name;
	
	}
	/**
	 * turne odd intigers to true and even to false
	 * @param i intiget to be translated to a boolean
	 * @return true or fals 
	 */
	private static boolean intToBoolean(int i)
	{
		assert true;
		if (i%2!=0)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
	/**
	 * incrypts or unencrypts a letter a with the key r
	 * @param input: user input
	 * @param r: random key
	 * @param encrypt: encrypt or un incrypt 
	 */
	private static int crypt(char input, int r, boolean encrypt)
	{
		assert (r>=0&&r<=95): "variable r is out of range @finction:crypt";
		int a= input;
		if (a>=32&&a<=127)
		{
			int b;
			if (encrypt)
			{
				b=(a-32+r+96)%96+32;
			}
			else
			{
				b=(a-32-r+96)%96+32;
			}
			//System.out.print((char)b);
			return b;
		}
		else
		{
			return a;
		}
	}
	/**
	 * prints out the characters with data tag between 32,127
	 * @param r: random incryption key
	 * @param var: variable to signify which case
	 */
	private static void test(int r, int var)
	{
		assert (var>0&&var<=4 && r>=0&&r<=95):"invalid arguments for variable var or r @finction:test";
		System.out.print("|| ");
		switch(var)
		{
		case 1:
			for (int i=32;i<=127;i++)
			{
				System.out.print((char)i);
			}
			break;
		case 2:
			for (int i=32;i<=127;i++)
			{
				System.out.print((char)crypt((char)i, r, true));
			}
			break;
		case 3:
			for (int i=32;i<=127;i++)
			{
				System.out.print((char)crypt((char)crypt((char)i, r, true), r, false));
			}
			break;
		case 4:
			for (int i=32;i<=127;i++)
			{
				System.out.print("=");
			}
			break;
		}
		System.out.println(" ||");
		
	}
	/**
	 * incripts or decrypts with function crypt the data from ine file and writes it to a new file
	 * @param encrypt; true or fals for encrypt or decrypt
	 * @param random; random intiger for key
	 * @param fileName; read adress
	 * @param destination; write adress
	 * @param run; states weather the function will devrypt all the file or just a sample of |range|
	 * @param sampleSize; the range in which the program will test 
	 * @return true if %letters against characters is greater than 80%
	 */
	
	private static boolean callFileNCompile(boolean encrypt, int seed, String fileName, String destination, boolean run,int sampleSize)
	{
		Random generator=new Random(seed);
		assert true; 
		try
		{
			InputStreamReader reader= new InputStreamReader(new FileInputStream(fileName));
			OutputStreamWriter writer= new OutputStreamWriter(new FileOutputStream(destination));
			int c;
			int percent=0;
			if(run)
			{
				while((c=reader.read())>=0)
				{
					writer.write((char)crypt((char)c,generator.nextInt(96),encrypt));
				}
			}
			else
			{
				int a;
				int letters=0;
				int characters=0;
				while(characters<=sampleSize&&(c=reader.read())>=0)
				{
					a=((char)crypt((char)c,generator.nextInt(96),encrypt));
					if (a>65&&a<90||a>97&&a<122||a==32)
					{
						letters++;
					}
					characters++;
				}
				percent= (letters*100/sampleSize);
				System.out.println(percent+"% letters");

			}
			reader.close();
			writer.close();	
			if (percent>=80)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(IOException e)
		{
			System.out.println("oops! something went wrong ");
			return true;
		}
	}
	/**
	 * searches for a seed for which the decrypted result should contain only english characters
	 * @param input; the incrypted input file
	 * @param output; the destination file for the decrypted text
	 * @param keyLimit; the maximum key to which the function will check. prevents endless loop
	 * @param sampleSize; the amount of characters the function will check.
	 * @return the seed of the random number generator
	 */
	private static int searchKey(String input, String output, int keyLimit, int sampleSize)
	{
		assert true;
		int key=1;
		while (key<=keyLimit)
		{
			System.out.println("testing for seed "+key);
			if (callFileNCompile(false, key, input, output, false,sampleSize))
			{
				callFileNCompile(false, key, input, output, true,1);
				return key;
			}
			key++;
		}		
		return key;
	}


	public static void main(String[] args) {
		System.out.println("test");
		for(int i=1;i<=4;i++)
		{
			test(42, i);
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("1=incrypting,2=decrypting,3=search key");
		int mode=scanner.nextInt();
		System.out.println("please spesify file type eg '.txt'");
		String fileType=scanner.next();
		System.out.println("give an input file");
		String input=fileType(scanner.next(), fileType);
		System.out.println("give a destination file");
		String output=fileType(scanner.next(), fileType);
		switch(mode)
		{
		case(1):
		case(2):// fall through is intentional
		{
			System.out.println("give a seed");
			int seed =scanner.nextInt();
			callFileNCompile(intToBoolean(mode),seed,input,output,true,1);
			break;
		}
		case(3):
		{
			System.out.println("give a keyLimit");
			int keyLimit =scanner.nextInt();
			System.out.println("give a sample size");
			int sampleSize =scanner.nextInt();
			while (sampleSize<10||sampleSize>200)
			{
				System.out.println("bro you crazy, try a more reasonable sample size");
				System.out.println("give a sample size");
				sampleSize =scanner.nextInt();
			}	
			int result=searchKey(input, output, keyLimit, sampleSize);
			if (result>keyLimit)
			{
				System.out.println("inconclusive");
			}
			else
			{
				System.out.println("the seed is "+ result);	
			}
			break;
		}
		default:
		{
			System.out.println("you gave an invalid mode");
		}
		}				
		scanner.close();
		System.out.print("done!");

		// TODO Auto-generated method stub

	}

}
