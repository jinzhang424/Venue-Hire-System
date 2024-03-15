package nz.ac.auckland.se281;

import com.github.houbb.data.struct.core.util.list.ArrayList;

public class Venues {
  private String VENUE_NAME = null;
  private String VENUE_CODE = null;
  private String VENUE_CAPACITY = null;
  private String HIRE_FEE = null;

  
  public Venues(String venueName, String venueCode, String venueCapacity, String hireFee) {

    this.VENUE_NAME = venueName;
    this.VENUE_CODE = venueCode;
    this.VENUE_CAPACITY = venueCapacity;
    this.HIRE_FEE = hireFee;
    
  }
}
