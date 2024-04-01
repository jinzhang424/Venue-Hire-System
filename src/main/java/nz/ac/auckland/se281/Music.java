package nz.ac.auckland.se281;

public class Music extends Services{

  private int totalOfCostMusic = 500;
  
  public Music(String bookingReference, int numOfAttendees) {
    super(bookingReference, "Music");
    this.totalOfCostMusic = 500; 
  }

  public int getMusicCost() {
    return totalOfCostMusic;
  }
}
