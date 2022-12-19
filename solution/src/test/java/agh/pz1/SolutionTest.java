package agh.pz1;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {

    @Mock
    private Library mockLib;

    @Before
    public void setUp() {
        mockLib = org.mockito.Mockito.mock(Library.class);
        Solution.lib = mockLib;
    }

    @org.junit.jupiter.api.Test
    public void testCreateVisitors_CreatesReadersAndWriters() {
        int readersCount = 2;
        int writersCount = 3;
        ArrayList<Thread> visitors = new ArrayList<>();
        Solution.createVisitors(readersCount, writersCount, visitors);
        assertEquals(5, visitors.size());
    }

    @org.junit.jupiter.api.Test
    public void testCreateVisitors_ShufflesVisitors() {
        int readersCount = 2;
        int writersCount = 3;
        ArrayList<Thread> visitors = new ArrayList<>();
        Solution.createVisitors(readersCount, writersCount, visitors);
        ArrayList<Thread> visitors2 = new ArrayList<>();
        Solution.createVisitors(readersCount, writersCount, visitors2);
        assertNotEquals(visitors, visitors2);
    }

    @org.junit.jupiter.api.Test
    public void testInitialiseVisitors_StartsAllVisitors() throws InterruptedException {
        ArrayList<Thread> visitors = new ArrayList<>();
        visitors.add(new Reader(mockLib, 1));
        visitors.add(new Writer(mockLib, 1));
        visitors.add(new Reader(mockLib, 2));
        Solution.initialiseVisitors(visitors);
        verify(mockLib, times(2)).readerEnter();
        verify(mockLib, times(1)).writerEnter();
    }
}