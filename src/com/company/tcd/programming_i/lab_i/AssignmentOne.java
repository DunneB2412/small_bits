package com.company.tcd.programming_i.lab_i;

import java.util.Scanner;

public class AssignmentOne {

	public static void main(String[] args) {
		System.out.println("what is you'r name?");
		System.out.print(">>>");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		scanner.nextLine();
		System.out.println("Hello "+ name + " nice to meet you.");
		
		
		System.out.println("what is your height in meters?");
		System.out.print(">>>");
		double height = scanner.nextDouble();
		System.out.println("what is your weight in kilos?");
		System.out.print(">>>");
		double weight = scanner.nextDouble();
		double bMI = weight/(height*height);
		System.out.println("yor BMI is " + bMI);
		scanner.close();
	}

}
