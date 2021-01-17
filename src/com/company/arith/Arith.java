package com.company.arith;// -------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.Stack;

/**
 *  Utility class containing validation/evaluation/conversion operations
 *  for infix arithmetic expressions.
 *
 *  @author  brian dunne
 *  @version 1/12/15 13:03:48
 */

public class Arith {


  //~ Validation methods ..........................................................


  /**
   * Validation method for infix notation.
   *
   * @param infixLiterals : an array containing the string literals hopefully in infix order.
   * The method assumes that each of these literals can be one of:
   * - "(", ")", "^", "+", "-", "*", or "/"
   * - or a valid string representation of an double "0", "1" , "2", ..., "-1", "-2", ...
   *
   * - runtime complexity: O(n) cycles through literals.
   * - memory complexity: O(1) doesn't require additional memory proportional to input.
   * @return true if the parameter is indeed in infix notation, and false otherwise.
   **/
  public static boolean validateInfixOrder(String[] infixLiterals)
  {
    int unResolvedNumbersInARow = 0;
    boolean activeOperator = false;
    int unclosedBrackets = 0;
    for (int i = 0; i< infixLiterals.length; i++) {
      String arg = infixLiterals[i];
      try{
        Double.valueOf(arg);
        unResolvedNumbersInARow ++;
        if(activeOperator) {
          activeOperator = false;
          unResolvedNumbersInARow--;
        }
        if(unResolvedNumbersInARow>1)return false;
      }catch (Exception e){
        switch (arg) {
          case "+":
          case "-":
          case "*":
          case "/":
          case "^"://operator
            if (unResolvedNumbersInARow < 1|| activeOperator) return false;
            activeOperator = true;
            break;
          case "(":
            if(i>0){
              try {
                Double.valueOf(infixLiterals[i-1]);
                activeOperator = true;
              }catch (Exception ignored){}
            }
            unclosedBrackets++;
            break;
          case ")":
            if (unclosedBrackets < 1) return false;
            unclosedBrackets--;
            break;
          default:
            return false;
        }
      }
    }
    return unResolvedNumbersInARow==1&&unclosedBrackets==0&&!activeOperator;
  }

  /**
   * Validation method for postfix notation.
   *
   * @param postfixLiterals : an array containing the string literals hopefully in infix order.
   * The method assumes that each of these literals can be one of:
   * - "(", ")", "^", "+", "-", "*", or "/"
   * - or a valid string representation of an double "0", "1" , "2", ..., "-1", "-2", ...
   *
   * - runtime complexity: O(n) cycles through literals.
   * - memory complexity: O(1) doesn't require additional memory proportional to input.
   * @return true if the parameter is indeed in postfix notation, and false otherwise.
   **/
  public static boolean validatePostfixOrder(String[] postfixLiterals){
    int unResolvedNumbers = 0;
    for (String arg: postfixLiterals) {
      try{
        Double.valueOf(arg);
        unResolvedNumbers++;
      }catch (Exception e){
        if(arg.equals("+")||arg.equals("-")||arg.equals("*")|| arg.equals("/")||arg.equals("^")){
          if(unResolvedNumbers<2) return false;// to many operators
          unResolvedNumbers--;
        }else return false; // un recognised token
      }
    }
    return unResolvedNumbers==1;
  }

  //~ Evaluation  methods ..........................................................


  /**
   * Evaluation method for infix notation.
   *
   * @param infixLiterals : an array containing the string literals in infix order.
   * The method assumes that each of these literals can be one of:
   * - "(", ")", "^", "+", "-", "*", or "/"
   * - or a valid string representation of an double.
   *
   * - runtime complexity: O(n^2) as of convertInfixToPostfix: O(n^2), evaluatePostfixOrder: O(n).
   * - memory complexity: O(n) convertInfixToPostfix: O(n), evaluatePostfixOrder: O(n).
   * @return the integer result of evaluating the expression
   **/
  public static int evaluateInfixOrder(String[] infixLiterals) {
    return evaluatePostfixOrder(convertInfixToPostfix(infixLiterals));
  }

  /**
   * Evaluation method for postfix notation.
   *
   * @param postfixLiterals : an array containing the string literals in postfix order.
   * The method assumes that each of these literals can be one of:
   * - "(", ")", "^", "+", "-", "*", or "/"
   * - or a valid string representation of an double.
   *
   * - runtime complexity: O(n) cycling through literals, Math.round, stack.push and stack.pop: O(1).
   * - memory complexity: O(n) only need space for numbers.
   * @return the integer result of evaluating the expression
   **/
  public static int evaluatePostfixOrder(String[] postfixLiterals) {
    Stack<Double> numbers = new Stack<>();
    for (String arg: postfixLiterals) {
      try{
        numbers.push(Double.valueOf(arg));
      }catch (Exception e){
        if(numbers.size()<2) return 0;//not enough numbers left
        double b = numbers.pop();
        double a = numbers.pop();
        switch (arg){
          case ("+"):
            numbers.push(a+b);
            break;
          case ("-"):
            numbers.push(a-b);
            break;
          case ("*"):
            numbers.push(a*b);
            break;
          case ("/"):
            numbers.push(a/b);
            break;
          case ("^"):
            numbers.push(Math.pow(a,b));
            break;
          default: return 0; // not a valid argument
        }
      }
    }
    return numbers.size()==1?(int) Math.round(numbers.pop()):0;
  }

  //~ Conversion  methods ..........................................................


  /**
   * Converts infix to postfix.
   *
   * @param infixLiterals : an array containing the string literals in infix order.
   * The method assumes that each of these literals can be one of:
   * - "(", ")", "^", "+", "-", "*", or "/"
   * - or a valid string representation of an double.
   *
   * - runtime complexity: O(n^2) back tracking through operators for each operator,
   *                      stack.push, stack.pop and list.add: O(1). list.toArray: O(n).
   * - memory complexity: O(n) only need space for operators and the postfix literals.
   * @return the expression in postfix order.
   **/
  public static String[] convertInfixToPostfix(String[] infixLiterals) {
    Stack<String> ops = new Stack<>();
    ArrayList<String> newArgs = new ArrayList<>();
    for (int i = 0; i< infixLiterals.length; i++) {
      String literal = infixLiterals[i];
      try{
        Double.valueOf(literal);
        newArgs.add(literal);
      }
      catch (Exception e){
        switch (literal) {
          case "(":
            if(i>0) {
              try {
                Double.valueOf(infixLiterals[i-1]);
                ops.push("*");//if the preceding arg is a number introduce a multiplication
              }catch (Exception ignored){}
            }
            ops.push(literal);
            break;
          case "+":
          case "-":
          case "*":
          case "/":
          case "^": {
            String operator;
            if (ops.size() > 0) {
              boolean acted;
              do {
                acted = false;
                operator = ops.pop();
                switch (literal) {
                  case ("+"):
                  case ("-"):
                    if (operator.equals("+") || operator.equals("-")) {
                      newArgs.add(operator);
                      acted = true;
                    }
                  case ("*"):
                  case ("/"):
                    if (operator.equals("*") || operator.equals("/")) {
                      newArgs.add(operator);
                      acted = true;
                    }
                  default: // no other posible vale will be pushed
                    if (operator.equals("^")) {
                      newArgs.add(operator);
                      acted = true;
                    }
                }
                if(!acted)ops.push(operator);
              } while (acted&&ops.size()>0);
            }
            ops.push(literal);
            break;
          }
          case ")": {
            String operator = ops.pop();
            while (!operator.equals("(")) {
              newArgs.add(operator);
              operator = ops.pop();
            }
            break;
          }
          default: return new String[]{}; // invalid argument found
        }
      }
    }
    while(ops.size()>0){
      newArgs.add(ops.pop());
    }
    return newArgs.toArray(new String[0]);
  }


  /**
   * Converts postfix to infix.
   *
   * @param postfixLiterals : an array containing the string literals in postfix order.
   * The method assumes that each of these literals can be one of:
   * - "(", ")", "^", "+", "-", "*", or "/"
   * - or a valid string representation of an double.
   *
   * - runtime complexity: O(n^2) all that messing with the arrays, stack.push and stack.pop: O(1).
   * - memory complexity: O(n) only requires the space required to fit the infix.
   * - comment: a little quick to add brackets for my taste but technically works
   * @return the expression in infix order.
   **/
  public static String[] convertPostfixToInfix(String[] postfixLiterals) {
    Stack<String[]> clumps = new Stack<>();
    for (String arg: postfixLiterals) {
      try{
        Double.valueOf(arg);
        clumps.push(new String[]{arg});
      }catch (Exception e){
        if(!(arg.equals("+")||arg.equals("-")||arg.equals("*")|| arg.equals("/")||arg.equals("^"))) {
          return new String[]{}; //invalid argument found
        }
        String[] B = clumps.pop();
        String[] A = clumps.pop();
        String[] next = new String[A.length+B.length+3];
        next[0] = "(";
        int i = 1;
        for (String a: A){
          next[i] = a;
          i++;
        }
        next[i] = arg;
        i++;
        for (String b: B){
          next[i] = b;
          i++;
        }
        next[i] = ")";
        clumps.push(next);
      }
    }
    return clumps.size()==1?clumps.pop(): new String[]{};
  }

}
/*
  import java.util.ArrayList;
    list.add: O(1): java dock
    list.toArray: O(n): i can imagine it to have L(n): has to move items over however an iterator should be able to
                        cycle through the iterable in O(n) otherwise it's a terrible iterator and we would never use it
  import java.util.Stack;
    stack.push: O(n): we discussed this in a lecture: any sensible implementation of a stack can easily be this
    stack.pop: o(n): same as above.

  these are insignificant in with respect to the complexity of the functions they are called in.
 */

