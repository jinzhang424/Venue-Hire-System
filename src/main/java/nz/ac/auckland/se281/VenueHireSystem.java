package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  public VenueHireSystem() {}

  public void printVenues() {
    
    
  }

  public void createVenue(String venueName, String venueCode, String venueCapacity, String hireFee) {

    // Checks for invalid venue name
    if (venueName.replaceAll("\\s", "") == "") {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    }
    else if (Integer.parseInt(venueCapacity) < 1) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
    }
    else { // Creates a venue when all inputs are valid
    // Creates new Venue
    Venues venue = new Venues(venueName, venueCode, venueCapacity, hireFee);

    // Message about the creation of a new venue
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    }
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
