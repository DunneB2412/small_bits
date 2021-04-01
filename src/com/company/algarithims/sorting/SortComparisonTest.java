package com.company.algarithims.sorting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;

import static org.junit.Assert.*;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author dunneb5
 *  @version HT 2020
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    /**
     * @param input input array
     * @param answer sorted answer
     * @param test what were testing for
     *  insertion sorting many random:25041922 compares and 25041922 swaps
     *  selection sorting many random:50005000 compares and 10000 swaps
     *      quick sorting many random:153554 compares and 79873 swaps
     *     mergeR sorting many random:132650 compares and 124656 swaps
     *     mergeI sorting many random:134671 compares and 127120 swaps
     *  insertion sorting many sorted:0 compares and 0 swaps
     *  selection sorting many sorted:50005000 compares and 10000 swaps
     *      quick sorting many sorted:49995000 compares and 10000 swaps
     *     mergeR sorting many sorted:9999 compares and 0 swaps
     *     mergeI sorting many sorted:9999 compares and 0 swaps
     */
    private static void checkMethodsWith(double[] input, double[] answer, String test){
        SortComparison.resetCounters();
        assertArrayEquals("insertion sort failed to sort "+test+" array",answer, SortComparison.insertionSort(input.clone()),0);
        SortComparison.showCounters("insertion sorting "+test);

        SortComparison.resetCounters();
        assertArrayEquals("selection sort failed to sort "+test+" array", answer, SortComparison.selectionSort(input.clone()),0);
        SortComparison.showCounters("selection sorting "+test);

        SortComparison.resetCounters();
        assertArrayEquals("quick sort failed to sort "+test+" array", answer, SortComparison.quickSort(input.clone()),0);
        SortComparison.showCounters("quick sorting "+test);

        SortComparison.resetCounters();
        assertArrayEquals("merge recursive sort failed to sort "+test+" array", answer, SortComparison.mergeSort(input.clone()),0);
        SortComparison.showCounters("mergeR sorting "+test);

        SortComparison.resetCounters();
        assertArrayEquals("merge iterative sort failed to sort "+test+" array", answer, SortComparison.mergeSortI(input.clone()),0);
        SortComparison.showCounters("mergeI sorting "+test);
    }
    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty() {
        double[] test = new double[]{};
        double[] result = new double[]{};
        checkMethodsWith(test, result, "empty");
    }

    /**
     * Check that the methods work for arrays of length 1
     */
    @Test
    public void testOneLong() {
        double[] test = new double[]{1};
        double[] result = new double[]{1};
        checkMethodsWith(test, result, "one long");
    }

    /**
     * Check that the methods work for arrays of length 10
     */
    @Test
    public void testTenLong() {
        double[] test = new double[]{2,5,4,7,8,1,0,3,6,9};
        double[] result = new double[]{0,1,2,3,4,5,6,7,8,9};
        checkMethodsWith(test, result, "ten long");
        checkMethodsWith(result, result, "ten long pre sorted");
    }

    /**
     * Check that the methods work for arrays of length 10 with decimal
     */
    @Test
    public void testTenLongDecimal() {
        double[] test = new double[]{0.2,0.5,0.4,0.7,0.8,0.1,0.0,0.3,0.6,0.9,6};
        double[] result = new double[]{0.0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,6};
        checkMethodsWith(test, result, "ten long decimal");
        checkMethodsWith(result, result, "ten long pre sorted decimal");
    }

    /**
     * Check that the methods work for arrays of length 10 with decimal
     */
    @Test
    public void testMany() {
        double[] test = new double[10000];
        Random random = new Random(100);
        for (int i = 0; i < test.length; i++) {
            test[i] = random.nextDouble();
        }
        double[] result = test.clone();
        Arrays.sort(result);
        checkMethodsWith(test, result, "many random");
        checkMethodsWith(result, result, "many sorted");
    }

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     *  checking for numbers1000
     *  times for mergeSort{7676300,214900,215300} averaging:2702166
     *  times for selectionSort{5039500,1487700,1843300} averaging:2790166
     *  times for insertionSort{4483800,2512900,1931800} averaging:2976166
     *  times for quickSort{728200,255600,248200} averaging:410666
     *  times for mergeSortI{991700,515200,586300} averaging:697733
     *
     *  checking for numbers1000Duplicates
     *  times for mergeSort{254500,207300,186300} averaging:216033
     *  times for selectionSort{550900,547700,759900} averaging:619500
     *  times for insertionSort{382500,370800,366200} averaging:373166
     *  times for quickSort{295000,169900,236900} averaging:233933
     *  times for mergeSortI{308300,328800,515800} averaging:384300
     *
     *  checking for numbersNearlyOrdered1000
     *  times for mergeSort{167900,158200,147200} averaging:157766
     *  times for selectionSort{640500,519900,512700} averaging:557700
     *  times for insertionSort{126300,104800,92200} averaging:107766
     *  times for quickSort{169100,115300,96100} averaging:126833
     *  times for mergeSortI{330000,427500,254000} averaging:337166
     *
     *  checking for numbersReverse1000
     *  times for mergeSort{141200,119800,4019500} averaging:1426833
     *  times for selectionSort{887700,1013400,715600} averaging:872233
     *  times for insertionSort{734300,661800,667000} averaging:687700
     *  times for quickSort{686900,666800,627300} averaging:660333
     *  times for mergeSortI{424800,431200,349900} averaging:401966
     *
     *  checking for numbersSorted1000
     *  times for mergeSort{105200,79500,88300} averaging:91000
     *  times for selectionSort{844100,682200,1011300} averaging:845866
     *  times for insertionSort{39700,25500,25300} averaging:30166
     *  times for quickSort{913600,764900,756800} averaging:811766
     *  times for mergeSortI{168900,158100,153100} averaging:160033
     *
     *  checking for numbers10000
     *  times for mergeSort{3130600,1343600,1377200} averaging:1950466
     *  times for selectionSort{48136000,49235200,58105600} averaging:51825600
     *  times for insertionSort{38368500,32608600,31736000} averaging:34237700
     *  times for quickSort{1187900,999500,1003200} averaging:1063533
     *  times for mergeSortI{3536900,3318800,3997000} averaging:3617566
     *
     */
    public static void main(String[] args) {
        String[] files = new String[]{"numbers1000", "numbers1000Duplicates", "numbersNearlyOrdered1000", "numbersReverse1000", "numbersSorted1000", "numbers10000"};
        double[] sorted;
        double[] numbers;
        for(String s: files){
            System.out.println("checking for "+s);
            numbers = getNumbers(s+".txt", (s.equals(files[files.length-1]))?10000:1000);
            sorted = numbers.clone();
            Arrays.sort(sorted);
            Method[] methods = SortComparison.class.getDeclaredMethods();
            for (Method m: methods){
                if(m.getReturnType().isArray() && m.getParameterTypes().length==1){
                    try {
                        testTimes(sorted, numbers, 3, m, m.getName());
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println();
        }
    }
    private static double[] getNumbers(String fileName, int count){
        File file = new File(fileName); //try local directory first
        if(!file.exists()) {            //try path though my workspace
            file = new File("src/com/company/algorithms/sorting/" + fileName);
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            double[] out = new double[count];
            for (int i = 0; i < count; i++) {
                out[i] = Double.parseDouble(reader.readLine());
            }
            return out;
        }catch (Exception e){
            return null;
        }
    }

    private static void testTimes(double[] answer, double[] input, int cycles, Method method, String name) throws InvocationTargetException, IllegalAccessException {
        double[] pass;
        long total = 0;
        long time1;
        long time2;
        System.out.print("times for "+name+"{");
        for (int i = 0; i < cycles; i++) {
            pass = input.clone();
            time1 = System.nanoTime();
            assertArrayEquals(name+" sort failed to sort array",answer, (double[]) method.invoke(null, pass),0);
            //should flag a problem if the result is wrong
            time2 = System.nanoTime();
            System.out.print((time2-time1)+(i<cycles-1?",":""));
            total += time2- time1;
        }
        System.out.println("} averaging:"+(total)/cycles);

    }
}

