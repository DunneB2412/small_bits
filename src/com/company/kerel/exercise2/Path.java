package com.company.kerel.exercise2;

import static com.company.kerel.karel.Karel.*;

public class Path
{
	private static void turn180()
	{
		turnRight();
		turnRight();
	}
	
	private static void followBals()
	{
		while (onBall()&& !inFrontOfWall())
		{
			getBall();
			step();
		}
		if(onBall())
		{
			getBall();
			
		}
		else
		{
			turn180();
			step();
		}
	}
	
	private static void searchForPath(int trials)
	{
		while (trials>0)
		{
			turnRight();
			if(inFrontOfWall())
			{
				turnRight();
				trials--;
			}
			else
			{
				step();
			if (!onBall())
			{
				turn180();
				step();
				turn180();
				trials--;
			}
			else
				trials=0;
			}
		}
	}
	
  public static void main(String[] args)
  {
    map("path.map");
    speed(100);
    while(onBall())
    {
    	followBals();
    	searchForPath(4);
    }
    
    // your code here
  }
}
