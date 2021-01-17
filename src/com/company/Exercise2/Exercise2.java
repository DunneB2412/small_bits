package com.company.Exercise2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * Q1): put in the post conditions of corrosponding functions
 * Q2):a
 * function 1) O(log(n)): as it only cycles up to the square rute of n n being the value of input number
 * function 2) O(n): rintime of access to araylists is O(n), dont know after that thb
 * function 3) O(n*log(k)): for all values k in array list of size n there is a function (function 1) 
 *                          applied to k with runtime of log(k)
 * function 4) O(n): cycles through every eliment in an arraylist of size n and checks that each element is in order 
 *                   against the next one
 *
 * Q2):b
 * 1):
 * phase 1) {3, 28, -5, 6, 12, 3} -> {28, 3, -5, 6, 12, 3} -> {28, 12, -5, 6, 3, 3} -> {28, 12, 3, 6, 3, -5}. heap built
 * i included with my submition a bitmap which shows phase 1 in the tree graph form as requested
 * phase 2) {-5, 12, 3, 6, 3, 28} -> {12, -5, 3, 6, 3, 28} -> {12, 6, 3, -5, 3, 28}. heap mended
 *          {3, 6, 3, -5, 12, 28} -> {6, 3, 3, -5, 12, 28}. heap mended
 *          {-5, 3, 3, 6, 12, 28} -> {3, 3, -5, 6, 12, 28}. heap mended
 *          {-5, 3, 3, 6, 12, 28} -> {3, -5, 3, 6, 12, 28}. heap mended
 *          {-5, 3, 3, 6, 12, 28}. heap mended
 *          {-5, 3, 3, 6, 12, 28}. finished
 *          
 * 2)
 * pushUp) O(log(n)) physically similar to a swap function and there is no loops
 * buildHeap) O(n*log(n)) must check for each element if it is in the right place where there are n elements
 * pushDown) O(log()) there in no need for loops in this function
 * pickHeap) O(n*log(n)) for every element it solects the top of the heap and then mends it
 *           where mending it has a maximum expence of log(n).
 * heapSort) O(n*log(n)) as constants are neglected the fact that 2 functions of O(n*log(n)) are callde dosn't change
 *           the fact that this function is O(n*log(n))
 */
public class Exercise2 //brian 1016603 dimitar
{
  public static void main(String [] arguments) throws IOException
  {
    ArrayList<Character> source=new ArrayList<Character>();
    ArrayList<Character> destination=new ArrayList<Character>();
    readFromFile(source,"AliceSorted.txt");
    int numberOfComparisons=removeSortedDuplicates(source,destination);
    System.out.printf("Source: %s\n",source);
    System.out.printf("Destination: %s\n",destination);
    System.out.printf("%d comparisons made\n",numberOfComparisons);
  }
  
  /**
   * Copies Characters from source array to destination array, without duplicates
   * @param source
   * @param destination
   * @return number of comparisons made
   *  O(n^2) for every element in the array a function of run time complexity O(n) is called
   *  this function contains a while loop of n iterations within for loop of n ittirations making n*n or n^2
   *  where n is the length of the array list
   */
    private static int removeDuplicates(ArrayList<Character> source, ArrayList<Character> destination)
    {
      assert source!=null : "Source array should be initialized";
      assert destination!=null : "Destination array should be initialized";
      int numberOfComparisons=0;
      for(int i=0;i<source.size();i++) 
        numberOfComparisons+=addWithoutDuplicates(source.get(i),destination);
  	/*
  	 * numberOfComparasone++;
  	if (!(destination.contains(source.get(i))))
  		destination.add(source.get(i))
  	*/  	
  	// replaces addWithoutDuplicates function?
      return numberOfComparisons;
    }
/**
 * Copies Characters from source array to destination array, without duplicates
 * @param source
 * @param destination
 * @return number of comparisons made
 * O(n) compareing current to last character tharefore only runs n times 
 * by asumption all same character apear on one block and when past the block 
 * don't need to be thought of
 * n being length of array list
 */
  private static int removeSortedDuplicates(ArrayList<Character> source, ArrayList<Character> destination)
  {
    assert source!=null : "Source array should be initialized";
    assert destination!=null : "Destination array should be initialized";
    int numberOfComparisons=0;
    destination.add(source.get(0));
    for(int i=1;i<source.size();i++)
    {                               
    	numberOfComparisons++;
    	if (source.get(i)!=destination.get(destination.size()-1)&& source.get(i)!='\n')
    		destination.add(source.get(i));
    }
    return numberOfComparisons;
  }

  /**
   * Copy a character to the destination array, without duplicates
   * @param character
   * @param destination
   * @return number of comparisons made
   *  O(n) while loop is dependent on n with up to n itirations
   *  n is length of array list
   */
  private static int addWithoutDuplicates(Character character, ArrayList<Character> destination)
  {
    assert destination!=null : "Destination array should be initialized";
    int i=0;
    while(i<destination.size() && destination.get(i)!=character)//O(n)
      i++;
    if(i==destination.size())
      destination.add(character);
    return i;
  }
  
  /**
   * Fills the source character array with the contents of the specified string
   * @param source
   * @param string
   */
  private static void readFromFile(ArrayList<Character> source, String file)
  {
    assert source!=null : "Source array should be initialized";
    try 
    {
    	InputStreamReader reader= new InputStreamReader(new FileInputStream(file));
    	int c;
    	while((c=reader.read())>=0)
    	{
    		source.add((char)c);
    	}
    	reader.close();
    	System.out.println(source.size()+" characters found.");
    }
    catch(IOException e)
    {
    	System.out.println("something went wrong in reading "+file+".");
    }
  }
  
}
