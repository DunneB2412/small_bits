package nl.ru.ai.p4ai.exercise1;

import static nl.ru.ai.karel.Karel.*;

public class Church // brian s1016603, stijn s1019256
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
