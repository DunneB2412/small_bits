package cipher;

import java.util.Random;
import java.util.Scanner;

/* SELF ASSESSMENT

1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?

       Mark out of 5:5
       Comment: i se no reason why they are not correct

2. Did I indent the code appropriately?

       Mark out of 5:5
       Comment:  all good i think

3. Did I write the createCipher function correctly (parameters, return type and function body) and invoke it correctly?

      Mark out of 20:20
       Comment:  

4. Did I write the encrypt function correctly (parameters, return type and function body) and invoke it correctly?

      Mark out of 20: 19 
       Comment: it's not like you expected but i believe that the 'translation()' method is more effective

5. Did I write the decrypt function correctly (parameters, return type and function body) and invoke it correctly?

      Mark out of 20:  19
       Comment: same as before

6. Did I write the main function body correctly (repeatedly obtaining a string and encrypting it and then decrypting the encrypted version)?

      Mark out of 25: 24  
       Comment: it is a little curde in how the loop is formed but the consept is there

7. How well did I complete this self-assessment?

       Mark out of 5: 5
       Comment: i think i was fare

Total Mark out of 100 (Add all the previous marks):

*/ 

public class CipherMain {
	private static final char[] ALPHABET = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
			'o','p','q','r','s','t','u','v','w','x','y','z',' '};
	private static final String EXIT_TOKEN = "quit";
	
	public static void main(String[] args) {
		Scanner systemIn = new Scanner(System.in);
		Random random = new Random();
		char[] cipher = createCipher(random);
		System.out.println("Alphabet:<"+new String(ALPHABET)+">");
		System.out.println("cipher  :<"+new String(cipher)+">");
		String sentance;
		do {
			System.out.print("give a sentance to incrypt(say <"+EXIT_TOKEN+"> to quit:");
			sentance = systemIn.nextLine().toLowerCase();
			String secretSentance = encrypt(cipher, sentance);
			System.out.println(secretSentance);
			String secretRevealed = decrypt(cipher, secretSentance);
			System.out.println(secretRevealed);
		}while(!sentance.equals(EXIT_TOKEN));
		
		System.out.println("just in case you were curious.");
		System.out.println("good bye.");
		systemIn.close();
	}
	
	private static String decrypt(char[] cipher, String string) {
		return translate(string, ALPHABET, cipher);
	}

	private static String encrypt(char[] cypher, String string) {
		return translate(string, cypher, ALPHABET);
	}
	
	private static String translate(String string, char[] base, char[] translation) {
		char[] outAsArray = new char[string.length()];
		for(int index = 0; index< string.length(); index++) {
			int posistion = -1;
			for(int index2=0; index2<base.length; index2++) {
				if(base[index2]==string.charAt(index))
					posistion=index2;	
			}
			
			if(posistion ==-1) 
				outAsArray[index] = string.charAt(index);
			else 
				outAsArray[index] = translation[posistion];
		}
		return new String(outAsArray);
	}
	
	private static char[] createCipher(Random random) {
		char [] out = ALPHABET.clone();
		for(int counter = 0; counter < (out.length*100); counter++) {
			swap(out, random.nextInt(out.length), random.nextInt(out.length));
		}
		return out;
	}
	private static void swap(char[] charArray, int index1, int index2) {
		char helper = charArray[index1];
		charArray[index1]= charArray[index2];
		charArray[index2] = helper;
	}

}
