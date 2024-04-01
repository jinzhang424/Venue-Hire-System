package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Catering extends Services{
  
  private Types.CateringType cateringType;


  public Catering(Types.CateringType typeOfCatering, String bookingReferenceCode) {

    super(bookingReferenceCode, "Catering");
    
    this.cateringType = typeOfCatering;
  }



  @Override
  public int totalCostOfThisService(int numOfAttendees) {
    
    return this.cateringType.getCostPerPerson() * numOfAttendees;
  }
}
