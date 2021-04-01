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
 * This class implements the competition using Dijkstra's algorithm
 *
 *
 * brian dunne 18318357
 */

import org.omg.IOP.CodecPackage.InvalidTypeForEncoding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class CompetitionDijkstra {

    private final Intercection[] intersections;
    public final int sA, sB, sC;

    /**
     * @param sA, sB, sC: speeds for 3 contestants
     * @param filename : A filename containing the details of the city road network
     */
    CompetitionDijkstra(String filename, int sA, int sB, int sC){
        intersections = readFromFile(new File(CompetitionTests.PATHS[CompetitionTests.PATH]+filename));
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
    }

    private Intercection[] readFromFile(File file){
        //System.out.println(file.getAbsolutePath());
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            int size = Integer.parseInt(fileReader.readLine());
            Intercection[] intercections = new Intercection[size];
            int nRoads = Integer.parseInt(fileReader.readLine()); //det number of roads
            Scanner scanner;
            int a, b;
            float l;
            for (int i = 0; i < nRoads; i++) {
                scanner = new Scanner(fileReader.readLine());
                a = scanner.nextInt();
                b = scanner.nextInt();
                l = scanner.nextFloat();
                if (intercections[a] == null) {
                    intercections[a] = new Intercection(a);
                }
                intercections[a].addRoad(b, l);
            }
            for (int i = 0; i < intercections.length; i++) {
                if (intercections[i] == null){
                    intercections[i] = new Intercection(i);
                }
            }
            return intercections;
        }catch (Exception e){
            System.out.println("Something went wong while reading '"+file.getName()+"/n"+e.toString());
            return null;
        }
    }

    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition() {
        if(intersections == null || (sA<50||sA>100 || sB<50||sB>100 || sC<50||sC>100)){
            return -1;
        }
        double max = Double.NEGATIVE_INFINITY;
        for (Intercection a : intersections) {
            for (Intercection b : intersections) {
                float[][] path = getShortestPath(a,b);
                if(path == null){
                    max = Double.POSITIVE_INFINITY;
                }else {
                    max = Math.max(max, path[path.length - 1][1]);
                }
            }
        }
        return (int) ((max*1000)/((double)Math.max(sA,Math.max(sB,sC))))/60;
    }

    public float[][] getShortestPath(Intercection a, Intercection b){
        BitSet set = new BitSet();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Node dest = new Node(-1, b,-1);
        Node current = new Node(0,a,0);
        queue.add(current);
        while(!queue.isEmpty() && !(current = queue.poll()).equals(dest)){
            if(!set.get(current.intercection.id)){
                set.set(current.intercection.id);
                queue.addAll(current.getChildren(this));
            }
        }
        if(current.equals(dest)) {
            float[][] out = new float[current.dept + 1][2];
            while (current != null) {
                out[current.dept][0] = current.intercection.id;
                out[current.dept][1] = current.l;
                current = current.parent;
            }
            return out;
        }
        return null;
    }


    private class Node implements Comparable<Node>{
        Intercection intercection;
        Node parent;
        int dept;
        float l;

        public Node(float length, Intercection intersection, Node parent, int dept) {
            l = length;
            this.intercection = intersection;
            this.parent = parent;
            this.dept = dept;
        }
        public Node(float length, Intercection intersection, int dept){ this (length,intersection,null,dept);}

        @Override
        public int compareTo(Node o) {
            return Float.compare(l, o.l);
        }

        @Override
        public boolean equals(Object obj) {
            return intercection.equals(((Node)obj).intercection);
        }

        public ArrayList<Node> getChildren(CompetitionDijkstra searchSpace){
            Road[] roads = intercection.getRoads();
            ArrayList<Node> children = new ArrayList<>(roads.length);
            for (Road road : roads) {
                children.add(new Node(l+road.length, searchSpace.intersections[road.dest], this, dept+1));
            }
            return children;
        }
    }

    private class Intercection{
        public final int id;
        private final ArrayList<Road> roads;
        public Intercection(int id){
            this.id = id;
            roads = new ArrayList<>();
        }
        public void addRoad(int b, float l){
            roads.add(new Road(b,l));
        }
        public Road[] getRoads(){
            Road[] roads = new Road[this.roads.size()];
            return this.roads.toArray(roads);
        }

        @Override
        public boolean equals(Object obj) {
            return id == ((Intercection)obj).id;
        }
    }
    private class Road{
        public final int dest;
        public final float length;
        public Road(int dest, float length){
            this.dest = dest;
            this.length = length;
        }
    }
}
