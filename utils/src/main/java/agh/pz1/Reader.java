package agh.pz1;

public class Reader extends Thread {
    private Library library;
    private int id;
  
    public Reader(Library givenLibrary, int givenId) {
      this.library = givenLibrary;
      this.id = givenId;
    }

    void logger (String message) {
      System.out.println("Reader " + id + ": " + message);
    }
  
    @Override
    public void run() {
      try {
        logger("In queue");
        library.readerEnter();
        logger("Reading");
        sleep(5000);
        logger("Leaving");
        library.readerExit();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
