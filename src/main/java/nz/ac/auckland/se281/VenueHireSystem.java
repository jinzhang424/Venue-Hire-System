package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;
import com.github.houbb.data.struct.core.util.list.ArrayList;


public class VenueHireSystem {

  public VenueHireSystem() {}

  public void printVenues() {
  
  
    
  }

  public void createVenue(String venueName, String venueCode, String venueCapacity, String hireFee) {

    // Checks for invalid venue name
    if (venueName.replaceAll("\\s", "") == "") {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    try {
      int venueCapacityValue = Integer.parseInt(venueCapacity);
      if (venueCapacityValue < 1) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive"); 
        return;
      }
    }
    catch(Exception a) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", ""); 
      return;
    }
    

    // Checks for invalid hiring fee
    try {
      int hireFeeValue = Integer.parseInt(hireFee);
      if (hireFeeValue < 1) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }
    }
    catch(Exception b) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", ""); 
      return;
    }
    
    // Creates a venue when all inputs are valid
    Venues venue = new Venues(venueName, venueCode, venueCapacity, hireFee);

    // Message about the creation of a new venue
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);

    // Adds the new venue to a list (VenueList)
    venue.addVenueToList();
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
