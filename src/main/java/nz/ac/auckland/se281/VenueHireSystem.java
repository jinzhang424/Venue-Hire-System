package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private ArrayList<Venues> venueList = new ArrayList<Venues>();
  private ArrayList<Bookings> bookingList = new ArrayList<Bookings>();
  private ArrayList<Services> serviceList = new ArrayList<Services>();

  private String systemDate = null;

  public VenueHireSystem() {}

  /** Prints out all (if any) venues in the system */
  public void printVenues() {

    String[] numberAsWord =
        new String[] {"", "", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    // Prints message for when no venues have been created
    if (venueList.size() == 0) {

      MessageCli.NO_VENUES.printMessage();
    }

    // Prints message for when there is 1 venue
    else if (venueList.size() == 1) {

      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");

      venueList.get(0).printVenueEntry(nextAvailableDate(venueList.get(0).getVenueCode()));
    }

    // Prints message for when there is more than 1 but less than 10 venues
    else if (venueList.size() > 1 && venueList.size() < 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", numberAsWord[venueList.size()], "s");

      for (int i = 0; i < venueList.size(); i++) {
        venueList.get(i).printVenueEntry(nextAvailableDate(venueList.get(i).getVenueCode()));
      }
    }

    // Prints message for when there is more than 9 venues
    else {
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(venueList.size()), "s");

      for (int i = 0; i < venueList.size(); i++) {
        venueList.get(i).printVenueEntry(nextAvailableDate(venueList.get(i).getVenueCode()));
      }
    }
  }

  /**
   * Performs all the necessary checks on the venue name (venueName), code of the venue (venueCode),
   * capacity of the venue (venueCapacity), and the hiring fee (hireFee) of the venue to see if
   * they're valid. If they're not, print an error message, and if it passes all the checks create
   * the venue.
   *
   * @param venueName - Name of the venue
   * @param venueCode - Code of the venue
   * @param venueCapacity - Capacity of the venue
   * @param hireFee - Hire fee of the venue
   */
  public void createVenue(
      String venueName, String venueCode, String venueCapacity, String hireFee) {

    // Checks for invalid venue name
    if (venueName.replaceAll("\\s", "").equals("")
        || venueName.replaceAll("\\s", "").equals("''")) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();

      return;
    }

    // Checks if the venue code entered is unique
    else if (venueList.size() > 0) {
      for (int i = 0; i < venueList.size(); i++) {
        if (venueList.get(i).getVenueCode().equalsIgnoreCase(venueCode)) {
          MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(
              venueCode, venueList.get(i).getVenueName());

          return;
        }
      }
    }

    // Checks for invalid venue capacity
    try {
      int venueCapacityValue = Integer.parseInt(venueCapacity);
      if (venueCapacityValue < 1) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return;
      }
    } catch (Exception a) {
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
    } catch (Exception b) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }

    // Creates a venue when all inputs are valid
    Venues venue = new Venues(venueName, venueCode, venueCapacity, hireFee);

    // Message about the creation of a new venue
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);

    // Adds the new venue to a list (VenueList)
    venueList.add(venue);
  }

  /**
   * Loops through the venue list (VenueList) to search for a particular venue's index position in
   * the list
   *
   * @param venueCode - Code of a venue
   * @return - Returns the index position of the venue
   */
  public int venueFinder(String venueCode) {

    for (int i = 0; i < venueList.size(); i++) { // Looks through VenueList for matching venue code
      if (venueList.get(i).getVenueCode().equals(venueCode)) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Sets the system date
   *
   * @param dateInput - User's input date
   */
  public void setSystemDate(String dateInput) {
    systemDate = dateInput;

    MessageCli.DATE_SET.printMessage(systemDate);
  }

  /** Prints out the system date */
  public void printSystemDate() {
    if (systemDate == null) { // Checks if there is a system date
      System.out.println("Current system date is not set.");
    } else {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }

  /**
   * Checks whether the information provided for a booking is value
   *
   * @param bookingInfo - Contains booking information
   * @return - Returns true if all information is valid otherwise false
   */
  public boolean bookingInfoChecker(String[] bookingInfo) {

    // Checks if system date has been set
    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return false;
    }
    // Checks if there's venues in the system
    else if (venueList.size() == 0) {
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
    if (isDateAvailableForVenue(bookingInfo[1], bookingInfo[0]) == false) {

      MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
          venueList.get((venueFinder(bookingInfo[0]))).getVenueName(), bookingInfo[1]);

      return false;
    }

    // Checking if venue code exists
    if (doesVenueCodeExist(bookingInfo[0]) == false) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(bookingInfo[0]);

      return false;
    }

    return true;
  }

  /**
   * Loops through the venue list (VenueList) to see if the venue exists
   *
   * @param codeOfVenue - Code of a venue
   * @return - True if the venue code exists, otherwise false
   */
  public boolean doesVenueCodeExist(String codeOfVenue) {

    // Loops through the venue list to check if the venue exists
    for (int i = 0; i < venueList.size(); i++) {
      if (venueList.get(i).getVenueCode().equals(codeOfVenue)) {
        return true;
      }
    }

    return false;
  }

  /**
   * To check if the venue is available for booking that day (i.e. it's not already booked on that
   * day)
   *
   * @param bookingDate - The date we're trying to book
   * @param codeOfVenue - Code of a venue
   * @return - True if the date hasn't already been booked for a venue, otherwise false
   */
  public boolean isDateAvailableForVenue(String bookingDate, String codeOfVenue) {

    if (bookingList.size() > 0) {
      for (int k = 0; k < bookingList.size(); k++) {
        // Checks if venue code matches and if the particular venue has a matching date
        if (codeOfVenue.equals(bookingList.get(k).getBookingVenueCode())
            && bookingDate.equals(bookingList.get(k).getBookingVenueDate())) {

          return false;
        }
      }
    }

    return true;
  }

  /**
   * Checks if the number of attendees is less than 25% of the venue capacity or more than 100% of
   * it's capacity
   *
   * @param bookingAttendees - Number of attendees specificied in the booking
   * @param venueCode - code of a venue
   * @return
   */
  public String bookingAttendeesChecker(String bookingAttendees, String venueCode) {

    int numOfAttendees = Integer.parseInt(bookingAttendees);
    int capacityOfVenue =
        Integer.parseInt(venueList.get(venueFinder(venueCode)).getVenueCapacity());

    // Checks if the number of attendees is 25% of venue capacity
    if (numOfAttendees < capacityOfVenue / 4) {

      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          bookingAttendees,
          Integer.toString(capacityOfVenue / 4),
          venueList.get(venueFinder(venueCode)).getVenueCapacity());
      return Integer.toString(capacityOfVenue / 4);
    }

    // Checks if the number of attendees exceeds venue capacity
    else if (numOfAttendees > capacityOfVenue) {

      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          bookingAttendees,
          venueList.get(venueFinder(venueCode)).getVenueCapacity(),
          venueList.get(venueFinder(venueCode)).getVenueCapacity());
      return Integer.toString(capacityOfVenue);
    }

    return bookingAttendees;
  }

  /**
   * Checks the user input options to see if they're valid and then creates a booking
   *
   * @param options - User input of Venue code, event date, email, number of attendees
   */
  public void makeBooking(String[] options) {

    // Checking that inputs are valid and if the system is prepared to make bookings
    if (bookingInfoChecker(options) == true) {

      // Converts the attendees value to the valid damount
      options[3] = bookingAttendeesChecker(options[3], options[0]);

      Bookings newBooking = new Bookings(options, systemDate);

      bookingList.add(newBooking);

      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
          newBooking.getBookingReferece(),
          venueList.get(venueFinder(options[0])).getVenueName(),
          options[1],
          options[3]);
    }
  }

  /**
   * Looks for the next available date of a particular venue
   *
   * @param codeOfVenue - The venue's code
   * @return - Returns the next available date of a particular venue
   */
  public String nextAvailableDate(String codeOfVenue) {

    // Checks for when the system date is null
    if (systemDate == null) {
      return "";
    }

    String availableDate = systemDate;
    String[] availableDateParts = availableDate.split("/");

    // Checks for when the booking size is 0
    if (bookingList.size() == 0) {
      return availableDate;
    }

    // Keeps looping until we find the next available date for this particular venue
    while (isDateAvailableForVenue(availableDate, codeOfVenue) == false) {

      // Increments the day of the date by 1
      availableDateParts[0] = String.valueOf(Integer.parseInt(availableDateParts[0]) + 1);

      // Puts split string back together
      availableDate =
          availableDateParts[0] + "/" + availableDateParts[1] + "/" + availableDateParts[2];

      // Checks if the string value is less than 9 and if it is, concatenate a 0 to left
      if (Integer.parseInt(availableDateParts[0]) < 10) {
        availableDate = "0" + availableDate;
      }
    }

    return availableDate;
  }

  /**
   * Looks through the booking list to see if a booking has been made for a particular venue
   *
   * @param venueCode - A venue's code
   * @return - True if there is a booking for a venue otherwise false
   */
  public boolean isThereBookingForVenue(String venueCode) {

    // Loops through the booking list to check if a booking has been made for a particular venue
    for (int i = 0; i < bookingList.size(); i++) {
      if (bookingList.get(i).getBookingVenueCode().equals(venueCode)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Searches the booking list (BookingList) to find the bookings for a particular venue and prints
   * out their reference code and date of that venue's event
   *
   * @param venueCode - A venue's code
   */
  public void printBookings(String venueCode) {

    // Checks if venue code exists
    if (doesVenueCodeExist(venueCode) == false) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
    }
    // Checks if there are bookings for the venue
    else if (isThereBookingForVenue(venueCode) == false) {

      String bookingName = venueList.get(venueFinder(venueCode)).getVenueName();

      MessageCli.PRINT_BOOKINGS_HEADER.printMessage(bookingName);

      MessageCli.PRINT_BOOKINGS_NONE.printMessage(bookingName);
    }
    // Prints out all the bookings for the venue
    else {

      MessageCli.PRINT_BOOKINGS_HEADER.printMessage(
          venueList.get(venueFinder(venueCode)).getVenueName());

      for (int i = 0; i < bookingList.size(); i++) {

        // Matching the venue code with the booking
        if (bookingList.get(i).getBookingVenueCode().equals(venueCode) == true) {

          MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
              bookingList.get(i).getBookingReferece(), bookingList.get(i).getBookingVenueDate());
        }
      }
    }
  }

  /**
   * Looks through the booking list using a booking reference to see if a booking a exists
   *
   * @param bookingReference - A booking's reference code
   * @return - True if the booking exists otherwise false
   */
  public boolean doesBookingReferenceExist(String bookingReference) {

    // Looks through the booking list to check if the booking reference exists
    for (int i = 0; i < bookingList.size(); i++) {
      if (bookingList.get(i).getBookingReferece().equals(bookingReference)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Adds catering to a booking by first checking if the booking reference exists and if it does,
   * then creates a new Catering instance
   *
   * @param bookingReference - The reference code of a booking
   * @param cateringType - The user input of what Catering type they want to add
   */
  public void addCateringService(String bookingReference, CateringType cateringType) {

    // Checking if the booking reference exists or not
    if (doesBookingReferenceExist(bookingReference) == true) {

      Catering newCatering = new Catering(cateringType, bookingReference);

      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
          "Catering " + "(" + cateringType.getName() + ")", bookingReference);

      serviceList.add(newCatering);
    }

    // For when reference code doesn't exist
    else {

      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
    }
  }

  /**
   * Adds music to a booking by first checking if the booking reference exists and then creating a
   * Music instance
   *
   * @param bookingReference - The reference code of a booking
   */
  public void addServiceMusic(String bookingReference) {

    // Checking if the booking reference exists
    if (doesBookingReferenceExist(bookingReference) == true) {

      Music newMusic = new Music(bookingReference);

      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);

      serviceList.add(newMusic);
    } else {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
    }
  }

  /**
   * Adds floral to a booking by first checking if the booking reference exists and then creating an
   * instance of a Floral
   *
   * @param bookingReference - The reference code of a booking
   * @param floralType - The user's input of what type of floral they want to add
   */
  public void addServiceFloral(String bookingReference, FloralType floralType) {

    // Checking if booking reference exists
    if (doesBookingReferenceExist(bookingReference) == true) {

      Floral newFloral = new Floral(bookingReference, floralType);

      serviceList.add(newFloral);

      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
          "Floral " + "(" + floralType.getName() + ")", bookingReference);
    }

    // When booking reference doesn't exist
    else {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
    }
  }

  /**
   * First checks if the booking reference exists and then uses a for loop and if statement to go
   * through the array to search for the index of a particular booking based on the booking
   * reference, and returns the index
   *
   * @param bookingReference - The reference code of a booking
   * @return - The index position of a booking
   */
  public int bookingFinder(String bookingReference) {

    // Checking if the booking reference exists
    if (doesBookingReferenceExist(bookingReference) == true) {

      // Searching BookingList to find the booking's index
      for (int i = 0; i < bookingList.size(); i++) {
        if (bookingList.get(i).getBookingReferece().equals(bookingReference)) {
          return i;
        }
      }
    }

    return 0;
  }

  /**
   * Uses a for loop to look through the service list to search for the index of a type of service
   * and will return -1 if the service is not part of the service list
   *
   * @param bookingReference - The reference code of a booking
   * @param typeOfService - The type of service we're searching for
   * @return - The index of the service we're searching for in ServiceList
   */
  public int serviceFinder(String bookingReference, String typeOfService) {

    for (int i = 0; i < serviceList.size(); i++) {

      // Checks if the booking reference matches and the typeOfService matches
      if (serviceList.get(i).getBookingReferenceCode().equals(bookingReference)
          & serviceList.get(i).getTypeOfService().equals(typeOfService)) {
        return i;
      }
    }

    // Returns -1 for when that kind of service was not part of a the booking
    return -1;
  }

  /**
   * Prints out the invoice of a booking which contains information about the:
   *
   * <p>booking details - customer email, date of booking event details - party date, number of
   * guests, venue name cost breakdown - hire costs, catering costs (if catering was booking), music
   * costs (if music was booked) , floral costs (if floral was booked) and the total cost of the
   * venue
   *
   * @param bookingReference - The reference code of a booking
   */
  public void viewInvoice(String bookingReference) {

    if (doesBookingReferenceExist(bookingReference) == false) {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);

      return;
    }

    // Finding where the corresponding venue and booking is in their respective lists
    int bookingIndex = bookingFinder(bookingReference);
    int venueIndex = venueFinder(bookingList.get(bookingIndex).getBookingVenueCode());

    // Finding where each corresponding service type is in the service list
    int cateringIndex = serviceFinder(bookingReference, "Catering");
    int musicIndex = serviceFinder(bookingReference, "Music");
    int floralIndex = serviceFinder(bookingReference, "Floral");

    int totalCostOfServices = Integer.parseInt(venueList.get(venueIndex).getHireFee());

    // Printing details for the top half of the invoice
    MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
        bookingReference,
        bookingList.get(bookingIndex).getBookingVenueEmail(),
        bookingList.get(bookingIndex).getDateOfBooking(),
        bookingList.get(bookingIndex).getBookingVenueDate(),
        bookingList.get(bookingIndex).getNumOfAttendees(),
        venueList.get(venueIndex).getVenueName());

    // Service cost section
    MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(venueList.get(venueIndex).getHireFee());

    if (cateringIndex != -1) { // Checking if catering was added to this booking

      Catering isCatering = (Catering) serviceList.get(cateringIndex);

      // Finding the number of attendees and cost of catering for calculating total cost
      int numOfAttendees = Integer.parseInt(bookingList.get(bookingIndex).getNumOfAttendees());
      int costOfCatering = isCatering.getCostOfService();

      // Adding catering costs
      totalCostOfServices += isCatering.getCostOfService() * numOfAttendees;

      MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
          isCatering.getCateringType().getName(), String.valueOf(costOfCatering * numOfAttendees));
    }

    if (musicIndex != -1) { // Checking if music was added to this booking

      // Adding music costs
      totalCostOfServices += 500;

      MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage("500");
    }

    if (floralIndex != -1) { // Checking if floral was added to this booking

      Floral isFloral = (Floral) serviceList.get(floralIndex);

      // Adding floral costs
      totalCostOfServices += serviceList.get(floralIndex).getCostOfService();

      MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
          isFloral.getFloralType().getName(),
          String.valueOf(serviceList.get(floralIndex).getCostOfService()));
    }

    MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(String.valueOf(totalCostOfServices));
  }
}
