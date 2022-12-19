package agh.pz1;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    private final Library lib = new Library();

    @org.junit.jupiter.api.Test
    public void testWriterEnter() throws InterruptedException {
        Writer writer = new Writer(lib, 1);
        writer.start();
        writer.join();
        assertEquals(0, lib.getReadersCount());
        assertEquals(0, lib.getWritersCount());
        assertEquals(0, lib.getReadersInQueueCount());
        assertEquals(0, lib.getWritersInQueueCount());
    }
}
