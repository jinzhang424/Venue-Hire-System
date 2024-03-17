package nz.ac.auckland.se281;

import com.github.houbb.data.struct.core.util.list.ArrayList;

public class Venues {
  private String VENUE_NAME = null;
  private String VENUE_CODE = null;
  private String VENUE_CAPACITY = null;
  private String HIRE_FEE = null;

  private ArrayList<Venues> VenueList = new ArrayList<Venues>();

  public Venues(String venueName, String venueCode, String venueCapacity, String hireFee) {

    this.VENUE_NAME = venueName;
    this.VENUE_CODE = venueCode;
    this.VENUE_CAPACITY = venueCapacity;
    this.HIRE_FEE = hireFee;
    
  }

  // Getting venue name
  public String getVenueName() {
    return this.VENUE_NAME;
  }

  // Getting venue code
  public String getVenueCode() {
    return this.VENUE_CODE;
  }

  // Getting venue capacity
  public String getVenueCapacity() {
    return this.VENUE_CAPACITY;
  }

  // Getting venue hire fee
  public String hireFee() {
    return this.HIRE_FEE;
  }

  // Getting venue list
  public ArrayList<Venues> getVenueList() {
    return VenueList;
  }
}
