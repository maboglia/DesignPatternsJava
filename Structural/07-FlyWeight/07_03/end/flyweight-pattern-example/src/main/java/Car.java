public class Car implements Vehicle {

  private int[] location = new int[2];

  public String getModel() {
    return "Car";
  }

  public void setLocation(int latitude, int longitude) {
    location[0] = latitude;
    location[1] = longitude;
  }

  public int[] getLocation() {
    return location;
  }

}
