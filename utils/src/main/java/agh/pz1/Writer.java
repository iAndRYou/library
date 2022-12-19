package agh.pz1;

public class Writer extends Thread {
    private Library library;
    private int id;

    public Writer(Library givenLibrary, int givenId) {
      this.library = givenLibrary;
      this.id = givenId;
    }

    void logger (String message) {
      System.out.println("Writer " + id + ": " + message);
    }
  
    @Override
    public void run() {
      try {
        logger("In queue");
        library.writerEnter();
        logger("Writing");
        sleep(5000);
        logger("Leaving");
        library.writerExit();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
}

