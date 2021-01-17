package com.company.Week5;

import java.util.ArrayList;
import java.util.Scanner;

public class tutorial {

	public static void main(String[] args) {
		System.out.println("hive a list of numbers seperated by ','.");
		Scanner scanner = new Scanner(System.in);
		String numbers = scanner.nextLine();
		scanner = new Scanner(numbers);
		scanner.useDelimiter(",");
		
		ArrayList<Integer> numbersList = new ArrayList<>();
		while (scanner.hasNext()) {
			numbersList.add(Integer.parseInt(scanner.next()));
		}
		scanner.close();
		
		int min = (int)Double.POSITIVE_INFINITY;
		int max = (int)Double.NEGATIVE_INFINITY;
		for(int number: numbersList) {
			min = (number<min)? number: min;
			max = (number>max)? number: max;
		}
		
		System.out.println("oyur min ="+ min + ", your max ="+ max +".");
		
		System.out.print("to what accuracy do you want to se PI? use 0 to never stop! >");
		scanner = new Scanner(System.in);
		int limit = scanner.nextInt();
		scanner.close();
		
		int pos = 1;
		System.out.print("3.");
		while(pos!=limit) {
			Double a = 2.0+(2*(pos-1));
			System.out.print(((4/(a*a+1*a+2))+"").charAt(2));
			pos++;
		}
		
	}

}
