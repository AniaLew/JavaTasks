package newjava.java5;

public enum Size {
  EXTRA_SMALL("XS"),
  SMALL("S"),
  LARGE("L"),
  EXTRA_LARGE("XL"),
  EXTRA_EXTRA_LARGE("XXL");

  //Enum can have a field declaration
  private final String abbreviation;

  //Constructor
  Size(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public String getAbbreviation() {
    return this.abbreviation;
  }

}