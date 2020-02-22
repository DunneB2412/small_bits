package nl.ru.ai.p4ai.exercise1;

import static nl.ru.ai.karel.Karel.*;

public class Sowing // brian s1016603, stijn s1019256
{
	private static void fillLine()
	  {
		  while( !onBall() && !inFrontOfWall())
		  {
			  putBall();
			  step();
	  		
		  }
		  turn();
	  }
	private static void turn()
	  {
		  if ( onBall())
		  { 
			  turnRight();
			  turnRight();
			  step();
			  turnLeft();
			  step();
		  }
		  else
		  {
			  putBall();
			  turnRight();
			  step();
		  }
	      if ( !onBall())
	      {
	    	  fillLine();
	      }
	  }

  public static void main(String[] args)
  {
	  
	  speed(25);	
	  fillLine();
	  
    /* add your code for exercise 1.2 here */
  }

}


