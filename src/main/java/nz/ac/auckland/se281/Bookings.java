package nz.ac.auckland.se281;

public class Bookings {
  
  private String bookingVenueCode = null;
  private String bookingVenueDate = null;
  private String dateOfBooking = null;
  private String bookingVenueEmail = null;
  private String numOfAttendees = null;
  private String bookingReference = null;

  public Bookings (String[] BookingInfo, String dateOfBooking) {
    bookingVenueCode = BookingInfo[0];
    bookingVenueDate = BookingInfo[1];
    bookingVenueEmail = BookingInfo[2];
    numOfAttendees = BookingInfo[3]; 
    this.dateOfBooking = dateOfBooking;
    bookingReference = BookingReferenceGenerator.generateBookingReference();
  }
  
  public String getBookingVenueDate() {
    return this.bookingVenueDate;
  }

  public String getBookingReferece() {
    return this.bookingReference;
  }

  public String getBookingVenueCode() {
    return this.bookingVenueCode;
  }

  public String getNumOfAttendees() {
    return numOfAttendees;
  }

  public String getBookingVenueEmail() {
    return bookingVenueEmail;
  }

  public String getDateOfBooking() {
    return dateOfBooking;
  }
}
