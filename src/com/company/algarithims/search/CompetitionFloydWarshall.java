package com.company.algarithims.search;
/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 *
 *
 * brian dunne 18318357
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class CompetitionFloydWarshall {

    private static double[][] readFromFile(File file){
        //System.out.println(file.getAbsolutePath());
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            int size = Integer.parseInt(fileReader.readLine());
            double[][] matrix= new double[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(i!=j){
                        matrix[i][j] = Double.POSITIVE_INFINITY;
                    }
                }
            }
            int nRoads = Integer.parseInt(fileReader.readLine()); //det number of roads
            Scanner scanner;
            int a, b;
            float l;
            for (int i = 0; i < nRoads; i++) {
                scanner = new Scanner(fileReader.readLine());
                a = scanner.nextInt();
                b = scanner.nextInt();
                l = scanner.nextFloat();
                matrix[a][b] = l;
            }
            return matrix;
        }catch (Exception e){
            System.out.println("Something went wong while reading '"+file.getName()+"/n"+e.toString());
            return null;
        }
    }

    private final double[][] adjMatrix;
    private final int sA, sB, sC;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
        adjMatrix = readFromFile(new File(CompetitionTests.PATHS[CompetitionTests.PATH]+filename));
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
        if (adjMatrix == null || (sA<50||sA>100 || sB<50||sB>100 || sC<50||sC>100)){
            return -1;
        }
        for (int t = 0; t < 2; t++) {
            for (int e = 0; e < adjMatrix.length; e++) {
                for (int i = 0; i < adjMatrix.length; i++) {//for node
                    for (int j = 0; j < adjMatrix.length; j++) { //check other nodes
                        if(j!=i) {
                            for (int k = 0; k < adjMatrix.length; k++) { //is there an alternative path through k
                                if(k!=i&&k!=j){
                                    //System.out.println("("+i+","+j+","+k+")");
                                    if(adjMatrix[i][k]+adjMatrix[k][j]<adjMatrix[i][j]){
                                        if(t == 0){
                                            adjMatrix[i][j] = adjMatrix[i][k]+adjMatrix[k][j];
                                            //save path somehow but i don't think it's relavant in this instance
                                        }else {
                                            adjMatrix[i][j] = Double.NEGATIVE_INFINITY;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        double max = Double.NEGATIVE_INFINITY;
        for (double[] column : adjMatrix) {
            for (int j = 0; j < adjMatrix.length; j++) {
                max = Math.max(max, column[j]);
            }
        }
        return (int) ((max*1000)/((double)Math.max(sA,Math.max(sB,sC))))/60;
    }
}