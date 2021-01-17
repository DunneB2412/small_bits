package com.company.fr_recursion;

public class FrRecursion {
	

	public static void main(String[] args) {
		
		primNumber(100);
	}
		private static boolean isDivisor(int d, int n)
		{
		return (n%d==0);
		}
		
		 private static void primNumber(int limit)
		 {
		 for(int i=2; i<limit; i++){
		 boolean a=true;
		   for(int j=2; j<i; j++)
		     if(isDivisor(j,i))
		        a=false;
		   if(a==true)
		   System.out.print(" "+i+";");
		   }
		   
		 }

	}


