package lab2;

import java.util.Scanner;

public class Tutorial2 {

	public static void main(String[] args) {
		System.out.print("do you have fins?");
		Scanner scanner = new Scanner(System.in);
		if (scanner.next().equals("yes")) {
			show("fish");
		}
		
		else {
			System.out.print("Do you have warm blood");
			if (scanner.next().equals("yes")) {
				System.out.print("do you have feathers?");
				if (scanner.next().equals("yes")) {
					show("bird");
				}
				else {
					show("mammal");
				}
			}
			else {
				System.out.print("do you have scales?");
				if (scanner.next().equals("yes")) {
					show("reptile");
				}
				else {
					show("amphibian");
				}
			}
		}
		scanner.close();

	}

	private static void show(String string) {
		System.out.println("You are a "+ string + ".");
	}

}
