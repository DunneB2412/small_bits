package com.company.rootThing.src.rootThing;

import java.util.Scanner;

public class Root {

	public static void main(String[] args) {
		System.out.println("give a number");
		Scanner scanner = new Scanner(System.in);
		double input = scanner.nextDouble();
		scanner.close();
		int max = (int) (Math.sqrt(input));
		for(int i=1;i<=max;i++) {
			System.out.println(i);
		}

	}

}
