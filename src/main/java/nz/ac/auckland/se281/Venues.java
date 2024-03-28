package nz.ac.auckland.se281;

public class Venues {
  private String venueName = null;
  private String venueCode = null;
  private String venueCapacity = null;
  private String hireFee = null;

  
  public Venues(String venueName, String venueCode, String venueCapacity, String hireFee) {

    this.venueName = venueName;
    this.venueCode = venueCode;
    this.venueCapacity = venueCapacity;
    this.hireFee = hireFee;
    
  }

  // Getting venue name
  public String getVenueName() {
    return this.venueName;
  }

  // Getting venue code
  public String getVenueCode() {
    return this.venueCode;
  }

  // Getting venue capacity
  public String getVenueCapacity() {
    return this.venueCapacity;
  }

  // Getting venue hire fee
  public String hireFee() {
    return this.hireFee;
  }
}
