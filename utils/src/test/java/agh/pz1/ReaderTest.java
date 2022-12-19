package agh.pz1;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {
    private final Library lib = new Library();

    @org.junit.jupiter.api.Test
    public void testReader() throws InterruptedException {
        // Test that a reader can enter and exit the lib
        Reader reader = new Reader(lib, 1);
        reader.start();
        reader.join();
        assertEquals(0, lib.getReadersCount());
        assertEquals(0, lib.getWritersCount());

        // Test that multiple readers can enter and exit the lib concurrently
        Reader reader2 = new Reader(lib, 2);
        Reader reader3 = new Reader(lib, 3);
        reader2.start();
        reader3.start();
        reader2.join();
        reader3.join();
        assertEquals(0, lib.getReadersCount());
        assertEquals(0, lib.getWritersCount());
    }
}
