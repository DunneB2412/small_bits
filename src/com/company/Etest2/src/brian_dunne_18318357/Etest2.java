package brian_dunne_18318357;

import java.util.Scanner;

public class Etest2 {
	private static final String EXIT_TOKEN = "quit"; //only valid constant i could think of
	private static final String EVEN_VALUES	 = "all even";
	private static final String ODD_VALUES = "all odd";
	private static final String NITHER_ODD_OR_EVEN = "a mixture of odd and even";

	public static void main(String[] args) {
		Scanner systemIn = new Scanner(System.in);
		int[] array = retreveArray(systemIn);
		System.out.println("Your data set "+intArrayAsString(array)+" is "+evenOddOrMixed(array)+", with a range of "+getRange(array));
	}

	private static String intArrayAsString(int[] array) {
		String out = "[";
		for(int number: array) {
			if(out.length()>1) {
				out+=",";
			}
			out+=number+"";
		}
		return out+"]";
	}

	private static String evenOddOrMixed(int[] array) {
		boolean even = true;
		boolean odd = true;
		for(int number: array) {
			if(number%2==0) {
				odd = false;
			}else {
				even = false;
			}
		}
		if(even) {
			return EVEN_VALUES;
		}
		if(odd) {
			return ODD_VALUES;
		}
		return NITHER_ODD_OR_EVEN;
	}

	private static int[] retreveArray(Scanner systemIn) {
		int[] out = {};
		String input;
		Scanner intFinder;
		do {
			System.out.print("Enter an integer to add to the array(or '"+EXIT_TOKEN+"' to finish):");
			if((intFinder = new Scanner(input=systemIn.nextLine().toLowerCase())).hasNextInt()) {
				int[] tOut = new int[out.length+1];
				for(int i=0; i< out.length; i++){
					tOut[i] = out[i];
				}
				out = tOut;
				out[out.length-1] = intFinder.nextInt(); 
				System.out.println("Your data set contains "+evenOddOrMixed(out)+" numbers, with a range of "+getRange(out));
			}
		}while(!input.equals(EXIT_TOKEN));
		intFinder.close();
		return out;
	}

	private static int getRange(int[] array) {
		if(array.length==0) {
			return 0;
		}
		double max = Double.NEGATIVE_INFINITY;
		double min = Double.POSITIVE_INFINITY;
		for(int number: array) {
			if(number>max) {
				max = number;
			}
			if(number<min) {
				min = number;
			}
		}
		return (int) (max-min);
	}
}
