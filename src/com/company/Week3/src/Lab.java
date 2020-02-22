import java.util.Random;
import java.util.Scanner;

public class Lab {
	/* SELF ASSESSMENT
	   1. Did I use appropriate easy-to-understand, meaningful variables and CONSTANTS within the code? 
	       Mark out of 10:
	       Comment:9
	   2. Did I format the variable and CONSTANT names appropriate (in lowerCamelCase and UPPERCASE)?
	       Mark out of 5:
	       Comment:5
	   3. Did I generate the computer's choice in each game correctly using a Random number generator?
	       Mark out of 10:
	       Comment:10
	   4. Did I input the user's choice in each game correctly?
	       Mark out of 10:
	       Comment:10
	   5. Did I correctly compare the choices and update the score appropriately?
	       Mark out of 20:
	       Comment:20
	   6. Did I inform the user of who won each game (and why) correctly?
	       Mark out of 10:
	       Comment:10
	   7. Did I use an appropriate for loop to allow the player to play 5 games?  There should be only one loop.
	       Mark out of 20: 
	       Comment:20
	   8. Did I output the final scores correctly after the 5 games were played. 
	       Mark out of 10: 
	       Comment:10
	   9. How well did I complete this self-assessment?
	       Mark out of 5:
	       Comment:5
	   Total Mark out of 100 (Add all the previous marks):99
	*/
	
	
	static final int ROCK = 1;
	static final int PAPER = 2;
	static final int SISSORS = 3;
	
	static final String WIN = "win";
	static final String LOSE = "lose";
	static final String DRAW = "draw";
	static final String INVALID_CHOIVE = "forfited";
	
	static final Random random = new Random();
	public static void main(String[] args) {
		System.out.print("How many times do you want to play 'rock pater sissors'. >");
		Scanner scannerIn = new Scanner(System.in);
		int number = scannerIn.nextInt();
		int palyerScore = 0;
		int computerScore = 0;
		for( ;number>0;number--) {
			System.out.print("Enter 1 (for Rock) or 2 (for Paper) or 3 (for Scissors). >");
			int playerChoice = scannerIn.nextInt();
			int computerChoice = (random.nextInt()%3)+1;
			String outcome = playerWins(playerChoice, computerChoice);
			if(outcome.equals(WIN)) {
				palyerScore++;
			}
			if(outcome.equals(LOSE) || outcome.equals(INVALID_CHOIVE)) {
				computerScore++;
			}
			
		}
		scannerIn.close();
		System.out.println("the final score was, Computer: " + computerScore + ", user: " +palyerScore + ".");

	}
	private static String playerWins(int playerChoice, int computerChoice) {
		
		String matchOutcom = "";
		if(playerChoice == computerChoice) {
			System.out.print("This round was a draw");
			matchOutcom = DRAW;
		}
		else {
			if((playerChoice==1 && computerChoice == 2) ||
				(playerChoice==2 && computerChoice == 3) ||
				(playerChoice==3 && computerChoice == 1)) {
			matchOutcom = LOSE;
			}
			else if(playerChoice>0&&playerChoice<=3){
				matchOutcom = WIN;
			}
			else {
				matchOutcom = INVALID_CHOIVE;
			}
			System.out.print("You "+matchOutcom+" this round"+(matchOutcom.equals(INVALID_CHOIVE)? " because you didn't choos a valid input": "")); 
		}
		System.out.println(", I chose "+((computerChoice==1)? "rock":
			(computerChoice==2)? "paper": "sissors")+".");
			
		return matchOutcom;
	}

}
