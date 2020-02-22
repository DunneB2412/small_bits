package labSesionOneTCD;

import java.util.Scanner;

public class statisticsMain {
	
static final int SIZE = 3;
	public static void main(String[] args) {
		double[] elements = new double[SIZE];
		assignElements(elements);
		System.out.println("your avrage is " + getAvrage(elements) + ".");
		System.out.println("your standard deviation is "+ getStandardDeviation(elements) + ".");
		
		// TODO Auto-generated method stub

	}
	private static double getStandardDeviation(double[] elements) {
		double differenceSum = 0;
		double avrage = getAvrage(elements);
		for(int index = 0; index < SIZE; index ++)
			differenceSum += ((elements[index]-avrage)*(elements[index]-avrage));
		
		
		return Math.sqrt(differenceSum/SIZE);
	}
	private static double getAvrage(double[] elements) {
		double sum = 0;
		for(int index = 0; index < SIZE; index ++)
			sum += elements[index];
		
		return sum/SIZE;
	}
	private static void assignElements(double[] elements) {
		Scanner scanner = new  Scanner(System.in);
		for(int index = 0; index < SIZE; index++) {
			System.out.print("give a value for element " + (index+1) + " >");
			elements[index] = scanner.nextDouble();	
		}
		scanner.close();	
	}
	

}
