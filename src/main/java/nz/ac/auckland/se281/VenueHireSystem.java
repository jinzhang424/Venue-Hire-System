package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;
import com.github.houbb.data.struct.core.util.list.ArrayList;


public class VenueHireSystem {

  //Creating a list for storing venues
  public ArrayList<Venues> VenueList = new ArrayList<Venues>();

  public VenueHireSystem() {}

  public void printVenues() {
    if (VenueList.size() == 0) { //Prints message for when no venues have been created
      MessageCli.NO_VENUES.printMessage();
    }
    else if (VenueList.size() == 1) { //Prints message for when there is 1 venue
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      MessageCli.VENUE_ENTRY.printMessage(VenueList.get(0).getVenueName(), VenueList.get(0).getVenueCode(), 
      VenueList.get(0).getVenueCapacity(), VenueList.get(0).hireFee());
    }
    else { //Prints message for when there is more than 1 venue
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(VenueList.size()), "s");
      for (int i = 0; i < VenueList.size(); i++) {
        MessageCli.VENUE_ENTRY.printMessage(VenueList.get(i).getVenueName(), VenueList.get(i).getVenueCode(), 
      VenueList.get(i).getVenueCapacity(), VenueList.get(i).hireFee());
      }
    }
  }


  public void createVenue(String venueName, String venueCode, String venueCapacity, String hireFee) {

    // Checks for invalid venue name
    if (venueName.replaceAll("\\s", "") == "") {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }
    else if (VenueList.size() > 0) { // Checks if the venue code entered is unique
      for (int i = 0; i < VenueList.size(); i++) {
        if (VenueList.get(i).getVenueCode().equalsIgnoreCase(venueCode)) {
          MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, VenueList.get(i).getVenueName());
          return;
        }
      }
    }
    

    //Checks for invalid venue capacity
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
    VenueList.add(venue);
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
