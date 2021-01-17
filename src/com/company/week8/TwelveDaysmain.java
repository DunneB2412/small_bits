package com.company.week8;

/* SELF ASSESSMENT
1. Did I use easy-to-understand meaningfu,l properly formatted, variable names and CONSTANTS?
       Mark out of 10:10
       Comment: easy to read and minipulate constants for what one would get each day and no magic numbers  
2. Did I implement the getVerse function correctly and in a manner that can be understood (5 marks for function definition, 5 marks for function call and 15 marks for function implementation)?
      Mark out of 25:25
       Comment: i found that if the function should return a string that colaps the verse and output it, recursion would be ucefull.  
3. Did I implement the getChristmasGift function correctly using a switch statement and in a manner that can be understood (5 marks for function definition, 5 marks for function call and 15 marks for function implementation)?
      Mark out of 25:25
       Comment:  it uses a switch, what can i say
4. Did I implement the getOrdinalString function correctly using if or conditional operators and in a manner that can be understood (5 marks for function definition, 5 marks for function call and 15 marks for function implementation)?
      Mark out of 25:25
       Comment:  i feel like there is reduncancies here but i need to make a function of this name, it is also a switch
5. Does the program produce the output correctly?
      Mark out of 10:10
       Comment:  i find the enphases on the beginning of the gifts to be nessisary so.
6. How well did I complete this self-assessment?
       Mark out of 5:5
       Comment: i included your o so desired comments, ps i didn't kow that's what the comments ment. i thought i just needed to give some value.
Total Mark out of 100 (Add all the previous marks):
*/ 


public class TwelveDaysmain {
	
	private static final String DAY_1 = "a java convention manule";
	private static final String DAY_2 = "Two programming assignments";
	private static final String DAY_3 = "Three for loops";
	private static final String DAY_4 = "Four system.exit(1)s";
	private static final String DAY_5 = "Five golden breaks";
	private static final String DAY_6 = "Six switch statments";
	private static final String DAY_7 = "Sevin Strings called 's'";
	private static final String DAY_8 = "Eight inline coments";
	private static final String DAY_9 = "Nine sign in sheets";
	private static final String DAY_10 = "Ten card games to make";
	private static final String DAY_11 = "Eleven songs to sing";
	private static final String DAY_12 = "Twelve well defined questions";
	private static final int NUMBER_OF_DAYS = 12;
	
	public static void main(String[] args) {
		for(int index = 0; index<NUMBER_OF_DAYS; index++) {
			int verse = index + 1;
			System.out.println("[verse "+verse+"]\nOn the "+intToStringOrdinal(verse)+" day of christmass,\n"
					+ "my true love gave to me:");
			
			System.out.println(getVerse(verse));
		}	
		
	}
	private static String getVerse(int verseNumber) {
		if(verseNumber == 1) {
			return getChristmasGift(1)+".\n";
		}
		return getChristmasGift(verseNumber)+(verseNumber>2?",\n":"\nAnd ")+getVerse(verseNumber-1);
	}
	private static String getChristmasGift(int verseNumber) {
		switch (verseNumber) {
		case (1):
			return DAY_1;
		case (2):
			return DAY_2;
		case (3):
			return DAY_3;
		case (4):
			return DAY_4;
		case (5):
			return DAY_5;
		case (6):
			return DAY_6;
		case (7):
			return DAY_7;
		case (8):
			return DAY_8;
		case (9):
			return DAY_9;
		case (10):
			return DAY_10;
		case (11):
			return DAY_11;
		case (12):
			return DAY_12;
		default:
			throw new IndexOutOfBoundsException();
		}
	}
	private static String intToStringOrdinal(int number) {
		switch (number) {
		case (1):
			return "first";
		case (2):
			return "seccond";
		case (3):
			return "third";
		case (4):
			return "forth";
		case (5):
			return "fifth";
		case (6):
			return "sixth";
		case (7):
			return "seventh";
		case (8):
			return "eight";
		case (9):
			return "ninth";
		case (10):
			return "tenth";
		case (11):
			return "eleventh";
		case (12):
			return "twelvth";
		default:
			throw new IndexOutOfBoundsException();
		}
	}
}
