package com.company.kerel.exercise1;

import static com.company.kerel.karel.Karel.*;

public class Boat
{
	static void placeBall()
	{
		if (!onBall())
			putBall();
			
	}
	static void turn180()
	{
		turnRight();
		turnRight();
	}
	private static void stepsx3()
	{
		step();
		step();
		step();
	}
	static void angleStep()
	{
		turnLeft();
		step();
		turnRight();
		step();
		placeBall();
	}
	static void angleLine3()
	{
		angleStep();
		angleStep();
		angleStep();
	}
	static void angleLine2()
	{
		angleStep();
		angleStep();

	}
	static void straightLine6()
	{
		placeBall();
		step();
		placeBall();
		step();
		placeBall();
		step();
		placeBall();
		step();
		placeBall();
		step();
		placeBall();
		
	}
	private static void paintHull()
	{
		straightLine6();
		straightLine6();
		turn180();
		angleLine2();
		step();
		straightLine6();
		turnRight();
		angleLine2();
	}
	private static void paintSail()
	{
		turnRight();
		angleLine2();
		angleLine3();
		turnRight();
		angleLine2();
		angleLine3();
		turnRight();
		straightLine6();
		turnRight();
		straightLine6();
	}

  public static void main(String[] args)
  {
	  speed(25);
	  stepsx3();
	  turnRight();
	  stepsx3();
	  paintHull();
	  paintSail();
	  
	    
    /* Add your code for exercise 1.1 here.*/
  }

  
  
}
