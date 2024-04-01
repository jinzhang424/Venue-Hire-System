package nz.ac.auckland.se281;

public abstract class Services{
  
  private String typeOfService = null;
  private String bookingReferenceCode = null;

  public Services(String referenceCode, String service) {
    
    this.bookingReferenceCode = referenceCode;
    this.typeOfService = service;
  }

  public abstract int totalCostOfThisService(int numOfAttendeees);

  public String getBookingReferenceCode() {
    
    return this.bookingReferenceCode;
  }
}
