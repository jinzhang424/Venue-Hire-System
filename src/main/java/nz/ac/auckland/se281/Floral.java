package nz.ac.auckland.se281;

public class Floral extends Services{
  
  private Types.FloralType floralType;
  private int totalFloralCost = 0;

  public Floral(String referenceCode, int numOfAttendees, Types.FloralType floralType) {

    super(referenceCode, "Floral");
    this.floralType = floralType;
    this.totalFloralCost = totalCostOfThisService(numOfAttendees);
  }

  public int totalCostOfThisService(int numOfAttendees) {
    
    if (this.floralType.equals(Types.FloralType.STANDARD)) {
      return 550;
    }
    else {
      return 1000;
    }
  }

  public int getFloralCost() {
    return this.totalFloralCost;
  }
}
