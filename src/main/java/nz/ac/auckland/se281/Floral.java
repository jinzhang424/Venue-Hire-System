package nz.ac.auckland.se281;

public class Floral extends Services{
  
  private Types.FloralType floralType;

  public Floral(String referenceCode, int numOfAttendees, Types.FloralType floralType) {

    super(referenceCode, "Floral", floralType.getCost());
  }

  public Types.FloralType getFloralType() {
    return floralType;
  }
}
