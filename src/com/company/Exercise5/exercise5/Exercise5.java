package com.company.Exercise5.exercise5;

//import static nl.ru.ai.exercise5.Maze.*;

import java.util.ArrayList;

public class Exercise5//brian 1016603 dimitar 1018291
{
  public static void main(String[] arguments)
  {
    ArrayList<Candidate> candidates=new ArrayList<Candidate>();
    candidates.add(new Candidate(new Attempt(1,7),-1));//start pos
    Maze.visited(1, 7);
    //Maze maze=new Maze();
    //System.out.println(Maze.beenHere[1][1]);
    boolean found=false;
    int c=0;
    while(c<candidates.size() & !found)
    {
    	Candidate getC= candidates.get(c);
    	
    	if (Maze.hasRabbit(getC.attempt.row, getC.attempt.col))
    	{
    		System.out.println("yeeet!!!!!");
    		found=true;
    		showPath(candidates,c);
    	}
    	tryAdd(new Attempt(getC.attempt.row,getC.attempt.col-1),c,candidates);
    	tryAdd(new Attempt(getC.attempt.row,getC.attempt.col+1),c,candidates);
    	tryAdd(new Attempt(getC.attempt.row-1,getC.attempt.col),c,candidates);
    	tryAdd(new Attempt(getC.attempt.row+1,getC.attempt.col),c,candidates);
    	c++;
    
    }
  }
  /**
   * shows the path to the target as co-ordinates
   * @param candidates 
   * @param c index of candidate to look at
   */
  private static void showPath(ArrayList<Candidate> candidates,int c)
  {
	  assert(candidates!=null):"array list shold be initalised";
	  if(c==-1)
		 return;
	  showPath(candidates, candidates.get(c).parentCandidate);
	  System.out.println(candidates.get(c).attempt.row+","+candidates.get(c).attempt.col);		  
  }
  /**
   *  trys to add a new atempt to the array list
   * @param next the new attempt
   * @param c parent of potental new attempt
   * @param candidates the array list of candidates
   */
  private static void tryAdd(Attempt next, int c,ArrayList<Candidate> candidates)
  {
	assert(candidates!=null):"array list shold be initalised";
	assert(next!=null):"there should be a next attempt";
	assert(c<candidates.size()):"c should be a valid index for candidates";
	  if(Maze.hasWall(next.row,next.col)||Maze.hasVisited(next.row,next.col))
		  return;
	  Maze.visited(next.row,next.col);
	  candidates.add(new Candidate(next,c));
  }
}
