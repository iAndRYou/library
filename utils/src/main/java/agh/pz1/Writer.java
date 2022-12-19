package agh.pz1;

import java.security.SecureRandom;

/**
 * The class representing a writer.
 */
class Writer extends Thread {

    /**
     * Random object for generating random numbers.
     */
    private SecureRandom random = new SecureRandom();
    
    /**
     * Library where the writer will be reading.
     */
    private Library lib;

    /**
     * Id of the writer.
     */
    private int id;

    /**
     * Constructor for Writer class object.
     * Extends Thread class for multithreaded implementation of readers-writers problem.
     * @param givenLibrary - lib where the writer will be reading
     * @param givenId - id of the writer
     */
    public Writer(Library givenLibrary, int givenId) {
      this.lib = givenLibrary;
      this.id = givenId;
    }

    /**
     * Prints the action message with the writer's id.
     * @param message
     */
    void logger(String message) {
      System.out.println("Writer " + id + ": " + message);
    }
  
    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
      for (int i = 0; i < 3; i++) {
        try {
          logger("In queue");

          lib.writerEnter();

          int time = random.ints(1000, 3000)
            .findFirst()
            .getAsInt();
          sleep(time);

          logger("Leaving");
          lib.writerExit();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
}

