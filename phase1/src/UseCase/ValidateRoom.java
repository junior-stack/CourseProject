package UseCase;

import Entity.Event;
import Entity.Room;
import exception.InvertedTime;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;


/**
 * A class representing a ValidateRoom.
 *
 * @author Ye Zhou &
 * @version 1.0
 **/

public class ValidateRoom {

  public static HashMap<Room, HashMap<ArrayList<Time>, Integer>> rooms_list;


  /**
   * Create a ValidateRoom with given room_list. A room_list is a HashMap of {Rooms to [HashMaps of
   * (ArrayList of Time to remaining spots)]}.
   *
   * @param room_list
   */
  public ValidateRoom(HashMap<Room, HashMap<ArrayList<Time>, Integer>> room_list) {
    ValidateRoom.rooms_list = room_list;
  }

  /**
   * Return a HashMap of Rooms to their related Event schedule which is an ArrayList of an ArrayList
   * of a couple of starts and ends time.
   *
   * @return HashMap of Room to their related Time schedule
   */
  public HashMap<Room, ArrayList<ArrayList<Time>>> get_rooms_list() {
    HashMap<Room, ArrayList<ArrayList<Time>>> tmp = new HashMap<>();
    for (Room rm : rooms_list.keySet()) {
      Set<ArrayList<Time>> temp1 = rooms_list.get(rm).keySet();//// 为了test 改过
      ArrayList<ArrayList<Time>> temp2 = new ArrayList<>();//// 为了test 改过
      //// 为了test 改过
      temp2.addAll(temp1);
      tmp.put(rm, temp2);
    }
    return tmp;
  }

  /**
   * Return a HashMap of Rooms' roomId to their related Event schedule which is an ArrayList of an
   * ArrayList of a couple of starts and ends time.
   *
   * @return HashMap of Rooms' roomId to their related Time schedule
   */
  public HashMap<Integer, ArrayList<ArrayList<Time>>> get_rooms_schedule() {
    HashMap<Integer, ArrayList<ArrayList<Time>>> tmp = new HashMap<>();
    for (Room rm : rooms_list.keySet()) {
      Collection<ArrayList<Time>> tmp2 = rooms_list.get(rm).keySet();
      ArrayList<ArrayList<Time>> temp2 = new ArrayList<>();//// 为了test 改过
      temp2.addAll(tmp2);
      tmp.put(rm.getRoomId(), temp2);
    }
    return tmp;
  }

  /**
   * This method add a Room with given roomId and capacity to the ValidateRoom's rooms_list and
   * return boolean of whether room is added or not. A new room has a empty HashMap since it has no
   * assigned events.
   *
   * @param roomID
   * @param capacity
   * @return return boolean of whether room is added or not
   */
  public boolean addRoom(int roomID, int capacity) {
    Room rm = new Room(roomID, capacity);
    for (Room r : rooms_list.keySet()) {
      if (r.getRoomId() == roomID) {
        return false;
      }
    }
    rooms_list.put(rm, new HashMap<>());
    return true;
  }

  /**
   * This method check and return boolean result of: given room exists in the ValidateRoom's
   * room_list and start time is earlier than end time and the time period from start to end is
   * completely available.
   *
   * @param rm
   * @param start
   * @param end
   * @return boolean of whether the room and start and end time are valid
   * @throws exception.DoubleBooking
   * @throws InvertedTime
   */
  public boolean validateRoom(Room rm, Time start, Time end)
      throws exception.DoubleBooking, InvertedTime {
    if (!rooms_list.containsKey(rm)) {
      return false;
    }
    for (ArrayList<Time> schedule : rooms_list.get(rm).keySet()) {
      Time start2 = schedule.get(0);
      Time end2 = schedule.get(1);
      if (start.compareTo(start2) >= 0 && start.compareTo(end2) < 0) {
        throw new exception.DoubleBooking(rm, schedule);
      } else if (start.compareTo(end) >= 0) {
        throw new exception.InvertedTime();
      } else if (end.compareTo(start2) > 0 && end.compareTo(end2) <= 0) {
        throw new exception.DoubleBooking(rm, schedule);
      }
    }
    return true;
  }

  /**
   * This method add a couple of start and end time to a room (added to the room_list's mapped
   * value's key_set.
   *
   * @param rm
   * @param start
   * @param end
   */
  public void give_room_schedule(Room rm, Time start, Time end) {
    ArrayList<Time> p = new ArrayList<>();
    p.add(start);
    p.add(end);
    rooms_list.get(rm).put(p, rm.getCapacity());
  }

  /**
   * Remove the Time coupling of start and time of the Event schedule of the room with given rm_ID.
   *
   * @param rm_ID
   * @param start
   * @param end
   */
  public void del_room_schedule(int rm_ID, Time start, Time end) {
    ArrayList<Time> p = new ArrayList<>();
    p.add(start);
    p.add(end);
    for (Room r : rooms_list.keySet()) {
      if (rm_ID == r.getRoomId()) {
        ArrayList<ArrayList<Time>> tmp = new ArrayList<>();
        tmp.addAll(rooms_list.get(r).keySet());
        for (ArrayList<Time> o : tmp) {
          if (o.equals(p)) {
            rooms_list.get(r).remove(p);
            return;
          }
        }
      }
    }
  }

  /**
   * Return the Room with given rm_ID in the room_list of the ValidateRoom, print "There is no room
   * inside the system with that room_ID" if there is no Room with given roomId.
   *
   * @param rm_ID
   * @return Room with given roomId
   * @throws RuntimeException
   */
  public Room get_rm(int rm_ID) throws RuntimeException {
    HashMap<Integer, Room> tmp = new HashMap<>();
    try {
      for (Room rm : this.get_rooms_list().keySet()) {
        tmp.put(rm.getRoomId(), rm);
      }

    } catch (NullPointerException e) {
      System.out.println("There is no room inside the system with that room_ID");
    }
    return tmp.get(rm_ID);
  }

  /**
   * Return a HashMap of capacity to the string representation of the Room.
   *
   * @return HashMap of capacity to the string representation of the Room
   */
  public HashMap<Integer, String> get_rms_info() {
    HashMap<Integer, String> tmp = new HashMap<>();
    for (Room rm : this.get_rooms_list().keySet()) {
      tmp.put(rm.getRoomId(), rm.toString());
    }
    return tmp;
  }

  /**
   * This method delete the give room from the system.
   *
   * @param rm
   */
  public void deleteRoom(Room rm) {
    rooms_list.remove(rm);
  }

  /**
   * This method checks whether the Event in this room is full or not.
   *
   * @param e
   * @param rm
   * @return boolean of whether the room is full for this event
   */
  public boolean check_room_is_full(Event e, Room rm) {
    ArrayList<Time> event_time = new ArrayList<>();
    for (ArrayList<Time> time : rooms_list.get(rm).keySet()) {
      if (time.get(0).equals(e.getStartTime()) && time.get(1).equals(e.getEndTime())) {
        event_time = time;
        break;
      }
    }
    return rooms_list.get(rm).get(event_time) == 0;
  }

  /**
   * For the given room's given event, reduce the remaining spot by one.
   *
   * @param e
   * @param rm
   */
  public void signroom(Event e, Room rm) {
    for (ArrayList<Time> time : rooms_list.get(rm).keySet()) {
      if (time.get(0).equals(e.getStartTime()) && time.get(1).equals(e.getEndTime())) {
        Integer m = rooms_list.get(rm).get(time);
        rooms_list.get(rm).replace(time, m - 1);
      }
    }
  }

  /**
   * For the given room's given event, increase the remaining spot by one.
   *
   * @param e
   * @param rm
   */
  public void cancelroom(Room rm, Event e) {
    for (ArrayList<Time> time : rooms_list.get(rm).keySet()) {
      if (time.get(0).equals(e.getStartTime()) && time.get(1).equals(e.getEndTime())) {
        Integer m = rooms_list.get(rm).get(time);
        rooms_list.get(rm).replace(time, m + 1);
      }
    }
  }


}





