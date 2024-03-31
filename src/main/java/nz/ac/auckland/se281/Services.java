package nz.ac.auckland.se281;

public abstract class Services extends Bookings{
  
  private String typeOfService;

  public Services(String bookingReferenceCode, String service) {

    super(null);
    
    this.typeOfService = service;
  }
}
