package com.company.kerel.exercise1;

import static com.company.kerel.karel.Karel.*;

public class Church
{
	private static void runNLook()
	{
		turnRight();
		while (inFrontOfWall())
			turnLeft();
		step();		
	}
	private static void setup()
	{ 
		while( !inFrontOfWall())
			step();
		putBall();
		turnLeft();
		step();
	}

  public static void main(String[] args)
  {
    map("church.map");
    speed(25);
    setup();
    while (!onBall())
    	runNLook();
    putBall();
    
    /* Add your code for exercise 1.3 here */
  }

}
