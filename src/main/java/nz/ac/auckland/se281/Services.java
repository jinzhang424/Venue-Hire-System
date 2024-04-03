package nz.ac.auckland.se281;

public abstract class Services {

  private String typeOfService = null;
  private String bookingReferenceCode = null;
  private int costOfService = 0;

  public Services(String referenceCode, String service, int serviceCost) {

    this.bookingReferenceCode = referenceCode;
    this.typeOfService = service;
    this.costOfService = serviceCost;
  }

  public String getBookingReferenceCode() {

    return this.bookingReferenceCode;
  }

  public String getTypeOfService() {
    return this.typeOfService;
  }

  public int getCostOfService() {
    return this.costOfService;
  }
}
