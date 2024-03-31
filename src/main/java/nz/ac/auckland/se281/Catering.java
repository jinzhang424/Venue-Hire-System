package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Catering extends Services{
  
  private String cateringType;

  private ArrayList <Types.CateringType> listOfCatering = new ArrayList <Types.CateringType>();



  public Catering(Types.CateringType typeOfCatering, String bookingReferenceCode) {

    super(bookingReferenceCode, "Catering");
    
    listOfCatering.add(typeOfCatering);
  }



  public int getTotalCostOfThisService(int numOfAttendees) {
    int totalCost = 0;

    // Uses a for each loop to go through the catering list to find total cost of this service (catering)
    for (Types.CateringType catering: listOfCatering) {
      totalCost += catering.getCostPerPerson() * numOfAttendees;
    }

    return totalCost;
  }
  
}
