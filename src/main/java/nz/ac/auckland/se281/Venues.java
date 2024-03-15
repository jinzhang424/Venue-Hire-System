package nz.ac.auckland.se281;

import com.github.houbb.data.struct.core.util.list.ArrayList;

public class Venues {
  public String VENUE_NAME = null;
  public String VENUE_CODE = null;
  public String VENUE_CAPACITY = null;
  public String HIRE_FEE = null;

  
  public Venues(String venueName, String venueCode, String venueCapacity, String hireFee) {

    this.VENUE_NAME = venueName;
    this.VENUE_CODE = venueCode;
    this.VENUE_CAPACITY = venueCapacity;
    this.HIRE_FEE = hireFee;
    
  }
  
  private ArrayList<Venues> VenueList = new ArrayList<Venues>(); 

  public void addVenueToList() {
    VenueList.add(this);
  }

  public void getVenueSize() {
    VenueList.size();
  }
}
