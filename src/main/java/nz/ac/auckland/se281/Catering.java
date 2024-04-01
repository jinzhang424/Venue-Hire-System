package nz.ac.auckland.se281;

public class Catering extends Services{
  
  private Types.CateringType cateringType;
  private int totalCostOfCatering;

  public Catering(Types.CateringType typeOfCatering, String bookingReferenceCode, int numOfAttendees) {

    super(bookingReferenceCode, "Catering");
    
    this.cateringType = typeOfCatering;
    this.totalCostOfCatering = cateringType.getCostPerPerson() * numOfAttendees;
  }

  public int getCateringCost() {
    return totalCostOfCatering;
  }
}
