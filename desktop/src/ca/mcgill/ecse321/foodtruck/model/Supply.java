/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruck.model;

// line 32 "../../../../../FoodTruck.ump"
// line 65 "../../../../../FoodTruck.ump"
public class Supply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private String name;
  private int count;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Supply(String aName, int aCount)
  {
    name = aName;
    count = aCount;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCount(int aCount)
  {
    boolean wasSet = false;
    count = aCount;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getCount()
  {
    return count;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "count" + ":" + getCount()+ "]"
     + outputString;
  }
}