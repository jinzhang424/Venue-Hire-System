package nz.ac.auckland.se281;

public abstract class Services{
  
  private String typeOfService;

  public Services(String bookingReferenceCode, String service) {
    
    this.typeOfService = service;
  }

  public abstract int getTotalCostOfThisService(int numOfAttendees);
}
