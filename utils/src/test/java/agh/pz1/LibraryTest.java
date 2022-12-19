package agh.pz1;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private final Library lib = new Library();

    @org.junit.jupiter.api.Test
    public void testConcurrentReaders() throws InterruptedException {
        // Test that multiple readers can enter and exit the lib concurrently
        Thread reader1 = new Thread(() -> {
            try {
                lib.readerEnter();
                Thread.sleep(500);
                lib.readerExit();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread reader2 = new Thread(() -> {
            try {
                lib.readerEnter();
                Thread.sleep(500);
                lib.readerExit();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        reader1.start();
        reader2.start();
        reader1.join();
        reader2.join();
        assertEquals(0, lib.getReadersCount());
        assertEquals(0, lib.getWritersCount());
    }

    @org.junit.jupiter.api.Test
    public void testConcurrentReadersAndWriters() throws InterruptedException {
        // Test that a writer has to wait for all readers to exit before entering the lib
        Thread reader1 = new Thread(() -> {
            try {
                lib.readerEnter();
                Thread.sleep(500);
                lib.readerExit();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread reader2 = new Thread(() -> {
            try {
                lib.readerEnter();
                Thread.sleep(500);
                lib.readerExit();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread writer = new Thread(() -> {
            try {
                lib.writerEnter();
                Thread.sleep(500);
                lib.writerExit();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        reader1.start();
        reader2.start();
        writer.start();
        reader1.join();
        reader2.join();
        writer.join();
        assertEquals(0, lib.getReadersCount());
        assertEquals(0, lib.getWritersCount());
    }
}
