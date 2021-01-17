package com.company.Exercise7Concordances;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;

public class test {
	final static int MAX_NR_OF_WORDS=20000;
	public static void main(String [] args) throws FileNotFoundException
	{
		String [] words=new String[MAX_NR_OF_WORDS] ;
		int [] freqs=new int[MAX_NR_OF_WORDS] ;
		String command;
		int nr=0;
		Scanner mainscanner=new Scanner(System.in);
		System.out.println("What do you want me to do?");
		do
		{
			command=mainscanner.next();
			Scanner scanner=new Scanner(System.in);
			switch(command)
			{
				case "Read":
					Scanner scanner2 = readFile();
					nr=getNumberOfWords(scanner2, words, freqs) ;
					
					break;
				case "content":
					doContent();
					break;
				default:
					System.out.println("Sorry I don't know how to do that!");
			}
		}while(!command.equals("stop"));
	}
	
	private static void doContent() 
	{
		System.out.println("words");
	}

	private static void doStop() 
	{
		System.out.println("bye");
		
	}
/**
 * this function reads the File
 * @param fileName
 * @return
 * @throws FileNotFoundException
 */
	private static Scanner readFile() throws FileNotFoundException 
	{
		Scanner input=new Scanner(System.in);
		input.useDelimiter("[^a-zA-Z|']+[ \\\n]'|' | '| +|\\\\\\n");
		System.out.println("Please enter file name: ");
		String fileName= input.nextLine();
		FileInputStream inputStream= new FileInputStream(fileName);
		return new Scanner(inputStream);
	}

/**
* this function counts the number of words in the given file
* @param scanner
* @param words
* @param freqs
* @return displayFrequency(nr, words, freqs) ;
* @throws FileNotFoundException
*/
	private static int getNumberOfWords(Scanner scanner2, String[] words, int[] freqs) throws FileNotFoundException 
	{
		int nr = 0 ; 
		while(scanner2.hasNext())
		{
			String word=scanner2.next();
			if(updateWord(word,words,freqs,nr))
			{
				nr++;
			}
		}
		displayFrequency(nr, words, freqs) ;
		return nr;
	}
	
/**
 * this function displays the frequency of a word
 * @param nr
 * @param words
 * @param freqs
 */
	private static void displayFrequency(int nr, String[] words, int[] freqs) 
	{
		System.out.println("display");
		for(int i = 0; i < nr; i++ )
		{   
			System.out.println(words[i]+" "+freqs[i]);
		}
	}
	
/**
 * this function updates the word	
 * @param word
 * @param words
 * @param freqs
 * @param nr
 * @return
 */
	static boolean updateWord(String word, String [] words, int [] freqs, int nr)
	{   
		int pos = sequentialSearch(words, 0, nr, word ) ;
		if( pos < nr)
		{  
			freqs[pos]++ ;
			return false;
		}
		else
		{    
			words[pos]= word ;  
			freqs[pos] = 1 ;
			return true;
	}
	}

/**
 * this function searches for a word
 * @param words
 * @param i
 * @param limit
 * @param searchWord
 * @return
 */
	private static int sequentialSearch(String[] words, int i, int limit, String searchWord) 
	{
		assert words!=null: "Word should be initialized" ;
		if (i>limit)
		{
			return limit;
		}
		int pos = i;
		while((pos< words.length&&words[pos]!=null) &&!(words[pos].toUpperCase()).equals(searchWord))
		{
			pos++ ;	
		}
		return pos;
	}
}