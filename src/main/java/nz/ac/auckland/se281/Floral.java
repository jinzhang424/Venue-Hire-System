package nz.ac.auckland.se281;

public class Floral extends Services{
  
  private Types.FloralType floralType;
  private int totalFloralCost = 0;

  public Floral(String referenceCode, int numOfAttendees, Types.FloralType floralType) {

    super(referenceCode, "Floral");
    this.floralType = floralType;
    this.totalFloralCost = floralType.getCost();
  }

  public int getFloralCost() {
    return this.totalFloralCost;
  }

  public Types.FloralType getFloralType() {
    return this.floralType;
  }
}
