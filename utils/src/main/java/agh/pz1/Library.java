package agh.pz1;

import java.util.concurrent.Semaphore;

public class Library {
    private int readersCount = 0;
    private int writersCount = 0;
    private int readersInQueueCount = 0;
    private int writersInQueueCount = 0;
    private Semaphore readerAction = new Semaphore(1);
    private Semaphore library = new Semaphore(1);
    private Semaphore queue = new Semaphore(1);
    private Semaphore capacity = new Semaphore(5);

    public void readerEnter() throws InterruptedException {
        readersInQueueCount++;
        log();

        queue.acquire();
        capacity.acquire();
        readerAction.acquire();
        readersInQueueCount--;
        readersCount++;
        if (readersCount == 1) {
            library.acquire();
        }

        log();
        readerAction.release();
        queue.release();
    }

    public void readerExit() throws InterruptedException {
        readerAction.acquire();

        readersCount--;
        if (readersCount == 0) {
            library.release();
        }
        readerAction.release();
        capacity.release();
    }

    public void writerEnter() throws InterruptedException {
        writersInQueueCount++;
        log();

        queue.acquire();
        library.acquire();
        writersInQueueCount--;
        writersCount++;

        log();
        queue.release();
    }

    public void writerExit() {
        writersCount--;
        library.release();
    }

    private void log () {
        System.out.println("Status:");
        System.out.println("Queue - readers: " + readersInQueueCount + ", writers: " + writersInQueueCount);
        System.out.println("Library - readers: " + readersCount + ", writers: " + writersCount);
        System.out.println();
    }
}
