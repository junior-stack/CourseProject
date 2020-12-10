package Entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents an Room.
 *
 * @author Jun Xing;
 */
@DatabaseTable(tableName = "room")
public class Room implements Schedulable {

  @DatabaseField(columnName = "schedule", dataType = DataType.SERIALIZABLE)
  public HashMap<ArrayList<Time>, Integer> schedule = new HashMap<>();
  @DatabaseField(id = true)
  private int roomName = 0;
  @DatabaseField(columnName = "capacity")
  private int capacity = 0;

  /**
   * This method creates an Instance of Room, a Room has a roomId and capacity.
   *
   * @param roomName
   */
  public Room(int roomName, int capacity) {
    this.roomName = roomName;
    this.capacity = capacity;
  }

  public Room() {

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
   * @param capacity the capacity of this room
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
   * @param other another Room object
   * @return boolean of whether other Object is equals to this Attendee.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Room)) {
      return false;
    }
    return this.getRoomName() == ((Room) other).getRoomName()
        && this.getCapacity() == ((Room) other)
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

  /**
   * This method checks whether this room has a schedule at a given time period
   * @param start the start time of the given time period
   * @param end  the end time of the given time period
   */
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

  /**
   *  This method adds a schedule to the room's schedule list, which gives information whether this
   *  room is available at this time
   * @param start the start time of the given time period
   * @param end the end time of the given time period
   */
  @Override
  public void giveSchedulableNewSchedule(Time start, Time end) {
    ArrayList<Time> tmp = new ArrayList<>();
    tmp.add(start);
    tmp.add(end);
    schedule.put(tmp, this.getCapacity());
  }

  /**
   * This method deletes the Schedule from the room's schedule list
   * @param start the start time of the given schedule
   * @param end the end time of the given schedule
   * @return boolean whether the deletion of the schedule time is successful
   */
  @Override
  public boolean delSchedulableSchedule(Time start, Time end) {
    for (ArrayList<Time> t : schedule.keySet()) {
      if (t.get(0).equals(start) && t.get(1).equals(end)) {
        schedule.remove(t);
        return true;
      }
    }
    return false;
  }

  /**
   *
   * @return ArrayList<ArrayList<Time>> a list of the room's schedule times which contains start
   *    *  time and end time
   */
  @Override
  public ArrayList<ArrayList<Time>> getScheduleableSchedulelist() {
    ArrayList<ArrayList<Time>> tmp = new ArrayList<>();
    tmp.addAll(schedule.keySet());
    return tmp;
  }

  /**
   *
   * @param sch the id of this schedulable, it is a roomID
   * @return String the information about this schedulable instance
   */
  @Override
  public String get_sch_info(int sch) {
    return schedule.toString();
  }

  /**
   *
   * @return Integer the id of this schedulable. It is SpeakerId
   */
  @Override
  public Integer give_id() {
    return this.getRoomName();
  }

  @Override
  public String get_sch_info() {
    return this.toString();
  }

  /**
   *  This method decreases the remaining spot at a given time.
   * @param start the start time of a given time period(Event start time)
   * @param end the end time of a given time period(Event start time)
   * @return boolean whether the change is succesful
   */
  public boolean DecreaseRemainingspot(Time start, Time end) {
    for (ArrayList<Time> t : schedule.keySet()) {
      if (t.get(0).equals(start) && t.get(1).equals(end)) {
        schedule.replace(t, schedule.get(t) - 1);
        return true;
      }
    }
    return false;
  }

  /**
   * This method increases the remaining spot at a given time.
   * @param start the start time of a given time period(Event start time)
   * @param end the end time of a given time period(Event start time)
   * @return boolean whether the change is succesful
   */
  public boolean IncreaseRemainingspot(Time start, Time end) {
    for (ArrayList<Time> t : schedule.keySet()) {
      if (t.get(0).equals(start) && t.get(1).equals(end)) {
        schedule.replace(t, schedule.get(t) + 1);
        return true;
      }
    }
    return false;
  }

  /**
   * This method gets the how many remaining spots are left at a given event time period
   * @param start the start time of a given time period(Event start time)
   * @param end the end time of a given time period(Event start time)
   * @return Integer the remaining spot during this event period
   */
  public Integer getRemainingSpot(Time start, Time end) {
    ArrayList<Time> tmp = new ArrayList<>();
    tmp.add(start);
    tmp.add(end);
    return schedule.get(tmp);
  }
}
