package nl.ru.aiBrianStijn;

import java.util.Scanner;

public class NewtonRaphson {
	private static Scanner scanner; // brian 1016603 stign 
	
	private static void calculate(double a)
	{
		if (a==0)
		{
			System.out.println("root of "+a+"= 0" );
		}
		else
		{
			double x=1;
			while ((x+0.001)-x-((x*x-a)/2*x)>0.001)
			{
				x=x+0.001;
			}
			System.out.println("root of "+a+" = "+x+"" );
		}
	}

	public static void main(String[] args) {		
		scanner = new Scanner(System.in);
		System.out.println("guiv a value for a which you want an approximate root of.");
		System.out.print("a=");		
		calculate(scanner.nextDouble());
		
		
		// TODO Auto-generated method stub

	}

}
