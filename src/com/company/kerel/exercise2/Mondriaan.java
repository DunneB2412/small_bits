package com.company.kerel.exercise2;

import static com.company.KarelRevisited.src.nl.ru.ai.karel.Karel.*;// brian 1016603 stijn 1019256

public class Mondriaan
{
	private static void placeBall()
	{
		if (!onBall())
			putBall();
			
	} 
	private static void crossScreen()
	{
		speed(1);
		turn180();
		while (!inFrontOfWall())
		{
			step();
		}
		turn180();
		speed(10);
	}
	private static void turn180()
	{
		turnRight();
		turnRight();
	}

	private static void stepsNPlace (int noOfSteps, boolean ball)
	{
		while (noOfSteps > 0)
		{
			if(ball == true)
			{
				placeBall();
			}
			if (inFrontOfWall())
			{
				crossScreen();
			}
			else
				step();		
			noOfSteps--;
		}
	}
	private static void rectangle (int width,int hight)
	{
		stepsNPlace(width,true);
		turnLeft();
		stepsNPlace(hight,true);
		turnLeft();
		stepsNPlace(width,true);
		turnLeft();
		stepsNPlace(hight,true);
		turnLeft();
	}
	private static void moovToRandom (int distanceX, int distanceY)
	{
		turnRight();
		stepsNPlace(distanceX,false);
		turnLeft();
		stepsNPlace(distanceY,false);
		turnRight();
	}
	private static void thisIsArt (int noOfRectangles)
	{
		while (noOfRectangles > 0)
		{
			moovToRandom(random(1,10),random(1,10));
			rectangle(random(1,10),random(1,10));
			noOfRectangles--;
		}
	}
	
  public static void main(String[] args)
  {
    speed(10);
    thisIsArt (random(8,16));
 
     // your code here 
  }
}
