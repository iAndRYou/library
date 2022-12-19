package agh.pz1;

public class Writer extends Thread {
    private Library library;
    private int id;

    /**
     * Constructor for Writer class object.
     * Extends Thread class for multithreaded implementation of readers-writers problem.
     * @param givenLibrary - library where the writer will be reading
     * @param givenId - id of the writer
     */
    public Writer(Library givenLibrary, int givenId) {
      this.library = givenLibrary;
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

          library.writerEnter();
          sleep(5000);

          logger("Leaving");
          library.writerExit();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
}

