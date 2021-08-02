package UseCase;

import Dao.RoomDao;
import Entity.Room;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author yezhou
 **/
public class RoomManager implements Iterable<Room> {

  List<Room> roomList;

  public RoomManager(List<Room> roomList) {
    this.roomList = roomList;
  }

  /**
   * This method returns a single room object from the roomList given the roomID
   *
   * @param roomID int the ID of the Room object
   * @return Room the room object
   */
  public Room getSingleRoom(int roomID) {
    for (Room tmp : this) {
      if (tmp.getRoomName() == roomID) {
        return tmp;
      }
    }
    return null;
  }

  /**
   * This method returns the roomlist in Schedulable form so that SchedulableManager can analyze
   *
   * @return List<Schedulable> the list of the room
   */
  public List getRoomList() {
    List tmp = new ArrayList<>();
    for (Room room : this) {
      tmp.add(room);
    }
    return tmp;
  }

  /**
   * This method adds the room to the roomList
   *
   * @param roomID   the ID of the room
   * @param capacity the capacity of the room
   * @return boolean whether the addition is successful
   */
  public boolean addRoom(int roomID, int capacity) {
    for (Room curr : this) {
      if (curr.getRoomName() == roomID) {
        return false;
      }
    }
    Room rm2 = new Room(roomID, capacity);
    roomList.add(rm2);
    return true;
  }

  /**
   * This method deletes the Room object from the roomList given an ID
   *
   * @param roomID the ID of the Room Object
   * @return boolean true iff the deletionis successful
   */
  public boolean delRoom(int roomID) {
    Iterator<Room> rmi = this.iterator();
    Room curr = null;
    while (rmi.hasNext()) {
      curr = rmi.next();
      if (curr.getRoomName() == roomID) {
        return false;
      }
    }
    if (curr == null) {
      return false;
    } else {
      return roomList.remove(curr);
    }
  }

  /**
   * This method returns the list of rooms
   *
   * @return the list of rooms
   */
  public List<Room> getRooms() {
    return roomList;
  }


  /**
   * This method tells the capacity of a room given the roomID
   *
   * @param roomID the ID of the Room objetc
   * @return Integer the capacity of the Room Object
   */
  public Integer getCapacity(int roomID) {
    Room tmp = getSingleRoom(roomID);
    if (tmp != null) {
      return tmp.getRoomName();
    }
    return null;
  }


  /**
   * This method checks whether the Room is still available or not when the attendees sign up the
   * event at this room
   *
   * @param roomID the ID of the room
   * @param start  the start time of the room
   * @param end    the end time of the room
   * @return boolean true iff the room is not full
   */
  public boolean CheckRemainingSpot(Integer roomID, Time start, Time end) {
    Integer tmp = getRemainingSpot(roomID, start, end);
    if (tmp == null) {
      return false;
    }
    return tmp != 0;
  }

  /**
   * This method gives the remaining spot of a room given the ID at the given time
   *
   * @param roomID the ID of the room
   * @param start  the start time of the event at this room
   * @param end    the end time of the event at this room
   * @return the remaining spot of the room from start time to end time
   */
  public Integer getRemainingSpot(Integer roomID, Time start, Time end) {
    Room rm = getSingleRoom(roomID);
    if (rm == null) {
      return null;
    }
    return rm.getRemainingSpot(start, end);
  }

  /**
   * Thid method increases the remaining spot of a given room at the given time when user cancels
   * the event at this room
   *
   * @param roomID the ID of the room
   * @param start  the start time of the event
   * @param end    the end time of the event
   * @return boolean iff the the increase  is successful
   */
  public boolean IncreaseRemainingSpot(Integer roomID, Time start, Time end) {
    Room rm = getSingleRoom(roomID);
    if (rm == null) {
      return false;
    }
    if (rm.getRemainingSpot(start, end) == rm.getCapacity()) {
      return false;
    }
    rm.IncreaseRemainingspot(start, end);
    return true;
  }

  /**
   * This method decreases the remaining spot of a given room at the given time when user signs up
   * the event at this room
   *
   * @param roomID the ID of the room
   * @param start  the start time of the event
   * @param end    the end time of the event
   * @return boolean iff the the decrease is successful
   */
  public boolean DecreaseRemainingSpot(Integer roomID, Time start, Time end) {
    Room rm = getSingleRoom(roomID);
    if (rm == null) {
      return false;
    }
    if (rm.getRemainingSpot(start, end) == 0) {
      return false;
    }
    rm.DecreaseRemainingspot(start, end);
    return true;
  }

  /**
   * This method override the <>iterator()</> in <>Iterable</> interface.
   *
   * @return Iterator
   */
  @Override
  public Iterator<Room> iterator() {
    return new RoomManagerIterator();
  }

  /**
   * save roomlist to database.
   */
  public void saveRooms() {
    RoomDao.saveAll(roomList);
  }

  /**
   * a private class RoomManagerIterator
   */
  private class RoomManagerIterator implements Iterator<Room> {

    private int current = 0;

    /**
     * This method override the <>hasNext()</> method in <>Iterable</> interface.
     *
     * @return {@code true} if the iteration has more Rooms
     */
    @Override
    public boolean hasNext() {
      return current < roomList.size();
    }

    /**
     * This method override the <>next()</> method in <>Iterable</> interface.
     *
     * @return Room   the next Room in the iteration
     */
    @Override
    public Room next() {
      Room res;
      try {
        res = roomList.get(current);
      } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
      }
      current += 1;
      return res;

    }

    /**
     * This method override the <>remove()</> method in <>Iterable</> interface.
     */
    @Override
    public void remove() {
      roomList.remove(current - 1);
    }
  }
}

