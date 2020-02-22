package nl.ru.ai.brianDimitar;

public class test {

	public static void main(String[] args) {
		System.out.println(ordered(16));

	}
	private static int value(char bob) {
		if(bob=='|')
			return 1;
		if(bob=='+')
			return 3;
		if(bob=='*')
			return 9;
		else return 0;
	}
	private static int valueString(String string) {
		int sum=0;
		for(int i=0; i<string.length(); i++)
			sum+=value(string.charAt(i));
		return sum;
	}
	private static String ordered(int value) {
		String string="";
		while(value-9>=0) {
			string+="*";
			value-=9;
		}
		while(value-3>=0) {
			string+="+";
			value-=3;
		}
		while(value-1>=0) {
			string+="|";
			value-=1;
		}
		return string;
	}

}
