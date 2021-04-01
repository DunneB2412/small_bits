package com.company.algarithims.sorting;

public class TestingClass {
    public static void main(String[] args) {
        TestingClass a3 = new TestingClass();

        //System.out.print("Before: ");		printArray(arr);
        a3.testSpeed(10);
        //System.out.print("After: ");		printArray(arr);

        //printArray(arr);
        a3.testSpeed(99);
        //System.out.print("After: ");		printArray(arr);

        //printArray(arr);
        a3.testSpeed(100);
        //System.out.print("After: ");		printArray(arr);

        //printArray(arr);
        a3.testSpeed(101);
        //System.out.print("After: ");		printArray(arr);

        //printArray(arr);
        a3.testSpeed(500);
        //System.out.print("After: ");		printArray(arr);

        //printArray(arr);
        a3.testSpeed(5000);
        //System.out.print("After: ");		printArray(arr);

    }
    public void testSpeed(int size)
    {
        double[] arr = createArray(size);

        System.out.print("Before: ");
        printArray(arr);

        long start = System.nanoTime();
        mergeInsertSort(arr,0,arr.length);
        long end = System.nanoTime();
        long timeToRun = end-start;

        System.out.print("After: ");
        printArray(arr);

        System.out.println("Time in nanseconds for array size "+size+": "+timeToRun);

        checkSort(arr); // If array is not sorted it will print an error
        if (arr.length<102)	printArray(arr);
    }
    public double[] createArray(int numElements)
    {
        double[] myArray = new double[numElements];
        for(int i=0;i<numElements;i++){
            myArray[i] = Math.random()*100;
        }
        return myArray;
    }

    public double[] checkSort(double[] arr)
    {
        for(int i=0; i<arr.length-1; i++){
            if (arr[i] > arr[i+1])
            {
                for(int j=0; j<arr.length; j++){
                    if (j==i)System.out.print("###");
                    if (j==i+2)System.out.print("###");
                    System.out.print(arr[j] + " ");
                }
                System.out.println();
                System.out.println();
                System.out.printf("!!!!! Array is not sorted, value at index %d is bigger than the next value\n", i);
                System.out.println();
                break;
            }
        }
        return arr;
    }

    public void printArray(double[] myArray)
    {
        for(int j=0;j<myArray.length;j++){
            System.out.print(myArray[j]+" ");
        }
        System.out.println();
    }

    public void mergeInsertSort(double[] arr, int lb, int ub) {
        //checking if the length is under a certain length
        if(ub - lb < 100) {
            System.out.println("in if statement");
            insertSort(arr,lb,ub);
        }else {
            System.out.println("in else");
            if(lb + 1 < ub){
                System.out.println("in if");
                int mid = (lb + ub)/2;
                mergeInsertSort(arr, lb, mid);
                mergeInsertSort(arr, mid, ub);
                merge(arr, lb, mid, ub);
            }
        }
    }

    public void merge(double[] arr, int lo, int mid, int hi) {
        int i = lo;
        int j = mid;
        //use temp array to store merged sub-sequence
        double temp[] = new double[hi - lo];
        int t = 0;
        while(i < mid && j < hi){
            if(Double.compare(arr[i], arr[j])<=0){
                temp[t] = arr[i]; i++; t++;
            }
            else{
                temp[t] = arr[j]; j++; t++;
            }
        }
    }

    public void insertSort(double[] arr, int start, int end) {
        for (int i = start; i < end-1; i++) {
            System.out.println("in insert sort");
            int j = i;
            while (j>0 && arr[j]<arr[j-1]){
                double help = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = help;
                j--;
            }
        }
    }
}
