package agh.pz1;

import java.util.concurrent.Semaphore;

/**
 * The class representing a lib.
 * It handles all the logic of the readers-writers problem.
 */
public class Library {

    /**
     * The number of readers currently in the lib.
     */
    private int readersCount = 0;

    /**
     * The number of writers currently in the lib.
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
     * The semaphore controlling the access to the lib.
     * Only one writer can enter the lib given that there are no readers in the lib.
     */
    private Semaphore lib = new Semaphore(1);

    /**
     * The semaphore controlling who is the first in queue/
     * The first in queue can enter the lib.
     */
    private Semaphore queue = new Semaphore(1);

    /**
     * The semaphore controlling the capacity of the lib.
     * The lib can hold up to 5 readers at the same time.
     */
    private Semaphore capacity = new Semaphore(5);

    /**
     * The method called by a reader when he wants to enter the lib.
     * He has to wait for his turn in the queue and then for the lib to be free.
     * @throws InterruptedException if the reader thread is interrupted
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
            lib.acquire();
        }

        log();
        readerAction.release();
        queue.release();
    }

    /**
     * The method called by a reader when he wants to leave the lib.
     * @throws InterruptedException if the reader thread is interrupted
     */
    public void readerExit() throws InterruptedException {
        readerAction.acquire();
        
        if (readersCount > 0) {
            readersCount--;
            if (readersCount == 0) {
                lib.release();
            }
        }

        readerAction.release();
        capacity.release();
    }

    /**
     * The method called by a writer when he wants to enter the lib.
     * He has to wait for his turn in the queue and then for the lib to be free.
     * @throws InterruptedException if the writer thread is interrupted
     */
    public void writerEnter() throws InterruptedException {
        writersInQueueCount++;
        Thread.sleep(5);

        queue.acquire();
        lib.acquire();
        writersInQueueCount--;
        writersCount++;

        log();
        queue.release();
    }

    /**
     * The method called by a writer when he wants to leave the lib.
     */
    public void writerExit() {
        writersCount--;
        lib.release();
    }

    /**
     * Prints the current status of the lib.
     */
    private void log() {
        logger("Status:");
        logger("Queue - readers: " + readersInQueueCount + ", writers: " + writersInQueueCount);
        logger("Library - readers: " + readersCount + ", writers: " + writersCount);
        logger("");
    }

    /**
     * Prints the message to the console.
     */
    private void logger(String message) {
        System.out.println(message);
    }

    /**
     * Gets the number of readers currently in the lib.
     * @return the readersCount
     */
    public int getReadersCount() {
        return readersCount;
    }

    /**
     * Gets the number of writers currently in the lib.
     * @return the writersCount
     */
    public int getWritersCount() {
        return writersCount;
    }

    /**
     * Gets the number of readers currently in the queue.
     * @return the readersInQueueCount
     */
    public int getReadersInQueueCount() {
        return readersInQueueCount;
    }

    /**
     * Gets the number of writers currently in the queue.
     * @return the writersInQueueCount
     */
    public int getWritersInQueueCount() {
        return writersInQueueCount;
    }
}
