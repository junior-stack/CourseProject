package Controller;


import UseCase.ValidateRoom;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by yezhou on 2020/11/12
 **/
public class RoomController {

  private ValidateRoom vr;

  public RoomController(ValidateRoom vr) {
    this.vr = vr;
  }

  public void confirmaddroom(int roomID, int capacity) {
    vr.addRoom(roomID, capacity);
  }

  public boolean confirmdeleteroom(int roomID) {
    HashMap<Integer, ArrayList<ArrayList<Time>>> room_schedule = vr.get_rooms_schedule();
    if (room_schedule.get(roomID).isEmpty()) {
      vr.deleteRoom(vr.get_rm(roomID));
      return true;
    }
    return false;
  }

  public HashMap<Integer, String> get_rooms() {
    return vr.get_rms_info();
  }

  public HashMap<Integer, ArrayList<ArrayList<Time>>> get_rooms_schedule() {
    return vr.get_rooms_schedule();
  }
}
