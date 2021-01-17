package com.company.algarithims.arith;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;


/**
 *  Test class for Doubly Linked List
 *
 *  @author brian Dunne
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class ArithTest {
    @Test
    public void testConstructor(){
        try {
            Arith a = new Arith();
        }catch (Exception e){
            fail();
        }
        // this is a little silly but apparently it's needed
    }
    @Test
    public void testValidateInfixOrder() {
        String[] test = new String[]{};
        assertFalse("testing validate infix with an empty expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "+", "2"};
        assertTrue("testing validate infix with a simple expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "-", "2"};
        assertTrue("testing validate infix with a simple expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "*", "2"};
        assertTrue("testing validate infix with a simple expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "/", "2"};
        assertTrue("testing validate infix with a simple expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "^", "2"};
        assertTrue("testing validate infix with a simple expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "a", "2"};
        assertFalse("testing validate infix with a simple invalid expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "+", "2", "+"};
        assertFalse("testing validate infix with a simple invalid expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "2", "+"};
        assertFalse("testing validate infix with a simple invalid expression", Arith.validateInfixOrder(test));
        test = new String[]{ "+", "1", "2"};
        assertFalse("testing validate infix with a simple invalid expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "-", "a"};
        assertFalse("testing validate infix with a invalid arg", Arith.validateInfixOrder(test));
        test = new String[]{"1", "+", "+", "2"};
        assertFalse("testing validate infix with a simple invalid expression, to many operators", Arith.validateInfixOrder(test));

        test = new String[]{"1", "+", "2", "-", "2"};
        assertTrue("testing validate infix with a medium expression", Arith.validateInfixOrder(test));
        test = new String[]{"(","1", "+", "2",")", "-", "2"};
        assertTrue("testing validate infix with a medium expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "+","(", "2", "-", "2", ")"};
        assertTrue("testing validate infix with a medium expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "(", "2", "-", "2", ")"};
        assertTrue("testing validate infix with a medium expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "2", "-", "2"};
        assertFalse("testing validate infix with a medium invalid expression", Arith.validateInfixOrder(test));
        test = new String[]{"(","1", "+", "2",")", "2"};
        assertFalse("testing validate infix with a medium invalid expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "(", "2", "-", "2"};
        assertFalse("testing validate infix with a medium invalid expression", Arith.validateInfixOrder(test));
        test = new String[]{"1", "2", "-", "2", ")"};
        assertFalse("testing validate infix with a medium invalid expression", Arith.validateInfixOrder(test));

        test = new String[]{"(","1","+","2",")","3","(","4","*","2","^","(","6","/","2",")",")"};
        assertFalse("testing validate infix with a long invalid expression", Arith.validateInfixOrder(test));
        test = new String[]{"(","1","+","2",")","+","3","*","(","4","*","2","^","(","6","/","2",")",")"};
        assertTrue("testing validate infix with a long expression", Arith.validateInfixOrder(test));
        test = new String[]{"1","+","2","+","3","*","4","*","2","^","6","/","2"};
        assertTrue("testing validate infix with a long expression", Arith.validateInfixOrder(test));

    }

    @Test
    public void testValidatePostfixOrder() {
        String[] test = new String[]{};
        assertFalse("testing validate postfix with an empty expression", Arith.validatePostfixOrder(test));
        test = new String[]{"1", "2", "+"};
        assertTrue("testing validate postfix with a simple expression", Arith.validatePostfixOrder(test));
        test = new String[]{"1", "2", "-"};
        assertTrue("testing validate postfix with a simple expression", Arith.validatePostfixOrder(test));
        test = new String[]{"1", "2", "*"};
        assertTrue("testing validate postfix with a simple expression", Arith.validatePostfixOrder(test));
        test = new String[]{"1", "2", "/"};
        assertTrue("testing validate postfix with a simple expression", Arith.validatePostfixOrder(test));
        test = new String[]{"1", "2", "^"};
        assertTrue("testing validate postfix with a simple expression", Arith.validatePostfixOrder(test));
        test = new String[]{ "1", "2","a"};
        assertFalse("testing validate postfix with a simple invalid expression", Arith.validatePostfixOrder(test));
        test = new String[]{ "1", "a","+"};
        assertFalse("testing validate postfix with a simple invalid expression", Arith.validatePostfixOrder(test));

        test = new String[]{"1", "+", "2"};
        assertFalse("testing validate postfix with a simple invalid expression", Arith.validatePostfixOrder(test));
        test = new String[]{ "+", "1", "2"};
        assertFalse("testing validate postfix with a simple invalid expression", Arith.validatePostfixOrder(test));



        test = new String[]{"1","2","+","3","4","2","6","2","/","^","*","*","+"};
        assertTrue("testing validate postfix with a long expression", Arith.validatePostfixOrder(test));
        test = new String[]{"(","1","+","2",")","3","(","4","*","2","^","(","6","/","2",")",")"};
        assertFalse("testing validate postfix with a long invalid expression", Arith.validatePostfixOrder(test));
        test = new String[]{"(","1","+","2",")","+","3","*","(","4","*","2","^","(","6","/","2",")",")"};
        assertFalse("testing validate postfix with a long invalid expression", Arith.validatePostfixOrder(test));
    }

    @Test
    public void testEvaluateInfixOrder() {
        String[] test = new String[]{};
        assertEquals("testing Evaluate infix with an empty expression",0, Arith.evaluateInfixOrder(test));
        test = new String[]{"1", "+", "2"};
        assertEquals("testing Evaluate infix with an simple expression",3, Arith.evaluateInfixOrder(test));
        test = new String[]{"1", "-", "2"};
        assertEquals("testing Evaluate infix with an simple expression",-1, Arith.evaluateInfixOrder(test));
        test = new String[]{"1", "*", "2"};
        assertEquals("testing Evaluate infix with an simple expression",2, Arith.evaluateInfixOrder(test));
        test = new String[]{"2", "/", "1"};
        assertEquals("testing Evaluate infix with an simple expression",2, Arith.evaluateInfixOrder(test));
        test = new String[]{"1", "^", "2"};
        assertEquals("testing Evaluate infix with an simple expression",1, Arith.evaluateInfixOrder(test));
        test = new String[]{"1", "a", "2"};
        assertEquals("testing Evaluate infix with an simple expression",0, Arith.evaluateInfixOrder(test));
        test = new String[]{"1", "+", "2", "-", "2"};
        assertEquals("testing Evaluate infix with an medium expression",1, Arith.evaluateInfixOrder(test));
        test = new String[]{"(","1","+","2",")","+","3","*","(","4","*","2","^","(","6","/","2",")",")"};
        assertEquals("testing Evaluate infix with an long expression",99, Arith.evaluateInfixOrder(test));
        test = new String[]{"1","+","2","+","3","*","4","*","2","^","6","/","2"};
        assertEquals("testing Evaluate infix with an long expression no brackets",387, Arith.evaluateInfixOrder(test));

        test = new String[]{"1","2","+","3","*","4","*","2","^","6","/","2"};
        assertEquals("testing Evaluate infix with an long expression no brackets, missing operator",0, Arith.evaluateInfixOrder(test));
        test = new String[]{"1","+","2","+","3","*","*","4","*","2","^","6","/","2"};
        assertEquals("testing validate infix with an long expression no brackets extra operator",0, Arith.evaluateInfixOrder(test));
        test = new String[]{"1","+","2","+","3","a","4","*","2","^","6","/","2"};
        assertEquals("testing validate infix with an long expression no brackets invalid arg on operator",0, Arith.evaluateInfixOrder(test));
        test = new String[]{"1","+","2","+","3","*","a","*","2","^","6","/","2"};
        assertEquals("testing validate infix with an long expression no brackets invalid arg on number",0, Arith.evaluateInfixOrder(test));
    }

    @Test
    public void testEvaluatePostfixOrder() {
        String[] test = new String[]{};
        assertEquals("testing Evaluate postfix with an empty expression",0, Arith.evaluatePostfixOrder(test));
        test = new String[]{"1", "2", "+"};
        assertEquals("testing Evaluate infix with an simple expression",3, Arith.evaluatePostfixOrder(test));
        test = new String[]{"1", "2", "-"};
        assertEquals("testing Evaluate infix with an simple expression",-1, Arith.evaluatePostfixOrder(test));
        test = new String[]{"1", "2", "*"};
        assertEquals("testing Evaluate infix with an simple expression",2, Arith.evaluatePostfixOrder(test));
        test = new String[]{"2", "1", "/"};
        assertEquals("testing Evaluate infix with an simple expression",2, Arith.evaluatePostfixOrder(test));
        test = new String[]{"1", "2", "^"};
        assertEquals("testing Evaluate infix with an simple expression",1, Arith.evaluatePostfixOrder(test));
        test = new String[]{"1", "2", "a"};
        assertEquals("testing Evaluate infix with an simple expression, invalid arg @ operator",0, Arith.evaluatePostfixOrder(test));
        test = new String[]{"1", "a", "+"};
        assertEquals("testing Evaluate infix with an simple expression, invalid arg @ number",0, Arith.evaluatePostfixOrder(test));
        test = new String[]{"1", "+"};
        assertEquals("testing Evaluate infix with an simple expression, not enough numbers",0, Arith.evaluatePostfixOrder(test));
        test = new String[]{"1", "2"};
        assertEquals("testing Evaluate infix with an simple expression, not enough operators",0, Arith.evaluatePostfixOrder(test));

        test = new String[]{"1", "2", "+", "2", "-"};
        assertEquals("testing Evaluate infix with an medium expression",1, Arith.evaluatePostfixOrder(test));
        test = new String[]{"1","2","+","3","4","2","6","2","/","^","*","*","+"};
        assertEquals("testing Evaluate infix with an long expression",99, Arith.evaluatePostfixOrder(test));
        test = new String[]{"1","2","+","3","4","*","2","6","^","*","2","/","+"};
        assertEquals("testing Evaluate infix with an long expression",387, Arith.evaluatePostfixOrder(test));
    }

    @Test
    public void testConvertInfixToPostfix() {
        String[] test = new String[]{};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{},test);
        test = new String[]{"1", "+", "2"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","+"},test);
        test = new String[]{"1", "-", "2"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","-"},test);
        test = new String[]{"1", "*", "2"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","*"},test);
        test = new String[]{"1", "/", "2"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","/"},test);
        test = new String[]{"1", "^", "2"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","^"},test);
        test = new String[]{"1", "a", "2"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{},test);
//        com.company.fr_recursion.test = new String[]{"1", "2"};
//        com.company.fr_recursion.test = Arith.convertInfixToPostfix(com.company.fr_recursion.test);
//        assertArrayEquals(new String[]{"1", "2"},com.company.fr_recursion.test);//it's difficult to check it an appropriate number of operators exist
//        //especially not wort resolving when a method allredy exists for that

        test = new String[]{"1", "+", "2", "-", "2"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","+","2","-"},test);
        test = new String[]{"1", "-", "2", "-", "2"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","-","2","-"},test);
        test = new String[]{"1", "*", "2", "-", "2"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","*","2","-"},test);
        test = new String[]{"1", "/", "2", "-", "2"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","/","2","-"},test);
        test = new String[]{"1", "^", "2", "-", "2"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","^","2","-"},test);

        test = new String[]{"1", "-", "2", "+", "2", "-", "4"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","-","2","+","4", "-"},test);

        test = new String[]{"1", "*", "2", "/", "2", "*", "4"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","*","2","/","4", "*"},test);

        test = new String[]{"(","1","+","2",")","+","3","*","(","4","*","2","^","(","6","/","2",")",")"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","+","3","4","2","6","2","/","^","*","*","+"},test);
        test = new String[]{"(","1","+","2",")","+","3","(","4","*","2","^","(","6","/","2",")",")"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals("implicit multiplication",new String[]{"1","2","+","3","4","2","6","2","/","^","*","*","+"},test);
        test = new String[]{"1","+","2","+","3","*","4","*","2","^","6","/","2"};
        test = Arith.convertInfixToPostfix(test);
        assertArrayEquals(new String[]{"1","2","+","3","4","*","2","6","^","*","2","/","+"},test);


    }

    @Test
    public void testConvertPostfixToInfix() {
        String[] test = new String[]{};
        test = Arith.convertPostfixToInfix(test);
        assertArrayEquals(new String[]{},test);
        test = new String[]{"1","2","+"};
        test = Arith.convertPostfixToInfix(test);
        assertArrayEquals(new String[]{"(","1", "+", "2",")"},test);
        test = new String[]{"1","2","a"};
        test = Arith.convertPostfixToInfix(test);
        assertArrayEquals(new String[]{},test);
        test = new String[]{"1","2",};
        test = Arith.convertPostfixToInfix(test);
        assertArrayEquals(new String[]{},test);

        test = new String[]{"1","2","+","2","-"};
        test = Arith.convertPostfixToInfix(test);
        assertArrayEquals(new String[]{"(","(","1", "+", "2",")", "-", "2",")"},test);
        assertTrue("checking it is infact a valid infix", Arith.validateInfixOrder(test));
        test = new String[]{"1","2","+","3","4","2","6","2","/","^","*","*","+"};
        test = Arith.convertPostfixToInfix(test);
        assertArrayEquals(new String[]{"(","(","1","+","2",")","+","(","3","*","(","4","*","(","2","^","(","6","/","2",")",")",")",")",")"},test);
        test = new String[]{"1","2","+","3","4","*","2","6","^","*","2","/","+"};
        String[] result = Arith.convertPostfixToInfix(test);
        assertArrayEquals(new String[]{"(","(","1","+","2",")","+","(","(","(","3","*","4",")","*","(","2","^","6",")",")","/","2",")",")"},result);
        assertEquals("checking that it evaluates the same", Arith.evaluatePostfixOrder(test),Arith.evaluateInfixOrder(result));
    }
}