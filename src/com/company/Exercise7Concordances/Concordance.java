package com.company.Exercise7Concordances;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;


public class Concordance { //brian 1016603, stijn 1019256
	/**
	 * makes sure the file name has .txt at the end in case it was left out
	 * @param name input name
	 * @return file with a shiny .txt at the end
	 */
	private static String fileTXT(String name)
	{
		assert (name.length()>=1):"file should have a name";
		String fileType=".txt";
		if (name.length()<4) return name+fileType;
		for (int i=1;i<=4;i++)
		{
			if (name.charAt(name.length()-(i))!=fileType.charAt(fileType.length()-(i)))
			{
				System.out.println("fixed:"+name+fileType);
				return name+fileType;
			}
			
		}
		return name;
	}
	/**
	 * shifts the elements of an array shortening it
	 * @param string array of strings
	 * @param index how far along the array should the strings be moved
	 */
	private static void sequentialShift(String[] string, int index)
	{
		assert(string!=null&string.length>1):"invalid array of strings";
		assert(index>0):"array should have a length";
		String[] stringRef= new String[index+1];
		for(int i=0;i<index;i++)
		{
			stringRef[i]=string[i];
		}
		for(int i=0;i<index;i++)
		{
			string[i]=stringRef[i+1];
		}
	}
	/**
	 *  compresses an array of strings into a sigle string
	 * @param string array of strings to be compressed
	 * @return single string
	 */
	private static String compressToString(String[] string)
	{
		assert(string!=null):"string should be initalised";
		String compressed="";
		for(int i=0;i<string.length;i++)
		{
			compressed+=string[i];
			if(i<string.length)
			{
				compressed+=" ";
			}
		}
		return compressed;
	}
	/**
	 * generates a stingh of = with a gefined length
	 * @param limit the defined length
	 * @return line
	 */
	private static String line(int start,int limit, char filler)
	{
		assert true;//tecniclly it wont break
		String line="";
		for(int i=start;i<limit;i++)
		{
			line+=filler;
		}
		return line;
	}
	/**
	 * writes a line into a file and breaks the line
	 * @param writer io
	 * @param line the line being wrote
	 * @throws IOException
	 */
	private static void writeln(OutputStreamWriter writer, String line) throws IOException
	{
		assert(writer!=null):"innitiate writer";
		writer.write(line);
		writer.write(System.getProperty( "line.separator" ));	
	}
	/**
	 * says bie
	 */
	private static void doStop()
	{
		assert true;
		System.out.println("bie");
		//System.exit(1);
	}
	/**
	 * queres a usr input to a message
	 * @param scanner scanner
	 * @param message the message displayed to the user
	 * @param complex if the input has multipal elements
	 * @param noOfinput the number of expected inputs
	 * @return the input
	 */
	private static String[] input(Scanner scanner, String message, int noOfinput)
	{
		assert(scanner!=null):"scanner must be initiated";
		assert(message.length()>=1):"its nice to let people know what you want";
		assert(noOfinput>0):"there should be atleast one input";
		
		System.out.print(message);
		if (noOfinput>1) System.out.print(" different components can be seperated with ';' or ':', eg <command;arg;arg;arg>");
		System.out.println("");
		System.out.print(">>>");
		String[] input= new String[noOfinput];	
		String line=scanner.nextLine();
		int i=0;
		input[0]="";
		for (int c=0;c<line.length();c++)
		{
			if(line.charAt(c)==';'||line.charAt(c)==':')
			{
				i++;
				if(i>input.length-1) throw new IllegalArgumentException("To many inputs, expected:"+noOfinput+" or less.");
				input[i]="";
			}
			else input[i]+=line.charAt(c);
		}
		/* don't know y but dosn't work, maby i'm dumb
		scanner.useDelimiter("[\\n\\t;:]+");
		int i=0;
		while(scanner.hasNext()&&i<noOfinput)
		{
			input[i]=scanner.next();
			i++;
		}*/
		return input;
	}
	/**
	 * converts any string to capatalised first char with everything else lower case
	 * @param word word to format
	 * @return formated word
	 */
	private static String wordFormated(String word)
	{
		assert (word!=null): "word being formated must exist";
		String newWord="";
		if (word.charAt(0)>='a'&&word.charAt(0)<='z') newWord+=(char)(word.charAt(0)-32);
		else newWord+=(char)word.charAt(0);
		for (int i=1;i<(word.length());i++)
		{
			if (word.charAt(i)>='A'&&word.charAt(i)<='Z') newWord+=(char)(word.charAt(i)+32);
			else if (word.charAt(i)!='.'&&word.charAt(i)!='!'&&word.charAt(i)!='?') newWord+=(char)word.charAt(i);
		}
		return newWord;
	}
	/**
	 * searches through the array for a target string
	 * @param words array of words to search through
	 * @param noOfWord information about the array of words
	 * @param word target word
	 * @return the position in the array the word exists or will exist
	 */
	private static int sequentialSearch(String[] words, int length, String word)
	{
		assert (words!=null): "String array needs to be initalised";
		assert (word!=null): "target of serch must have a value";
		int i=0;
		while(i<length&&!(words[i].equals(word)))
		{
			i++;
		}
		return i;
	}
	/**
	 * finds all the sentances which contain the target word
	 * @param fileName input file
	 * @param target the target word
	 */
	private static void wordSentance(String fileName, String target)
	{
		assert (fileName!=null):"file name should be initalised";
		assert ( target!=null && target!="."):"invalid target word";
		Scanner scannerIO=openTextFile(fileName);
		scannerIO.useDelimiter("[^a-zA-Z\\-'^.^!^?]+");     
		String[] sentances= new String[100000];
		String[] senWordIndsx= new String[100000];
		sentances[0]="";
		sentances[0]="<";
		boolean inSenatance=false;
		int[] times=new int[100000];
		int index=0;
		int totalWords=0;
		int longSentance=0;
		String word;
		System.out.println("beep beep boop, this might take a second");
		while(scannerIO.hasNext())
		{
			do
			{
				if (sentances[index].length()>1) sentances[index]+=" ";
				word=scannerIO.next();
				totalWords++;
				if (wordFormated(word).equals(target)) 
					{
						senWordIndsx[index]+=(", "+totalWords);// word occurance index
						inSenatance=true;
						word=("<"+word.toUpperCase()+">");
						times[index]++;//target occurs in centance
					}
				sentances[index]+=word;			
			}while (word.charAt(word.length()-1)!='.'&&word.charAt(word.length()-1)!='!'&&word.charAt(word.length()-1)!='?');
			
			sentances[index]+=">, word apears";				
			if(inSenatance)
			{
				if(sentances[index].length()>longSentance) longSentance=sentances[index].length();
				index++;
			}
			sentances[index]="<";
			inSenatance=false;
		}
		saveArrays(sentances,senWordIndsx,times,index,totalWords,"sentances",longSentance+2,25,false);
	}	
	/**
	 * saves data to a log file all the impormation on the different words in a file
	 * @param fileName name of file being red
	 * @param target word to serch for
	 * @param all count all words
	 */
	private static void countWords(String fileName)
	{
		assert (fileName!=null):"file name should be initalised";
		Scanner scannerIO=openTextFile(fileName);
		scannerIO.useDelimiter("[^a-zA-Z\\-']+");     
		String[] words= new String[1000000];
		String[] wordsIndex= new String[1000000];
		int[] wordCount= new int[1000000];
		int index=0;
		int totalWords=0;
		int longestWord=0;
		int longestNo=0;
		String word;
		System.out.println("beep beep boop, this might take a second");
		while(scannerIO.hasNext())
		{
			word=wordFormated(scannerIO.next());
			totalWords++;//total words
			int pos=sequentialSearch(words, index, word);
			if (pos==index) 
			{
				if (word.length()>longestWord) longestWord=word.length();//longest word
				index++;
				words[pos]=word;
				wordsIndex[pos]="";
			}
			wordCount[pos]++;
			if((""+wordCount[pos]).length()>longestNo) longestNo=(""+wordCount[pos]).length();
			wordsIndex[pos]+=(", "+totalWords);		
		}
		saveArrays(words,wordsIndex,wordCount,index,totalWords,"words",(longestNo+longestWord)+2,42,true);
	}
	/**
	 *  saves to a log file all the situations that the target word has aprared with a range of words on eather side
	 * @param fileName file being red
	 * @param target target word
	 * @param range amount of words that should apper on eather side
	 */
	private static void adjasentWords(String fileName, String target, int range)
	{
		assert (fileName!=null):"file name should be initalised";
		assert ( target!=null && target!="."):"invalid target word";
		Scanner scannerIO=openTextFile(fileName);
		scannerIO.useDelimiter("[^a-zA-Z\\-']+");
		String[] wordNrange= new String[100000];
		String[] wordIndex= new String[100000];
		String[] wordsCash= new String[range*2+1];
		int[] occurance=new int[100000];
		int index=0;
		int totalWords=0;
		int length=0;
		int i=0;
		boolean hasword=false;
		System.out.println("beep beep boop, this might take a second");
		while(scannerIO.hasNext())
		{
			do
			{
				if (i>range)
				{
					sequentialShift(wordsCash, i);
					i--;
				}
				wordsCash[i]=scannerIO.next();
				totalWords++;
				i++;
			}while(!wordFormated(wordsCash[i-1]).equals(target)&&scannerIO.hasNext());
			hasword=(wordFormated(wordsCash[i-1]).equals(target));
			String indexFound=(""+totalWords);
			occurance[index]++;
			for(int x=i;x<wordsCash.length;x++)
			{
				if(scannerIO.hasNext())
				{
					wordsCash[x]=scannerIO.next();
					if ((wordFormated(wordsCash[x]).equals(target)))
					{
						occurance[index]++;
						indexFound+=(", "+totalWords);
					}
					totalWords++;
				}
			}
			if (hasword)
			{
				String stringCash=compressToString(wordsCash);
				wordNrange[index]=compressToString(wordsCash);
				wordIndex[index]=(", "+indexFound);
				index++;		
				if (stringCash.length()>length) length=stringCash.length();
			}
			i=0;
		}
		saveArrays(wordNrange,wordIndex,occurance,index,totalWords,"occurances", length+3,20, false);
		
	}
	/**
	 * saves the imrormation into a log file
	 * @param string the main strings being saved
	 * @param stringIndex the concatination of indexes at witch the strings are relevent
	 * @param stringCount the number of occurance of the relevant elements in the string
	 * @param arrayLength the length of the array
	 * @param totalWords total number of words from which the array was formed from
	 * @param discription relevent name for a compinent in the array
	 * @param stringWidth width of the longest string  from string[]
	 * @param boxWidth aditional width to be added to the box
	 * @param usePercent should percentage be shown or not
	 */
	private static void saveArrays(String[] string,String[] stringIndex,int[] stringCount,int arrayLength,int totalWords,String discription,int stringWidth, int boxWidth,boolean usePercent)
	{
		assert (string!=null): "String array needs to be initalised";
		assert (stringIndex!=null): "index array should be initalised";
		assert (stringCount!=null): "int array needs to be initalised";
		assert true;//to other paramiters as tecnically they shouldn't break the function
		System.out.println("woop, nearly thare");
		try
		{
			OutputStreamWriter writer= new OutputStreamWriter(new FileOutputStream("log.txt"));		
			assert (string!=null): "String array needs to be initalised";
			assert (stringCount!=null): "int array needs to be initalised";
			writeln(writer,"total words:"+totalWords);
			writeln(writer,"different "+discription+":"+arrayLength);
			writeln(writer,line(0,boxWidth+stringWidth,'='));
			for(int i=0;i<arrayLength;i++)
			{
				writer.write("|"+string[i]+":"+stringCount[i]);
				for (int l=(string[i].length()+(""+stringCount[i]).length());l<stringWidth;l++)
				{
					writer.write(" ");
				}
				if (usePercent) 
				{
					double count=stringCount[i];
					double total=totalWords;
					double percent=(double)Math.round(((count/total)*100) * 10000d) / 10000d;
					writer.write("| percentage:<"+percent+line((""+percent).length(), 6, '0')+"%>");
				}
				writeln(writer,"| appeard at index"+stringIndex[i]);
			}
			writeln(writer,line(0,boxWidth+stringWidth,'='));
			System.out.println("finished!!! check log.txt for result");
			writer.close();
		}
		catch(IOException e)
		{
			System.out.println("ooh noo! something wet wrong when saving the result");
		}
	}	
	/**
	 * initiates a scanner with a user specified file
	 * @param fileName target file
	 * @return
	 */
	private static Scanner openTextFile(String fileName) 
	{
		assert (fileName!=null):"file name should be initalised";
		try
		{
			FileInputStream inputStream=new FileInputStream(fileName);
			return new Scanner(inputStream);
		}
		catch (IOException e)
		{
			System.out.println("ooh noo! something went wrong when reading the file, are you sure you wanted to read "+fileName+"?");
			return null;
		}
	}
	
	
	public static void main(String[] args) 
	{	
		Scanner scanner= new Scanner(System.in);
		String command;
		do 
		{
			String[] input= new String[4];
			
			input=input(scanner, "what do you want to do?",4);
			if (input[0]==null) input[0]=input(scanner, "please give a commant",1)[0];
			command=input[0];
			switch(command)
			{
			case("count"):// fall through
			case("all"):
			case("countAllWords"):
			case("count all words"):
			case("read"):
				if (input[1]==null) input[1]=input(scanner, "Please enter file name.",1)[0];
				countWords(fileTXT(input[1]));
				break;
			case("where"):
				if (input[1]==null) input[1]=input(scanner, "Please enter target word.",1)[0];
				if (input[2]==null) input[2]=input(scanner, "Please enter file name.",1)[0];
				adjasentWords(fileTXT(input[2]), wordFormated(input[1]), 0);
				break;
			case("adjasentWords")://fall through
			case("adjasent"):
			case("contex"):
				if (input[1]==null) input[1]=input(scanner, "Please enter target word.",1)[0];
				if (input[2]==null) input[2]=input(scanner, "Please enter file name.",1)[0];
				if (input[3]==null) input[3]=input(scanner, "Please enter a range.",1)[0];
				adjasentWords(fileTXT(input[2]),wordFormated(input[1]),Integer.parseInt(input[3]));
				break;
			case("sentance"):// extra
				if (input[1]==null) input[1]=input(scanner, "Please enter target word.",1)[0];
				if (input[2]==null) input[2]=input(scanner, "Please enter file name.",1)[0];			
				wordSentance(fileTXT(input[2]),wordFormated(input[1]));
				break;
			case("stop"):
				doStop();
				break;
			default:
				System.out.println("can't <"+command+">");
			}
		}
		while (!(command.equals("stop")));
		scanner.close();
	}
}
