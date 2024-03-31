package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Catering extends Services{
  
  private String cateringType;

  private ArrayList <Types.CateringType> listOfCatering = new ArrayList <Types.CateringType>();

  public Catering(String typeOfCatering, String bookingReferenceCode) {

    super(bookingReferenceCode, "Catering");
    
    // Checking for "Breakast"
    if (typeOfCatering.equals("B")) {
      listOfCatering.add(Types.CateringType.BREAKFAST);
    }
    // Checking for "Lunch"
    else if (typeOfCatering.equals("L")) {
      listOfCatering.add(Types.CateringType.LUNCH);
    }
    // Checking for "Dinner"
    else if (typeOfCatering.equals("D")) {
      listOfCatering.add(Types.CateringType.DINNER);
    }
    // Checking for "Drinks"
    else if (typeOfCatering.equals("X")) {
      listOfCatering.add(Types.CateringType.DRINKS);
    }
    // Checking for "Two Course Breakfast/Lunch"
    else if (typeOfCatering.equals("BL")) {
      listOfCatering.add(Types.CateringType.TWO_COURSE_BL);
    }
    // Checking for "Two Course Lunch/Dinner"
    else if (typeOfCatering.equals("LD")) {
      listOfCatering.add(Types.CateringType.TWO_COURSE_LD);
    }
    // Checking for "Three Course"
    else if (typeOfCatering.equals("BLD")) {
      listOfCatering.add(Types.CateringType.THREE_COURSE);
    }
  }


  public int getTotalCostOfThisService(int numOfAttendees) {
    int totalCost = 0;

    return totalCost;
  }
  
}
