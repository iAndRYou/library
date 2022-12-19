package agh.pz1;

import java.util.concurrent.Semaphore;

/**
 * The class representing a library.
 * It handles all the logic of the readers-writers problem.
 */
public class Library {
    /**
     * The number of readers currently in the library.
     */
    private int readersCount = 0;

    /**
     * The number of writers currently in the library.
     */
    private int writersCount = 0;

    /**
     * The number of readers currently in the queue.
     */
    private int readersInQueueCount = 0;

    /**
     * The number of writers currently in the queue.
     */
    private int writersInQueueCount = 0;

    /**
     * The semaphore controlling which reader can perform an action.
     * It prevents all readers from leaving at the same time.
     */
    private Semaphore readerAction = new Semaphore(1);

    /**
     * The semaphore controlling the access to the library.
     * Only one writer can enter the library given that there are no readers in the library.
     */
    private Semaphore library = new Semaphore(1);

    /**
     * The semaphore controlling who is the first in queue/
     * The first in queue can enter the library.
     */
    private Semaphore queue = new Semaphore(1);

    /**
     * The semaphore controlling the capacity of the library.
     * The library can hold up to 5 readers at the same time.
     */
    private Semaphore capacity = new Semaphore(5);

    /**
     * The method called by a reader when he wants to enter the library.
     * He has to wait for his turn in the queue and then for the library to be free.
     * @throws InterruptedException
     */
    public void readerEnter() throws InterruptedException {
        readersInQueueCount++;
        Thread.sleep(5);

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

    /**
     * The method called by a reader when he wants to leave the library.
     * @throws InterruptedException
     */
    public void readerExit() throws InterruptedException {
        readerAction.acquire();

        readersCount--;
        if (readersCount == 0) {
            library.release();
        }
        readerAction.release();
        capacity.release();
    }

    /**
     * The method called by a writer when he wants to enter the library.
     * He has to wait for his turn in the queue and then for the library to be free.
     * @throws InterruptedException
     */
    public void writerEnter() throws InterruptedException {
        writersInQueueCount++;
        Thread.sleep(5);

        queue.acquire();
        library.acquire();
        writersInQueueCount--;
        writersCount++;

        log();
        queue.release();
    }

    /**
     * The method called by a writer when he wants to leave the library.
     */
    public void writerExit() {
        writersCount--;
        library.release();
    }

    /**
     * Prints the current status of the library.
     */
    private void log() {
        System.out.println("Status:");
        System.out.println("Queue - readers: " + readersInQueueCount + ", writers: " + writersInQueueCount);
        System.out.println("Library - readers: " + readersCount + ", writers: " + writersCount);
        System.out.println();
    }
}
