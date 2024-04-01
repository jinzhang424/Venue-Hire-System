package nz.ac.auckland.se281;

public class Music extends Services{
  
  public Music(String bookingReference) {
    super(bookingReference, "Music");
  }


  
  @Override
  public int totalCostOfThisService(int numOfAttendees) {

    return 500 * numOfAttendees;
  }
}
