package com.company.week5lab.src.week5lab;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        System.out.println("This program computes the average and variance of all numbers entered.");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> numbers = new ArrayList<>();
        Double avrage = 0.0;
        Double variance = 0.0;
        String input = "";
        do{

            System.out.print("Enter a number (or type 'exit'):");
            input = scanner.nextLine();
            Matcher numberObserver = Pattern.compile("([+-]?\\d+[\\.\\d+]?)").matcher(input);
            if(numberObserver.matches()){
                Double number = Double.parseDouble(input);
                numbers.add(number);
                Double oldAvrage = avrage;
                avrage = avrage +(number-avrage)/numbers.size();
                variance =(variance*(numbers.size()-1)+(number-oldAvrage)*(number-avrage))/numbers.size();
            }
            System.out.println((input.equals("exit"))? "The":"So Far the" +"average is " +avrage+ " and the variance is " +variance+ ", to the grupe.");
            System.out.println(numbers);
        }while (!(input.equals("exit")));
	// write your code here
    }
}
