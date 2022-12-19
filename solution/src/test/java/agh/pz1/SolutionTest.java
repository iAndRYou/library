package agh.pz1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

class SolutionTest {
    private final Library lib = new Library();

    @org.junit.jupiter.api.Test
    void testCreateVisitorsCreatesReadersAndWriters() {
        int readersCount = 2;
        int writersCount = 3;
        ArrayList<Thread> visitors = new ArrayList<>();
        Solution.createVisitors(readersCount, writersCount, visitors);
        assertEquals(5, visitors.size());
        // check if the number of readers and writers is correct
        for(Thread visitor : visitors) {
            if(visitor instanceof Reader) {
                readersCount--;
            } else if(visitor instanceof Writer) {
                writersCount--;
            }
        }
        assertEquals(0, readersCount);
        assertEquals(0, writersCount);
    }

    @org.junit.jupiter.api.Test
    void testCreateVisitorsShuffled() {
        int readersCount = 2;
        int writersCount = 3;
        ArrayList<Thread> visitors = new ArrayList<>();
        Solution.createVisitors(readersCount, writersCount, visitors);
        ArrayList<Thread> otherVisitors = new ArrayList<>();
        Solution.createVisitors(readersCount, writersCount, otherVisitors);
        assertNotEquals(visitors, otherVisitors);
        // check if the number of readers and writers is correct
        for(Thread visitor : visitors) {
            if(visitor instanceof Reader) {
                readersCount--;
            } else if(visitor instanceof Writer) {
                writersCount--;
            }
        }
        assertEquals(0, readersCount);
        assertEquals(0, writersCount);
        for(Thread visitor : otherVisitors) {
            if(visitor instanceof Reader) {
                readersCount++;
            } else if(visitor instanceof Writer) {
                writersCount++;
            }
        }
        assertEquals(2, readersCount);
        assertEquals(3, writersCount);
    }

    @org.junit.jupiter.api.Test
    void testInitialiseVisitors_StartsAllVisitors() throws InterruptedException {
        ArrayList<Thread> visitors = new ArrayList<>();
        visitors.add(new Reader(lib, 1));
        visitors.add(new Writer(lib, 1));
        visitors.add(new Reader(lib, 2));
        Solution.initialiseVisitors(visitors);
        for(Thread visitor : visitors) {
            visitor.join();
        }
        assertEquals(0, lib.getReadersCount());
        assertEquals(0, lib.getWritersCount());
        assertEquals(0, lib.getReadersInQueueCount());
        assertEquals(0, lib.getWritersInQueueCount());
    }
}