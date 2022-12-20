package agh.pz1;

import java.security.SecureRandom;

/**
 * The class representing a reader.
 */
public class Reader extends Thread {

    /**
     * Random object for generating random numbers.
     */
    SecureRandom random = new SecureRandom();
    
    /**
     * Library where the reader will be reading.
     */
    private Library lib;

    /**
     * Id of the reader.
     */
    private int id;
  
    /**
     * Constructor for Reader class object.
     * Extends Thread class for multithreaded implementation of readers-writers problem.
     * @param givenLibrary - lib where the reader will be reading
     * @param givenId - id of the reader
     */
    public Reader(Library givenLibrary, int givenId) {
      this.lib = givenLibrary;
      this.id = givenId;
    }

    /**
     * Prints the action message with the reader's id.
     * @param message
     */
    void logger(String message) {
      System.out.println("Reader " + id + ": " + message);
    }
  
    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
      long startTime = System.currentTimeMillis();
      long endTime = startTime + 30 * 1000; 
      while (System.currentTimeMillis() < endTime) {
        try {
          logger("In queue");

          lib.readerEnter();
          
          int time = random.ints(1000, 3000)
            .findFirst()
            .getAsInt();
          sleep(time);

          logger("Leaving");
          lib.readerExit();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
