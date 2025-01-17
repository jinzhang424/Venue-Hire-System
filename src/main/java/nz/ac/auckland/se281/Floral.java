package nz.ac.auckland.se281;

public class Floral extends Services {

  private Types.FloralType floralType;

  public Floral(String referenceCode, Types.FloralType floralType) {

    super(referenceCode, "Floral", floralType.getCost());
    this.floralType = floralType;
  }

  public Types.FloralType getFloralType() {
    return floralType;
  }
}
