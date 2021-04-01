package com.company.algarithims.sorting;// -------------------------------------------------------------------------

import java.util.LinkedList;
import java.util.Queue;

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author dunneb5
 *  @version HT 2021
 */

class SortComparison {
   private static int cmpCounter = 0;
   private static int arrayAccessCounter = 0;

   private static void swap(double[] array, int a, int b){
      double help = array[a];
      array[a] = array[b];
      array[b] = help;
      arrayAccessCounter ++;
   }

   /**
    * Sorts an array of doubles using InsertionSort.
    * This method is static, thus it can be called as SortComparison.sort(a)
    * @param a: An unsorted array of doubles.
    * @return array sorted in ascending order.
    */
   static double [] insertionSort (double[] a){
      for (int i = 1; i < a.length; i++) {
         int j = i;
         while (j>0 && a[j]<a[j-1]){
            cmpCounter ++;
            swap(a,j,j-1);
            j--;
         }
      }
      return a;
   }//end insertionsort
	
   /**
    * Sorts an array of doubles using Selection Sort.
    * This method is static, thus it can be called as SortComparison.sort(a)
    * @param a: An unsorted array of doubles.
    * @return array sorted in ascending order
    */
   static double [] selectionSort (double a[]){
      for (int i = 0; i < a.length; i++) {
         int min = i;
         for (int j = min; j < a.length; j++) {
            cmpCounter++;
            if(a[j]<a[min]) {
               min = j;
            }
         }
         swap(a,i,min);
      }
      return a;
   }//end selectionsort

   /**
    * Sorts an array of doubles using Quick Sort.
    * This method is static, thus it can be called as SortComparison.sort(a)
    * @param a: An unsorted array of doubles.
    * @return array sorted in ascending order
    */
   static double [] quickSort (double[] a){
      return quickSortRecur(a, 0, a.length);
   }//end quicksort

   private static double [] quickSortRecur (double[] a, int low, int high){
      if(low==high){
         return a;
      }
      int pivot = high-1;
      int i = low-1;
      int j = low;
      while(j < pivot){
         cmpCounter++;
         if(a[j]<=a[pivot]){
            i++;
            if(i!=j) swap(a, i, j); //skip pointless swaps
         }
         j++;
      }
      i++;
      swap(a, i, pivot);
      quickSortRecur(a, low, i);
      quickSortRecur(a,i+1,high);
      return a;
   }//end quicksort recur

   /**
    * Sorts an array of doubles using Merge Sort.
    * This method is static, thus it can be called as SortComparison.sort(a)
    * @param a: An unsorted array of doubles.
    * @return array sorted in ascending order
    */
   static double[] mergeSort (double[] a) {
      return mergeSortRecur(a, 0, a.length-1);
   }//end mergesort
   private static double[] mergeSortRecur(double[] a, int low, int high) {
      if (high-low==1){
         cmpCounter++;
         if(a[low]>a[high]) swap(a, low, high);
         return a;
      }else if(high-low<1){
         return a;
      }
      int mid = low+((high-low)/2);
      //split left
      mergeSortRecur(a,low, mid);
      //split right
      mergeSortRecur(a,mid+1,high);
      //merge
      merge(a,low,mid, high);
      return a;
   }
   private static void merge(double[] a, int low, int mid, int high){
      cmpCounter++;
      if(a[mid]<a[mid+1]) {
         return;
      }
      else {
         double[] help = new double[(high-low)+1];
         int l = low;
         int h = mid+1;
         for (int i = 0; i < help.length; i++) {
            cmpCounter++;
            if (h>high || (a[l]<=a[h] && l<=mid)){
               help[i]=a[l];
               l++;
            }else {
               help[i] = a[h];
               h++;
            }
         }
         //pass back to main
         System.arraycopy(help,0,a,low,help.length);
         arrayAccessCounter+=help.length;
      }
   }
   /**
    * Sorts an array of doubles using iterative implementation of Merge Sort.
    * This method is static, thus it can be called as SortComparison.sort(a)
    *
    * @param a: An unsorted array of doubles.
    * @return after the method returns, the array must be in ascending sorted order.
    */
   static double[] mergeSortI (double[] a) {
      Queue<int[]> queue = new LinkedList<int[]>();
      for (int i = 0; i < a.length; i+=2) {
         int u = Math.min(i+1, a.length-1);
         queue.add(new int[]{i,u}); //setup
         cmpCounter++;
         if(a[i]>a[u]) swap(a,i,u);     //locally sort
      }
      while(queue.size()>1){
         int[] groupA = queue.poll();
         int[] groupB = queue.peek();
         if(groupA[1]+1==groupB[0]){
            queue.poll();
            merge(a, groupA[0], groupA[1], groupB[1]);
            queue.add(new int[]{groupA[0],groupB[1]});
         }else {
            queue.add(groupA);
         }
      }
      return a;
   }//end mergesort

   public static void resetCounters(){
      cmpCounter = 0;
      arrayAccessCounter = 0;
   }
   public static void showCounters(String msg){
      System.out.println(String.format("%1$" + 30 + "s", msg)+":"+cmpCounter+" compares and "+arrayAccessCounter+" swaps");
   }

 }//end class

