package nz.ac.auckland.se281;

public class Catering extends Services{
  
  private Types.CateringType cateringType;

  public Catering(Types.CateringType typeOfCatering, String bookingReferenceCode) {

    super(bookingReferenceCode, "Catering", typeOfCatering.getCostPerPerson());
    
    this.cateringType = typeOfCatering;
  }

  public Types.CateringType getCateringType() {
    return this.cateringType;
  }
}
