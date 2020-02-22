package examples;

import java.util.Scanner;

public class Examples {
	
	static final Scanner SCANNER_IO = new Scanner(System.in);
	public static void main(String[] args) {
		Scanner doubleFinder;
		do {
			System.out.print("give a double:");
			String input = SCANNER_IO.nextLine();
			doubleFinder = new Scanner(input);
		}while(!doubleFinder.hasNextDouble());
		Double number = doubleFinder.nextDouble();
		doubleFinder.close();
		System.out.println(number);
		
	}

}
