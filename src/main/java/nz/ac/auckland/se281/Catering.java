package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Catering extends Services{
  
  private String cateringType;
  private int totalCateringCost;

  private ArrayList <String> listOfCatering = new ArrayList <String>();

  public Catering(String typeOfCatering, String costOfCatering, String bookingReferenceCode) {
    super(bookingReferenceCode, "Catering");
    listOfCatering.add(typeOfCatering);
  }
}
