package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;
import java.util.ArrayList;


public class VenueHireSystem {

  //Creating a list for storing venues
  private ArrayList <Venues> VenueList = new ArrayList <Venues>();

  //Creating a list for storing 
  private ArrayList <Bookings> BookingList = new ArrayList <Bookings>();

  public VenueHireSystem() {}

  private String systemDate = null;

  public String getSystemDate() {
    return systemDate;
  }


  public void printVenues() {

    //Prints message for when no venues have been created
    if (VenueList.size() == 0) {

      MessageCli.NO_VENUES.printMessage();
    }
    //Prints message for when there is 1 venue
    else if (VenueList.size() == 1) {

      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      MessageCli.VENUE_ENTRY.printMessage(
        VenueList.get(0).getVenueName()
      , VenueList.get(0).getVenueCode()
      , VenueList.get(0).getVenueCapacity()
      , VenueList.get(0).hireFee()
      , nextAvailableDate());
    }
    //Prints message for when there is more than 1 venue
    else { 
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(VenueList.size()), "s");

      for (int i = 0; i < VenueList.size(); i++) {
        MessageCli.VENUE_ENTRY.printMessage(
          VenueList.get(i).getVenueName()
        , VenueList.get(i).getVenueCode()
        , VenueList.get(i).getVenueCapacity()
        , VenueList.get(i).hireFee()
        , nextAvailableDate());
      }
    }
  }



  public void createVenue(String venueName, String venueCode
  , String venueCapacity, String hireFee) {

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



  public int venueFinder(String venueCode) {

    for (int i = 0; i < VenueList.size(); i++) { // Looks through VenueList for matching venue code
      if (VenueList.get(i).getVenueCode().equals(venueCode)) {
        return i;
      }
    }

    return 0;
  }



  public void setSystemDate(String dateInput) {
    systemDate = dateInput;
    
    MessageCli.DATE_SET.printMessage(systemDate); // Prints message saying system date has been set
  }



  public void printSystemDate() {
    if (systemDate == null) { // Checks if there is a system date
      System.out.println("Current system date is not set.");
    }
    else { // Prints system date
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }



  public boolean bookingInfoChecker(String[] bookingInfo) {
  
    // Checks if system date has been set
    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return false;
    }
    // Checks if there's venues in the system
    else if (VenueList.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return false;
    }

    // Seperating Dates in to sections
    String[] dateParts = bookingInfo[1].split("/");
    String[] systemDateParts = systemDate.split("/");


    // Checks if the booking date is in the past
    for (int j = 2; j >= 0; j--) {
      if (Integer.parseInt(systemDateParts[j]) > Integer.parseInt(dateParts[j])) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingInfo[1], systemDate);
        return false;
      }
    }

    // Checking if the date we're trying to book is available or not
    if (isDateAvailable(bookingInfo[1]) == false) {
      
      MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
        VenueList.get((venueFinder(bookingInfo[0]))).getVenueName(), bookingInfo[1]);

      return false;
    }

    // Looks through VenueList for matching venue code to check if venue exists
    for (int i = 0; i < VenueList.size(); i++) { 
      if (VenueList.get(i).getVenueCode().equals(bookingInfo[0])) {
        return true;
      }
    }
    MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(bookingInfo[0]);

    return false;
  }


  // This method checks if the day we're trying to book is available or not
  public boolean isDateAvailable(String date) {

    if (BookingList.size() > 0) { 
      for (int k = 0; k < BookingList.size(); k++) {
        // Checks if there is a matching date in the booking list
        if (date.equals(BookingList.get(k).getBookingVenueDate())) {

          return false;
        }
      }
    }

    return true;
  }


  
  // This method checks if the number of attendees is suitable for a given venue's capacity
  public String bookingAttendeesChecker(String bookingAttendees, String venueCode) {

    int numOfAttendees = Integer.parseInt(bookingAttendees);
    int capacityOfVenue = Integer.parseInt(VenueList.get(venueFinder(venueCode)).getVenueCapacity()); 

    // Checks if the number of attendees is 25% of venue capacity
    if (numOfAttendees < capacityOfVenue/4) {

      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(bookingAttendees, Integer.toString(capacityOfVenue/4), VenueList.get(venueFinder(venueCode)).getVenueCapacity());
      return Integer.toString(capacityOfVenue/4);
    }
    // Checks if the number of attendees exceeds venue capacity
    else if (numOfAttendees > capacityOfVenue) { 
      
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(bookingAttendees, VenueList.get(venueFinder(venueCode)).getVenueCapacity(), VenueList.get(venueFinder(venueCode)).getVenueCapacity());
      return Integer.toString(capacityOfVenue);
    }
    return bookingAttendees;
  }



  public void makeBooking(String[] options) {

    //Checking that inputs are valid and if the system is prepared to make bookings
    if (bookingInfoChecker(options) == true) { 

      // Converts the attendees value to the valid damount
      options[3] = bookingAttendeesChecker(options[3], options[0]);

      Bookings newBooking = new Bookings(options);

      BookingList.add(newBooking);

      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(newBooking.getBookingReferece(),
        VenueList.get(venueFinder(options[0])).getVenueName(), options[1], options[3]);
    }
  }



  public String nextAvailableDate() {

    // Checks for when the system date is null
    if (systemDate == null) {
      return "";
    }
    
    String availableDate = systemDate;
    String availableDateParts[] = availableDate.split("/");

    // Checks for when the booking size is 0
    if (BookingList.size() == 0) {
      return availableDate;
    }

    // Keeps looping until we find the next available date
    while(isDateAvailable(availableDate) == false) {
      
      // Increments the day of the date by 1
      availableDateParts[0] = String.valueOf(Integer.parseInt(availableDateParts[0]) + 1); 

      // Puts split string back together
      availableDate = availableDateParts[0] + "/" + availableDateParts[1] + "/" + availableDateParts[2];
    }

    return availableDate;
  }



  public void printBookings(String venueCode) {
    
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
