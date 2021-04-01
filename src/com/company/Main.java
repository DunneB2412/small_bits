package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

//        final Object lock = new Object();
//
//
//        MyThread t = new MyThread();
//        t.lock = lock;
//        t.run();
//
//        while (true) {
//            try {
//                synchronized (lock) {
//                    lock.wait();
//                }
//                System.out.println("hello");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

//        for (int i = 0; i < 100; i++) {
//            int[] a = new int[i];
//            fib(a.length-1,a);
//            System.out.print("{");
//            for(int j: a){
//                System.out.print(j+",");
//            }
//            System.out.println("\b}");
//        }

    public static void main(String[] args) {
        double[] x = new double[]{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150};
        double[] y = new double[]{4.2,6.8,5.2,8.4,5.9,10.4,9.1,12.4,9.4,12.8,12.9,16.2,11.7,12.9,14.3};

        double b1 = sSab(x,y)/sSab(x,x);
        double b0 = mean(y)-(b1*mean(x));
        System.out.println("b0 = "+b0+", and b1 = "+b1);

        double[] results = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            results[i] = Math.pow(y[i]-b0-(b1*x[i]),2);
        }
        double mse = doubleSum(results)*(1d/(x.length-2));
        System.out.println("MSE = "+mse);
    }

    public static double mean(double[] a){
        return doubleSum(a)/a.length;
    }

    public static double doubleSum(double[] a){
        return doubleSumR(a,0,a.length);
    }
    public static double doubleSumR(double[] a, int l, int u){
        if(u==l){
            return 0;
        }
        if(u-l==1){
            return a[l];
        }
        if(u-l==2){
            return a[u-1] + a[l];
        }
        int mid = (l + u+1)/2;
        return doubleSumR(a,l,mid)+doubleSumR(a,mid,u);
    }

    public static double sSab(double[] a, double[] b){
        //System.out.println("llllllllllllllllllllllllllllllllllllllllllllllllllllllll\n");
        if(a.length!=b.length){
            throw new IllegalArgumentException("size of a dose not match b");
        }
        double ma = mean(a);
        double mb = mean(b);
        double[] results = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            results[i] = (a[i]*b[i]);
            //System.out.println(results[i]+"("+i+")");
        }
        return doubleSum(results)-(a.length*ma*mb);
    }



//    public static void fib(int i, int f[]){
//        if(i == -1);
//        else if(i == 0) f[0] = 1;
//        else if(i == 1) {
//            f[0] = f[1] = 1;
//        }
//        else {
//            fib(i-1,f);
//            f[i] = f[i-1]+f[i-2];
//        }
//    }
}


//class MyThread extends Thread {
//
//    Object lock;
//
//    @Override
//    public void run() {
//
//        JFrame fr = new JFrame("Anothing");
//        JButton btn = new JButton("Next");
//        btn.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                synchronized (lock) {
//                    lock.notify();
//                }
//
//            }
//        });
//        fr.setLayout(new FlowLayout());
//        fr.add(btn);
//        fr.setSize(400, 400);
//        fr.setVisible(true);
//    }
//}