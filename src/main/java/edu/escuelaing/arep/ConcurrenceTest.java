package edu.escuelaing.arep;

import java.util.ArrayList;

/**
 * This is a class that creates the thread and makes test concurrent
 * @author: Francisco Javier Rojas M
 * @version: 18/08/2022/A
 */
public class ConcurrenceTest extends Thread{
    private static SparkWebApp app;
    private static Integer totalThreads;
    private static ArrayList<TestThread> threads;
    /**
     * This is the method that creates the thread and makes the test of concurrence
     * @param args
     */
    public static void main(String[] args) {
        totalThreads = 1000;
        app = new SparkWebApp();
        threads = new ArrayList<TestThread>();
        for (int i = 0; i < totalThreads; i++) {
            threads.add(new TestThread());
            threads.get(i).start();
        }

    }
}
