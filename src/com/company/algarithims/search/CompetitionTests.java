package com.company.algarithims.search;
/*
 *
 *
 *
 * brian dunne 18318357
 */

import org.junit.Test;

import static org.junit.Assert.*;


public class CompetitionTests {
    public static final String[] PATHS = new String[]{"src/com/company/algarithims/search/inputAssignment2/","src/com/company/algarithims/search/",""};
    public static final int PATH = 1;

    @Test
    public void testDijkstraConstructor(){
        CompetitionDijkstra t = new CompetitionDijkstra("tinyEWD.txt", 1,1,1);
        for (int i = 0; i < 'Z'-'A'; i++) {
            char car = (char) (i+'A');
            t = new CompetitionDijkstra("input-" + car +".txt", 1, 1, 1);
        }
    }


    @Test
    public void testFWConstructor(){
        CompetitionFloydWarshall t = new CompetitionFloydWarshall("tinyEWD.txt", 1,1,1);
        for (int i = 0; i < 'Z'-'A'; i++) {
            char car = (char) (i+'A');
            t = new CompetitionFloydWarshall("input-" + car+".txt", 1, 1, 1);
        }
    }

    @Test
    public void testComputeDistances(){//makes sure that the algorithms agree on the results, with such different approaches, it's highly unlikely they'll agree on the same wrong answer
        CompetitionDijkstra t = new CompetitionDijkstra("tinyEWD.txt", 51,70,88);
        CompetitionFloydWarshall t2 = new CompetitionFloydWarshall("tinyEWD.txt", 72,70,65);
        int da = t.timeRequiredforCompetition();
        int db = t2.timeRequiredforCompetition();
        System.out.println("tinyEWD:" + da+"v"+db);
        for (int i = 0; i <= 'N'-'A'; i++) {
            char car = (char) (i + 'A');
            t = new CompetitionDijkstra("input-" + car + ".txt", 50, 50, 50);
            t2 = new CompetitionFloydWarshall("input-" + car + ".txt", 50, 50, 50);
            da = t.timeRequiredforCompetition();
            db = t2.timeRequiredforCompetition();
            System.out.println(car + ":" + da + "v" + db);
            assertEquals("algorithms didn't agree for:" + car, da, db);
        }
        for (int i = 0; i < 12; i++) {

            t = new CompetitionDijkstra("input-A.txt", 1, 1, 1);

        }
    }

    @Test
    public void testInvalidSpeeds(){ //teste all combinations of v{0-120:+10} and 50 on the algorithms
        CompetitionDijkstra t;
        CompetitionFloydWarshall t2;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 8; j++) {
                int s1 = (i*(10*(j%2)))+(50*((j+1)%2));         //just wanted to see if i could formulise combinations. at least it doesn't have branches
                int s2 = (i*(10*((j/2)%2)))+(50*(((j/2)+1)%2));
                int s3 = (i*(10*((j/4)%2)))+(50*(((j/4)+1)%2));
                t = new CompetitionDijkstra("input-A.txt",s1, s2, s3);
                t2 = new CompetitionFloydWarshall("input-A.txt",s1, s2, s3);
                String msg = "search should return -1 if and only if one or more of the speeds exde the bounds of 50>= speed <= 100\n"+
                        "received speeds of:["+s1+","+s2+","+s3+"]";
                boolean validInputs = !(s1<50||s1>100 || s2<50||s2>100 || s3<50||s3>100);
                assertTrue(msg, t.timeRequiredforCompetition() == -1 || validInputs);
                assertTrue(msg, t2.timeRequiredforCompetition() == -1 || validInputs);
            }
        }
    }

}