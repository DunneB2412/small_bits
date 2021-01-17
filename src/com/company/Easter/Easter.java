package com.company.Easter;
import java.util.Scanner;
public class Easter
{
  static boolean isLeapYear(int year)
  {
	  return (year%4==0 && year%100!=0) || year%400==0;
    // implement this function
  }

  static int numberOfDaysInMonth(int year, Month month)
  {
	  switch(month)
	  {
	  	case FEBRUARY:
		  if(isLeapYear(year))
			  return 29;
		  else
			  return 28;
	  	
	  	case SEPTEMBER:
	  	case APRIL:
	  	case JUNE:
	  	case NOVEMBER:
	  		return 30;
	  	default:
	  		return 31;	  
	  }
    // implement this function
  }

  static Month easterMonth(int year)
  {
	  int a=year%19;
	  int b=year/100;
	  int c=year%100;
	  int d=b/4;
	  int e=b%4;
	  int f=(b+8)/25;
	  int g=(b-f+1)/3;
	  int h=(19*a+b-d-g+15)%30;
	  int i=c/4;
	  int k=c%4;
	  int L=(32+2*e+2*i-h-k)%7;
	  int m=(a+11*h+22*L)/451;
    // implement this function
	  
    return Month.month((h+L-7*m+114)/31);
  }

  static int easterDay(int year)
  {
	  int a=year%19;
	  int b=year/100;
	  int c=year%100;
	  int d=b/4;
	  int e=b%4;
	  int f=(b+8)/25;
	  int g=(b-f+1)/3;
	  int h=(19*a+b-d-g+15)%30;
	  int i=c/4;
	  int k=c%4;
	  int L=(32+2*e+2*i-h-k)%7;
	  int m=(a+11*h+22*L)/451;
    // implement this function
	  
    return ((h+L-7*m+114)%31)+1;
  }

  static int dayNumberInYear(int day, Month month, int year)
  {
	  int noOfDays=0;
	  for(int i=1;i<month.number();i++)
	  {
		  noOfDays=noOfDays+numberOfDaysInMonth(year,Month.month(i));
	  }
    // implement this function
    return noOfDays+day;
  }

  static Month monthInYearOfDayNumber(int dayNumber, int year)
  {
	  Month month=Month.JANUARY;
	  while(dayNumber > 0)
	  {
		  dayNumber=dayNumber-numberOfDaysInMonth(year,month);
		  if (dayNumber>0)
		  {
			  month=Month.month(month.number()+1);
		  }
	  }
    // implement this function
    return month;
  }

  static int dayInMonthOfDayNumber(int dayNumber, int year)
  {
	  int placeHolder=0;
	  Month month=Month.JANUARY;
	  while(dayNumber > 0)
	  {
		  placeHolder= dayNumber;
		  dayNumber=dayNumber-numberOfDaysInMonth(year,month);
		  if (dayNumber>0)
		  {
			  month=Month.month(month.number()+1);
		  }
	  }
    // implement this function
    return placeHolder;
  }

  static void showHolyDays(int year)
  {
	  int easterDay=dayNumberInYear(easterDay(year),easterMonth(year),year);
	  System.out.println("easter is on "+dayInMonthOfDayNumber(easterDay,year)+"/"+monthInYearOfDayNumber(easterDay,year));
	  System.out.println("Carnival starts on "+dayInMonthOfDayNumber(easterDay-49,year)+"/"+monthInYearOfDayNumber(easterDay-49,year));
	  System.out.println("Good friday is on "+dayInMonthOfDayNumber(easterDay-2,year)+"/"+monthInYearOfDayNumber(easterDay-2,year));
	  System.out.println("Ascension is on "+dayInMonthOfDayNumber(easterDay+39,year)+"/"+monthInYearOfDayNumber(easterDay+39,year));
	  System.out.println("Whitsuntide is on "+dayInMonthOfDayNumber(easterDay+49,year)+"/"+monthInYearOfDayNumber(easterDay+49,year));
    // implement this function
	  
  }
  private static int stepsToTake(Month month, int year)
  {
	  int easterDay=dayNumberInYear(easterDay(year),easterMonth(year),year);
	  int firstNthDay =dayNumberInYear(1,month,year);
	  int daysOffSunday = (firstNthDay-easterDay)%7;
	  if(daysOffSunday==0)
	  {
		  return 6;
	  }
	  if(daysOffSunday<0)
	  {
		  return daysOffSunday+6;
	  }
	  else
	  {
		  return daysOffSunday-1;
	  }
  }
  private static void calender(Month month, int year)
  {
	  int date=1;
	  System.out.println("||Mo|Tu|We|Th|Fr|Sa|Su||");
	  System.out.print("||");
	  for (int i=stepsToTake(month,year);i>0;i--)
	  {
		  System.out.print("  |");
	  }
	  for (int i=7-stepsToTake(month, year);i>0;i--)
	  {
		  safePrint(date,numberOfDaysInMonth(year, month));
		  date++;
	  }
	  System.out.println("|");
	  while (date<numberOfDaysInMonth(year, month))
	  {
		  System.out.print("||");
		  for (int i=0;i<7;i++)
		  {
			  safePrint(date,numberOfDaysInMonth(year, month));
			  date++;
		  }
		  System.out.println("|");
	  }	
  }
  
  private static void safePrint(int date, int monthSize)
  {
	  
	  if (date<=monthSize)
	  {
		  if(date<10)
		  {
			  System.out.print(" "+date+"|"); 
		  }
		  else
		  {
			  System.out.print(date+"|");
		  }
	  }
	  else
		  System.out.print("  |");
  }
  
  public static void main(String[] arguments)
  {
	  Scanner scanner = new Scanner(System.in);
	  System.out.println("give the year");
	  int year = scanner.nextInt();
	  System.out.println("It is "+isLeapYear(year)+" that "+year+" is a leap year." );
	  System.out.println("give the month as a number");
	  Month month = Month.month(scanner.nextInt());
	  System.out.println("there is "+numberOfDaysInMonth(year,month)+" days in "+month);
	  calender(month, year);
	  System.out.println("here is some extra info about the year just for you");
	  showHolyDays(year);
	  
	  
	
  }
}
