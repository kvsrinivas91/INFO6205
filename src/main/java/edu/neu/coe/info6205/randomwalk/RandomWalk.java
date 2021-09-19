/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import org.jfree.ui.RefineryUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class ListItem{
    private int steps;
    private double euclideanDistance;

    /**
     * I created a ListItem to store all the values of d and n to send it to LineChart Program to make a graph of the values
     *
     * @param steps the number of steps taken by man
     * @param euclideanDistance the displacement between the man's current position and the initial point
     * */
    public ListItem(int steps, double euclideanDistance) {
        this.steps = steps;
        this.euclideanDistance = euclideanDistance;
    }

    public int getSteps() {
        return steps;
    }

    public double getEuclideanDistance() {
        return euclideanDistance;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setEuclideanDistance(double euclideanDistance) {
        this.euclideanDistance = euclideanDistance;
    }
}

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        // TO BE IMPLEMENTED

        /**
         * adding dx to x and dy to y, to move the position of the man
         * */
        x+=dx;
        y+=dy;
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        // TO BE IMPLEMENTED

        /** Invoking ramdomMove function for m number of time
         * */
        for(int i=0;i<m;i++){
            randomMove();
        }


    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        // TO BE IMPLEMENTED
        /** Formula to find the Euclidean distance
         * */
        return Math.sqrt((x*x)+(y*y));

    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance / n;
    }


    public static void main(String[] args) {

        /**
        * I am passing steps in arguments and setting n(number of experiments) as 999.
        * */
        if (args.length == 0)
            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
        int steps = Integer.parseInt(args[0]);
        int n = 999;

        List<ListItem> objectList = new ArrayList<>();
        /**
        * Made changes to the main function to run the program for multiple steps
        * */
        for(int m=0;m<=steps;m=m+1){
            double meanDistance = randomWalkMulti(m, n);
            System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
//            System.out.println(meanDistance);
            ListItem li = new ListItem(m, meanDistance);
            objectList.add(li);
        }

        /**
         * Invoking function to create graph
        * */
        LineChart chart = new LineChart( "Euclidean Distance vs Steps" , "Euclidean Distance vs Steps", objectList, steps);

        chart.pack( );
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible( true );


    }

}

