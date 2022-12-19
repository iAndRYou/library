package agh.pz1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The main class of the program.
 * Creates all readers and writers and starts them.
 * @author Andrzej Kmiecik
 * @version 1.0
 */
public class Solution {

    /**
     * The lib object.
     * Handles all the lib logic.
     */
    static Library lib = new Library();

    /**
     * The list of all readers and writers.
     */
    private static ArrayList<Thread> visitors = new ArrayList<>();

    /**
     * The main method of the program.
     * Creates all readers and writers and starts them.
     * @param args the number of readers and writers respectively read from commandline: [readersCount] [writersCount]
     * @throws InterruptedException if the main thread is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        try {
            int readersCount = Integer.parseInt(args[0]);
            int writersCount = Integer.parseInt(args[1]);
            createVisitors(readersCount, writersCount, visitors);
            initialiseVisitors(visitors);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Default values used: 10 readers, 3 writers.");
            int readersCount = 10;
            int writersCount = 3;
            createVisitors(readersCount, writersCount, visitors);
            initialiseVisitors(visitors);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Creates all readers and writers and shuffles them.
     * @param readersCount the number of readers
     * @param writersCount the number of writers
     */
    public static void createVisitors(int readersCount, int writersCount, ArrayList<Thread> visitors) {
        for (int i = 0; i < readersCount; i++) {
            visitors.add(new Reader(lib, i+1));
        }
        for (int i = 0; i < writersCount; i++) {
            visitors.add(new Writer(lib, i+1));
        }
        Collections.shuffle(visitors);
    }

    /**
     * Starts all readers and writers. 
     * Delay of 500ms between each one.
     * @throws InterruptedException
     */
    public static void initialiseVisitors(ArrayList<Thread> visitors) throws InterruptedException {
        try {
            for (Thread visitor : visitors) {
                Thread.sleep(500);
                visitor.start();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
