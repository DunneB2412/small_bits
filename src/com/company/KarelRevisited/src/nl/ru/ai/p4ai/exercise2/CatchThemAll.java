package nl.ru.ai.p4ai.exercise2;

import static nl.ru.ai.karel.Karel.*;// brian 1016603 stijn 1019256

public class CatchThemAll
{
	private static void turn180()
	{
		turnRight();
		turnRight();
	}
	private static void turnAround()
	{
		if(north())
		{
			turnRight();
			if (!inFrontOfWall())
			{
				step();
				turnRight();
			}	
		}
		else
		{
			
			turnLeft();
			if (!inFrontOfWall())
			{
				step();
				turnLeft();
			}	
			
		}
	}
	private static void runToWall()
	{
		while(!inFrontOfWall())
		{
			stepNLook();
		}
	}
	private static void stepNLook()
	{
		if(onBall())
		{
			getBall();
		}
		if(onPokemon()&&getNumberOfBalls()>0 )
		{
			getPokemon();
		}
		step();
	}
	private static void getThemAll()
	{ 
		while (!inFrontOfWall())
		{
			runToWall();
			turnAround();
			
		}
	}
	private static void resetKarel()
	{
	    turn180();
	    runToWall();
	    turnRight();
	}

  public static void main(String[] args)
  {
    map("pokemap.map");
    speed(10);
    while (getNumberOfPokemon()<5)
    {
    	getThemAll();
    	resetKarel();
    }
   
    
    // Put your code here
  }

}
