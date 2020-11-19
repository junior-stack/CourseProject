package Entity;

import java.io.Serializable;

/**
 * This class represents an Room.
 * @author Jun Xing
 * @version 1.0
 */
public class Room implements Serializable {

  final int roomId;
  private int capacity;
  private int remainingSpot;

  /**
   * This method creates an Instance of Room, a Room has a roomId and capacity.
   * @param roomId
   * @param capacity
   */
  public Room(int roomId, int capacity) {
    this.roomId = roomId;
    this.capacity = capacity;
    //this.remainingSpot = capacity;
  }

  /**
   * This method returns a Room's roomId.
   * @return Room's roomId
   */
  public int getRoomId() {
    return roomId;
  }

  /**
   * This method returns a Room's capacity.
   * @return Room's capacity
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * This method sets a Room's capacity.
   * @param capacity
   */
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
  //public int getRemainingSpot() {return remainingSpot; }
  //public void setRemainingSpot(int remainingSpot) {this.remainingSpot = remainingSpot;}

  /**
   * This method returns a string representing the Room including its roomId and capacity.
   * @return a string representing the Room
   */
  @Override
  public String toString() {
    return "Room{" +
        "roomId=" + roomId +
        ", capacity=" + capacity +
        '}';
  }

  /**
   * This method returns whether other Object is equals to this Room. They are equal when the
   * Object is an instance of Room and they have the same roomId and capacity.
   * @param other
   * @return boolean of whether other Object is equals to this Attendee.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Room)) {
      return false;
    }
    return this.getRoomId() == ((Room) other).getRoomId() && this.getCapacity() == ((Room) other)
        .getCapacity();
  }

  /**
   * This method returns the integer hashed value of the Room which is exactly the Room's roomId.
   * @return integer hashed value of the Room
   */
  @Override
  public int hashCode() {
    return this.getRoomId();
  }
}
