package agh.pz1;

/**
 * The class representing a reader.
 */
public class Reader extends Thread {
    /**
     * Library where the reader will be reading.
     */
    private Library library;

    /**
     * Id of the reader.
     */
    private int id;
  
    /**
     * Constructor for Reader class object.
     * Extends Thread class for multithreaded implementation of readers-writers problem.
     * @param givenLibrary - library where the reader will be reading
     * @param givenId - id of the reader
     */
    public Reader(Library givenLibrary, int givenId) {
      this.library = givenLibrary;
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
      for (int i = 0; i < 3; i++) {
        try {
          logger("In queue");

          library.readerEnter();
          sleep(5000);

          logger("Leaving");
          library.readerExit();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
