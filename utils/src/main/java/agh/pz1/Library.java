package agh.pz1;

import java.util.concurrent.Semaphore;

public class Library {
    private int readersCount = 0;
    private Semaphore readerAction = new Semaphore(1);
    private Semaphore library = new Semaphore(1);
    private Semaphore queue = new Semaphore(1);
    private Semaphore capacity = new Semaphore(5);

    public void readerEnter() throws InterruptedException {
        queue.acquire();
        capacity.acquire();
        readerAction.acquire();
        readersCount++;
        if (readersCount == 1) {
            library.acquire();
        }
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
        queue.acquire();
        library.acquire();
        queue.release();
    }

    public void writerExit() {
        library.release();
    }
}
