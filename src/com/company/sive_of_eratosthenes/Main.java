package com.company.sive_of_eratosthenes;/* SELF ASSESSMENT
   1.  createSequence:
Did I use the correct method definition?
Mark out of 5:5
Comment: I assume so, this is oop right?
Did I create an array of size n (passed as the parameter) and initialise it?
Mark out of 5: 5
Comment: limit wasn't actually the right size as we were excluding both 0 and 1, and since limit is inclusive we only need to subtrace 1
Did I return the correct item?
Mark out of 5: 5
Comment: the proof is in the pudding(providing you accept using my own data structure, as suggested in the hint)

   2.  crossOutMultiples            [this is where the problems start, why is this and sive seperated?]
Did I use the correct method definition?
Mark out of 5: 5
Comment: i don't need the array 'sequence' as this is a method to an object to which sequence is an atribute of.
Did I ensure the parameters are not null and one of them is a valid index into the array
Mark out of 2: 2
Comment: i check the index is valid but as stated above there is no 'sequence' in the paramitors
Did I loop through the array using the correct multiple?
Mark out of 5: 5
Comment: you HAVE to check all the elements, there is no relyable way to skip over them, unless we use a different prime number finding method
Did I cross out correct items in the array that were not already crossed out?
Mark out of 3: 3
Comment: the elements in 'sequence' behave like a latch without a reset, boom found to not be prime IT WILL NEVER BE PRIME, so i can say it's not prime as may times as i like

   3.  sieve
Did I have the correct function definition?
Mark out of 5: 5
Comment: it takes an int n, complains if n is to small, returns an array of ints that are multibles of n, what else dose it need
Did I make calls to other methods?
Mark out of 5:5
Comment: i do
Did I return an array with all non-prime numbers are crossed out?
Mark out of 2:2
Comment:i did but why do i need to pas an object's attribute to one of it's methods and why do i need to return a new one.
even of they were static methods i wouldn't need to return a new array and
why do we need a seperate method to find the items to cross off, then use another method to actually cross them off.

   4.  sequenceTostring
Did I have the correct function definition?
Mark out of 5: 5
Comment: all the bits and bobs
Did I ensure the parameter to be used is not null?
Mark out of 3: 3
Comment: just for you
Did I Loop through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets?
Mark out of 10: 10
Comment: what can i say, the class i use to represent the elements handle the brackets, the rest is a list

   5.  nonCrossedOutSubseqToString
Did I have the correct function definition
Mark out of 5: 5
Comment: pro coppy paste for the name, pro coppy paste fr the body, why dose this need to be a seperate method?
Did I ensure the parameter to be used is not null?
Mark out of 3: 3
Comment: yeet the shizz out od null arrays
Did I loop through the array updating the String variable with just the non-crossed out numbers?
Mark out of 5:5
Comment:prais the if statment

   6.  main
Did I ask  the user for input n and handles input errors?
Mark out of 5: 5
Comments: catch all the problems and say it's your fault :P
Did I make calls to other methods (at least one)?
Mark out of 5: 5
Comment: i sqeezed in one just for you
Did I print the output as shown in the question?
Mark out of 5: 5
Comment: mostly, i did add a main loop, an exit token and the friendly message when something went wrong

   7.  Overall
Is my code indented correctly?
Mark out of 4: 4
Comments:
Do my variable names make sense?
Mark out of 4: 4
Comments:
Do my variable names, method names and class name follow the Java coding standard
Mark out of 4: 4
Comments:
      Total Mark out of 100 (Add all the previous marks):95
*/

import java.util.Scanner;

public class Main {
    private static final String EXIT_TOKEN = "quit";
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.print("Enter int or >= 2 :");
            input = scanner.nextLine().toLowerCase();
            if (!input.equals(EXIT_TOKEN)){
                try {
                    PrimeList list = new PrimeList(Integer.parseInt(input));
                    System.out.println(list.nonCrossedOutSubseqToString());
                } catch (Exception e) {
                    System.out.println("Something Went wrong: "+e.getLocalizedMessage());
                }
            }
        }while(!input.equals(EXIT_TOKEN));
        scanner.close();
        System.out.println("good bie");
        
        
        


    }
}
