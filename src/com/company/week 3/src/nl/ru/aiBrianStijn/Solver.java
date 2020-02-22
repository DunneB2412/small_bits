package nl.ru.aiBrianStijn;

import java.util.Scanner; // brian 1016603 stign 

public class Solver // brian 1016603 stign 
{
	private static Scanner scanner;

	private static void xValue (double a, double b, double c,double D)
	{
		if (D==0)
		{
			double top = (-b + Math.sqrt(D));
			System.out.print("x="+top/(a*2)+"");
		}
		else
		{
			double top = (-b + Math.sqrt(D));
			System.out.print("x="+top/(a*2)+" ");

			top = (-b - Math.sqrt(D));
			System.out.print("and x="+top/(a*2)+"");
		}

	}
	
	public static void main(String[] args) 
	{
	

		scanner = new Scanner(System.in);

		System.out.println("give values of a,b,c for a function ax^2+bx+c.");
		System.out.print("a=");
		double a= scanner.nextDouble();
		while (a==0)
		{
			System.out.println("a cannot be equal to 0 plz guiv a different value for a.");
			System.out.print("a=");
			a= scanner.nextDouble();
		}	
		
		System.out.print("b=");
		double b = scanner.nextDouble();
		System.out.print("c=");
		double c = scanner.nextDouble();
		
		
		double D= (b*b-4*a*c);
		if (D>=0)
		{
			xValue(a,b,c,D);
		}
		else
			System.out.print("there are no real roots");
		
		
		// TODO Auto-generated method stub
 
	}

}
