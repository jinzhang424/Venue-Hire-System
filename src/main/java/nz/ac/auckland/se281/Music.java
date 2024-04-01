package nz.ac.auckland.se281;

public class Music extends Services{

  private int totalOfCostMusic = 0;
  
  public Music(String bookingReference, int numOfAttendees) {
    super(bookingReference, "Music");
    totalOfCostMusic = totalCostOfThisService(numOfAttendees); 
  }


  
  @Override
  public int totalCostOfThisService(int numOfAttendees) {

    return 500 * numOfAttendees;
  }
}
