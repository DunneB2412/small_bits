package com.company.colinear;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.junit.Assert.*;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author
 *  @version 18/09/18 12:21:26
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }


    @Test
    public void testBinarySearchSmall(){
        int[] empty = {};
        int[] single = {10};
        int[] two = {10,20};

        //empty
        assertFalse("binary search triggered a false positive for empty array", Collinear.binarySearch(empty, 1));

        //one
        assertTrue("binary search failed to find for one long array", Collinear.binarySearch(single, 10));
        assertFalse("binary search triggered a false positive for one long array", Collinear.binarySearch(single, 11));

        //two
        assertTrue("binary search failed to find for one long array", Collinear.binarySearch(two, 10)||Collinear.binarySearch(two,20));
        assertFalse("binary search triggered a false positive for one long array", Collinear.binarySearch(two, 11));
    }
    @Test
    public void testBinarySearchLarge(){
        int[] manny = {-10,-9,-8,-7,-6,-5,-4,-3,-2,-1,0,1,3,4,5,6,7,8,9,10}; //omited 2 for middle false
        assertTrue("binary search failed to find for one long array (rhs)", Collinear.binarySearch(manny, 10));
        assertTrue("binary search failed to find for one long array (lhs)", Collinear.binarySearch(manny, -10));
        assertTrue("binary search failed to find for one long array (mid)", Collinear.binarySearch(manny, 0));
        assertFalse("binary search triggered a false positive for one long array (rhs)", Collinear.binarySearch(manny, 11));
        assertFalse("binary search triggered a false positive for one long array (lhs)", Collinear.binarySearch(manny, -11));
        assertFalse("binary search triggered a false positive for one long array (mid)", Collinear.binarySearch(manny, 2));
    }

    @Test
    public void testSort(){
        int[] empty = {};
        int[] orderd = {0,1,2,3,4,5,6,7,8,9};
        int[] mixed =  {1,0,4,3,2,5,8,7,9,6};

        Collinear.sort(empty);
        assertArrayEquals( "sort failed on empty ser", new int[]{}, empty);
        int[] orderdT = orderd.clone();
        Collinear.sort(orderdT);
        assertArrayEquals("sort failed on presorted array", orderd, orderdT);
        int[] mixedT = mixed.clone();
        Collinear.sort(mixedT);
        assertArrayEquals("sort failed on presorted array", orderd, mixedT);
    }
}

