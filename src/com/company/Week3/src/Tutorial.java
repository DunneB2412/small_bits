import java.util.Scanner;

public class Tutorial {

	public static void main(String[] args) {
		
		System.out.print("give a number to count with. >");
		Scanner scanner = new Scanner(System.in);
		int countingNumber = scanner.nextInt();
		System.out.print("give a uper limit. >");
		int upperLimit = scanner.nextInt();
		scanner.close();
		String numbers = "";
		if(countingNumber >=1) {
			for(int counter = countingNumber; counter <= upperLimit; counter += countingNumber) {
				numbers += (counter+""+(((counter+countingNumber)<=upperLimit)? ", ": "")+"");
			}
		}
		System.out.println("the multiples of "+ countingNumber + "(up to "+ upperLimit + ") are "+
				((numbers.length()>0)? numbers: "none") + ".");

	}

}
