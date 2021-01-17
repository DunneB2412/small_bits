package com.company.week6errorhandlingandnestedloops;

import java.util.Random;
import java.util.Scanner;

/* SELF ASSESSMENT
1. Did I use appropriate CONSTANTS instead of numbers within the code?
    Mark out of 5:
    Comment:5
2. Did I use easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE?
    Mark out of 5:
    Comment:5
3. Did I use easy-to-understand meaningful variable names?
    Mark out of 10:
    Comment:10 
4. Did I format the variable names properly (in lowerCamelCase)?
    Mark out of 5:
    Comment:5 
5. Did I indent the code appropriately?
    Mark out of 10:
    Comment:10 
6. Did I use an appropriate loop to allow the user to enter their guesses until they win or lose?
    Mark out of 20: 
    Comment: 19
7. Did I check the input to ensure that invalid input was handled appropriately?
    Mark out of 10: 
    Comment: 10
8. Did I generate the cards properly using random number generation (assuming all cards are equally likely each time)?
    Mark out of 10: 
    Comment: 10
9. Did I output the cards correctly as 2, 3, 4, ... 9, 10, Jack, Queen, King?
    Mark out of 10: 
    Comment: 10
10. Did I report whether the user won or lost the game before the program finished?
    Mark out of 10:
    Comment: 10
11. How well did I complete this self-assessment?
    Mark out of 5:
    Comment: 5
Total Mark out of 100 (Add all the previous marks):99
*/

public class HiLoCardGame {
	private static final boolean KEN_LIKES_BRAKES = false;
	private static final boolean BRIAN_HAS_A_SOLUTION = true;
	private static final boolean IS_GOOD_GAME = false;
	private static final int STARTING_LIVES = 10;
	private static final String EXIT_TOKEN = "quit";
	
	private static final int ACE = 1;
	private static final int JACK = 11;
	private static final int QUEEN = 12;
	private static final int KING = 13;
	
	public static void main(String[] args) {
		Scanner systemIn = new Scanner(System.in);
		String input;
		do {
			System.out.print("Do you want to play the hi/lo card game? 'play' to play:" );
			input = systemIn.nextLine().toLowerCase();
			if(input.toLowerCase().equals("play")) {
				System.out.println("you "+(playCardGame(new Random(), systemIn)? "win":"lose")+".");
			}
		}while(!input.equals(EXIT_TOKEN));
		
		systemIn.close();
		System.out.println("good bie");

	}
	

	private static boolean playCardGame(Random random, Scanner systemIn) {
		int score = 0;
		int lives = STARTING_LIVES;
		int openingCardNumber = random.nextInt(KING)+1;
		String input;
		do {
			String cardString = carfNumberToString(openingCardNumber);
			System.out.println("The card is a"+(cardString.equals("ace")? "n ":" ")+cardString+",");
			System.out.println("you guessed correctly "+score+" times in a row and you have "+lives+" lives left.");
			System.out.print("Do you think the next card will be higher, lower or equal?");
			do {
				input = systemIn.nextLine().toLowerCase();
			}while(!(input.equals("higher")||input.equals("lower")||input.equals("equal")||input.equals(EXIT_TOKEN)));
			int nextCardNumber = random.nextInt(KING)+1;
			if((nextCardNumber>openingCardNumber&&input.equals("higher"))
					||(nextCardNumber<openingCardNumber&&input.equals("lower"))
					||(nextCardNumber==openingCardNumber&&input.equals("equal"))) 
			{
				score++;
			}
			else {
				if(IS_GOOD_GAME) {
					lives--;	    
					score = 0;
				}
				else {
					if(KEN_LIKES_BRAKES) {
						break;
					}
					else if(BRIAN_HAS_A_SOLUTION) {
						return false; // this is my solution
					}
					else {
						System.out.print("Oof, rip break");
						System.out.println("ps, you lost");
						System.exit(1);
					}
				}
			}
			openingCardNumber = nextCardNumber;
			
		}while(score<4&&lives>0&&!input.equals(EXIT_TOKEN));
		return score>=4;
	}
	
	private static String carfNumberToString(int cardNumber) {
		switch(cardNumber){
		case(ACE):
			return "ace";
		case(JACK):
			return "jack";
		case(QUEEN):
			return "queen";
		case(KING):
			return "king";
		default:
			return cardNumber+"";
		}
	}
}