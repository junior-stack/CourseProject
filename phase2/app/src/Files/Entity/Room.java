package Entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents an Room.
 *
 * @author Jun Xing
 * @version 1.0
 */
public class Room implements Serializable, Schedulable {

  final int roomName;
  private int capacity;
  public HashMap<ArrayList<Time>, Integer> schedule = new HashMap<>();

  /**
   * This method creates an Instance of Room, a Room has a roomId and capacity.
   *
   * @param roomName
   */
  public Room(int roomName, int capacity) {
    this.roomName = roomName;
    this.capacity = capacity;

  }

  /**
   * This method returns a Room's roomId.
   *
   * @return Room's roomId
   */
  public int getRoomName() {
    return roomName;
  }

  /**
   * This method returns a Room's capacity.
   *
   * @return Room's capacity
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * This method sets a Room's capacity.
   *
   * @param capacity
   */
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
  //public int getRemainingSpot() {return remainingSpot; }
  //public void setRemainingSpot(int remainingSpot) {this.remainingSpot = remainingSpot;}

  /**
   * This method returns a string representing the Room including its roomId and capacity.
   *
   * @return a string representing the Room
   */
  @Override
  public String toString() {
    return "Room{" +
        "roomId=" + roomName +
        ", capacity=" + capacity +
        '}';
  }

  /**
   * This method returns whether other Object is equals to this Room. They are equal when the Object
   * is an instance of Room and they have the same roomId and capacity.
   *
   * @param other
   * @return boolean of whether other Object is equals to this Attendee.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Room)) {
      return false;
    }
    return this.getRoomName() == ((Room) other).getRoomName() && this.getCapacity() == ((Room) other)
        .getCapacity();
  }

  /**
   * This method returns the integer hashed value of the Room which is exactly the Room's roomId.
   *
   * @return integer hashed value of the Room
   */
  @Override
  public int hashCode() {
    return this.getRoomName();
  }

  @Override
  public boolean CheckSchedulable(Time start, Time end) {
    for (ArrayList<Time> t : schedule.keySet()) {
      if (start.compareTo(t.get(0)) >= 0 && start.compareTo(t.get(1)) < 0) {
        return false;
      } else if (end.compareTo(t.get(0)) > 0 && end.compareTo(t.get(1)) <= 0) {
        return false;
      } else if (start.compareTo(end) >= 0) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void giveSchedulableNewSchedule(Time start, Time end) {
    ArrayList<Time> tmp = new ArrayList<>();
    tmp.add(start);
    tmp.add(end);
    schedule.put(tmp, this.getCapacity());
  }

  @Override
  public boolean delSchedulableSchedule(Time start, Time end) {
    for(ArrayList<Time> t: schedule.keySet()){
      if(t.get(0).equals(start) && t.get(1).equals(end)){
        schedule.remove(t);
        return true;
      }
    }
    return false;
  }

  @Override
  public ArrayList<ArrayList<Time>> getScheduleableSchedulelist() {
    ArrayList<ArrayList<Time>> tmp = new ArrayList<>();
    tmp.addAll(schedule.keySet());
    return tmp;
  }

  @Override
  public String get_sch_info(int sch) {
    return schedule.toString();
  }

  @Override
  public Integer give_id() {
    return this.getRoomName();
  }

  @Override
  public String get_sch_info() {
    return this.toString();
  }

  public boolean DecreaseRemainingspot(Time start, Time end) {
    for(ArrayList<Time> t: schedule.keySet()){
      if(t.get(0).equals(start) && t.get(1).equals(end)){
        schedule.replace(t, schedule.get(t) - 1);
        return true;
      }
    }
    return false;
  }

  public boolean IncreaseRemainingspot(Time start, Time end){
    for(ArrayList<Time> t: schedule.keySet()){
      if(t.get(0).equals(start) && t.get(1).equals(end)){
        schedule.replace(t, schedule.get(t) + 1);
        return true;
      }
    }
    return false;
  }

  public Integer getRemainingSpot(Time start, Time end){
    ArrayList<Time> tmp = new ArrayList<>();
    tmp.add(start);
    tmp.add(end);
    return schedule.get(tmp);
  }
}
