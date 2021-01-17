package com.company.Exercise4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Exercise4//brian, dimitar
{
	private static int nrOfFails=0;
	private static int nrOfPrunes=0;
  
  public static void main(String[] args)
  {
	int[] money0= {};
	showSolutions(0,money0,0,true);//pre pruning 0 fails: no elements to fail on
                     //post pruning 0 fails: same
    
    int[] money1= { 2, 2, 2, 5, 10, 10, 20 };
    showSolutions(1,money1,0,true);;//pre pruning 8 fails: 1 for every element and 1 at the end
                      //post pruning 0 fails: instantly prunes everything
    
    int[] money2= { 20, 10, 10, 5, 2, 2, 2 };
    showSolutions(42,money2,0,true);//pre pruning 114 fails: many are to much
                      //post pruning 2 fails: in the case thins can't add up it stops, and in the case the smallest is to big it stops
    
    int[] money3= { 20, 50, 1000, 1000, 2000 };
    showSolutions(2021,money3,0,true);//pre pruning 30 fails: similar to above but not as extreme
                        //post pruning 0 fails: in the case thins can't add up it stops, and in the case the smallest is to big it stops
    // in pruning it cuts of entire branches while fails only caters for the local non possible solution
    
    int[] people= {15, 24, 32, 40, 50, 60, 72, 80, 90};
    int s=0;
    int tolerance=0;
    while (s<1)
    {
    	s=showSolutions(250,people,tolerance,false);
    	tolerance++;
    }
    
    readSudoku("com.company.fr_recursion.test.txt");
    
  }
  /**
   * Returns the number of ways of creating specified target value as a sum of money starting with c
   * @param array
   * @param c
   * @param target
   * @param s cash for solutions
   * @param solutionLim the limit of the sise of solution array list
   * @param tolerance the amount the solution can be away from a perfect solution
   * @return number of solutions
   */
  private static int solutions(int[] array, int c, int target, ArrayList<Integer> s, int solutionLim, int tolerance)
  {
    assert array!=null : "array should be initialized";
    assert c>=0&&c<=array.length;
    if(target>=0&&target<=tolerance)
    {
      System.out.println(s+" with "+target+" left.");
      return 1;
    }
    if(c>=array.length||target<0||s.size()>=solutionLim)
    {
      nrOfFails++;
      return 0;
    }
    if (sum(array,c)<target||smallest(array,c)>target)
    {
    	nrOfPrunes++;
    	return 0;
    }
    s.add(array[c]);
    int with=solutions(array,c+1,target-array[c],s,solutionLim,tolerance);
    s.remove(s.size()-1);
    int without=solutions(array,c+1,target,s,solutionLim,tolerance);
    return with+without;
  }
  /**
   * finds the smallest value at or after index
   * @param intArray
   * @param index
   * @return
   */
  private static int smallest(int[] intArray, int index)
  {
	  assert (intArray!=null):"array should be initalised";
	  assert (index>=0&&index<intArray.length):"index should be within array bounds";
	  assert (intArray.length!=0): "array should have an element to find";
	  int smallest=intArray[index];
	  for( ;index<intArray.length;index++)
		  if(intArray[index]<smallest)
			  smallest=intArray[index];
	  return smallest;
  }
  /**
   * gets the sum of all elements at and past index
   * @param intArray
   * @param index
   * @return
   */
  private static int sum(int[] intArray, int index)
  {
	  assert (intArray!=null):"array should be initalised";
	  assert (index>=0&&index<intArray.length):"index should be within array bounds";
	  int sum=0;
	  for( ;index<intArray.length;index++)
		  sum+=intArray[index];
	  return sum;
  }
  /**
   * calls solutions function and displays imformation
   * @param target target value
   * @param array objects to solect from
   * @param tolerance amount a solution can deviate from a perfect solution
   * @param alwaysPrint if the functn should print results all the time
   * @return nr of solutions found
   */
  private static int showSolutions(int target, int[] array, int tolerance, boolean alwaysPrint)
  {
	  assert (array!=null):"array must be initalised";
	  ArrayList<Integer> s= new ArrayList<Integer>();
	  int nrSolutions=solutions(array,0,target,s,array.length,tolerance);
	  if(nrSolutions>0||alwaysPrint)
	  {
		  System.out.println("solutions:"+nrSolutions);
		  System.out.println("fails:"+nrOfFails);
		  System.out.println("prunes:"+nrOfPrunes);
		  System.out.println("===========================");
	  }
	  nrOfFails=0;
	  nrOfPrunes=0;
	  return nrSolutions;
  }
  /**
   * reads a file and constructs a map based on the file
   * @param fileName; the name of the target file
   * @return an array of live or dead files
   */
  static int[][] readSudoku(String fileName)
  {	 
	assert (fileName!=null):"there is no configureation";
	try 
	{	
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		int y=0;
		int configuration[][]= new int[9][9];
		while((line=reader.readLine())!=null)
		{
			if (line.length()!=9)
			{
				reader.close();
				throw new IllegalArgumentException("there is a line that is not 9");				
			}
			for (int x=0;x<line.length();x++ )
			{
				if (line.charAt(x)==' ')
					configuration[x][y]=0;
				else
					configuration[x][y]=Integer.parseInt(line.charAt(x)+"");
			}
			y++;
		}
		if (y!=9)
		{
			reader.close();
			throw new IllegalArgumentException("grid must be 9 tall");				
		}
	    if(isValid(configuration))
	    	System.out.println("input puzzle is valad");
		reader.close();
		
		solve(configuration);
		return configuration;
	}
	catch (IOException e) 
	{
		throw new IllegalArgumentException("something went wrong when reading the map "+fileName+".");
	}
  }
  /**
   * prints into the console the 2*2 array
   * @param array array to be printed
   */
  private static void show2x2Array(int[][] array)
  {
	  assert (array!=null):"array should be initalised";
	  for (int y=0;y<array.length;y++)
	  {
		  for (int x=0;x<array[y].length;x++)
		  {
			  System.out.print(array[x][y]+" ");
		  }
		  System.out.println("");
	  }
  }
  /**
   * solves the puzzle
   * @param puzzle puzzle being solved
   * @return if it can be solved
   */
  private static boolean solve(int[][] puzzle)
  { 
	  assert (puzzle!=null):"array should be initalised";
	  Cord cord=new Cord(9,9);
	  if(!findBestFreePosition(puzzle,cord)&&isValid(puzzle))
	  {
		  show2x2Array(puzzle);
		  return true;
	  }  
	  else
	  {
		  for(int digit=1;digit<=9;digit++)
		  {
			  puzzle[cord.x][cord.y]=digit;// prepare
			  if(isValid(puzzle))
				   if(solve(puzzle))// recurse
					   return true;
			  puzzle[cord.x][cord.y]=0;// repair
		  }
	  }
	  return false;
  }
  /**
   * uses uristics to find the best free position
   * @param puzzle puzzle being looked at
   * @param cord cord being minipulated
   * @return if there is a cord or not
   */
  private static boolean findBestFreePosition(int[][] puzzle,Cord cord)
  {
	  assert (puzzle!=null):"array should be initalised";
	  int constraints=0;
	  for(int y=0; y<9;y++)
	  {
		  for(int x=0;x<9;x++)
		  {
			  if(puzzle[x][y]==0&&countConstraints(puzzle, new Cord(x,y))>constraints)
			  {
				  cord.x=x;
				  cord.y=y;
				  //return true; 
			  }
		  }
	  }
	  if (cord.x==9&&cord.y==9)
		  return false;
	  return true;
  }
  /**
   * checks if the entire puzzle is currently valid
   * @param puzzle puzzle being checked
   * @return if it is valid or not
   */
  private static boolean isValid(int[][] puzzle) 
  {
	  assert (puzzle!=null):"array should be initalised";
	  for (int e=0;e<81;e++)
	  {
		  int x=(e/9);
		  int y=(e%9);
		  int target=puzzle[x][y];
		  if (target!=0)
		  {
			  for(int i=0;i<9;i++)
			  {
				  int subx=(i/3)+((x/3)*3);
				  int suby=(i%3)+((y/3)*3);
				  if(i!=x&&puzzle[i][y]==target)
					  return false;
				  if(i!=y&&puzzle[x][i]==target)
					  return false;
				  if((subx!=x&&suby!=y)&&(puzzle[subx][suby]==target))
					  return false;
			  }  
		  }
	  }
	  return true;
  }
  /**
   * counts the constraints that apply to a location
   * @param puzzle pussle being looked at
   * @param cord co-ordinate being checked
   * @return numberr of constraints
   */
  private static int countConstraints(int[][] puzzle, Cord cord)
  {
	  assert (puzzle!=null):"array should be initalised";
	  assert (cord!=null):"cord scould be initalised";
	  int counter=0;
	  for(int i=0;i<9;i++)
	  {
		  int subx=(i/3)+((cord.x/3)*3);
		  int suby=(i%3)+((cord.y/3)*3);
		  if(i!=cord.x&&puzzle[i][cord.y]!=0)
			  counter++;
		  if(i!=cord.y&&puzzle[cord.x][i]!=0)
			  counter++;
		  if((subx!=cord.x&&suby!=cord.y)&&(puzzle[subx][suby]!=0))
			  counter++;
	  }
	  return counter;
  }
}
