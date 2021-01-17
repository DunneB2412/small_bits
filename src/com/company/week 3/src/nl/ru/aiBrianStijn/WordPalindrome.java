import java.util.Scanner;

public class WordPalindrome {// brian 1016603 stign 
	
	private static Scanner scanner;
	private static void checkWord(String word)
	{
		boolean isPalindrome = true;
		for(int i=0; i<word.length(); i++)
		{
			if(word.charAt(i)!=word.charAt(word.length()-(i+1)))
			{
				isPalindrome= false;
			}
		}
		System.out.println(isPalindrome);
	}

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		System.out.println("give a word and see if all nice and symetric");
		String word = scanner.next(); 
		checkWord(word);
		
		// TODO Auto-generated method stub

	}

}
