package Controller;


import UseCase.RoomManagers;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by yezhou on 2020/11/12
 **/
public class RoomController {

  private RoomManagers vr;

  public RoomController(RoomManagers vr) {
    this.vr = vr;
  }

  /**
   * This method add new room.
   *
   * @return weather the room is sucessfully added
   */
  public boolean confirmaddroom(int roomName) {
    return vr.addRoom(roomName);
  }

  /**
   * This method return whether a particular room is successfully deleted according the the id of
   * this room
   *
   * @param roomName the id of a particular room
   * @return boolean of whether the room is deleted.
   */
  public boolean confirmdeleteroom(int roomName) {
    HashMap<Integer, ArrayList<ArrayList<Time>>> room_schedule = vr.get_rooms_schedule();
    if (!room_schedule.containsKey(roomName)) {
      return false;
    }
    if (room_schedule.get(roomName).isEmpty()) {
      vr.deleteRoom(vr.get_rm(roomName));
      return true;
    }
    return false;
  }

  /**
   * This method return a list of all rooms.
   *
   * @return a map of all rooms and their information
   */
  public HashMap<Integer, String> get_rooms() {
    return vr.get_rms_info();
  }

  /**
   * This method return information of the schedule
   *
   * @return a map of scheduled events, room, time information.
   */
  public HashMap<Integer, ArrayList<ArrayList<Time>>> get_rooms_schedule() {
    return vr.get_rooms_schedule();
  }
}
