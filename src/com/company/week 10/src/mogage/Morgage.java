package mogage;

import java.util.Scanner;

/* SELF ASSESSMENT

1. Did I use easy-to-understand meaningfu,l properly formatted, variable names and CONSTANTS?
       Mark out of 10: 8
       Comment: #ASSUMED_DURATION would be a 'magic number' but   
2. Did I indent the code appropriately?
       Mark out of 5: 
       Comment:   
3. Did I implement the mainline correctly with a loop which continues using the user says "no" ?
      Mark out of 10: 
       Comment:  
4. Did I obtain the relevant inputs from the user and produce the relevant outputs using the specified prompts & formats ?
      Mark out of 10: 
       Comment:  
5. Did I implement the readDoubleFromUser function correctly and in a manner that can be easily understood (4 marks for function definition, 4 marks for function call and 12 marks for function implementation)?
      Mark out of 20: 
       Comment:  
6. Did I implement the calculateMonthlyRepayment function correctly in a manner that can be easily understood (4 marks for function definition, 4 marks for function call and 12 marks for function implementation)?
      Mark out of 20: 
       Comment:  
7. Did I implement the calculateMonthsToRepayMortgage function correctly in a manner that can be easilyunderstood (4 marks for function definition, 4 marks for function call and 12 marks for function implementation)?
      Mark out of 20: 
       Comment:  
8. How well did I complete this self-assessment?
       Mark out of 5: 
       Comment:
Total Mark out of 100 (Add all the previous marks):
*/ 



public class Morgage {
	private static final double ASSUMED_DURATION = 20;
	private static final int MONTHS_IN_YEAR = 12;

	public static void main(String[] args) {
		Scanner systemIn = new Scanner(System.in);
		String input;
		do {
			System.out.println("Welcome to the mortgage calculator.");
			doMorgage(systemIn);
			System.out.print("Do you want to calculate again?:");
			input = systemIn.nextLine();
		}while(input.equals("yes"));
		systemIn.close();
	}

	private static void doMorgage(Scanner systemIn) {
		double totalMorgage;
		do {
			totalMorgage = getDouble(systemIn, "Please enter the mortgage amount:");//+(totalMorgage != null? "":"("+totalMorgage+" is not a valid morgage)")); //200000.00
		}while(totalMorgage<0);
		double aPR = getDouble(systemIn, "Please enter the annual interest rate (APR):"); //4.1
		
		
		System.out.println("Assuming a "+ASSUMED_DURATION+" year term, the monthly "
				+ "repayments would be <"+ calculateMonthlyRepayment(totalMorgage, aPR, ASSUMED_DURATION) +">.");
		
		Double userSpecifiedPayment = getDouble(systemIn, "How much can you afford to pay per month?:"); //2000.00
		double months = calculateMonthsToRepayMortgage(totalMorgage, userSpecifiedPayment, aPR);
		while (months == Double.POSITIVE_INFINITY) {
			System.out.println("You don't cover your intrest, please solect another value");
			userSpecifiedPayment = getDouble(systemIn, "How much can you afford to pay per month?:"); //2000.00
			months = calculateMonthsToRepayMortgage(totalMorgage, userSpecifiedPayment, aPR);
		}
		int years = (int)months/12;
		int extraMonths = (int)months%12;
		System.out.println("If you pay "+userSpecifiedPayment+" per month your mortgage would be paid off in "
				+(extraMonths == 0? "exactly "+(years>0? years+" year"+(years>1? "s":"") :"" ):
					(years>0? years+" year"+(years>1? "s ":" ")+"and " :"" )+extraMonths+ " month"+(extraMonths>1? "s":""))+".");
		
	}

	private static double calculateMonthsToRepayMortgage(double totalMorgage, Double userSpecifiedPayment, double aPR) {
		if((totalMorgage-userSpecifiedPayment)<=0) {
			return 1;
		}
		double multiplier = 1+((aPR/MONTHS_IN_YEAR)/100);
		double subCapital = (totalMorgage-userSpecifiedPayment);
		double newTotalmorgage = subCapital*multiplier;
		if(newTotalmorgage > totalMorgage) {
			return Double.POSITIVE_INFINITY;
		}
		return 1+calculateMonthsToRepayMortgage(newTotalmorgage, userSpecifiedPayment, aPR);
	}

	private static double calculateMonthlyRepayment(double totalMorgage, double aPR, double assumedDuration) {
		return totalMorgage * (aPR/MONTHS_IN_YEAR/100) / (1- Math.pow((1+((aPR/MONTHS_IN_YEAR)/100)),(-assumedDuration*MONTHS_IN_YEAR)));
	}

	private static double getDouble(Scanner systemIn, String message) {
		Scanner findDouble;
		do {
			System.out.print(message);
			findDouble = new Scanner(systemIn.nextLine());
		}while(!findDouble.hasNextDouble());
		double out = findDouble.nextDouble();
		findDouble.close();
		return out;
	}

}
