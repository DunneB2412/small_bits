package fr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) {
		Matcher matcher= Pattern.compile("([+-]?\\d+)\\s([a-zA-Z]+)"
				+ "\\s([a-zA-Z]+)[a-zA-Z\\s]*\\n?").matcher("23 Default Null");
		System.out.println(matcher.matches());
		System.out.println(matcher.group(1));
		System.out.println(matcher.group(2));
		System.out.println(matcher.group(3));
		
		// TODO Auto-generated method stub

	}

}
