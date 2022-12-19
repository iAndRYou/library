package agh.pz1;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static Library library = new Library();
    private static ArrayList<Thread> visitors = new ArrayList<>();

    public static void main(String[] args) {
        try {
            int readersCount = 10;
            int writersCount = 3;
            createVisitors(readersCount, writersCount);
            initialiseVisitors();
        } catch (Exception e) {
            System.out.println("Invalid arguments");
        }
    }

    private static void createVisitors(int readersCount, int writersCount) {
        for (int i = 0; i < readersCount; i++) {
            visitors.add(new Reader(library, i+1));
        }
        for (int i = 0; i < writersCount; i++) {
            visitors.add(new Writer(library, i+1));
        }
        Collections.shuffle(visitors);
    }

    private static void initialiseVisitors() {
        try {
            for (Thread visitor : visitors) {
                Thread.sleep(500);
                visitor.start();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
