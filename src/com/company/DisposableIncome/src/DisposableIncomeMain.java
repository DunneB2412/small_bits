import java.util.Scanner;

public class DisposableIncomeMain {
	
	/*  SELF ASSESSMENT
	   1. Did I use appropriate CONSTANTS instead of numbers within the code?
	       Mark out of 10:   
	       Comment: 10
	   2. Did I use easy-to-understand, meaningful CONSTANT names?
	       Mark out of 5:  
	       Comment: 5
	    3. Did I format the CONSTANT names properly (in UPPERCASE)?
	       Mark out of 5:  
	       Comment: 5
	   4. Did I use easy-to-understand meaningful variable names?
	       Mark out of 10:  
	       Comment: 10
	   5. Did I format the variable names properly (in lowerCamelCase)?
	       Mark out of 10:  
	       Comment: 10
	   6. Did I indent the code appropriately?
	       Mark out of 10:  
	       Comment: 10
	   7. Did I read the input correctly from the user using appropriate question(s)?
	       Mark out of 10:  
	       Comment: 10
	   8. Did I compute the disposable income correctly?
	       Mark out of 10:  
	       Comment: 10
	   9. Did I compute the disposable income percentage correctly?
	       Mark out of 10:  
	       Comment: 10
	   10. Did I output the correct answer in the correct format (as shown in the examples)?
	       Mark out of 10:  
	       Comment: 10
	   11. How well did I complete this self-assessment?
	       Mark out of 10:  
	       Comment: 10
	   Total Mark out of 100 (Add all the previous marks):  
	    100
	*/
	
	/* SELF ASSESSMENT v2
	   1. Did I use easy-to-understand meaningful variable and CONSTANT names? 
	       Mark out of 10:
	       Comment: 10
	   2. Did I format the variable and CONSTANT names properly (in lowerCamelCase and UPPERCASE_WITH_UNDERSCORES)?
	       Mark out of 10:
	       Comment: 10
	   3. Did I indent the code appropriately?
	       Mark out of 10:
	       Comment: 8
	   4. Did I read the input correctly from the user using appropriate questions?
	       Mark out of 15:
	       Comment: 15
	   5. Did I computer the disposable income and disposable income percentage correctly, and output it in the correct format?
	       Mark out of 15:
	       Comment: 15
	  6. Did I use an appropriate series of if statements to generate the income analysis to the user?
	       Mark out of 25:
	       Comment: 14
	   7. Did I provide the correct output for each possibility in an easy to read format?
	       Mark out of 10:
	       Comment: 10
	   8. How well did I complete this self-assessment?
	       Mark out of 5:
	       Comment: 5
	   Total Mark out of 100 (Add all the previous marks): 98
	*/


	
	/* example values
	 * How much are you paid a month before tax?4000.00
	   How much do you pay in rent/mortgage a month? 1000.00
	   How much does your commute cost a month? 500.00
	   How much do you spend on food per month? 800.00
	 */
	
static final double TAX_RATE = 35;
static final double AVRAGE_DISPOSABLE_INCOME = 500;
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		System.out.print("How much are you paid a month before tax?");
		double netIncome = inputScanner.nextDouble();
		System.out.print("How much do you pay in rent/mortgage a month?");
		double accomidationCost = inputScanner.nextDouble();
		System.out.print("How much does your commute cost a month?");
		double commuteCost = inputScanner.nextDouble();
		System.out.print("How much do you spend on food per month?");
		double foodCost = inputScanner.nextDouble();
		inputScanner.close();
		
		double income = netIncome*((100-TAX_RATE)/100);
		double disposableIncome = income - accomidationCost - commuteCost - foodCost;
		double percent = (disposableIncome/netIncome)*100;
		
		System.out.println("Your monthly disposable income is " + disposableIncome + " which is " + percent + "% of your salary.");
		
		System.out.println("You have"+((disposableIncome<=0)? " no":
			(((disposableIncome>(500+(500/2)))?" much more than":
					(disposableIncome>500)? " more than": 
					(disposableIncome<500-(500/2))? " much less than": 
					(disposableIncome<500)? " less than":"")
				+" avrage"))+" disposable income.");
	}

}
