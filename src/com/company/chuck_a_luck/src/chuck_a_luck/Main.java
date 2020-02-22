package chuck_a_luck;

import java.util.Scanner;

/* SELF ASSESSMENT 

1. ResolveBet

I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7:6 ].
Comment: i have a resolve bet but it is implimented by bet and since bet also containes the prediction the only information left that it needs is 
the dice outcome
My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8:8 ].
Comment: yes it dose, it even has a name
My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5:5 ].
Comment: yes, compatible with splition goyu bet aswell
My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15: 15]..
Comment:i don't use your dice mainly beause i didn't see them but it roles and stores it's most recent role
My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20: 20 ].
Comment: it compairs enums but prity much dose that
My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10: 10 ].
Comment: i added the telling the user just for you

2. Main

I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15: 13]##################
Comment: it's a little dirty but it's there.
My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5:5 ]
Comment:yes
I ask the user to enter any of the four bet types or quit [Mark out of 5:5 ].
Comment:yes
My program calls resolveBet for each bet type entered [Mark out of 5: 5].
Comment: you can add multiple bets but they are all resolved
At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5: 5]###########################
Comment: got a basic log

 Total Mark out of 100 (Add all the previous marks):
*/

public class Main {
	private static final String EXIT_TOKEN = "quit";
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Dice[] tDice = new Dice[] {new Dice(6), new Dice(6), new Dice(6)};
		
		System.out.println("What is your player name");
		String name = scanner.next();
		do {
			scanner.nextLine();
			System.out.println("how much cash do you  want to play with");
		}while(!scanner.hasNextFloat());
		Player tPlayer = new Player(name,scanner.nextFloat());
		String input = (scanner.hasNextLine())? scanner.nextLine(): "";
        
		do{
			for(Dice d: tDice) {
				d.role();
			}
			RoleType[] role = RoleType.getroleType(tDice);
			
			System.out.println(tPlayer);
        	System.out.print("Enter amount to bet followed by what you bet will happen \nfrom{triple, field, high, low, no_return}, or "+EXIT_TOKEN+" to stop, redy to reveal:");
			input = scanner.nextLine().toLowerCase();
			if (!input.equals(EXIT_TOKEN)){
				if(input.equals("redy")) {
					tPlayer.setBalance(role);
					System.out.println("\n"+tPlayer);
					for(RoleType r: role) {
						System.out.print(r+"");
					}
					System.out.println("---------------------------\n");
					if(scanner.hasNextLine()) scanner.nextLine();
				}
				else {
	                try {
	                	Scanner getparts = new Scanner(input);
	                	float betVal = 0.0f;
	                	if(getparts.hasNextFloat()) {
	                		betVal = getparts.nextFloat();
	                	}
	                	else {
	                		betVal = tPlayer.getBalance();
	                	}
	                	if(getparts.hasNext()) {
	                		String roleType = getparts.nextLine().toUpperCase().replaceAll("\\s", "");
	            			tPlayer.placeBet(betVal, RoleType.valueOf(roleType));
	                	}
	                	else {
	            			tPlayer.placeBet(betVal, RoleType.valueOf("NO_RETURN"));
	                	}
	                	getparts.close();
	                } catch (Exception e) {
	                    System.out.println("Something Went wrong: "+e.getLocalizedMessage());
	                }
				}	
			}
		}while(!input.equals(EXIT_TOKEN)&&tPlayer.getBalance()>0);
        scanner.close();
        System.out.println("\nyour final state"+tPlayer);
        System.out.println((tPlayer.log.equals(""))?"you did nothing and left": tPlayer.log);
        System.out.println("good bie");

	}

}
