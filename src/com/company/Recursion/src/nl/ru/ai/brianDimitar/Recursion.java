package nl.ru.ai.brianDimitar;

import java.util.ArrayList;

public class Recursion //brian 1016603 dimitar 
{
	/**
	 * gives the sume of all numbers between 0 and x
	 * @param x
	 * @return
	 */
	private static int sum(int x)
	{
		assert(true);
		if (x==0)
			return 0;
		return x+sum(x-1);
	}
	/**
	 *  finds x to the power of n
	 * @param n
	 * @param m
	 * @return n*n if n!=0
	 */
	private static int power(int x, int n)
	{
		assert (true);
		if (n<0)
			return 0;
		if (n==0)
			return 1;
		return x*power(x,n-1);
	}
	/**
	 * finds the sallest element in the array
	 * @param a array
	 * @param n limit
	 * @return smallest element
	 */
	private static int minimum (int[] a, int n)
	{
		assert (a!=null): "array should be initalised";
		assert (n>=0&&n<a.length): "n should be within array bounds of a";
		if (n==0)
			return a[0];
		return Math.min(a[n-1], minimum(a,n-1));
			
	}
	/**
	 *  finds greatest common denominator
	 * @param n
	 * @param m
	 * @return 
	 */
	private static int gcd (int n, int m)
	{
		assert (true);
		if (m==0)
			return n;
		else if (n<m)
			return gcd(m,n);
		else
			return gcd(m,n%m);
	}
	/**
	 * finds the value of an item in a particular position
	 * @param row 
	 * @param col
	 * @return 
	 */
	private static int pascal (int row, int col)
	{
		assert (row>=0):"use a valid value for row";
		assert (col>=0):"use a valid value for col";
		if (col==0||col==row)
			return 1;
		return pascal(row-1,col-1)+pascal(row-1,col);
		
	}
	/**
	 * prints spaces for difference between n and lim
	 * @param n
	 */
	private static void spaces(int n, int lim)
	{
		assert (n>0 && n<lim):"n should be between o and the limit";
		for (int i=n; i<lim ;i++)
			System.out.print(" ");
	}
	/**
	 * finds the nth element in a fibonatchi sequence
	 * @param n nth number
	 * @param numbers a seed array where 0,1 are 1
	 * @return next element in the sequence untill nth element is found
	 */
	private static long fibC(int n , ArrayList<Integer> numbers)
	{
		assert (numbers!=null):"numbers should be initalised";
		assert (numbers.get(0)==1&&numbers.get(1)==1):"first two elements in array should be 1";
		assert (n>=0):"n should be a natural number";
		if (n<=1)
		{
			return 1;
		}
		numbers.add(numbers.get(numbers.size()-1)+numbers.get(numbers.size()-2));
		fibC(n-1,numbers);
		return numbers.get(numbers.size()-1);
	}
	/**
	 * inteligent fib function
	 * @param n nth position as target
	 * @param a seccond most recent element
	 * @param b most recent element
	 * @return next element in the sequence untill nth element is found
	 */
	private static int fibI(int n, int a, int b)
	{
		assert (n>=0):"n should be a natural number";
		if(n<=1)
			return b;
		return fibI(n-1,b,a+b);
	}
	/**
	 * more inteligent fib function that actually has runtime cost of O(n) and memory cost of O(n)
	 * @param n nth position as target
	 * @param abc a as [0], b as [1] and c as [2]. where a is seccond most recent element of the sequence,
	 *        b is the most recent element in the sequence and c is a helper position
	 * @return next element in the sequence untill nth element is found
	 */
	private static int fibVI(int n, int[] abc)
	{
		assert(abc!=null):"array must be initalised";
		assert (n>=0):"n should be a natural number";
		if(n<=1)
			return abc[1];
		abc[2]=abc[1];        //place holder to preserv the actual most recent value
		abc[1]=abc[0]+abc[1]; //creates new most recent value
		abc[0]=abc[2];        //passes value down so as previous most recent value is saced in front of new one
		return fibVI(n-1,abc);
	}
	/**
	 * a nasty function to find fib
	 * @param n nth position as target
	 * @return next element in the sequence untill nth element is found
	 */
	private static int fib(int n)
	{
		assert (n>=0):"n should be a natural number";
	 	if (n<=1)
	 		return 1;
	 	return (fib(n-1)+fib(n-2));
	}
	
	public static void main(String[] args) 
	{
		System.out.println(sum(100));
		System.out.println(power(2,-8));
		int [] array={12,8,14,23,16};
		System.out.println(minimum(array,array.length));
		System.out.println(gcd(1650,2250));
		
		int nForFib=45;
		ArrayList<Integer> numbers= new ArrayList<>();
		numbers.add(1);
		numbers.add(1);
		long time=System.currentTimeMillis();
		System.out.println(fibC(nForFib, numbers)+" : "+(System.currentTimeMillis()-time)+"ms"); //it breaks after 45 because the number is bigger than the biggest number for an int
		int[] fibVINumbers= {1,1,0};
		time=System.currentTimeMillis();
		System.out.println(fibVI(nForFib,fibVINumbers)+" : "+(System.currentTimeMillis()-time)+"ms");
		time=System.currentTimeMillis();
		System.out.println(fibI(nForFib,1,1)+" : "+(System.currentTimeMillis()-time)+"ms");
		time=System.currentTimeMillis();
		System.out.println(fib(nForFib)+" : "+(System.currentTimeMillis()-time)+"ms");
		//it breaks after 45 because the number is bigger than the biggest number for an int
		
		int rowslim = 5;
		for(int row=0;row<rowslim;row++)
		{
			String line="";
			for(int col=0;col<=row;col++)
				line+=(pascal(row,col)+" ");
			spaces(row, rowslim);
			System.out.println(line);
		}
	}

}
