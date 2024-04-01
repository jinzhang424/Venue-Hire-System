package nz.ac.auckland.se281;

public abstract class Services{
  
  private String typeOfService = null;
  private String bookingReferenceCode = null;

  public Services(String referenceCode, String service) {
    
    this.bookingReferenceCode = referenceCode;
    this.typeOfService = service;
  }

  public String getBookingReferenceCode() {
    
    return this.bookingReferenceCode;
  }

  public Services typeOfService() {
    return this.typeOfService();
  }
}
