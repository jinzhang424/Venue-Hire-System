package nz.ac.auckland.se281;

import java.util.List;

public class Bookings {
  
  private String bookingVenueCode = null;
  private String bookingVenueDate = null;
  private String bookingVenueEmail = null;
  private String numOfAttendees = null;

  public Bookings (String[] BookingInfo) {
    bookingVenueCode = BookingInfo[0];
    bookingVenueDate = BookingInfo[1];
    bookingVenueEmail = BookingInfo[2];
    numOfAttendees = BookingInfo[3]; 
  }
  
  public String getBookingVenueDate() {
    return this.bookingVenueDate;
  }
}
